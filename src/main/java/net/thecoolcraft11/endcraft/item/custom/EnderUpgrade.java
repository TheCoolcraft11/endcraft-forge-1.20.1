package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderUpgrade extends Item {
    String enchantment;
    int el;
    public EnderUpgrade(Properties pProperties, String enchantment2, int el2) {
        super(pProperties);
        this.enchantment = enchantment2;
        this.el = el2;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        pStack.getOrCreateTag().putString("type", this.enchantment);
        pStack.getOrCreateTag().putInt("level", this.el);
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("enchantment.endcraft." + this.enchantment).append(Component.translatable("enchantment.level." + this.el)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable("item.endcraft.ender_upgrade");
    }

}
