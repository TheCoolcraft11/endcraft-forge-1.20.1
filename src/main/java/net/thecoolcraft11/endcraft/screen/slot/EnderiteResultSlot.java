package net.thecoolcraft11.endcraft.screen.slot;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class EnderiteResultSlot extends Slot {
    private static final Container emptyInventory = new SimpleContainer(0);
    private final IItemHandler itemHandler;
    private final int index;
    public EnderiteResultSlot(IItemHandler itemHandler, int index, int pX, int pY) {
        super(emptyInventory, index, pX, pY);
        this.itemHandler = itemHandler;
        this.index = index;

    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public boolean mayPickup(Player pPlayer) {
            return !this.getItemHandler().extractItem(this.index, 1, true).isEmpty();
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
