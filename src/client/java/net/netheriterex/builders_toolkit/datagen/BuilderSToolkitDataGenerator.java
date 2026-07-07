package net.netheriterex.builders_toolkit.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BuilderSToolkitDataGenerator implements DataGeneratorEntrypoint {

    // This method gets rid of the glitchy texture
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator){
        var pack = fabricDataGenerator.createPack();

        // Runs a lambda the actually runs the thing that gives the item model
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
