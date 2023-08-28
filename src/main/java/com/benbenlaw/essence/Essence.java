package com.benbenlaw.essence;

import com.benbenlaw.essence.block.ModBlocks;
import com.benbenlaw.essence.block.entity.ModBlockEntities;
import com.benbenlaw.essence.config.ConfigFile;
import com.benbenlaw.essence.fluid.ModFluidTypes;
import com.benbenlaw.essence.fluid.ModFluids;
import com.benbenlaw.essence.item.ModCreativeTab;
import com.benbenlaw.essence.item.ModItems;
import com.benbenlaw.essence.loot.ModLootModifiers;
import com.benbenlaw.essence.networking.ModMessages;
import com.benbenlaw.essence.particles.ModParticles;
import com.benbenlaw.essence.recipe.ModRecipes;
import com.benbenlaw.essence.screen.EssenceStationScreen;
import com.benbenlaw.essence.screen.ModMenuTypes;
import com.benbenlaw.essence.screen.ResourceDuplicatorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Essence.MOD_ID)
public class Essence {

    public static final String MOD_ID = "essence";;
    private static final Logger LOGGER = LogManager.getLogger();

    public Essence() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigFile.SPEC, "essence_common.toml");

        ModCreativeTab.register(eventBus);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);

        ModMenuTypes.register(eventBus);

        ModLootModifiers.register(eventBus);

        ModFluids.register(eventBus);
        ModFluidTypes.register(eventBus);

        ModParticles.register(eventBus);

        ModRecipes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);
        eventBus.addListener(this::doClientStuff);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);

    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}");
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("essense", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    private void doClientStuff(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {

        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_LIGHTNING_WATER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_LIGHTNING_WATER.get(), RenderType.translucent());

        MenuScreens.register(ModMenuTypes.ESSENCE_STATION_MENU.get(), EssenceStationScreen::new);
        MenuScreens.register(ModMenuTypes.RESOURCE_DUPLICATOR_MENU.get(), ResourceDuplicatorScreen::new);
        });
    }
}
