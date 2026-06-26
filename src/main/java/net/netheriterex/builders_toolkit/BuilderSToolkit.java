package net.netheriterex.builders_toolkit;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.netheriterex.builders_toolkit.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderSToolkit implements ModInitializer {
	public static final String MOD_ID = "builders_toolkit";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		// This lambda adds a new water bottle interaction.
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			ItemStack stack = player.getItemInHand(hand);
			boolean contents = stack.get(DataComponents.POTION_CONTENTS) != null;
			if (contents == true) {
				if (entity instanceof ArmorStand armorStand && stack.get(DataComponents.POTION_CONTENTS).is(Potions.WATER)) {
					if (!world.isClientSide()) {
						armorStand.setInvisible(false);
					}
					return InteractionResult.SUCCESS;
				}
			}
			return InteractionResult.PASS;
		});

		// This method let's Minecraft know that wee added new items to the game.
		ModItems.registerModItems();

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
