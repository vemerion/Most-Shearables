package mod.vemerion.mostshearables;

import mod.vemerion.mostshearables.capability.Clipped;
import mod.vemerion.mostshearables.capability.ClippedMessage;
import mod.vemerion.mostshearables.capability.ClippedStorage;
import mod.vemerion.mostshearables.item.BearHideArmorItem;
import mod.vemerion.mostshearables.item.ClipperItem;
import mod.vemerion.mostshearables.item.PigSkinArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(setup(new ClipperItem(), "clipper_item"));
		event.getRegistry().register(setup(new Item(new Item.Properties().group(ItemGroup.MATERIALS)), "pig_skin"));
		event.getRegistry().register(setup(new Item(new Item.Properties().group(ItemGroup.MATERIALS)), "bear_hide"));
		
		event.getRegistry().register(setup(new BearHideArmorItem(EquipmentSlotType.HEAD), "bear_hide_helmet"));
		event.getRegistry().register(setup(new BearHideArmorItem(EquipmentSlotType.CHEST), "bear_hide_chest"));
		event.getRegistry().register(setup(new BearHideArmorItem(EquipmentSlotType.LEGS), "bear_hide_legs"));
		event.getRegistry().register(setup(new BearHideArmorItem(EquipmentSlotType.FEET), "bear_hide_boots"));
		
		event.getRegistry().register(setup(new PigSkinArmorItem(EquipmentSlotType.HEAD), "pig_skin_helmet"));
		event.getRegistry().register(setup(new PigSkinArmorItem(EquipmentSlotType.CHEST), "pig_skin_chest"));
		event.getRegistry().register(setup(new PigSkinArmorItem(EquipmentSlotType.LEGS), "pig_skin_legs"));
		event.getRegistry().register(setup(new PigSkinArmorItem(EquipmentSlotType.FEET), "pig_skin_boots"));

		event.getRegistry().register(setup(new Item(new Item.Properties()) {
		    @Override
			public int getEntityLifespan(ItemStack itemStack, World world) {
		    	return 0;
		    }
		}, "variant"));

	}
	
	@SubscribeEvent
	public static void onIParticleTypeRegistration(RegistryEvent.Register<ParticleType<?>> event) {
		event.getRegistry().register(setup(new BasicParticleType(true), "claw_particle_type"));
	}

	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(Clipped.class, new ClippedStorage(), Clipped::new);

		ClippedMessage.INSTANCE.registerMessage(0, ClippedMessage.class, ClippedMessage::encode, ClippedMessage::decode,
				ClippedMessage::handle);

	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}

}
