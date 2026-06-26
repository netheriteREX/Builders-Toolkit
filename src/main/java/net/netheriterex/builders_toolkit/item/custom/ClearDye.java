package net.netheriterex.builders_toolkit.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;

public class ClearDye extends Item {
    public ClearDye(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(final ItemStack itemStack, final Player player, final LivingEntity target, final InteractionHand hand) {
        /*
               itemStack: gets the item in the player's hand and its amount
               player: used to prevent desync issues
               target: the entity that the player is pointing at
               hand: only exists cuz the function requires it

         */

        if (target instanceof ArmorStand armorStand) {
            if (!player.level().isClientSide()) {
                armorStand.setInvisible(true);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

}
