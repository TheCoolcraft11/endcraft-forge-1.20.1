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
import net.thecoolcraft11.endcraft.block.custom.EnderiteChestBlock;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.EnderiteChestMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public class EnderiteChestBlockEntity extends BlockEntity implements MenuProvider {
    private UUID placer = null;
    private UUID pwd = null;
    private final ItemStackHandler inventory = new ItemStackHandler(66);
    private static final int INPUT_SLOT = 27;
    private static final int OUTPUT_SLOT = 28;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    public EnderiteChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ENDERITE_CHEST_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return 0;
            }

            @Override
            public void set(int pIndex, int pValue) {
            }

            @Override
            public int getCount() {
                return 66;
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
    public void setPlacer(UUID placer, String player, BlockPos blockPos) {
        this.placer = placer;
        this.inventory.setStackInSlot(65, ModItems.ENDERITE_CHEST_OWNER_PEARL.get().getDefaultInstance());
        this.inventory.getStackInSlot(65).getOrCreateTag().putString("playerName",player);
        this.inventory.getStackInSlot(65).getOrCreateTag().putInt("x",blockPos.getX());
        this.inventory.getStackInSlot(65).getOrCreateTag().putInt("y",blockPos.getY());
        this.inventory.getStackInSlot(65).getOrCreateTag().putInt("z",blockPos.getZ());
        setChanged();
    }
    public void setPwd(UUID pwd) {
        this.pwd = pwd;
        setChanged();
    }
    public UUID getPwd() {
        return this.pwd;
    }
    public UUID getPlacer() {
        return  this.placer;
    }
    public boolean isGuest(Player player) {
        boolean isGuest = false;
        for (int i = 0; i < 9;i++) {
            if(this.inventory.getStackInSlot(56+ i).getItem() == ModItems.ENDERITE_CHEST_ACCESS_PEARL.get()) {
                if(this.inventory.getStackInSlot(56+ i).getOrCreateTag().getUUID("playerUUID").equals(player.getUUID())) {
                    isGuest = true;
                }
            }
        }
        return isGuest;
    }
    public int getFillState() {
        int fillState = 0;
        for(int i = 0; i < 54; i++) {
            if(this.inventory.getStackInSlot(i).isEmpty()) {
                fillState++;
            }
        }
        return fillState;
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(this.inventory.getStackInSlot(INPUT_SLOT).getItem() == ModItems.ENDERITE_CHEST_KEY.get() && !this.inventory.getStackInSlot(INPUT_SLOT).getOrCreateTag().getBoolean("aligned")) {
            this.inventory.setStackInSlot(OUTPUT_SLOT, ModItems.ENDERITE_CHEST_KEY.get().getDefaultInstance());
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putInt("x1", this.worldPosition.getX());
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putInt("y1", this.worldPosition.getY());
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putInt("z1", this.worldPosition.getZ());
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putUUID("pwd", this.getPwd());
            this.inventory.getStackInSlot(OUTPUT_SLOT).getOrCreateTag().putBoolean("aligned", true);

            this.inventory.setStackInSlot(INPUT_SLOT, Items.AIR.getDefaultInstance());
            setChanged(level, pPos, pState);
        }
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> inventory);
    }

    public void drops() {
        SimpleContainer simpleContainer = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            if(i != 65) {
                simpleContainer.setItem(i, inventory.getStackInSlot(i));
            }
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
        return Component.translatable("block.endcraft.enderite_chest");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new EnderiteChestMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());
        if (placer != null) {
            pTag.putUUID("placer", placer);
        }
        if (pwd != null) {
            pTag.putUUID("pwd", pwd);
        }

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
        placer = pTag.getUUID("placer");
        pwd = pTag.getUUID("pwd");
    }

}