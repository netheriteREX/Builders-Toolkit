package net.netheriterex.builders_toolkit.mixin;

import net.minecraft.world.inventory.LoomMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


/*
* This mixin modifies one of the hard-coded banner layer caps. In this case,
* it changes a method in the loom's menu, so the player can keep adding
* patterns to the banner.
*/
@Mixin(LoomMenu.class)
public class LoomMenuMixin {
    /*
     * The code doesn't target a final variable; rather, it targets an int
     * literal with a value of 6. The ordinal parameter ensures that even if
     * there are other sixes in the slotsChanged() method, editBannerPatternLayerCap()
     * will still target the correct one.
     */
    @ModifyConstant(method = "slotsChanged", constant = @Constant(intValue = 6, ordinal = 0))
    public int editBannerPatternLayerCap(int original) {
        return 16;
    }
}
