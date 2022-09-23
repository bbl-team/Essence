package com.benbenlaw.essence.events;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.config.ConfigFile;
import com.benbenlaw.essence.damage.ModDamageSources;
import com.benbenlaw.essence.fluid.ModFluidTypes;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = Essence.MOD_ID)
public class ModEvents {


    @SubscribeEvent
    public static void inLightningWater(TickEvent.@NotNull PlayerTickEvent event) {

        Player player = event.player;
        Difficulty difficulty = player.getLevel().getDifficulty();
        FluidType fluid = ModFluidTypes.LIGHTNING_WATER_FLUID_TYPE.get();

        if (!player.isCreative()) {

            if (difficulty.equals(Difficulty.PEACEFUL));

            else if (player.isInFluidType(fluid)) {
                if (difficulty.equals(Difficulty.EASY)) {
                    player.hurt(ModDamageSources.SHOCKED, 1);
                }
                if (difficulty.equals(Difficulty.NORMAL)) {
                    player.hurt(ModDamageSources.SHOCKED, 2);
                }
                if (difficulty.equals(Difficulty.HARD)) {
                    player.hurt(ModDamageSources.SHOCKED, 3);
                }
            }
        }
    }

    //Mob Drops
    @SubscribeEvent
    public static void addMobEssenceToMobs(LivingDeathEvent event) {

        Vec3 entityPos = event.getEntity().position();
        Level world = event.getEntity().getLevel();
        Entity e = event.getEntity();

        if (e instanceof ServerPlayer) {}

        else if (Math.random() > ConfigFile.mobEssenceChance.get()) {

            world.addFreshEntity(new ItemEntity(world, entityPos.x(), entityPos.y(), entityPos.z(),
                    new ItemStack(ModItems.BASIC_MOB_ESSENCE.get())));
        }
    }
}

    /*

    //All Ores Drop Essence
    @SubscribeEvent
    public static void allOreChance(BlockEvent.BreakEvent event) {
        if (!event.getPlayer().level.isClientSide()) {
            BlockPos blockPos = event.getPos(); //          Block block = event.getState().getBlock();
            Level world = (Level) event.getLevel();
            ItemStack item = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            int level = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, item);

            if (level < 1) {
                if (event.getState().is(Tags.Blocks.ORES))  //CHANGE TO ORES
                    if (Math.random() > ConfigFile.allOresChance.get()) {
                        world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                                new ItemStack(ModItems.BASIC_ORE_ESSENCE.get())));
                    }

            }
        }
    }



    @SubscribeEvent
    public static void spawnerShards(PlayerInteractEvent.RightClickBlock event) {

        BlockPos blockPos = event.getPos();
        BlockState blockState = event.getWorld().getBlockState(blockPos);
        Level world = event.getWorld();

        if (event.getPlayer().getMainHandItem().is(ModItems.SPAWNER_SHARD_EXTRACTOR.get())) {
            if (blockState.is(Blocks.SPAWNER)) {

                event.getPlayer().getMainHandItem().hurtAndBreak(1, event.getPlayer(),(p_41303_) -> {
                    p_41303_.broadcastBreakEvent(event.getHand());
                });

                world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 1);{

                    if (Math.random() > ConfigFile.spawnerShardChance.get()) {
                        world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                                new ItemStack(ModItems.SPAWNER_SHARD.get())));
                        world.addFreshEntity((new ExperienceOrb(
                                world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10)));
                    }
                }
            }
        }
    }

}




















    /*

    @SubscribeEvent
    public static void addBasicEssenceToBasicOres(BlockEvent.BreakEvent event) {
        if (!event.getPlayer().level.isClientSide) {
//          Block block = event.getState().getBlock();
            BlockPos blockPos = event.getPos();
            Level world = (Level) event.getWorld();

            if (event.getState().is(ModTags.Blocks.ADVANCED_ORES))  //CHANGE TO ORES
                if (Math.random() > ConfigFile.basicOreEssenceChance.get()) {

                    world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                            new ItemStack(ModItems.BASIC_ORE_ESSENCE.get())));
                }
        }
    }

    @SubscribeEvent
    public static void addAdvancedEssenceToAdvancedOres(BlockEvent.BreakEvent event) {
        if (!event.getPlayer().level.isClientSide) {
//          Block block = event.getState().getBlock();
            BlockPos blockPos = event.getPos();
            Level world = (Level) event.getWorld();

            if (event.getState().is(ModTags.Blocks.ADVANCED_ORES))  //CHANGE TO ORES
                if (Math.random() > ConfigFile.advancedOreEssenceChance.get()) {

                    world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                            new ItemStack(ModItems.ADVANCED_ORE_ESSENCE.get())));
                }
        }
    }

    @SubscribeEvent
    public static void addEliteEssenceToEliteOres(BlockEvent.BreakEvent event) {
        if (!event.getPlayer().level.isClientSide()) {

            BlockPos blockPos = event.getPos();
            Level world = (Level) event.getWorld();

            if (event.getState().is(ModTags.Blocks.ELITE_ORES))  //CHANGE TO ORES
                if (Math.random() > ConfigFile.eliteOreEssenceChance.get()) {
                    world.addFreshEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                            new ItemStack(ModItems.ELITE_ORE_ESSENCE.get())));
                }
        }
    }



    @SubscribeEvent
    public static void addMobEssenceToMobs(LivingDeathEvent event) {

        Vec3 entityPos = event.getEntity().position();
        Level world = event.getEntity().getLevel();

        if (Math.random() > ConfigFile.mobEssenceChance.get()) {

            world.addFreshEntity(new ItemEntity(world, entityPos.x(), entityPos.y(), entityPos.z(),
                    new ItemStack(ModItems.BASIC_MOB_ESSENCE.get())));
        }
    }


}/*

     */
