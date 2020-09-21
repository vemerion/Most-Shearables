package mod.vemerion.mostshearables.clipperaction;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class NoClipperAction extends ClipperAction {

	@Override
	public boolean canClip(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		return false;
	}

	@Override
	protected void action(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {		
	}
}
