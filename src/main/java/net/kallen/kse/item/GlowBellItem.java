package net.kallen.kse.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GlowBellItem extends BellItem {
    public GlowBellItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) pPlayer;

            serverPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400,0, true, true));
            System.out.println(pPlayer.getActiveEffects());
            pPlayer.getCooldowns().addCooldown(this, 6000);

            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    SoundEvents.BELL_BLOCK,
                    SoundSource.PLAYERS, 1.0F, 1.0F);

        }


        return InteractionResultHolder.pass(itemInHand);
    }




}
