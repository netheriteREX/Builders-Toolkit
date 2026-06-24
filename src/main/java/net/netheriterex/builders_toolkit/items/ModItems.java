package net.netheriterex.builders_toolkit.items;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.netheriterex.builders_toolkit.BuilderSToolkit;

import java.util.function.Function;

public class ModItems {

    public static final Item CLEAR_DYE = registerItem("clear_dye", properties -> new Item(properties.stacksTo(64)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BuilderSToolkit.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BuilderSToolkit.MOD_ID, name)))));
    }

    public static void registerModItems() {
        BuilderSToolkit.LOGGER.info("Registering items for " + BuilderSToolkit.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {output.accept(CLEAR_DYE);});
    }
}
