package mod.vemerion.mostshearables;

import java.util.Random;

import mod.vemerion.mostshearables.capability.Clipped;
import mod.vemerion.mostshearables.capability.ClippedMessage;
import mod.vemerion.mostshearables.capability.ClippedProvider;
import mod.vemerion.mostshearables.config.Config;
import mod.vemerion.mostshearables.goal.ClippedEatGrassGoal;
import mod.vemerion.mostshearables.helper.Helper;
import mod.vemerion.mostshearables.item.BearHideArmorItem;
import mod.vemerion.mostshearables.item.ClipperItem;
import mod.vemerion.mostshearables.item.PigSkinArmorItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.PacketDistributor;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

	public static final ResourceLocation CLIPPED_CAP = new ResourceLocation(Main.MODID, "clipped");

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof MobEntity) {
			event.addCapability(CLIPPED_CAP, new ClippedProvider());
		}

	}

	// Server-only event
	@SubscribeEvent
	public static void pigDropPorkEvent(LivingDamageEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			World world = player.world;
			if (Helper.hasFullArmorSetEquipped(player, PigSkinArmorItem.class)
					&& event.getSource() instanceof EntityDamageSource && player.getRNG().nextDouble() < 0.1) {
				ItemEntity itemEntity;
				ItemStack pork = player.isBurning() ? new ItemStack(Items.COOKED_PORKCHOP) : new ItemStack(Items.PORKCHOP);
				itemEntity = new ItemEntity(world, player.getPosX(), player.getPosY() + 1, player.getPosZ(), pork);
				world.addEntity(itemEntity);
			}
		}
	}

	@SubscribeEvent
	public static void bearAttackEvent(AttackEntityEvent event) {
		PlayerEntity player = event.getPlayer();
		if (player.getHeldItemMainhand().isEmpty() && player.getCooledAttackStrength(0) > 0.95f
				&& Helper.hasFullArmorSetEquipped(player, BearHideArmorItem.class)) {
			player.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 0.9f, 0.8f + player.getRNG().nextFloat() * 0.4f);
			event.setCanceled(true);
			if (!event.getPlayer().world.isRemote) {
				event.getTarget().attackEntityFrom(DamageSource.causePlayerDamage(player), 4);
				createClawParticle(player);
			}
		}
	}

	@SubscribeEvent
	public static void eatHoneyAsBear(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntityLiving() instanceof PlayerEntity && event.getItem().getItem() == Items.HONEY_BOTTLE) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if (Helper.hasFullArmorSetEquipped(player, BearHideArmorItem.class))
				player.getFoodStats().addStats(3, 0.4f);
		}
	}

	private static void createClawParticle(PlayerEntity player) {
		Random rand = player.getRNG();
		Vec3d direction = Vec3d
				.fromPitchYaw(rand.nextFloat() * 40 - 20, player.rotationYaw + rand.nextFloat() * 40 - 20).scale(2.5);
		ServerWorld world = (ServerWorld) player.world;
		world.spawnParticle(Main.CLAW_PARTICLE_TYPE, player.getPosX() + direction.x,
				player.getPosYHeight(0.5D) + direction.y, player.getPosZ() + direction.z, 0, direction.x, direction.y,
				direction.z, 0);
	}

	@SubscribeEvent
	public static void addGrassEatingGoal(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof MobEntity) {
			MobEntity entity = (MobEntity) event.getEntity();
			entity.goalSelector.addGoal(5, new ClippedEatGrassGoal(entity));
		}
	}

	@SubscribeEvent
	public static void sendClippedToClient(PlayerEvent.StartTracking event) {
		if (event.getTarget() instanceof MobEntity) {
			Clipped clipped = event.getTarget().getCapability(Main.CLIPPED_CAP).orElse(new Clipped());
			if (clipped.isClipped())
				ClippedMessage.INSTANCE.send(
						PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new ClippedMessage(clipped.isClipped(), event.getTarget().getEntityId()));

		}
	}
	
	@SubscribeEvent
	public static void noTradeWithClippedVillager(PlayerInteractEvent.EntityInteract event) {
		if (event.getTarget() instanceof VillagerEntity && event.getTarget().getCapability(Main.CLIPPED_CAP).orElse(new Clipped()).isClipped()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void shearWithVanillaShear(PlayerInteractEvent.EntityInteract event) {
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();

		if (Config.canUseVanillaShears() && event.getSide() == LogicalSide.SERVER && !(item instanceof ClipperItem)
				&& item instanceof ShearsItem && event.getTarget() instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity) event.getTarget();

			if (ClipperItem.actions.containsKey(entity.getClass())) {
				if (ClipperItem.actions.get(entity.getClass()).get().clip(stack, event.getPlayer(), entity,
						event.getHand())) {
					event.setCanceled(true);
					event.setCancellationResult(ActionResultType.SUCCESS);
				}
			}
		}
	}
}
