package net.netheriterex.builders_toolkit.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

import static net.netheriterex.builders_toolkit.BuilderSToolkit.MOD_ID;
/*
* This mixin modifies the SkullBlockRenderer class.
*/

@Mixin(SkullBlockRenderer.class)
public class PlayerHeadMixin {

	/*
	* In SkullBlockRenderer, in the event that a player isn't mentioned when creating
	* a player head, resolveSkullRenderType() automatically defaults to the Steve skin.
	* Obviously, I changed the texture.
	*/
	@Inject(method = "resolveSkullRenderType", at = @At("HEAD"), cancellable = true)
	private void overridePlayerSkullTexture(SkullBlock.Type type, SkullBlockEntity entity, CallbackInfoReturnable<RenderType> cir) {
		if (type == SkullBlock.Types.PLAYER && entity.getOwnerProfile() == null) {
			cir.setReturnValue(
					SkullBlockRenderer.getSkullRenderType(type, Identifier.fromNamespaceAndPath(MOD_ID, "textures/entity/grayscale_steve.png")
					)
			);
		}
	}
}