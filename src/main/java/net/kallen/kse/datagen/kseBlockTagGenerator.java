package net.kallen.kse.datagen;

import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.kse;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class kseBlockTagGenerator extends BlockTagsProvider{
    public kseBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, kse.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(kseBlocks.ECHO_ORE.get(),
                        kseBlocks.DEEPSLATE_ECHO_ORE.get(),
                        kseBlocks.ECHO_SHARD_BLOCK.get(),
                        kseBlocks.SCULKED_DEEPSLATE.get()

                );


        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(kseBlocks.ECHO_ORE.get(),
                        kseBlocks.DEEPSLATE_ECHO_ORE.get()

                );


    }
}
