package net.kallen.kse.event;


import net.kallen.kse.item.WardenTotemItem;
import net.kallen.kse.item.kseItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WardenWarningListener {

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            // Get Warden warning level (From Warden Spawn Tracker)
            int warningLevel = player.getWardenSpawnTracker().get().getWarningLevel();

            if (warningLevel >= 3) {
                // Check if the totem is in the player's hand or offhand
                if (isHoldingWardenTotem(player)) {

                    Item mainHandItem = player.getMainHandItem().getItem();
                    Item offHandItem = player.getOffhandItem().getItem();

                    WardenTotemItem totem;

                    if(mainHandItem instanceof WardenTotemItem){
                        totem = (WardenTotemItem) player.getMainHandItem().getItem();
                        totem.activate(player, player.getMainHandItem());
                    }else if(offHandItem instanceof WardenTotemItem){
                        totem = (WardenTotemItem) player.getOffhandItem().getItem();
                        totem.activate(player, player.getOffhandItem());
                    }

                }
            }

        }
    }



    private static boolean isHoldingWardenTotem(ServerPlayer player) {
        return player.getMainHandItem().getItem() instanceof WardenTotemItem
                || player.getOffhandItem().getItem() instanceof WardenTotemItem;
    }

}
