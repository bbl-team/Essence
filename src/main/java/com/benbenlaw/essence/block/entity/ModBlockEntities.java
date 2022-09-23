package com.benbenlaw.essence.block.entity;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.block.entity.custom.EssenceStationBlockEntity;
import com.benbenlaw.essence.block.entity.custom.ResourceDuplicatorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Essence.MOD_ID);

    public static final RegistryObject<BlockEntityType<EssenceStationBlockEntity>> ESSENCE_STATION =
            BLOCK_ENTITIES.register("essence_station", () ->
                    BlockEntityType.Builder.of(EssenceStationBlockEntity::new,
                            ModBlocks.ESSENCE_STATION.get()).build(null));

    public static final RegistryObject<BlockEntityType<ResourceDuplicatorBlockEntity>> RESOURCE_DUPLICATOR =
            BLOCK_ENTITIES.register("resource_duplicator", () ->
                    BlockEntityType.Builder.of(ResourceDuplicatorBlockEntity::new,
                            ModBlocks.RESOURCE_DUPLICATOR.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}