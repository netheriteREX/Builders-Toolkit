package net.netheriterex.builders_toolkit.mixin;

import net.minecraft.world.level.block.entity.BannerBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
* This mixin is supposed to modify a banner layer cap, but
* because there are code where the cap is hard-coded
* (i.e. 6), there's a chance that this mixin does literally
* nothing.
*/
@Mixin(BannerBlockEntity.class)
public class BannerBlockEntityMixin {

    // Ensures that the variable can be modified.
    @Shadow
    @Mutable
    @Final
    public static int MAX_PATTERNS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void editBannerPatternLayerCap(CallbackInfo callbackInfo) {
        MAX_PATTERNS = 16;
    }
}
