package net.netheriterex.builders_toolkit.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.netheriterex.builders_toolkit.BuilderSToolkit;
import net.netheriterex.builders_toolkit.blocks.custom.DraftingTableBlockEntity;

public class ModBlockEntities {

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(BuilderSToolkit.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static final BlockEntityType<DraftingTableBlockEntity> DRAFTING_TABLE_BLOCK_ENTITY =
            register("counter", DraftingTableBlockEntity::new, ModBlocks.DRAFTING_TABLE_BLOCK);
}
