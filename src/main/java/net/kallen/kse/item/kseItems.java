package net.kallen.kse.item;

import net.kallen.kse.item.custom.HornItem;
import net.kallen.kse.item.custom.MurkyMirrorItem;
import net.kallen.kse.item.custom.WardenTotemItem;
import net.kallen.kse.item.custom.bell.AmethystBellItem;
import net.kallen.kse.item.custom.bell.BellItem;
import net.kallen.kse.item.custom.bell.EchoBellItem;
import net.kallen.kse.item.custom.bell.GlowBellItem;
import net.kallen.kse.kse;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class kseItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, kse.MOD_ID);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



    public static final RegistryObject<Item> IRON_BELL = ITEMS.register("iron_bell", () -> new BellItem(new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_BELL = ITEMS.register("amethyst_bell", () -> new AmethystBellItem(new Item.Properties()));
    public static final RegistryObject<Item> GLOW_BELL = ITEMS.register("glow_bell", () -> new GlowBellItem(new Item.Properties()));
    public static final RegistryObject<Item> ECHO_BELL = ITEMS.register("echo_bell", () -> new EchoBellItem(new Item.Properties()));
    public static final RegistryObject<Item> WARDEN_TOTEM = ITEMS.register("warden_totem", () -> new WardenTotemItem(new Item.Properties()));
    public static final RegistryObject<Item> MURKY_MIRROR = ITEMS.register("murky_mirror", () -> new MurkyMirrorItem(new Item.Properties()));
    public static final RegistryObject<Item> SCULK_HORN = ITEMS.register("sculk_horn", () -> new HornItem(new Item.Properties(), SoundEvents.WARDEN_SONIC_BOOM));





}
