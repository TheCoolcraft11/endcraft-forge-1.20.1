package net.thecoolcraft11.endcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.OculusCombinerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OculusCombinerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(3);
    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT1 = 1;
    private static final int OUTPUT_SLOT = 2;
    private int progress = 0;
    private int maxProgress = 0;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    public OculusCombinerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.OCULUS_COMBINER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> OculusCombinerBlockEntity.this.progress;
                    case 1 -> OculusCombinerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> OculusCombinerBlockEntity.this.progress = pValue;
                    case 1 -> OculusCombinerBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> inventory);
    }

    public void drops() {
        SimpleContainer simpleContainer = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            simpleContainer.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, simpleContainer);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.endcraft.oculus_combiner");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new OculusCombinerMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());
        pTag.putInt("progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("progress");

    }
    int currentprogress;
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            setMaxProgress();
            increaseProgress();
            setChanged(pLevel, pPos, pState);
            if(isOutputSlotEmpty() && isProgressFinshed()) {
                craftItem();
                resetProgess();
                currentprogress = 0;
            }
        }
    }

    private void setMaxProgress() {
        maxProgress = this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("maxDamage") + this.inventory.getStackInSlot(INPUT_SLOT1).getOrCreateTag().getInt("maxDamage");
    }

    private void resetProgess() {
        progress = 0;
    }

    private boolean isProgressFinshed() {
       return progress >= maxProgress;
    }

    private void increaseProgress() {
        progress++;
            }

    private void craftItem() {
        int newMaxDamage;
        newMaxDamage = (this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("maxDamage") + this.inventory.getStackInSlot(INPUT_SLOT1).getOrCreateTag().getInt("maxDamage"));
        Endcraft.LOGGER.info(String.valueOf(newMaxDamage));
        this.inventory.setStackInSlot(OUTPUT_SLOT, ModItems.OCULUS_ORE.get().getDefaultInstance());
        this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putInt("damage", 0);
        this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putInt("maxDamage", newMaxDamage);
        this.inventory.setStackInSlot(INPUT_SLOT, Items.AIR.getDefaultInstance());
        this.inventory.setStackInSlot(INPUT_SLOT1, Items.AIR.getDefaultInstance());
    }

    private boolean isOutputSlotEmpty() {
        return this.inventory.getStackInSlot(OUTPUT_SLOT).isEmpty();
    }
    public int getNextMaxDamage() {
        return this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("maxDamage") + this.inventory.getStackInSlot(INPUT_SLOT1).getOrCreateTag().getInt("maxDamage");
    }
    private boolean hasRecipe() {
        return (this.inventory.getStackInSlot(INPUT_SLOT).getItem() == ModItems.OCULUS_ORE.get() && this.inventory.getStackInSlot(INPUT_SLOT1).getItem() == ModItems.OCULUS_ORE.get() && this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("damage") == 0 && this.inventory.getStackInSlot(INPUT_SLOT1).getOrCreateTag().getInt("damage") == 0 && this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getInt("maxDamage") == this.inventory.getStackInSlot(INPUT_SLOT1).getOrCreateTag().getInt("maxDamage"));
    }

}