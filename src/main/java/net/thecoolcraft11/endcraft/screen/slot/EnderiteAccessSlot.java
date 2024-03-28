package net.thecoolcraft11.endcraft.screen.slot;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class EnderiteAccessSlot extends Slot {
    private static final Container emptyInventory = new SimpleContainer(0);
    private final IItemHandler itemHandler;
    private final int index;
    private final EnderiteChestBlockEntity blockEntity;
    public EnderiteAccessSlot(IItemHandler itemHandler, EnderiteChestBlockEntity blockEntity, int index, int pX, int pY) {
        super(emptyInventory, index, pX, pY);
        this.itemHandler = itemHandler;
        this.index = index;
        this.blockEntity = blockEntity;

    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (stack.getItem() == ModItems.ENDERITE_CHEST_ACCESS_PEARL.get() && stack.getOrCreateTag().contains("playerUUID")) {
            return stack.isEmpty() ? false : this.itemHandler.isItemValid(this.index, stack);
        }
        return false;
    }

    @Override
    public boolean mayPickup(Player pPlayer) {
        if (pPlayer.getUUID() == blockEntity.getPlacer()) {
            return !this.getItemHandler().extractItem(this.index, 1, true).isEmpty();
        }
        return false;
    }

    public @NotNull ItemStack getItem() {
        return this.getItemHandler().getStackInSlot(this.index);
    }

    public void set(@NotNull ItemStack stack) {
        ((IItemHandlerModifiable)this.getItemHandler()).setStackInSlot(this.index, stack);
        this.setChanged();
    }

    public void initialize(ItemStack stack) {
        ((IItemHandlerModifiable)this.getItemHandler()).setStackInSlot(this.index, stack);
        this.setChanged();
    }

    public void onQuickCraft(@NotNull ItemStack oldStackIn, @NotNull ItemStack newStackIn) {
    }

    public int getMaxStackSize() {
        return this.itemHandler.getSlotLimit(this.index);
    }

    public int getMaxStackSize(@NotNull ItemStack stack) {
        ItemStack maxAdd = stack.copy();
        int maxInput = stack.getMaxStackSize();
        maxAdd.setCount(maxInput);
        IItemHandler handler = this.getItemHandler();
        ItemStack currentStack = handler.getStackInSlot(this.index);
        if (handler instanceof IItemHandlerModifiable handlerModifiable) {
            handlerModifiable.setStackInSlot(this.index, ItemStack.EMPTY);
            ItemStack remainder = handlerModifiable.insertItem(this.index, maxAdd, true);
            handlerModifiable.setStackInSlot(this.index, currentStack);
            return maxInput - remainder.getCount();
        } else {
            ItemStack remainder = handler.insertItem(this.index, maxAdd, true);
            int current = currentStack.getCount();
            int added = maxInput - remainder.getCount();
            return current + added;
        }
    }


    public @NotNull ItemStack remove(int amount) {
        return this.getItemHandler().extractItem(this.index, amount, false);
    }

    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }
}
