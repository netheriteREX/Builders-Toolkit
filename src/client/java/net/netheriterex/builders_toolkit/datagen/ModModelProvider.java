package net.netheriterex.builders_toolkit.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.netheriterex.builders_toolkit.item.ModItems;


// This class creates the item model that's visible in-game

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    // Models for blocks
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    // Models for items
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.CLEAR_DYE, ModelTemplates.FLAT_ITEM);
    }
}
