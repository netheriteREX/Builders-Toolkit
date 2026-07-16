package net.netheriterex.builders_toolkit;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import net.minecraft.util.filefix.fixes.ResourcePackLocationFileFix;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PlayerHeadBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.EntityHitResult;
import net.netheriterex.builders_toolkit.blocks.ModBlocks;
import net.netheriterex.builders_toolkit.item.ModItems;
import net.netheriterex.builders_toolkit.item.custom.ClearDye;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderSToolkit implements ModInitializer {
	public static final String MOD_ID = "builders_toolkit";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void registerRightClickEvents() {
		// Water bottle clears invisibility from armor stands.
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			ItemStack stack = player.getItemInHand(hand);
			boolean contents = stack.get(DataComponents.POTION_CONTENTS) != null;
			if (contents == true) {
				if (entity instanceof ArmorStand armorStand && stack.get(DataComponents.POTION_CONTENTS).is(Potions.WATER)) {
					if (!world.isClientSide()) {
						if (armorStand.isInvisible()) {
							player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player,
									new ItemStack(Items.GLASS_BOTTLE), true));
							armorStand.setInvisible(false);
							return InteractionResult.SUCCESS;
						}
					}
				}
			}
			return InteractionResult.PASS;
		});

		// Water bottles make item frames visible too.
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			ItemStack stack = player.getItemInHand(hand);
			boolean contents = stack.get(DataComponents.POTION_CONTENTS) != null;
			if (contents == true) {
				if (entity instanceof ItemFrame itemFrame && stack.get(DataComponents.POTION_CONTENTS).is(Potions.WATER)) {
					if (!world.isClientSide()) {
						if (itemFrame.isInvisible()) {
							player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player,
									new ItemStack(Items.GLASS_BOTTLE), true));
							itemFrame.setInvisible(false);
							return InteractionResult.SUCCESS;
						}
					}
				}
			}
			return InteractionResult.PASS;
		});

		// Right-clicking an armor stand with at least 2 sticks adds arms to the stand.
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			ItemStack stack = player.getItemInHand(hand);
			if (entity instanceof ArmorStand armorStand && stack.is(Items.STICK) && stack.getCount() >= 2) {
				if (!world.isClientSide()) {
					if (!armorStand.showArms()) {
						armorStand.setShowArms(true);
						stack.consume(2, player);
						return InteractionResult.SUCCESS;
					}
				}
			}
			return InteractionResult.PASS;
		});

		// Makes item frames invisible. No, this wouldn't work if put in ClearDye.java.
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof ItemFrame itemFrame) {
				ItemStack stack = player.getItemInHand(hand);
				if (stack.getItem() instanceof ClearDye clearDye && !itemFrame.isInvisible()) {
					stack.consume(1, player);
					itemFrame.setInvisible(true);
					return InteractionResult.SUCCESS;
				}
			}
			return InteractionResult.PASS;
		});
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		// These are helper methods that add stuff to the game.
		ModItems.registerModItems();
		registerRightClickEvents();

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
