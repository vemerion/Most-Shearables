package mod.vemerion.mostshearables.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.mostshearables.Main;
import mod.vemerion.mostshearables.capability.Clipped;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class ClippedLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
	private ResourceLocation clippedTexture;

	public ClippedLayer(IEntityRenderer<T, M> entityRendererIn, ResourceLocation texture) {
		super(entityRendererIn);
		this.clippedTexture = texture;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.getCapability(Main.CLIPPED_CAP).orElse(new Clipped()).isClipped()) {
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEntityTranslucent(clippedTexture));
			getEntityModel().render(matrixStackIn, ivertexbuilder, packedLightIn,
					LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

}
