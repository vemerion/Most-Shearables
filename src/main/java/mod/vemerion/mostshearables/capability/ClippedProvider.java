package mod.vemerion.mostshearables.capability;

import mod.vemerion.mostshearables.Main;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ClippedProvider implements ICapabilitySerializable<INBT> {

	private LazyOptional<Clipped> instance = LazyOptional.of(Main.CLIPPED_CAP::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return Main.CLIPPED_CAP.orEmpty(cap, instance);
	}

	@Override
	public INBT serializeNBT() {
		return Main.CLIPPED_CAP.getStorage().writeNBT(Main.CLIPPED_CAP, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		Main.CLIPPED_CAP.getStorage().readNBT(Main.CLIPPED_CAP, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
	}

}
