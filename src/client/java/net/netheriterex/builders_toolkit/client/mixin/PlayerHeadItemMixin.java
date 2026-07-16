package net.netheriterex.builders_toolkit.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.special.PlayerHeadSpecialRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.netheriterex.builders_toolkit.BuilderSToolkit.MOD_ID;

/*
* This mixin makes the player head ITEM itself display a grayscale Steve skin.
*/

@Mixin(PlayerHeadSpecialRenderer.class)
public class PlayerHeadItemMixin {

    /*
    * You're probably wondering why this method requires so many parameters.
    * That's because it handles the rendering for the item, so it obviously
    * needs to account for stuff like lighting and other cosmetic changes that
    * can show up in the game
    */

    @Shadow
    private SkullModelBase modelBase;

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void overridePlayerHeadItemSkin(PlayerSkinRenderCache.RenderInfo cache, PoseStack poseStack,SubmitNodeCollector nodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor, CallbackInfo ci) {
        if (cache == null) {
            RenderType renderType = SkullBlockRenderer.getSkullRenderType(SkullBlock.Types.PLAYER, Identifier.fromNamespaceAndPath(MOD_ID, "textures/entity/grayscale_steve.png"));
            SkullBlockRenderer.submitSkull(0.0F, poseStack, nodeCollector, lightCoords, this.modelBase, renderType, outlineColor, null);
        }
    }
}
