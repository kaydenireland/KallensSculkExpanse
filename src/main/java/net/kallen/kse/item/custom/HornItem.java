package net.kallen.kse.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
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

        if (!pPlayer.isUsingItem() && !pPlayer.getCooldowns().isOnCooldown(stack.getItem())) {
            pPlayer.startUsingItem(hand);
            pPlayer.playSound(sound, 1.0F, 1.0F);
            pPlayer.getCooldowns().addCooldown(this, 100);
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }


}
