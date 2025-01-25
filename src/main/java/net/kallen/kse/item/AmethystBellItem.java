package net.kallen.kse.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AmethystBellItem extends BellItem {
    public AmethystBellItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) {

            pPlayer.removeAllEffects();

        }


        return InteractionResultHolder.pass(itemInHand);
    }




}
