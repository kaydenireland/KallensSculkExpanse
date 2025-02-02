package net.kallen.kse.network;


import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;
import java.util.function.Supplier;

public class kseNetworking {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("kse", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(id++, TotemAnimationPacket.class,
                TotemAnimationPacket::encode, TotemAnimationPacket::new,
                TotemAnimationPacket::handle);
    }

    public static <MSG> void sendToClient(MSG message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> CHANNEL.sendToServer(message));
    }
}
