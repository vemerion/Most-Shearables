package mod.vemerion.mostshearables.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Tabula 8.0.0
 */
public class PigSkinArmorModel<T extends LivingEntity> extends BipedModel<T> {
    public ModelRenderer rightHoof;
    public ModelRenderer rightLeg;
    public ModelRenderer snout;
    public ModelRenderer leftEar;
    public ModelRenderer rightEar;
    public ModelRenderer knorr;
    public ModelRenderer teat1;
    public ModelRenderer teat2;
    public ModelRenderer teat3;
    public ModelRenderer teat4;
    public ModelRenderer teat5;
    public ModelRenderer teat6;
    public ModelRenderer leftHoof;
    public ModelRenderer leftLeg;

    public PigSkinArmorModel(float modelSize) {
		super(modelSize, 0, 64, 64);
		
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.teat4 = new ModelRenderer(this, 0, 0);
        this.teat4.setRotationPoint(1.0F, 3.0F, -4.0F);
        this.teat4.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0);
        this.leftLeg = new ModelRenderer(this, 44, 37);
        this.leftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, modelSize);
        this.rightLeg = new ModelRenderer(this, 44, 37);
        this.rightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, modelSize);
        this.teat1 = new ModelRenderer(this, 0, 0);
        this.teat1.setRotationPoint(-2.0F, 3.0F, -4.0F);
        this.teat1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0);
        this.teat2 = new ModelRenderer(this, 0, 0);
        this.teat2.setRotationPoint(-2.0F, 6.0F, -4.0F);
        this.teat2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0);
        this.rightHoof = new ModelRenderer(this, 0, 40);
        this.rightHoof.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.rightHoof.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 3.0F, 5.0F, modelSize);
        this.snout = new ModelRenderer(this, 0, 32);
        this.snout.setRotationPoint(0.0F, -2.0F, -4.0F);
        this.snout.addBox(-2.0F, -1.5F, -1.0F, 4.0F, 3.0F, 1.0F, modelSize);
        this.teat5 = new ModelRenderer(this, 0, 0);
        this.teat5.setRotationPoint(1.0F, 6.0F, -4.0F);
        this.teat5.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0);
        this.rightEar = new ModelRenderer(this, 6, 36);
        this.rightEar.setRotationPoint(-4.0F, -5.0F, 0.0F);
        this.rightEar.addBox(-2.0F, -1.5F, -1.0F, 2.0F, 3.0F, 1.0F, modelSize);
        this.knorr = new ModelRenderer(this, 44, 32);
        this.knorr.setRotationPoint(0.0F, 9.0F, 3.0F);
        this.knorr.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, modelSize);
        this.setRotateAngle(knorr, -0.4300491170387584F, 0.0F, 0.0F);
        this.leftEar = new ModelRenderer(this, 0, 36);
        this.leftEar.setRotationPoint(4.0F, -5.0F, 0.0F);
        this.leftEar.addBox(0.0F, -1.5F, -1.0F, 2.0F, 3.0F, 1.0F, modelSize);
        this.teat6 = new ModelRenderer(this, 0, 0);
        this.teat6.setRotationPoint(1.0F, 9.0F, -4.0F);
        this.teat6.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0);
        this.teat3 = new ModelRenderer(this, 0, 0);
        this.teat3.setRotationPoint(-2.0F, 9.0F, -4.0F);
        this.teat3.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, modelSize);
        this.leftHoof = new ModelRenderer(this, 0, 40);
        this.leftHoof.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.leftHoof.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 3.0F, 5.0F, modelSize);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize);
        this.bipedRightArm = new ModelRenderer(this, 44, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSize);  
        this.bipedBody.addChild(this.teat4);
        this.bipedLeftLeg.addChild(this.leftLeg);
        this.bipedRightLeg.addChild(this.rightLeg);
        this.bipedBody.addChild(this.teat1);
        this.bipedBody.addChild(this.teat2);
        this.bipedRightLeg.addChild(this.rightHoof);
        this.bipedHead.addChild(this.snout);
        this.bipedBody.addChild(this.teat5);
        this.bipedHead.addChild(this.rightEar);
        this.bipedBody.addChild(this.knorr);
        this.bipedHead.addChild(this.leftEar);
        this.bipedBody.addChild(this.teat6);
        this.bipedBody.addChild(this.teat3);
        this.bipedLeftLeg.addChild(this.leftHoof);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
