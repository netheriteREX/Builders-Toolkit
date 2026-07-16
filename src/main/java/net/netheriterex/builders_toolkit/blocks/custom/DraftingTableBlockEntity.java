package net.netheriterex.builders_toolkit.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.netheriterex.builders_toolkit.blocks.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.BlockBehaviour.simpleCodec;

// This is where everything important occurs.
public class DraftingTableBlockEntity extends BlockEntity {
    public DraftingTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DRAFTING_TABLE_BLOCK_ENTITY, pos, blockState);
    }


}
