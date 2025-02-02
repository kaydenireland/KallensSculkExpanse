package net.kallen.kse.sound;


import net.kallen.kse.kse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class kseSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, kse.MOD_ID);

    public static final RegistryObject<SoundEvent> AMETHYST_BELL = registerSoundEvents("amethyst_bell");
    public static final RegistryObject<SoundEvent> GLOW_BELL = registerSoundEvents("glow_bell");
    public static final RegistryObject<SoundEvent> ECHO_BELL = registerSoundEvents("echo_bell");

    public static final RegistryObject<SoundEvent> BELL_UP = registerSoundEvents("bell_up");
    public static final RegistryObject<SoundEvent> BELL_UP_SNEAK = registerSoundEvents("bell_up_sneak");
    public static final RegistryObject<SoundEvent> BELL_STRAIGHT = registerSoundEvents("bell_straight");
    public static final RegistryObject<SoundEvent> BELL_STRAIGHT_SNEAK = registerSoundEvents("bell_straight_sneak");
    public static final RegistryObject<SoundEvent> BELL_DOWN = registerSoundEvents("bell_down");
    public static final RegistryObject<SoundEvent> BELL_DOWN_SNEAK = registerSoundEvents("bell_down_sneak");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(kse.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
