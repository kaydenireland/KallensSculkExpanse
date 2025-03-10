package net.kallen.kse;

import com.mojang.logging.LogUtils;
import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.event.PlayerEventHandler;
import net.kallen.kse.event.WardenWarningListener;
import net.kallen.kse.item.kseCreativeModeTabs;
import net.kallen.kse.item.kseItems;
import net.kallen.kse.item.potion.BetterBrewingRecipe;
import net.kallen.kse.item.potion.ksePotions;
import net.kallen.kse.loot.kseLootModifiers;
import net.kallen.kse.network.kseNetworking;
import net.kallen.kse.sound.kseSounds;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(kse.MOD_ID)
public class kse {
    public static final String MOD_ID = "kse";
    private static final Logger LOGGER = LogUtils.getLogger();
    

    public kse(){
        IEventBus kseEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        kseItems.register(kseEventBus);
        kseBlocks.register(kseEventBus);
        kseCreativeModeTabs.register((kseEventBus));
        ksePotions.register(kseEventBus);

        kseSounds.register(kseEventBus);

        kseEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        kseLootModifiers.register(kseEventBus);

        kseEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(PlayerEventHandler.class);
        MinecraftForge.EVENT_BUS.register(WardenWarningListener.class);
        kseNetworking.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event){

        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, Items.ECHO_SHARD, ksePotions.BLINDNESS_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(ksePotions.BLINDNESS_POTION.get(), Items.REDSTONE, ksePotions.EXTENDED_BLINDNESS_POTION.get()));


    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event){

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event){
        
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            
        }
    }
}
