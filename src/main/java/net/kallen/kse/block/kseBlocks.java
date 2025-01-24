package net.kallen.kse.block;

import net.kallen.kse.kse;
import net.kallen.kse.item.kseItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class kseBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, kse.MOD_ID);


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return kseItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    ///////////////// Blocks



    public static final RegistryObject<Block> ECHO_ORE = registerBlock("echo_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(),
                    UniformInt.of(1,3)));

    public static final RegistryObject<Block> DEEPSLATE_ECHO_ORE = registerBlock("deepslate_echo_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops(),
                    UniformInt.of(1,3)));

    public static final RegistryObject<Block> ECHO_SHARD_BLOCK = registerBlock("echo_shard_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SCULKED_DEEPSLATE = registerBlock("sculked_deepslate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));



}
