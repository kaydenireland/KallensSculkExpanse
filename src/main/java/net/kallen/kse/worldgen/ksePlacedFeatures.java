package net.kallen.kse.worldgen;


import net.kallen.kse.kse;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ksePlacedFeatures {

    public static final ResourceKey<PlacedFeature> ECHO_ORE_PLACED_KEY = registerKey("echo_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_ECHO_ORE_PLACED_KEY = registerKey("deepslate_echo_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, ECHO_ORE_PLACED_KEY, configuredFeatures.getOrThrow(kseConfiguredFeatures.ECHO_ORE_KEY),
                kseOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(10))));

        register(context, DEEPSLATE_ECHO_ORE_PLACED_KEY, configuredFeatures.getOrThrow(kseConfiguredFeatures.DEEPSLATE_ECHO_ORE_KEY),
                kseOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(10))));


    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(kse.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}