package mod.vemerion.mostshearables.clipperaction;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class PigmanClipperAction extends DropClipperAction {

	public PigmanClipperAction(Item loot, int count) {
		super(loot, count);
	}

	@Override
	protected void action(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		super.action(clipper, player, target, hand);
		if (target instanceof ZombiePigmanEntity) {
			ZombiePigmanEntity pigman = (ZombiePigmanEntity) target;
			pigman.setRevengeTarget(player);
			pigman.setAttackTarget(player);
		}
	}
}
