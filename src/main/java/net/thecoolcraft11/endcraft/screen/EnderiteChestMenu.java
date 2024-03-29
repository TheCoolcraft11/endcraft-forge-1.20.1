package net.thecoolcraft11.endcraft.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import net.thecoolcraft11.endcraft.screen.slot.EnderiteAccessSlot;
import net.thecoolcraft11.endcraft.screen.slot.EnderiteInfoSlot;

public class EnderiteChestMenu extends AbstractContainerMenu {
    public final EnderiteChestBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    private static final int SLOTS_PER_ROW = 9;
    private final int containerRows = 6;

    public EnderiteChestMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(66));
    }

    public EnderiteChestMenu(int pContainderId , Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ENDERITE_CHEST_MENU.get(), pContainderId);
        checkContainerSize(inventory, 41);
        blockEntity = ((EnderiteChestBlockEntity) entity);
        this.level = inventory.player.level();
        this.data = data;

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {


            int x;
            int y;
            for(x = 0; x < containerRows; ++x) {
                for(y = 0; y < 9; ++y) {
                    this.addSlot(new SlotItemHandler(iItemHandler, y + x * 9, 8 + y * 18, 18 + x * 18));
                }
            }

            this.addSlot(new SlotItemHandler(iItemHandler, 54, 178, 21));
            this.addSlot(new FurnaceResultSlot(inventory.player, inventory, 55, 178, 63));

            this.addSlot(new SlotItemHandler(iItemHandler, 56, 180,108));
            this.addSlot(new SlotItemHandler(iItemHandler, 57, 198,108));
            this.addSlot(new SlotItemHandler(iItemHandler, 58, 216,108));
            this.addSlot(new SlotItemHandler(iItemHandler, 59, 180,126));
            this.addSlot(new SlotItemHandler(iItemHandler, 60, 198,126));
            this.addSlot(new SlotItemHandler(iItemHandler, 61, 216,126));
            this.addSlot(new SlotItemHandler(iItemHandler, 62, 180,144));
            this.addSlot(new SlotItemHandler(iItemHandler, 63, 198,144));
            this.addSlot(new EnderiteAccessSlot(iItemHandler, blockEntity,64, 216,144));
            this.addSlot(new EnderiteInfoSlot(iItemHandler, blockEntity,65, 80,2));
        });

        addDataSlots(data);
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 64;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 140 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 198));
        }
    }
}
