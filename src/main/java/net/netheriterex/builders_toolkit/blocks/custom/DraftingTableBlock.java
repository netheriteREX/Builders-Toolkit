package net.netheriterex.builders_toolkit.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;

import static net.minecraft.world.level.block.state.BlockBehaviour.simpleCodec;

// This class only exists so that the drafting table can be placed and exist as an item.
public class DraftingTableBlock extends BaseEntityBlock{
    public DraftingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(DraftingTableBlock::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DraftingTableBlockEntity(pos, state);
    }
}
