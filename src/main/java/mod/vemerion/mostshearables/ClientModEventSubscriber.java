package mod.vemerion.mostshearables;

import java.util.HashMap;
import java.util.Map;

import mod.vemerion.mostshearables.renderer.ClippedLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(Main.CLAW_PARTICLE_TYPE,
				sprite -> new ClawParticle.Factory(sprite));
	}

	private static final ResourceLocation POLARBEAR_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/polarbear_clipped.png");
	private static final ResourceLocation COW_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/cow_clipped.png");
	private static final ResourceLocation CHICKEN_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/chicken_clipped.png");
	private static final ResourceLocation LLAMA_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/llama_clipped.png");
	private static final ResourceLocation RABBIT_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/rabbit_clipped.png");
	private static final ResourceLocation PIGMAN_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/pigman_clipped.png");
	private static final ResourceLocation PIG_CLIPPED = new ResourceLocation(Main.MODID,
			"textures/entity/pig_clipped.png");
	private static final ResourceLocation VILLAGER_CLIPPED = new ResourceLocation("minecraft",
			"textures/entity/villager/villager.png");

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		Map<EntityType<?>, ResourceLocation> layerTextures = new HashMap<>();
		layerTextures.put(EntityType.POLAR_BEAR, POLARBEAR_CLIPPED);
		layerTextures.put(EntityType.COW, COW_CLIPPED);
		layerTextures.put(EntityType.CHICKEN, CHICKEN_CLIPPED);
		layerTextures.put(EntityType.LLAMA, LLAMA_CLIPPED);
		layerTextures.put(EntityType.RABBIT, RABBIT_CLIPPED);
		layerTextures.put(EntityType.ZOMBIE_PIGMAN, PIGMAN_CLIPPED);
		layerTextures.put(EntityType.PIG, PIG_CLIPPED);
		layerTextures.put(EntityType.VILLAGER, VILLAGER_CLIPPED);

		DeferredWorkQueue.runLater(() -> {
			Minecraft mc = event.getMinecraftSupplier().get();
			Map<EntityType<?>, EntityRenderer<?>> renderers = mc.getRenderManager().renderers;
			layerTextures.keySet().forEach((type) -> {
				LivingRenderer<?, ?> renderer = (LivingRenderer<?, ?>) renderers.get(type);
				renderer.addLayer(new ClippedLayer(renderer, layerTextures.get(type)));
			});
		});
	}

}
