package net.awaronoob.energical;

import com.mojang.logging.LogUtils;
import net.awaronoob.energical.block.ModBlocks;
import net.awaronoob.energical.block.entity.ModBlockEntities;
import net.awaronoob.energical.item.ModCreativeModeTabs;
import net.awaronoob.energical.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.spongepowered.asm.logging.ILogger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Energical.MOD_ID)
public class Energical
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "energical";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public Energical()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        Energical.LOGGER.info("Registering creative mode tabs for " + Energical.MOD_ID);
        ModCreativeModeTabs.register(modEventBus);

        Energical.LOGGER.info("Registering items for " + Energical.MOD_ID);
        ModItems.register(modEventBus);
        Energical.LOGGER.info("Registering blocks for " + Energical.MOD_ID);
        ModBlocks.register(modEventBus);

        Energical.LOGGER.info("Registering block entities for " + Energical.MOD_ID);
        ModBlockEntities.register();

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.UNPRESSED_FLUXXITE);

            event.accept(ModItems.RAW_NEODYMITE);
            event.accept(ModItems.NEODYMITE_INGOT);

            event.accept(ModItems.COPPER_SHEET);
            event.accept(ModItems.IRON_SHEET);
            event.accept(ModItems.NEODYMITE_SHEET);
            event.accept(ModItems.NETHERITE_SHEET);

        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.FLUXXITE_BLOCK);
            event.accept(ModBlocks.NEODYMITE_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.NEODYMITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_NEODYMITE_ORE);
            event.accept(ModBlocks.RAW_NEODYMITE_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.ITEM_DISPLAY);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
