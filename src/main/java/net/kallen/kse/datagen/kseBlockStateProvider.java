package net.kallen.kse.datagen;

import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.kse;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class kseBlockStateProvider extends BlockStateProvider {
    public kseBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, kse.MOD_ID,exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {


        blockWithItem(kseBlocks.ECHO_ORE);
        blockWithItem(kseBlocks.DEEPSLATE_ECHO_ORE);
        blockWithItem(kseBlocks.ECHO_SHARD_BLOCK);
        blockWithItem(kseBlocks.SCULKED_DEEPSLATE);


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


}
