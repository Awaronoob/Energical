package net.awaronoob.energical.datagen;

import net.awaronoob.energical.Energical;
import net.awaronoob.energical.block.ModBlocks;
import net.awaronoob.energical.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
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

        nineBlockStorageRecipes(pRecipeOutput, RecipeCategory.MISC, ModItems.NEODYMITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEODYMITE_BLOCK.get());



        oreSmelting(pRecipeOutput, NEODYMITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEODYMITE_INGOT.get(), 0.75f, 200, "neodymite");
        oreBlasting(pRecipeOutput, NEODYMITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEODYMITE_INGOT.get(), 0.75f, 100, "neodymite");



    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemLike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike))
                    .save(recipeOutput, Energical.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }
}
