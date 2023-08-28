package com.benbenlaw.essence.block.entity.custom;

import com.benbenlaw.essence.block.entity.IInventoryHandlingBlockEntity;
import com.benbenlaw.essence.block.entity.ModBlockEntities;
import com.benbenlaw.essence.networking.ModMessages;
import com.benbenlaw.essence.networking.packets.PacketSyncItemStackToClient;
import com.benbenlaw.essence.recipe.ResourceDuplicatorRecipe;
import com.benbenlaw.essence.screen.ResourceDuplicatorMenu;
import com.benbenlaw.essence.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;

public class ResourceDuplicatorBlockEntity extends BlockEntity implements MenuProvider, IInventoryHandlingBlockEntity {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),

                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 1,
                            (index, stack) -> index == 1 && itemHandler.isItemValid(1, stack))),

                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCES::equals);
                                }
                                return false;
                            })),

                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCES::equals);
                                }
                                return false;
                            })),

                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCES::equals);
                                }
                                return false;
                            })),

                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCES::equals);
                                }
                                return false;
                            }))
            );

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public ItemStack getRenderStack() {
        ItemStack stack;
        if(itemHandler.getStackInSlot(1).isEmpty()) {
            stack = itemHandler.getStackInSlot(2);
        } else {
            stack = itemHandler.getStackInSlot(1);
        }

        return stack;
    }

    public void setHandler(ItemStackHandler handler) {
        copyHandlerContents(handler);
    }

    private void copyHandlerContents(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, handler.getStackInSlot(i));
        }
    }

    public ItemStackHandler getItemStackHandler() {
        return this.itemHandler;
    }

    public ResourceDuplicatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESOURCE_DUPLICATOR.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ResourceDuplicatorBlockEntity.this.progress;
                    case 1 -> ResourceDuplicatorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ResourceDuplicatorBlockEntity.this.progress = value;
                    case 1 -> ResourceDuplicatorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.essence.resource_duplicator.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ResourceDuplicatorMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return directionWrappedHandlerMap.get(side).cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        for (Direction dir : Direction.values()) {
            if (directionWrappedHandlerMap.containsKey(dir)) {
                directionWrappedHandlerMap.get(dir).invalidate();
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("resource_duplicator.progress", this.progress);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("resource_duplicator.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }



    public void tick() {

        Level pLevel = this.level;
        BlockPos pos  = this.worldPosition;
        assert pLevel != null;
        BlockState state = pLevel.getBlockState(pos);
        ResourceDuplicatorBlockEntity pEntity = this;

        if (level.isClientSide()) {
            return;
        }

        if (hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level, pos, state);

            if (pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem(ResourceDuplicatorBlockEntity pEntity) {
        Level level = pEntity.level;

        assert level != null;
        if(!level.isClientSide()) {

            SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
            for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
                inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
            }

            Optional<ResourceDuplicatorRecipe> recipeDuplicator = level.getRecipeManager()
                    .getRecipeFor(ResourceDuplicatorRecipe.Type.INSTANCE, inventory, level);

            if (recipeDuplicator.isPresent()) {

                pEntity.itemHandler.extractItem(0, recipeDuplicator.get().getEssenceInCount(), false);

                pEntity.itemHandler.setStackInSlot(2, new ItemStack (pEntity.itemHandler.getStackInSlot(1).getItem(),
                        pEntity.itemHandler.getStackInSlot(2).getCount() + recipeDuplicator.get().getOutCount()));

            }
        }

        pEntity.resetProgress();
    }

    private boolean hasRecipe(ResourceDuplicatorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<ResourceDuplicatorRecipe> recipeDuplicator = level.getRecipeManager()
                .getRecipeFor(ResourceDuplicatorRecipe.Type.INSTANCE, inventory, level);

        //Various Checks to create the item

        return recipeDuplicator.filter(resourceDuplicatorRecipe -> {
            if (!hasMakingItem(entity, recipeDuplicator.get()) ||
                    !canInsertAmountIntoOutputSlot(inventory) ||
                    !hasCorrectCountInInputSlotMaking(entity, recipeDuplicator.get()) ||
                    !hasOutputSpaceMaking(entity, recipeDuplicator.get())) return false;
            return canInsertItemIntoOutputSlot(inventory, itemHandler.getStackInSlot(1).getItem().asItem().getDefaultInstance());
        }).isPresent();
    }

    private boolean hasCorrectCountInInputSlotMaking(ResourceDuplicatorBlockEntity entity, ResourceDuplicatorRecipe recipe) {
        return entity.itemHandler.getStackInSlot(0).getCount() >= recipe.getEssenceInCount();
    }

    private boolean hasMakingItem(ResourceDuplicatorBlockEntity entity, ResourceDuplicatorRecipe recipe) {
        assert Minecraft.getInstance().level != null;

        List<ItemStack> inputItems = Arrays.stream(recipe.getIngredients().get(1).getItems()).toList();

        ItemStack slotItem = entity.itemHandler.getStackInSlot(1);

        for (ItemStack inputItem : inputItems) {
            if (ItemStack.isSameItem(slotItem, inputItem) && ItemStack.isSameItemSameTags(slotItem, inputItem)) {
                return true; // Found a matching item in the inputItems list
            }
        }

        return false; // No matching item found in the inputItems list
    }

    private boolean hasOutputSpaceMaking(ResourceDuplicatorBlockEntity entity, ResourceDuplicatorRecipe recipe) {
        return entity.itemHandler.getStackInSlot(2).getCount() + recipe.getOutCount() - 1 <
                entity.itemHandler.getStackInSlot(2).getMaxStackSize();
    }

    private boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

}
