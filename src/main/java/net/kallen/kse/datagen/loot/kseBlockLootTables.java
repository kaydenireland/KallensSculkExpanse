package net.kallen.kse.datagen.loot;

import net.kallen.kse.block.kseBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class kseBlockLootTables extends BlockLootSubProvider {
    public kseBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.add(kseBlocks.ECHO_ORE.get(),
                block -> createEchoOreDrops(kseBlocks.ECHO_ORE.get(), Items.ECHO_SHARD));
        this.add(kseBlocks.DEEPSLATE_ECHO_ORE.get(),
                block -> createEchoOreDrops(kseBlocks.DEEPSLATE_ECHO_ORE.get(), Items.ECHO_SHARD));


        this.dropSelf(kseBlocks.ECHO_SHARD_BLOCK.get());
        this.dropSelf(kseBlocks.SCULKED_DEEPSLATE.get());


    }

    protected LootTable.Builder createEchoOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return kseBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}