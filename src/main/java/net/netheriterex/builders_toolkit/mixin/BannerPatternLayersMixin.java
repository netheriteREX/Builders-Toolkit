package net.netheriterex.builders_toolkit.mixin;

import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/*
* This mixin modifies one of the hard-coded banner layer caps. In this case,
* the tooltip will now display up to 16 patterns, each being color-coded.
*/
@Mixin(BannerPatternLayers.class)
public class BannerPatternLayersMixin {
    @ModifyConstant(method = "addToTooltip", constant = @Constant(intValue = 6))
    public int editBannerPatternLayerCap(int original) {
        return 16;
    }
}
