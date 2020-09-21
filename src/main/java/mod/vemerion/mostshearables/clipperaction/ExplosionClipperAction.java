package mod.vemerion.mostshearables.clipperaction;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;

public class ExplosionClipperAction extends ClipperAction {

	@Override
	public void action(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		clipper.damageItem(1, target, e -> e.sendBreakAnimation(hand));
		target.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		player.world.createExplosion(target, target.getPosX(), target.getPosY(), target.getPosZ(), 3, Explosion.Mode.BREAK);
		target.remove();
	}
	
	@Override
	protected boolean canClip(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		return true;
	}
}
