package net.kallen.kse.item;

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
    public EchoBellItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) {

            AABB targetBox = new AABB(pPlayer.getX() + 20, pPlayer.getY() + 20, pPlayer.getZ() + 20,
                    pPlayer.getX() - 20, pPlayer.getY() - 20, pPlayer.getZ() - 20
                    );

            List<Entity> entityList = pLevel.getEntities(pPlayer, targetBox);
            for(Entity entity : entityList){
                if(entity instanceof LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400));
                }
            }

            pPlayer.getCooldowns().addCooldown(this, 12000);

        }


        return InteractionResultHolder.pass(itemInHand);
    }




}
