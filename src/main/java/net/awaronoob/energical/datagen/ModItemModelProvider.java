package net.awaronoob.energical.datagen;

import net.awaronoob.energical.Energical;
import net.awaronoob.energical.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Energical.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.UNPRESSED_FLUXXITE.get());

        basicItem(ModItems.RAW_NEODYMITE.get());
        basicItem(ModItems.NEODYMITE_INGOT.get());


    }
}
