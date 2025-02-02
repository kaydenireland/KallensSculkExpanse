package net.kallen.kse.network;


import net.kallen.kse.item.kseItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;
import java.util.function.Supplier;

public class TotemAnimationPacket {

    public TotemAnimationPacket() {} // Empty constructor

    // Decode from the network buffer
    public TotemAnimationPacket(FriendlyByteBuf buf) {}

    // Encode the packet (no data needed)
    public void encode(FriendlyByteBuf buf) {}

    // Handle the packet on the Client
    public static void handle(TotemAnimationPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Runs on the client
            Minecraft.getInstance().gameRenderer.displayItemActivation(kseItems.WARDEN_TOTEM.get().getDefaultInstance());
        });
        ctx.get().setPacketHandled(true);
    }
}
