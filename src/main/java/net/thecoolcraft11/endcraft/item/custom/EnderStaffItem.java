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
import net.thecoolcraft11.endcraft.networking.ModMessages;
import net.thecoolcraft11.endcraft.networking.packet.EnderStaffTeleportationC2SPacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class EnderStaffItem extends Item {
    public EnderStaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ModMessages.sendToServer(new EnderStaffTeleportationC2SPacket());
        return InteractionResultHolder.success(pPlayer.getMainHandItem());
    }
    @Override
    public void inventoryTick(ItemStack pStack, Level world, Entity entity, int slot, boolean selected) {
        if(pStack.getOrCreateTag().getString("upgrade1").isBlank()) {
            pStack.getOrCreateTag().putString("upgrade1", "none");
        }
        if(pStack.getOrCreateTag().getString("upgrade2").isBlank()) {
            pStack.getOrCreateTag().putString("upgrade2", "none");
        }
        if(pStack.getOrCreateTag().getString("upgrade3").isBlank()) {
            pStack.getOrCreateTag().putString("upgrade3", "none");
        }
        super.inventoryTick(pStack, world, entity, slot, selected);
    }
    
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(!(pStack.getOrCreateTag().getString("upgrade1").isBlank())) {
            pTooltipComponents.add(Component.translatable("upgrade.endcraft." + pStack.getOrCreateTag().getString("upgrade1")+ "." + pStack.getOrCreateTag().getInt("upgrade1level")));
        }
        if(!(pStack.getOrCreateTag().getString("upgrade2").isBlank())) {
            pTooltipComponents.add(Component.translatable("upgrade.endcraft." + pStack.getOrCreateTag().getString("upgrade2")+ "." + pStack.getOrCreateTag().getInt("upgrade2level")));
        }
        if(!(pStack.getOrCreateTag().getString("upgrade3").isBlank())) {
            pTooltipComponents.add(Component.translatable("upgrade.endcraft." + pStack.getOrCreateTag().getString("upgrade3")+ "." + pStack.getOrCreateTag().getInt("upgrade3level")));
        }


        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public static boolean hasNbtUpgrade(ItemStack itempStack, String string) {
        return itempStack.getOrCreateTag().getString("upgrade1").equals(string) ||itempStack.getOrCreateTag().getString("upgrade2").equals(string) || itempStack.getOrCreateTag().getString("upgrade3").equals(string);
    }
    public static double getNbtLevel(ItemStack itempStack, String string) {
        int level = 0;
        if(itempStack.getOrCreateTag().getString("upgrade1").equals(string)) {
            level = itempStack.getOrCreateTag().getInt("upgrade1level");
        }
        if(itempStack.getOrCreateTag().getString("upgrade2").equals(string)) {
            level = itempStack.getOrCreateTag().getInt("upgrade2level");
        }
        if(itempStack.getOrCreateTag().getString("upgrade3").equals(string)) {
            level = itempStack.getOrCreateTag().getInt("upgrade3level");
        }
        return level;
    }
}
