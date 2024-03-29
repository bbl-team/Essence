package com.benbenlaw.essence.item;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.fluid.ModFluids;
import com.benbenlaw.essence.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Essence.MOD_ID);

    public static final RegistryObject<Item> BASIC_MOB_ESSENCE = ITEMS.register("basic_mob_essence", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_MOB_ESSENCE = ITEMS.register("advanced_mob_essence", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> ELITE_MOB_ESSENCE = ITEMS.register("elite_mob_essence", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> ACTIVATED_SPAWNER_SHARD = ITEMS.register("activated_spawner_shard", () -> new SimpleFoiledItem(
            new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> SPAWNER_SHARD = ITEMS.register("spawner_shard", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> BLANK_SPAWN_EGG = ITEMS.register("blank_spawn_egg", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> BASIC_ORE_ESSENCE = ITEMS.register("basic_ore_essence", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_ORE_ESSENCE = ITEMS.register("advanced_ore_essence", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> ELITE_ORE_ESSENCE = ITEMS.register("elite_ore_essence", () -> new Item(
            new Item.Properties()));

    public static final RegistryObject<Item> ESSENCE_UPGRADER = ITEMS.register("essence_upgrader", () -> new EssenceUpgraderItem(
            new Item.Properties()
                    .durability(64)
                     ));

    public static final RegistryObject<Item> ESSENCE_CONVERTER = ITEMS.register("essence_converter", () -> new EssenceConverterItem(
            new Item.Properties()
                    .durability(64)
                     ));
    public static final RegistryObject<Item> LIGHTNING_INFUSER = ITEMS.register("lightning_infuser", () -> new LightningInfuserItem(
            new Item.Properties()
                    .durability(8)
                     ));


    public static final RegistryObject<Item> LIGHTNING_WATER_BUCKET = ITEMS.register("lightning_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_LIGHTNING_WATER.get(),
            new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
