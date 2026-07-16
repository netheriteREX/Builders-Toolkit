package net.netheriterex.builders_toolkit.mixin;

import net.minecraft.world.item.crafting.BannerDuplicateRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/*
* This mixin modifies one of the hard-coded banner layer caps. In this case,
* it prevents the (former) layer cap from being enforced when duplicating
* banners in the crafting menu.
*/
@Mixin(BannerDuplicateRecipe.class)
public class BannerDuplicateMixin {
    /*
    * The code doesn't target a final variable; rather, it targets an int
    * literal with a value of 6. The ordinal parameter ensures that even if
    * there are other sixes in the assemble() method, editBannerPatternLayerCap()
    * will still target the correct one.
    */
    @ModifyConstant(method = "assemble", constant = @Constant(intValue = 6, ordinal = 0))
    public int editBannerPatternLayerCapOne(int original) {
        return 16;
    }

    @ModifyConstant(method = "matches", constant = @Constant(intValue = 6, ordinal = 0))
    public int editBannerPatternLayerCapTwo(int original) {
        return 16;
    }
}