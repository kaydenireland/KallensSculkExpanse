package net.kallen.kse.item;

import net.kallen.kse.sound.kseSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class EchoBellItem extends BellItem {

    private static final String COOLDOWN_TAG = "EchoBellCooldown";
    private static final int COOLDOWN_TIME = 6000;


    public EchoBellItem(Properties pProperties) {
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

            AABB targetBox = new AABB(pPlayer.getX() + 20, pPlayer.getY() + 20, pPlayer.getZ() + 20,
                    pPlayer.getX() - 20, pPlayer.getY() - 20, pPlayer.getZ() - 20
                    );

            List<Entity> entityList = pLevel.getEntities(pPlayer, targetBox);
            for(Entity entity : entityList){
                if(entity instanceof LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400));
                }
            }

            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    kseSounds.ECHO_BELL.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);

            persistentData.putLong(COOLDOWN_TAG, currentTime);
            pPlayer.getCooldowns().addCooldown(this, COOLDOWN_TIME);


            return InteractionResultHolder.success(itemInHand);
        }


        return InteractionResultHolder.pass(itemInHand);
    }




}
