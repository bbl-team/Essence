package com.benbenlaw.essence.block.entity.custom;

import com.benbenlaw.essence.block.entity.IInventoryHandlingBlockEntity;
import com.benbenlaw.essence.block.entity.ModBlockEntities;
import com.benbenlaw.essence.item.ModItems;
import com.benbenlaw.essence.networking.ModMessages;
import com.benbenlaw.essence.networking.packets.PacketSyncItemStackToClient;
import com.benbenlaw.essence.recipe.EssenceStationRecipe;
import com.benbenlaw.essence.screen.EssenceStationMenu;
import com.benbenlaw.essence.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EssenceStationBlockEntity extends BlockEntity implements MenuProvider, IInventoryHandlingBlockEntity {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                ModMessages.sendToClients(new PacketSyncItemStackToClient(this, worldPosition));
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
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCE_STATION_CATALYSTS::equals);
                                }
                                return false;
                    })),

                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCE_STATION_CATALYSTS::equals);
                                }
                                return false;
                    })),

                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCE_STATION_CATALYSTS::equals);
                                }
                                return false;
                    })),

                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0,
                            (index, stack) -> {
                                if (index == 0 && itemHandler.isItemValid(0, stack)) {
                                    // Add a condition to check for the specific item you want to allow
                                    return stack.getTags().anyMatch(ModTags.Items.ESSENCE_STATION_CATALYSTS::equals);
                                }
                                return false;
                    }))
            );

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;

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

    public EssenceStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ESSENCE_STATION.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> EssenceStationBlockEntity.this.progress;
                    case 1 -> EssenceStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EssenceStationBlockEntity.this.progress = value;
                    case 1 -> EssenceStationBlockEntity.this.maxProgress = value;
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
        return Component.translatable("blockentity.essence.essence_station.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new EssenceStationMenu(id, inventory, this, this.data);
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
        nbt.putInt("essence_station.progress", this.progress);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("essence_station.progress");
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
        EssenceStationBlockEntity pEntity = this;

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

    private void craftItem(EssenceStationBlockEntity pEntity) {
        Level level = pEntity.level;

        assert level != null;
        if(!level.isClientSide()) {

            SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
            for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
                inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
            }

            Optional<EssenceStationRecipe> essenceRecipe = level.getRecipeManager()
                    .getRecipeFor(EssenceStationRecipe.Type.INSTANCE, inventory, level);

            if (essenceRecipe.isPresent()) {

                pEntity.itemHandler.extractItem(1, essenceRecipe.get().getItemInCount(), false);
                assert Minecraft.getInstance().level != null;

                pEntity.itemHandler.setStackInSlot(2, new ItemStack(essenceRecipe.get().getResultItem(Objects.requireNonNull(getLevel()).registryAccess()).getItem(),
                        pEntity.itemHandler.getStackInSlot(2).getCount() + essenceRecipe.get().getResultItem(Objects.requireNonNull(getLevel()).registryAccess()).getCount()));

                if (pEntity.itemHandler.getStackInSlot(0).hurt(1, RandomSource.create(), null)) {
                    pEntity.itemHandler.extractItem(0, 1, false);
                }
            }
        }

        pEntity.resetProgress();
    }

    private void playLightningSound(EssenceStationBlockEntity pEntity) {
        if (pEntity.itemHandler.getStackInSlot(0).is(ModItems.LIGHTNING_INFUSER.get())){
            assert pEntity.level != null;
            pEntity.level.playLocalSound(pEntity.getBlockPos().getX(), pEntity.getBlockPos().getY(), pEntity.getBlockPos().getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.BLOCKS, (float) 0.5, 3, false);

        }
    }

    private boolean hasRecipe(EssenceStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        assert level != null;
        Optional<EssenceStationRecipe> essenceRecipe = level.getRecipeManager()
                .getRecipeFor(EssenceStationRecipe.Type.INSTANCE, inventory, level);

        if (essenceRecipe.isEmpty() || !canInsertAmountIntoOutputSlot(inventory)) return false;

        assert Minecraft.getInstance().level != null;
        return
               canInsertItemIntoOutputSlot(inventory, essenceRecipe.get().getResultItem(Objects.requireNonNull(getLevel()).registryAccess()))
                && hasOutputSpaceMaking(entity, essenceRecipe.get())
                && hasCorrectItemCatalyst(entity, essenceRecipe.get())
                && hasCorrectItemRecipe(entity, essenceRecipe.get())
                && hasCorrectCountInInputSlotUpgrading(entity, essenceRecipe.get());
    }

    private boolean hasCorrectItemCatalyst(EssenceStationBlockEntity entity, EssenceStationRecipe recipe) {
        return entity.itemHandler.getStackInSlot(0).getItem() == recipe.getIngredients().get(0).getItems()[0].getItem();
    }

    private boolean hasCorrectItemRecipe(EssenceStationBlockEntity entity, EssenceStationRecipe recipe) {
        return entity.itemHandler.getStackInSlot(1).getItem() == recipe.getIngredients().get(1).getItems()[0].getItem();
    }

    private boolean hasCorrectCountInInputSlotUpgrading(EssenceStationBlockEntity entity, EssenceStationRecipe recipe) {
        return entity.itemHandler.getStackInSlot(1).getCount() >= recipe.getItemInCount();
    }

    private boolean hasOutputSpaceMaking(EssenceStationBlockEntity entity, EssenceStationRecipe recipe) {
        return entity.itemHandler.getStackInSlot(2).getCount() + recipe.getResultItem(Objects.requireNonNull(getLevel()).registryAccess()).getCount() - 1 <
                entity.itemHandler.getStackInSlot(2).getMaxStackSize();
    }

    private boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }


}
