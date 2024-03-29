package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
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
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class EnderiteChestOwnerPearlItem extends Item {
    public EnderiteChestOwnerPearlItem(Properties pProperties) {
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
        if (pStack.getOrCreateTag().contains("playerName") && pStack.getOrCreateTag().contains("x") && pStack.getOrCreateTag().contains("y") && pStack.getOrCreateTag().contains("z")) {
                pTooltipComponents.add(Component.nullToEmpty(pStack.getOrCreateTag().getString("playerName")).copy().append("'s EnderiteChest").withStyle(ChatFormatting.DARK_PURPLE));
                if(!Screen.hasShiftDown()) {
                    pTooltipComponents.add(Component.translatable("tooltip.endcraft.holdshift").withStyle(ChatFormatting.GOLD));
                }else {
                    BlockPos pos = new BlockPos(pStack.getOrCreateTag().getInt("x"),pStack.getOrCreateTag().getInt("y"),pStack.getOrCreateTag().getInt("z"));
                    int fillState =((EnderiteChestBlockEntity) pLevel.getBlockEntity(pos)).getFillState();
                    float p = (float) fillState / 54 * 100;
                    DecimalFormat df = new DecimalFormat("#.##");
                    pTooltipComponents.add(Component.translatable("tooltip.endcraft.fillstate1").append(df.format(p).replace(",", ".")).append(Component.translatable("tooltip.endcraft.fillstate2")).withStyle(ChatFormatting.AQUA));
                    float i = (float) 12 / 54  * 10;
                    int x = (int) i;
                    StringBuilder s = new StringBuilder();
                    for(int i2 = 0; i2 < x; i2++) {
                        s.append("-");
                    }
                    StringBuilder s2 = new StringBuilder();
                    for(int i2 =0; i2 < 10 - x; i2++) {
                        s2.append("-");
                    }
                    Component greenComponent = Component.literal(String.valueOf(s)).withStyle(ChatFormatting.GREEN);
                    Component redComponent = Component.literal(String.valueOf(s2)).withStyle(ChatFormatting.RED);

                    pTooltipComponents.add(greenComponent.copy().append(redComponent));
                }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
