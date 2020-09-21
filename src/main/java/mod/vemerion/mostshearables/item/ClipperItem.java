package mod.vemerion.mostshearables.item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import mod.vemerion.mostshearables.clipperaction.ClipperAction;
import mod.vemerion.mostshearables.clipperaction.DropClipperAction;
import mod.vemerion.mostshearables.clipperaction.ExplosionClipperAction;
import mod.vemerion.mostshearables.clipperaction.LlamaClipperAction;
import mod.vemerion.mostshearables.clipperaction.NoClipperAction;
import mod.vemerion.mostshearables.clipperaction.PigmanClipperAction;
import mod.vemerion.mostshearables.config.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.Hand;

public class ClipperItem extends ShearsItem {
	public static final Map<Class<?>, Supplier<ClipperAction>> actions = new HashMap<>();

	static {
		ImmutableList.of(ChickenEntity.class, CowEntity.class, ParrotEntity.class,
				PigEntity.class, PolarBearEntity.class, RabbitEntity.class, VillagerEntity.class).forEach((type) -> {
					actions.put(type, () -> Config.isMobShearingDisabled(type) ? new NoClipperAction()
							: new DropClipperAction(Config.getMobDrop(type), Config.getMobDropCount(type)));
				});
		actions.put(LlamaEntity.class, () -> Config.isMobShearingDisabled(LlamaEntity.class) ? new NoClipperAction()
						: new LlamaClipperAction(Config.getMobDrop(LlamaEntity.class),
								Config.getMobDropCount(LlamaEntity.class)));
		actions.put(CreeperEntity.class, () -> new ExplosionClipperAction());
		actions.put(ZombiePigmanEntity.class,
				() -> Config.isMobShearingDisabled(ZombiePigmanEntity.class) ? new NoClipperAction()
						: new PigmanClipperAction(Config.getMobDrop(ZombiePigmanEntity.class),
								Config.getMobDropCount(ZombiePigmanEntity.class)));
	}

	public ClipperItem() {
		super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).maxDamage(238));
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
		if (entity.world.isRemote)
			return false;
		if (actions.containsKey(entity.getClass())) {
			return actions.get(entity.getClass()).get().clip(stack, playerIn, entity, hand);
		} else {
			return super.itemInteractionForEntity(stack, playerIn, entity, hand);
		}
	}

}
