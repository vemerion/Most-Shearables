package mod.vemerion.mostshearables;

import java.lang.reflect.Field;

import mod.vemerion.mostshearables.capability.Clipped;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ParrotModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

	private static Field parrotFeather = ObfuscationReflectionHelper.findField(ParrotModel.class, "field_192772_i");

	@SubscribeEvent
	public static void renderClippedEntityPre(RenderLivingEvent.Pre<? extends MobEntity, ?> event) {
		LivingEntity entity = event.getEntity();
		Clipped clipped = entity.getCapability(Main.CLIPPED_CAP).orElse(new Clipped());
		if (clipped.isClipped()) {
			if (entity instanceof ParrotEntity) {
				setClippedFeather(event.getRenderer().getEntityModel(), false);
				return;
			} else if (entity instanceof VillagerEntity)
				return;

			event.getMatrixStack().push();
			event.getMatrixStack().scale(0.95f, 0.95f, 0.95f);
		}
	}

	@SubscribeEvent
	public static void renderClippedEntityPost(RenderLivingEvent.Post<? extends MobEntity, ?> event) {
		LivingEntity entity = event.getEntity();
		Clipped clipped = entity.getCapability(Main.CLIPPED_CAP).orElse(new Clipped());
		if (clipped.isClipped()) {
			if (entity instanceof ParrotEntity) {
				setClippedFeather(event.getRenderer().getEntityModel(), true);
				return;
			} else if (entity instanceof VillagerEntity)
				return;
			
			event.getMatrixStack().pop();
		}
	}

	private static void setClippedFeather(EntityModel<?> model, boolean show) {
		try {
			((ModelRenderer) parrotFeather.get(model)).showModel = show;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
