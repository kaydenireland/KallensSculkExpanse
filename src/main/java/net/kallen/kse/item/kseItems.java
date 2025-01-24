package net.kallen.kse.item;

import net.kallen.kse.kse;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class kseItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, kse.MOD_ID);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }






}
