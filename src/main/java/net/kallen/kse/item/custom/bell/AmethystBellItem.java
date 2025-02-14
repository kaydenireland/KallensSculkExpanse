package net.kallen.kse.item.custom.bell;

import net.kallen.kse.sound.kseSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AmethystBellItem extends BellItem {

    private static final String COOLDOWN_TAG = "AmethystBellCooldown";
    private static final int COOLDOWN_TIME = 6000;


    public AmethystBellItem(Item.Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {


            CompoundTag persistentData = pPlayer.getPersistentData();


            long lastUsedTime = persistentData.getLong(COOLDOWN_TAG);
            long currentTime = pPlayer.level().getGameTime();

            if ((currentTime < lastUsedTime + COOLDOWN_TIME) && lastUsedTime > 0) {
                return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
            }

            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    kseSounds.AMETHYST_BELL.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);

            pPlayer.removeAllEffects();
            persistentData.putLong(COOLDOWN_TAG, currentTime);
            pPlayer.getCooldowns().addCooldown(this, COOLDOWN_TIME);



            return InteractionResultHolder.success(itemInHand);

        }

        return InteractionResultHolder.pass(itemInHand);
    }




}
