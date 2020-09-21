package mod.vemerion.mostshearables;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

public class ClawParticle extends SpriteTexturedParticle {
	private final IAnimatedSprite sprites;

	protected ClawParticle(World world, double x, double y, double z, double xSpeed, IAnimatedSprite sprite) {
		super(world, x, y, z, 0, 0, 0);
		this.sprites = sprite;
		this.maxAge = 4;
		float color = this.rand.nextFloat() * 0.6F + 0.4F;
		this.particleRed = color;
		this.particleGreen = color;
		this.particleBlue = color;
		this.particleScale = 1.0F - (float) xSpeed * 0.5F;
		this.selectSpriteWithAge(sprite);
	}

	@Override
	public int getBrightnessForRender(float partialTick) {
		return 15728880;
	}

	@Override
	public void tick() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if (age++ >= maxAge) {
			setExpired();
		} else {
			selectSpriteWithAge(sprites);
		}
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprites;

		public Factory(IAnimatedSprite sprite) {
			this.sprites = sprite;
		}

		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new ClawParticle(worldIn, x, y, z, xSpeed, sprites);
		}
	}
}
