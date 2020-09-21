package mod.vemerion.mostshearables.capability;

import java.util.function.Supplier;

import mod.vemerion.mostshearables.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ClippedMessage {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Main.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	private boolean isClipped;
	private int id;

	public ClippedMessage(boolean isClipped, int id) {
		this.isClipped = isClipped;
		this.id = id;
	}

	public static void encode(final ClippedMessage msg, final PacketBuffer buffer) {
		buffer.writeBoolean(msg.isClipped);
		buffer.writeInt(msg.id);
	}

	public static ClippedMessage decode(final PacketBuffer buffer) {
		return new ClippedMessage(buffer.readBoolean(), buffer.readInt());
	}

	public static void handle(final ClippedMessage msg, final Supplier<NetworkEvent.Context> supplier) {
		final NetworkEvent.Context context = supplier.get();
		context.setPacketHandled(true);
		context.enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			Entity entity = Minecraft.getInstance().world.getEntityByID(msg.id);
			if (entity != null) {
				Clipped clipped = entity.getCapability(Main.CLIPPED_CAP)
						.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));
				clipped.setClipped(msg.isClipped);
			}
		}));
	}
}
