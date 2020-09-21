package mod.vemerion.mostshearables.goal;

import java.util.EnumSet;

import mod.vemerion.mostshearables.Main;
import mod.vemerion.mostshearables.capability.Clipped;
import mod.vemerion.mostshearables.capability.ClippedMessage;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.PacketDistributor;

public class ClippedEatGrassGoal extends EatGrassGoal {
	private MobEntity entity;

	public ClippedEatGrassGoal(MobEntity entity) {
		super(entity);
		this.entity = entity;
		this.setMutexFlags(EnumSet.noneOf(Goal.Flag.class));
	}

	@Override
	public boolean shouldExecute() {
		return entity.getCapability(Main.CLIPPED_CAP).orElse(new Clipped()).isClipped() && super.shouldExecute();
	}

	@Override
	public void tick() {
		int timer = Math.max(0, getEatingGrassTimer() - 1);
		if (timer == 4) {
			BlockPos pos = entity.getPosition();
			if (entity.world.getBlockState(pos).getBlock() == Blocks.GRASS
					|| entity.world.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
				Clipped clipped = entity.getCapability(Main.CLIPPED_CAP).orElse(new Clipped());
				clipped.setClipped(false);
				ClippedMessage.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity),
						new ClippedMessage(clipped.isClipped(), entity.getEntityId()));

			}
		}
		super.tick();
	}

}
