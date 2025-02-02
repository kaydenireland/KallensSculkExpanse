package net.kallen.kse.datagen;

import net.kallen.kse.datagen.loot.kseGlobalLootModifierProvider;
import net.kallen.kse.datagen.loot.kseLootTableProvider;
import net.kallen.kse.kse;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = kse.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new kseRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), kseLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new kseGlobalLootModifierProvider(packOutput));

        generator.addProvider(event.includeClient(), new kseBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new kseItemModelProvider(packOutput, existingFileHelper));

        kseBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new kseBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new kseItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new kseWorldGenProvider(packOutput, lookupProvider));
    }
}