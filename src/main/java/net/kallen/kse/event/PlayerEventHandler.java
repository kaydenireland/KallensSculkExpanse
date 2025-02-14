package net.kallen.kse.event;


import net.kallen.kse.item.MurkyMirrorItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEventHandler {

    private static final String COOLDOWN_TAG = "MirrorCooldown";
    private static final int COOLDOWN_TIME = 18000; // Ensure it matches the item cooldown

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CompoundTag persistentData = serverPlayer.getPersistentData();

            // Retrieve the last used cooldown timestamp
            long lastUsedTime = persistentData.getLong(COOLDOWN_TAG);
            long currentTime = serverPlayer.level().getGameTime();

            // If cooldown is still active, apply it back
            if ((currentTime < lastUsedTime + COOLDOWN_TIME) && lastUsedTime > 0) {
                int remainingCooldown = (int) ((lastUsedTime + COOLDOWN_TIME) - currentTime);
                //serverPlayer.getCooldowns().addCooldown(serverPlayer.getMainHandItem().getItem(), remainingCooldown);
                MurkyMirrorItem.applyCooldown((ServerPlayer) event.getEntity(), remainingCooldown);
            }
        }
    }
}
