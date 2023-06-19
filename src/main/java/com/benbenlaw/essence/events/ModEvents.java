package com.benbenlaw.essence.events;

import com.benbenlaw.essence.Essence;
import com.benbenlaw.essence.config.ConfigFile;
import com.benbenlaw.essence.fluid.ModFluidTypes;
import com.benbenlaw.essence.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
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


    //Mob Drops
    @SubscribeEvent
    public static void addMobEssenceToMobs(LivingDeathEvent event) {

        Vec3 entityPos = event.getEntity().position();
        Level world = event.getEntity().level();
        Entity e = event.getEntity();

        if (e instanceof ServerPlayer) {}

        else if (Math.random() > ConfigFile.mobEssenceChance.get()) {

            world.addFreshEntity(new ItemEntity(world, entityPos.x(), entityPos.y(), entityPos.z(),
                    new ItemStack(ModItems.BASIC_MOB_ESSENCE.get())));
        }
    }
}
