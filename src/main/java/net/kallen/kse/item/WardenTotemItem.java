package net.kallen.kse.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.List;

public class WardenTotemItem extends Item {
    public WardenTotemItem(Properties pProperties) {
        super(pProperties.stacksTo(1).rarity(Rarity.UNCOMMON));
    }



    public void activate(ServerPlayer pPlayer, ItemStack pStack){

        if (isWardenNearby(pPlayer, 48)) {
            pPlayer.displayClientMessage(Component.literal("Warden Nearby"), true);
            return;
        }

        // Display Animation
        Minecraft.getInstance().gameRenderer.displayItemActivation(pStack);


        // Apply effects
        pPlayer.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 300, 0, false, false));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0, false, false));

        // Spawn Particles
        spawnTotemParticles(pPlayer);

        // Reset Warden Warning Level to 0
        pPlayer.getWardenSpawnTracker().get().setWarningLevel(0);

        // Consume the item
        pStack.shrink(1);


        // Play a sound (Totem Use Sound)
        pPlayer.level().playSound(null, pPlayer.blockPosition(),
                SoundEvents.TOTEM_USE,
                net.minecraft.sounds.SoundSource.PLAYERS,
                1.0F, 1.0F);


    }


    private void spawnTotemParticles(ServerPlayer pPlayer) {
        ServerLevel level = (ServerLevel) pPlayer.level();
        for (int i = 0; i < 50; i++) {
            double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
            double offsetY = level.random.nextDouble() * 2.0;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2.0;
            level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING,
                    pPlayer.getX() + offsetX,
                    pPlayer.getY() + offsetY,
                    pPlayer.getZ() + offsetZ,
                    10,
                    0, 0, 0, .3);
        }

    }

    private boolean isWardenNearby(Player pPlayer, double range) {
        List<Entity> nearbyEntities = pPlayer.level().getEntities(pPlayer, pPlayer.getBoundingBox().inflate(range));
        for (Entity entity : nearbyEntities) {
            if (entity instanceof Warden) {
                return true; // A Warden is too close
            }
        }
        return false;
    }





    }
