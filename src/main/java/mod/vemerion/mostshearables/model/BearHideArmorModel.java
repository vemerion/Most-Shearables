package mod.vemerion.mostshearables.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Tabula 8.0.0
 */
public class BearHideArmorModel<T extends LivingEntity> extends BipedModel<T> {
	public ModelRenderer nose;
	public ModelRenderer leftEar;
	public ModelRenderer rightEar;
	public ModelRenderer belly1;
	public ModelRenderer belly2;
	public ModelRenderer tail;
	public ModelRenderer leftPaw;
	public ModelRenderer rightPaw;
	public ModelRenderer leftLeg;
	public ModelRenderer rightLeg;



	public BearHideArmorModel(float modelSize) {
		super(modelSize, 0, 64, 64);
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.nose = new ModelRenderer(this, 0, 32);
		this.nose.setRotationPoint(0.0F, -2.0F, -4.0F);
		this.nose.addBox(-2.5F, -1.5F, -3.0F, 5.0F, 3.0F, 3.0F, modelSize);
		this.leftEar = new ModelRenderer(this, 0, 38);
		this.leftEar.setRotationPoint(3.0F, -7.5F, 0.0F);
		this.leftEar.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, modelSize);
		this.rightEar = new ModelRenderer(this, 10, 38);
		this.rightEar.setRotationPoint(-3.0F, -7.5F, 0.0F);
		this.rightEar.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, modelSize);

		this.belly1 = new ModelRenderer(this, 20, 32);
		this.belly1.setRotationPoint(0.0F, 6.0F, -3.0F);
		this.belly1.addBox(-3.0F, -5.0F, 0.0F, 6.0F, 10.0F, 1.0F, modelSize);
        this.belly2 = new ModelRenderer(this, 34, 32);
        this.belly2.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.belly2.addBox(-2.0F, -4.0F, 0.0F, 4.0F, 8.0F, 1.0F, modelSize);    
        this.tail = new ModelRenderer(this, 44, 32);
        this.tail.setRotationPoint(0.0F, 9.0F, 2.0F);
        this.tail.addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 3.0F, modelSize);  
        
        this.leftPaw = new ModelRenderer(this, 0, 43);
        this.leftPaw.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.leftPaw.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);   
        this.rightPaw = new ModelRenderer(this, 0, 43);
        this.rightPaw.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.rightPaw.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);     
        this.leftLeg = new ModelRenderer(this, 44, 39);
        this.leftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftLeg.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 8.0F, 5.0F, modelSize);  
        this.rightLeg = new ModelRenderer(this, 44, 39);
        this.rightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightLeg.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 8.0F, 5.0F, modelSize);    

		this.bipedHead.addChild(this.nose);
		this.bipedHead.addChild(this.leftEar);
		this.bipedHead.addChild(this.rightEar);

		this.bipedBody.addChild(this.belly1);
        this.belly1.addChild(this.belly2);      
        this.bipedBody.addChild(this.tail);
        
        this.bipedLeftLeg.addChild(this.leftPaw);
        this.bipedRightLeg.addChild(this.rightPaw);
        this.bipedLeftLeg.addChild(this.leftLeg);
        this.bipedRightLeg.addChild(this.rightLeg);

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
