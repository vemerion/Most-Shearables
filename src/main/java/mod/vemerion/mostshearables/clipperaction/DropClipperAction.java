package mod.vemerion.mostshearables.clipperaction;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DropClipperAction extends ClipperAction {
	protected Item loot;
	protected int count;

	public DropClipperAction(Item loot, int count) {
		if (count <= 0)
			throw new IllegalArgumentException("Count can only be positive");
		this.loot = loot;
		this.count = count;
	}

	@Override
	protected void action(ItemStack clipper, PlayerEntity player, LivingEntity target, Hand hand) {
		World world = target.world;
		ItemEntity drop = new ItemEntity(world, target.getPosX(), target.getEyePosition(0.5f).getY(), target.getPosZ(),
				new ItemStack(loot, count));
		world.addEntity(drop);
	}
}
