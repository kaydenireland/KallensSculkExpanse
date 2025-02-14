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



    private final ModelFile GENERATED = getExistingFile(mcLoc("item/generated"));
    private void armorItemModel(RegistryObject<? extends ArmorItem> item) {
        ItemModelBuilder itemModel = itemModel(item, GENERATED);
        LinkedHashMap<String, Float> trimModels = Util.make(new LinkedHashMap<>(), map -> {
            map.put("quartz", 0.1f);
            map.put("iron", 0.2f);
            map.put("netherite", 0.3f);
            map.put("echo_shard", 0.37779f);
            map.put("redstone", 0.4f);
            map.put("copper", 0.5f);
            map.put("gold", 0.6f);
            map.put("emerald", 0.7f);
            map.put("diamond", 0.8f);
            map.put("lapis", 0.9f);
            map.put("amethyst", 1.0f);
        });

        for(String material : trimModels.keySet()) {
            ResourceLocation trimLoc = mcLoc("trims/items/");
            ResourceLocation trimLayer = new ResourceLocation(trimLoc + item.get().getType().getName() + "_trim_" + material);
            existingFileHelper.trackGenerated(trimLayer, PackType.CLIENT_RESOURCES, ".png", "textures");
            getBuilder(item.getId().getPath() + "_" + material + "_trim").parent(GENERATED)
                    .texture("layer0", "item/" + item.getId().getPath())
                    .texture("layer1", trimLayer);

            itemModel.override().model(getModel(item, material + "_trim")).predicate(ItemModelGenerators.TRIM_TYPE_PREDICATE_ID, trimModels.get(material)).end();
        }



    }

    public ItemModelBuilder itemModel(RegistryObject<?> item, ModelFile modelFile) {
        return getBuilder(item.getId().getPath()).parent(modelFile).texture("layer0", "item/" + item.getId().getPath());
    }

    private ModelFile.ExistingModelFile getModel(RegistryObject<?> item, String suffix) {
        return new ModelFile.ExistingModelFile(modLoc("item/" + item.getId().getPath() + "_" + suffix), existingFileHelper);
    }





}
