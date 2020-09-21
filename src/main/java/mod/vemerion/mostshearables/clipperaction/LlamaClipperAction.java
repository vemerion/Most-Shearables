package mod.vemerion.mostshearables.clipperaction;

import mod.vemerion.mostshearables.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class LlamaClipperAction extends DropClipperAction {

	public LlamaClipperAction(Item loot, int count) {
		super(loot, count);
	}

	@Override
	protected void action(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		if (!(target instanceof LlamaEntity))
			throw new IllegalArgumentException("LlamaClipperAction recieved non-llama entity");

		if (loot == Main.VARIANT) {
			int variant = ((LlamaEntity) target).getVariant();
			loot = variant == 3 ? Items.LIGHT_GRAY_WOOL : variant == 2 ? Items.BROWN_WOOL : Items.WHITE_WOOL;
		}
		super.action(clipper, player, target, hand);
	}

}
