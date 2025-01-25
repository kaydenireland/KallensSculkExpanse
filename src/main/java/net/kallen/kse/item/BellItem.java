package net.kallen.kse.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BellItem extends Item {
    public BellItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) {

            if(pPlayer.getXRot() < -30){

                if(pPlayer.isCrouching()){
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS,
                            .5f, 1);
                }else{
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.WITHER_HURT, SoundSource.PLAYERS,
                            .5f, 1);
                }

            }else if(pPlayer.getXRot() > 30){

                if(pPlayer.isCrouching()){
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.COW_AMBIENT, SoundSource.PLAYERS,
                            .5f, 1);
                }else{
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.COW_DEATH, SoundSource.PLAYERS,
                            .5f, 1);
                }

            }else{

                if(pPlayer.isCrouching()){
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.PIG_AMBIENT, SoundSource.PLAYERS,
                            .5f, 1);
                }else{
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.PIG_DEATH, SoundSource.PLAYERS,
                            .5f, 1);
                }

            }


        }


        return InteractionResultHolder.pass(itemInHand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.EAT;
    }





}
