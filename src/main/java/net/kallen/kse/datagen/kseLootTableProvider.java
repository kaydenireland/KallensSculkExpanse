package net.kallen.kse.datagen;


import net.kallen.kse.datagen.loot.kseBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class kseLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(kseBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}