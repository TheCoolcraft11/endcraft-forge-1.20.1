package net.thecoolcraft11.endcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
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
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.custom.EndPedastelBlock;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class EndPedastelBlockEntity extends BlockEntity {
    public final ItemStackHandler inventory = new ItemStackHandler(1){
    @Override
    protected void onContentsChanged(int slot) {
        setChanged();
        if(!level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
};
    private static final int INPUT_SLOT = 0;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    public EndPedastelBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.END_PEDASTEL_BE.get(), pPos, pBlockState);
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
                return 1;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }


    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> inventory);
    }
    public ItemStack getRenderStack() {
        return this.inventory.getStackInSlot(INPUT_SLOT);
    }
    float rot = 0;
    public float getRenderRot(float speed) {
        if(this.rot >= 360) {
            this.rot = 0;
        }
        this.rot = this.rot+ speed;
        return this.rot;
    }
    float ry = 0;
    int direction = 1;
    public float getRenderY(float speed, float maxHeight, float minHeight) {
        if(this.ry >= maxHeight) {
            this.direction = -1;
        }else if (this.ry <= minHeight){
            this.direction = 1;
        }
        this.ry += speed * this.direction;
        return this.ry;
    }
    public boolean getRightItemsForType(int type, ItemStack itemStack) {
        Map< Integer,ItemStack> integerItemStackHashMap = new HashMap<>();
        integerItemStackHashMap.put(0, Items.AIR.getDefaultInstance());
        integerItemStackHashMap.put(1, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(2, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(3, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(4, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(5, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(6, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(7, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());
        integerItemStackHashMap.put(8, ModItems.ENDERITE_PICKAXE.get().getDefaultInstance());

        if(integerItemStackHashMap.get(type) == null) {
            return false;
        }
        return integerItemStackHashMap.get(type).getItem() == itemStack.getItem();
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState.setValue(EndPedastelBlock.RIGHT_ITEM, getRightItemsForType(pState.getValue(EndPedastelBlock.TYPE), this.inventory.getStackInSlot(0).getItem().getDefaultInstance())), 3);
    }


    public void drops() {
        SimpleContainer simpleContainer = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) {
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
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", inventory.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithFullMetadata();
    }

}