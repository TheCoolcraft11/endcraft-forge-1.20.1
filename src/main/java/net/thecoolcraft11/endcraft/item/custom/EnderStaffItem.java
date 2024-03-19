package net.thecoolcraft11.endcraft.item.custom;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
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

        super.inventoryTick(pStack, world, entity, slot, selected);
    }
    
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
