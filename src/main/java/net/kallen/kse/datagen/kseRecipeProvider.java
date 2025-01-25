package net.kallen.kse.datagen;

import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.item.kseItems;
import net.kallen.kse.kse;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class kseRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> ECHO_SMELTABLES = List.of(kseBlocks.ECHO_ORE.get(), kseBlocks.DEEPSLATE_ECHO_ORE.get());



    public kseRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        oreSmelting(pWriter, ECHO_SMELTABLES, RecipeCategory.MISC, Items.ECHO_SHARD, 0.25f, 200, "_from_smelting");
        oreBlasting(pWriter, ECHO_SMELTABLES, RecipeCategory.MISC, Items.ECHO_SHARD, 0.25f, 100, "_from_blasting");


        threeByThreePacker(pWriter, RecipeCategory.BUILDING_BLOCKS, kseBlocks.ECHO_SHARD_BLOCK.get(), Items.ECHO_SHARD);
        threeByThreeUnpacker(pWriter, Items.ECHO_SHARD, kseBlocks.ECHO_SHARD_BLOCK.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, kseBlocks.SCULKED_DEEPSLATE.get())
                .requires(Blocks.COBBLED_DEEPSLATE)
                .requires(Blocks.SCULK_VEIN)
                .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                .unlockedBy(getHasName(Blocks.SCULK_VEIN), has(Blocks.SCULK_VEIN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, kseItems.IRON_BELL.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, kseItems.AMETHYST_BELL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.AMETHYST_SHARD)
                .define('B', kseItems.IRON_BELL.get())
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .unlockedBy(getHasName(kseItems.IRON_BELL.get()), has(kseItems.IRON_BELL.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, kseItems.GLOW_BELL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GLOWSTONE)
                .define('B', kseItems.IRON_BELL.get())
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .unlockedBy(getHasName(kseItems.IRON_BELL.get()), has(kseItems.IRON_BELL.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, kseItems.ECHO_BELL.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.ECHO_SHARD)
                .define('B', kseItems.IRON_BELL.get())
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .unlockedBy(getHasName(kseItems.IRON_BELL.get()), has(kseItems.IRON_BELL.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.EXPERIENCE_BOTTLE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.SCULK)
                .define('B', Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Blocks.SCULK), has(Blocks.SCULK))
                .unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                .save(pWriter);


    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  kse.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void threeByThreeUnpacker(Consumer<FinishedRecipe> pWriter, ItemLike pUnPacked, ItemLike pPacked){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pUnPacked, 9)
                .requires(pPacked, 1)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pWriter, pUnPacked + "_unpacked");
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial) {
        stonecutterResultFromBase(pFinishedRecipeConsumer, pCategory, pResult, pMaterial, 1);
    }


}