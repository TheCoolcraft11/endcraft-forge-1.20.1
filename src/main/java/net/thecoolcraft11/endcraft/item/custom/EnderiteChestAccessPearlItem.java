package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.thecoolcraft11.endcraft.Endcraft;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderiteChestAccessPearlItem extends Item {
    public EnderiteChestAccessPearlItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if(pInteractionTarget instanceof Player) {
            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getString("playerName");
            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().putUUID("playerUUID", pInteractionTarget.getUUID());
            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().putString("playerName", pInteractionTarget.getName().getString());
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.getOrCreateTag().contains("playerUUID") && pStack.getOrCreateTag().contains("playerName")) {
                pTooltipComponents.add(Component.nullToEmpty(pStack.getOrCreateTag().getString("playerName")).copy().withStyle(ChatFormatting.AQUA));
                if(!Screen.hasShiftDown()) {
                    pTooltipComponents.add(Component.translatable("tooltip.endcraft.holdshift").withStyle(ChatFormatting.GOLD));
                }else {
                    if(pStack.getOrCreateTag().getUUID("playerUUID") != null) {
                        pTooltipComponents.add(Component.nullToEmpty(String.valueOf(pStack.getOrCreateTag().getUUID("playerUUID"))).copy().withStyle(ChatFormatting.AQUA));
                    }
                }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
