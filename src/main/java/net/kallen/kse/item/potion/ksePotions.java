package net.kallen.kse.item.potion;


import net.kallen.kse.kse;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ksePotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, kse.MOD_ID);

    public static final RegistryObject<Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 600, 0)));

    public static final RegistryObject<Potion> EXTENDED_BLINDNESS_POTION = POTIONS.register("extended_blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 2400, 0)));




    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}