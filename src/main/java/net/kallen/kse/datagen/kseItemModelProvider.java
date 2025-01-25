package net.kallen.kse.datagen;

import net.kallen.kse.kse;
import net.kallen.kse.item.kseItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class kseItemModelProvider extends ItemModelProvider {
    public kseItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, kse.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(kseItems.IRON_BELL);
        simpleItem(kseItems.ECHO_BELL);


    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(kse.MOD_ID,"item/" + item.getId().getPath()));
    }


}
