package net.kallen.kse.item;

import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.kse;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class kseCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, kse.MOD_ID);

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }





    ///////////// Creative Mode Tabs
    public static final RegistryObject<CreativeModeTab> KSE_ITEMS = CREATIVE_MODE_TABS.register("kse",
            () -> CreativeModeTab.builder().icon(() ->  new ItemStack(kseBlocks.ECHO_ORE.get()))
                    .title(Component.translatable("creativetab.kse"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(kseBlocks.ECHO_ORE.get());
                        output.accept(kseBlocks.DEEPSLATE_ECHO_ORE.get());
                        output.accept(kseBlocks.ECHO_SHARD_BLOCK.get());
                        output.accept(kseBlocks.SCULKED_DEEPSLATE.get());



                    })
                    .build());


}
