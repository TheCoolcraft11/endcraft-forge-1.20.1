package net.thecoolcraft11.endcraft.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FallEnchantment extends Enchantment {
    public FallEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable("enchantment.endcraft.fall").append(Component.translatable("enchantment.level." + pLevel));
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }
}
