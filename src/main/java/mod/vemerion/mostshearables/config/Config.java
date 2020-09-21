package mod.vemerion.mostshearables.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import mod.vemerion.mostshearables.Main;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
	public static final CommonConfig COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;
	static {
		final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == Config.COMMON_SPEC) {
			bakeConfig();
		}
	}

	private static Class<?>[] mobs;
	private static String[] defaultDrops;
	private static int[] defaultDropCount;

	private static Map<Class<?>, Boolean> isMobDisabled;
	private static Map<Class<?>, String> getMobDrop;
	private static Map<Class<?>, Integer> getDropCount;
	private static boolean canUseVanillaShears;

	public static boolean isMobShearingDisabled(Class<?> type) {
		return isMobDisabled.getOrDefault(type, true);
	}

	public static Item getMobDrop(Class<?> type) {
		return getItemFromName(getMobDrop.getOrDefault(type, ""));
	}

	private static Item getItemFromName(String name) {
		if (name.equals(""))
			return Items.AIR;

		ResourceLocation item = new ResourceLocation(name);
		if (ForgeRegistries.ITEMS.containsKey(item)) {
			return ForgeRegistries.ITEMS.getValue(item);
		} else {
			return Items.AIR;
		}
	}

	public static int getMobDropCount(Class<?> type) {
		return getDropCount.getOrDefault(type, 0);
	}

	public static boolean canUseVanillaShears() {
		return canUseVanillaShears;
	}

	public static void bakeConfig() {
		isMobDisabled = new HashMap<>();
		getMobDrop = new HashMap<>();
		getDropCount = new HashMap<>();

		for (Class<?> mob : mobs) {
			isMobDisabled.put(mob, COMMON.isMobDisabled.get(mob).get());
			getMobDrop.put(mob, COMMON.getMobDrop.get(mob).get());
			getDropCount.put(mob, COMMON.getDropCount.get(mob).get());
		}

		canUseVanillaShears = COMMON.canUseVanillaShears.get();

	}

	public static class CommonConfig {
		public final Map<Class<?>, BooleanValue> isMobDisabled = new HashMap<>();
		public final Map<Class<?>, ConfigValue<String>> getMobDrop = new HashMap<>();
		public final Map<Class<?>, IntValue> getDropCount = new HashMap<>();
		public final BooleanValue canUseVanillaShears;

		public CommonConfig(ForgeConfigSpec.Builder builder) {
			mobs = new Class<?>[] { ChickenEntity.class, CowEntity.class, LlamaEntity.class, ParrotEntity.class,
					PigEntity.class, ZombiePigmanEntity.class, PolarBearEntity.class, RabbitEntity.class, VillagerEntity.class };
			defaultDrops = new String[] { "feather", "leather", "most-shearables:variant", "feather", "most-shearables:pig_skin", "rotten_flesh",
					"most-shearables:bear_hide", "rabbit_hide", "leather" };
			defaultDropCount = new int[] { 1, 2, 2, 1, 1, 1, 3, 1, 1 };

			builder.push("Mob shearing");
			for (Class<?> mob : mobs) {
				String name = mob.getSimpleName() + "Disable";
				isMobDisabled.put(mob, builder.comment("Set to true to disable " + mob.getSimpleName() + " shearing")
						.translation(Main.MODID + ".config." + name).define(name, false));
			}
			builder.pop();

			Map<Class<?>, String> mobDrops = new HashMap<>();
			Map<Class<?>, Integer> mobDropsCount = new HashMap<>();
			for (int i = 0; i < mobs.length; i++) {
				mobDrops.put(mobs[i], defaultDrops[i]);
				mobDropsCount.put(mobs[i], defaultDropCount[i]);
			}

			builder.push("Mob drops");
			for (Class<?> mob : mobs) {
				String name = mob.getSimpleName() + "Drop";
				getMobDrop.put(mob, builder.comment("A " + mob.getSimpleName() + " drops this when sheared")
						.translation(Main.MODID + ".config." + name).define(name, mobDrops.get(mob)));
			}
			builder.pop();

			builder.push("Mob drops count");
			for (Class<?> mob : mobs) {
				String name = mob.getSimpleName() + "DropCount";
				getDropCount.put(mob, builder.comment("The number of drops for " + mob.getSimpleName() + " when it is sheared").translation(Main.MODID + ".config." + name).defineInRange(name,
						mobDropsCount.get(mob), 0, Integer.MAX_VALUE));
			}
			builder.pop();

			builder.push("General");
			canUseVanillaShears = builder.comment("If true vanilla shears can be used to shear entities")
					.translation(Main.MODID + ".config.CanUseVanillaShears").define("CanUseVanillaShears", false);
			builder.pop();
		}

	}
}
