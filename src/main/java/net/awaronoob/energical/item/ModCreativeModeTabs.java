package net.awaronoob.energical.item;

import net.awaronoob.energical.Energical;
import net.awaronoob.energical.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Energical.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ENERGICAL_MATERIALS_TAB = CREATIVE_MODE_TABS.register("energical_materials_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NEODYMITE_INGOT.get()))
                    .title(Component.translatable("creativetab." + Energical.MOD_ID + ".energical_materials_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_NEODYMITE.get());
                        output.accept(ModItems.NEODYMITE_INGOT.get());

                        output.accept(ModItems.UNPRESSED_FLUXXITE.get());

                        output.accept(ModItems.NEODYMITE_SHEET.get());
                        output.accept(ModItems.COPPER_SHEET.get());
                        output.accept(ModItems.IRON_SHEET.get());
                        output.accept(ModItems.NETHERITE_SHEET.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> ENERGICAL_BLOCKS_TAB = CREATIVE_MODE_TABS.register("energical_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.NEODYMITE_BLOCK.get()))
                    .withTabsBefore(ENERGICAL_MATERIALS_TAB.getId())
                    .title(Component.translatable("creativetab." + Energical.MOD_ID + ".energical_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.NEODYMITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_NEODYMITE_ORE.get());
                        output.accept(ModBlocks.RAW_NEODYMITE_BLOCK.get());
                        output.accept(ModBlocks.NEODYMITE_BLOCK.get());

                        output.accept(ModBlocks.FLUXXITE_BLOCK.get());


                    }).build());



    public static void register(IEventBus eventBus) {
        Energical.LOGGER.info("Registering creative mode tabs for " + Energical.MOD_ID);
        CREATIVE_MODE_TABS.register(eventBus);

    }

}
