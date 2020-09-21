package mod.vemerion.mostshearables.clipperaction;

import mod.vemerion.mostshearables.Main;
import mod.vemerion.mostshearables.capability.Clipped;
import mod.vemerion.mostshearables.capability.ClippedMessage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class ClipperAction {

	public boolean clip(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		if (canClip(clipper, player, target, hand)) {
			action(clipper, player, target, hand);
			return true;
		} else {
			return false;
		}
	}

	protected abstract void action(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand);

	protected boolean canClip(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		if (target.isChild())
			return false;
		
		Clipped clipped = target.getCapability(Main.CLIPPED_CAP).orElse(new Clipped());
		if (!clipped.isClipped()) {
			clipper.damageItem(1, target, e -> e.sendBreakAnimation(hand));
			target.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);

			clipped.setClipped(true);
			ClippedMessage.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> target),
					new ClippedMessage(clipped.isClipped(), target.getEntityId()));
			return true;
		} else {
			return false;
		}
	}
}
