package net.kallen.kse.item;

import net.kallen.kse.sound.kseSounds;
import net.minecraft.nbt.CompoundTag;
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

    private static final String COOLDOWN_TAG = "GlowBellCooldown";
    private static final int COOLDOWN_TIME = 3000;

    public GlowBellItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) pPlayer;

            CompoundTag persistentData = serverPlayer.getPersistentData();


            long lastUsedTime = persistentData.getLong(COOLDOWN_TAG);
            long currentTime = serverPlayer.level().getGameTime();

            if ((currentTime < lastUsedTime + COOLDOWN_TIME) && lastUsedTime > 0) {
                return InteractionResultHolder.fail(serverPlayer.getItemInHand(pUsedHand));
            }

            serverPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400,0, true, true));
            System.out.println(pPlayer.getActiveEffects());
            persistentData.putLong(COOLDOWN_TAG, currentTime);
            serverPlayer.getCooldowns().addCooldown(this, COOLDOWN_TIME);

            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    kseSounds.GLOW_BELL.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);

            return InteractionResultHolder.success(itemInHand);

        }


        return InteractionResultHolder.pass(itemInHand);
    }




}
