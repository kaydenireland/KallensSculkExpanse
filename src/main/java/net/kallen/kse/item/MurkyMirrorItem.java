package net.kallen.kse.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MurkyMirrorItem extends Item {
    public MurkyMirrorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) { // Ensure server-side execution
            BlockPos spawnPos = serverPlayer.getRespawnPosition();
            if (spawnPos != null) {
                // Teleport the player safely
                serverPlayer.teleportTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);

                // Play a sound effect
                pLevel.playSound(null, serverPlayer.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);



            }else{
                spawnPos = pLevel.getSharedSpawnPos();
                serverPlayer.teleportTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
            }

            serverPlayer.getCooldowns().addCooldown(this, 18000);
            return InteractionResultHolder.success(itemInHand);

        }

        

        return InteractionResultHolder.pass(itemInHand);
    }
}
