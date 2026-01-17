package net.awaronoob.energical.datagen;

import net.awaronoob.energical.block.ModBlocks;
import net.awaronoob.energical.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> NEODYMITE_SMELTABLES = List.of(ModItems.RAW_NEODYMITE.get(), ModBlocks.NEODYMITE_ORE.get(),
                ModBlocks.DEEPSLATE_NEODYMITE_ORE.get());


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UNPRESSED_FLUXXITE.get(), 3)
                .pattern("GND")
                .pattern("CID")
                .pattern("DDD")
                .define('G', Items.GOLD_INGOT)
                .define('N', ModItems.NEODYMITE_INGOT.get())
                .define('C', Items.COPPER_INGOT)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.NEODYMITE_INGOT.get()), has(ModItems.NEODYMITE_INGOT.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NEODYMITE_INGOT.get(), 9)
                .requires(ItemTags.COAL_ORES)
                .unlockedBy(getHasName(Blocks.COAL_ORE), has(Blocks.COAL_ORE)).save(pRecipeOutput);

        oreSmelting(pRecipeOutput, NEODYMITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEODYMITE_INGOT.get(), 0.75f, 200, "neodymite");
        oreBlasting(pRecipeOutput, NEODYMITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEODYMITE_INGOT.get(), 0.75f, 100, "neodymite");



    }
}
