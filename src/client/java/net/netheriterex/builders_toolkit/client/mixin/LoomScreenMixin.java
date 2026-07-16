package net.netheriterex.builders_toolkit.client.mixin;

import net.minecraft.client.gui.screens.inventory.LoomScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/*
* This mixin works with the server-side LoomMenuMixin to allow the player
* to add up to 16 banner patterns.
*/
@Mixin(LoomScreen.class)
public class LoomScreenMixin {
    @ModifyConstant(method = "containerChanged", constant = @Constant(intValue = 6, ordinal = 0))
    public int editBannerPatternLayerCap(int original) {
        return 16;
    }
}
