package net.kallen.kse.datagen;

import net.kallen.kse.kse;
import net.kallen.kse.item.kseItems;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class kseItemModelProvider extends ItemModelProvider {
    public kseItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, kse.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(kseItems.IRON_BELL);
        simpleItem(kseItems.AMETHYST_BELL);
        simpleItem(kseItems.GLOW_BELL);
        simpleItem(kseItems.ECHO_BELL);
        simpleItem(kseItems.WARDEN_TOTEM);
        simpleItem(kseItems.MURKY_MIRROR);
        simpleItem(kseItems.SCULK_HORN);


    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(kse.MOD_ID,"item/" + item.getId().getPath()));
    }






}
