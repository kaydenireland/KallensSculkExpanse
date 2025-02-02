package net.kallen.kse.datagen.loot;


import net.kallen.kse.item.kseItems;
import net.kallen.kse.kse;
import net.kallen.kse.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class kseGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public kseGlobalLootModifierProvider(PackOutput output) {
        super(output, kse.MOD_ID);
    }

    @Override
    protected void start() {

        add("carapace_from_warden", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/warden")).build(),
                LootItemRandomChanceCondition.randomChance(1.0f).build()}, kseItems.WARDEN_TOTEM.get()));

        add("murky_mirror_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(.2f).build()}, kseItems.MURKY_MIRROR.get()));


    }
}