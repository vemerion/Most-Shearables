package mod.vemerion.mostshearables.capability;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ClippedStorage implements IStorage<Clipped> {

	@Override
	public INBT writeNBT(Capability<Clipped> capability, Clipped instance, Direction side) {
		return ByteNBT.valueOf(instance.isClipped());
		
	}

	@Override
	public void readNBT(Capability<Clipped> capability, Clipped instance, Direction side, INBT nbt) {
		instance.setClipped(((ByteNBT)nbt).getByte() == 1);
	}

}
