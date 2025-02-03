package net.kallen.kse.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class HornItem extends Item {

    private SoundEvent sound;
    public HornItem(Properties pProperties, SoundEvent sound) {
        super(pProperties);
        this.sound = sound;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand hand) {
        ItemStack stack = pPlayer.getItemInHand(hand);

        if (pLevel.isClientSide) {
            pPlayer.startUsingItem(hand);
            pPlayer.playSound(sound, 1.0F, 1.0F);
            pPlayer.getCooldowns().addCooldown(this, 60);
        }

        return InteractionResultHolder.success(stack);
    }
}
