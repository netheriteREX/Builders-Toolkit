package net.netheriterex.builders_toolkit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.netheriterex.builders_toolkit.item.custom.ClearDye;

import java.util.concurrent.CompletableFuture;

import static net.netheriterex.builders_toolkit.item.ModItems.CLEAR_DYE;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                /*
                * This function adds a shapeless clear dye recipe, using 2 glow ink sacs
                * and an amethyst shard. I thought that the clear dye could be created by
                * refracting the light created by the ink sac. Also, ink and dye are basically
                * the same thing :>
                */
                shapeless(RecipeCategory.MISC, CLEAR_DYE)
                        .requires(Items.GLOW_INK_SAC, 2)
                        .requires(Items.AMETHYST_SHARD)
                        // ↓ The recipe gets unlocked by getting amethysts.
                        .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD));

                shaped(RecipeCategory.DECORATIONS, Items.PLAYER_HEAD)
                        .pattern("ooo")
                        .pattern("oBo")
                        .pattern("ooo")
                        .define('o', Items.CLAY_BALL)
                        .define('B', Items.OBSIDIAN)
                        .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "Builder's Toolkit Recipes";
    }
}
