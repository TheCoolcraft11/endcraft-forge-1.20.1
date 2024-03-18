package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergyCellItem extends Item {
    public EnergyCellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(net.minecraft.world.level.Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        addNbt(pPlayer.getMainHandItem(), "Energies", getNbtInt(pPlayer.getMainHandItem(), "Energies") + countItems(pPlayer, ModItems.ENDERITE_ENERGY.get()));
        removeItems(pPlayer, ModItems.ENDERITE_ENERGY.get());
        return InteractionResultHolder.success(pPlayer.getMainHandItem());
    }

    private static int countItems(Player player, Item item) {
        int energyCount = 0;
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.getItem() == item) {
                energyCount += stack.getCount();
            }
        }
        return energyCount;
    }
    private void removeItems(Player player, Item item) {
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.getItem() == item) {
                stack.setCount(0);
            }
        }
    }
    private int getNbtInt(ItemStack item, String string) {
        return item.getOrCreateTag().getInt(string);
    }

    private void addNbt(ItemStack itemStack, String string, int i) {
        itemStack.getOrCreateTag().putInt(string, i);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Energies: " + getNbtInt(pStack, "Energies")));
        for (int i = 0; i < pTooltipComponents.size(); i++) {
            if (pTooltipComponents.get(i).getString().contains("Durability:")) {
                pTooltipComponents.remove(i);
                break;
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        stack.setDamageValue(stack.getMaxDamage() - stack.getOrCreateTag().getInt("Energies"));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x7119F0;
    }
}
