package com.benbenlaw.essence.item;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Essence.MOD_ID);

    public static final RegistryObject<CreativeModeTab> STRAINERS_TAB = CREATIVE_MODE_TABS.register("essence", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.BASIC_ORE_ESSENCE.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.essence"))
            .displayItems((parameters, output) -> {

                output.accept(ModItems.BASIC_MOB_ESSENCE.get());
                output.accept(ModBlocks.BASIC_MOB_ESSENCE_BLOCK.get());
                output.accept(ModItems.BASIC_ORE_ESSENCE.get());
                output.accept(ModBlocks.BASIC_ORE_ESSENCE_BLOCK.get());
                output.accept(ModItems.ADVANCED_MOB_ESSENCE.get());
                output.accept(ModBlocks.ADVANCED_MOB_ESSENCE_BLOCK.get());
                output.accept(ModItems.ADVANCED_ORE_ESSENCE.get());
                output.accept(ModBlocks.ADVANCED_ORE_ESSENCE_BLOCK.get());
                output.accept(ModItems.ELITE_MOB_ESSENCE.get());
                output.accept(ModBlocks.ELITE_MOB_ESSENCE_BLOCK.get());
                output.accept(ModItems.ELITE_ORE_ESSENCE.get());
                output.accept(ModBlocks.ELITE_ORE_ESSENCE_BLOCK.get());

                output.accept(ModItems.SPAWNER_SHARD.get());
                output.accept(ModItems.BLANK_SPAWN_EGG.get());
                output.accept(ModItems.LIGHTNING_INFUSER.get());
                output.accept(ModItems.ACTIVATED_SPAWNER_SHARD.get());
                output.accept(ModItems.ESSENCE_CONVERTER.get());
                output.accept(ModItems.ESSENCE_UPGRADER.get());
                output.accept(ModItems.LIGHTNING_INFUSER.get());
                output.accept(ModBlocks.BASIC_ESSENCE_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_BASIC_ESSENCE_ORE.get());
                output.accept(ModBlocks.ADVANCED_ESSENCE_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_ADVANCED_ESSENCE_ORE.get());
                output.accept(ModBlocks.ELITE_ESSENCE_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_ELITE_ESSENCE_ORE.get());
                output.accept(ModItems.LIGHTNING_WATER_BUCKET.get());

                output.accept(ModBlocks.ESSENCE_STATION.get());
                output.accept(ModBlocks.RESOURCE_DUPLICATOR.get());

            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }


}
