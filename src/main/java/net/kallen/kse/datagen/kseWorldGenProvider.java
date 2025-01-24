package net.kallen.kse.datagen;

import net.kallen.kse.kse;
import net.kallen.kse.worldgen.kseBiomeModifiers;
import net.kallen.kse.worldgen.kseConfiguredFeatures;
import net.kallen.kse.worldgen.ksePlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class kseWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, kseConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ksePlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, kseBiomeModifiers::bootstrap);


    public kseWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(kse.MOD_ID));
    }
}