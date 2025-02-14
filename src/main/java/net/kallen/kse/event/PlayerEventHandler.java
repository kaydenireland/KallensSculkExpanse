package net.kallen.kse.event;


import net.kallen.kse.item.CoolDownItem;
import net.kallen.kse.item.kseItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class PlayerEventHandler {

    private static final String[] COOLDOWN_TAGS = {"MirrorCooldown"};
    private static final int[] COOLDOWN_TIMES = {18000}; // Ensure it matches the item cooldown
    private static final RegistryObject[] COOLDOWN_ITEMS = {kseItems.MURKY_MIRROR};

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CompoundTag persistentData = serverPlayer.getPersistentData();

            for(int i = 0; i < COOLDOWN_TAGS.length; i++) {

                // Retrieve the last used cooldown timestamp
                long lastUsedTime = persistentData.getLong(COOLDOWN_TAGS[i]);
                long currentTime = serverPlayer.level().getGameTime();

                // If cooldown is still active, apply it back
                if ((currentTime < lastUsedTime + COOLDOWN_TIMES[i]) && lastUsedTime > 0) {
                    int remainingCooldown = (int) ((lastUsedTime + COOLDOWN_TIMES[i]) - currentTime);
                    ((CoolDownItem) COOLDOWN_ITEMS[i].get()).applyCooldown((ServerPlayer) event.getEntity(), remainingCooldown);
                }
            }


        }
    }


}
