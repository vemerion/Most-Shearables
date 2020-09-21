package mod.vemerion.mostshearables;

import mod.vemerion.mostshearables.capability.Clipped;
import mod.vemerion.mostshearables.config.Config;
import mod.vemerion.mostshearables.item.BearHideMaterial;
import mod.vemerion.mostshearables.item.PigSkinMaterial;
import net.minecraft.item.Item;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Main.MODID)
public class Main {
	
	public Main() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
	}
	
	public static final String MODID = "most-shearables";
	
	@ObjectHolder(Main.MODID + ":pig_skin")
	public static final Item PIG_SKIN = null;
	
	@ObjectHolder(Main.MODID + ":bear_hide")
	public static final Item BEAR_HIDE = null;
	
	@ObjectHolder(Main.MODID + ":variant")
	public static final Item VARIANT = null;
	
	@ObjectHolder(Main.MODID + ":claw_particle_type")
	public static final BasicParticleType CLAW_PARTICLE_TYPE = null;
	
	public static final PigSkinMaterial PIG_SKIN_MATERIAL = new PigSkinMaterial();
	
	public static final BearHideMaterial BEAR_HIDE_MATERIAL = new BearHideMaterial();
	
	@CapabilityInject(Clipped.class)
	public static final Capability<Clipped> CLIPPED_CAP = null;
	
}
