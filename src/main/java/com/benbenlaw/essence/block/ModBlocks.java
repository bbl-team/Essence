package com.benbenlaw.essence.block;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.block.custom.*;
import com.benbenlaw.essence.fluid.ModFluids;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Essence.MOD_ID);

    //New BE

    public static final RegistryObject<Block> ESSENCE_STATION = registerBlock("essence_station",
            () -> new EssenceStationBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(2.0f, 2.0f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> RESOURCE_DUPLICATOR = registerBlock("resource_duplicator",
            () -> new ResourceDuplicatorBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(2.0f, 2.0f)
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    //New Blocks

    public static final RegistryObject<Block> BASIC_MOB_ESSENCE_BLOCK = registerBlock("basic_mob_essence_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(2.0f, 2.0f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> ADVANCED_MOB_ESSENCE_BLOCK = registerBlock("advanced_mob_essence_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(2.5f, 2.5f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> ELITE_MOB_ESSENCE_BLOCK = registerBlock("elite_mob_essence_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> BASIC_ORE_ESSENCE_BLOCK = registerBlock("basic_ore_essence_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(2.0f, 2.0f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> ADVANCED_ORE_ESSENCE_BLOCK = registerBlock("advanced_ore_essence_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(2.5f, 2.5f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> ELITE_ORE_ESSENCE_BLOCK = registerBlock("elite_ore_essence_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> BASIC_ESSENCE_ORE = registerBlock("basic_essence_ore",
            () -> new BasicEssenceOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(litBlockEmission(9)),
                    UniformInt.of(2, 4)));

    public static final RegistryObject<Block> DEEPSLATE_BASIC_ESSENCE_ORE = registerBlock("deepslate_basic_essence_ore",
            () -> new BasicEssenceOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.5f, 3.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(litBlockEmission(9)),
                    UniformInt.of(2, 4)));

    public static final RegistryObject<Block> ADVANCED_ESSENCE_ORE = registerBlock("advanced_essence_ore",
            () -> new AdvancedEssenceOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(litBlockEmission(9)),
                    UniformInt.of(2, 4)));

    public static final RegistryObject<Block> DEEPSLATE_ADVANCED_ESSENCE_ORE = registerBlock("deepslate_advanced_essence_ore",
            () -> new AdvancedEssenceOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.5f, 3.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(litBlockEmission(9)),
                    UniformInt.of(2, 4)));

    public static final RegistryObject<Block> ELITE_ESSENCE_ORE = registerBlock("elite_essence_ore",
            () -> new EliteEssenceOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(litBlockEmission(9)),
                    UniformInt.of(2, 4)));

    public static final RegistryObject<Block> DEEPSLATE_ELITE_ESSENCE_ORE = registerBlock("deepslate_elite_essence_ore",
            () -> new EliteEssenceOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(3.5f, 3.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(litBlockEmission(9)),
                    UniformInt.of(2, 4)));


    public static final RegistryObject<LiquidBlock> LIGHTNING_WATER_BLOCK = BLOCKS.register("lightning_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_LIGHTNING_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));

    //Light Level When Interacted With

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.translatable(tooltipKey));
            }
        });

    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));

    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
