package net.kallen.kse.worldgen;


import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.kse;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class kseConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ECHO_ORE_KEY = registerKey("echo_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_ECHO_ORE_KEY = registerKey("deepslate_echo_ore_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> echoOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        kseBlocks.ECHO_ORE.get().defaultBlockState()),
                OreConfiguration.target(stoneReplaceabeles, kseBlocks.ECHO_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> deepslateEchoOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        kseBlocks.DEEPSLATE_ECHO_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, kseBlocks.DEEPSLATE_ECHO_ORE.get().defaultBlockState()));



        register(context, ECHO_ORE_KEY, Feature.ORE, new OreConfiguration(echoOres, 1));
        register(context, DEEPSLATE_ECHO_ORE_KEY, Feature.ORE, new OreConfiguration(deepslateEchoOres, 3));


    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(kse.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}