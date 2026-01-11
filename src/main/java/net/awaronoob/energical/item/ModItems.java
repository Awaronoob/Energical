package net.awaronoob.energical.item;

import net.awaronoob.energical.Energical;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Energical.MOD_ID);


    public static final RegistryObject<Item> UNPRESSED_FLUXXITE = ITEMS.register("fluxxite_brick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_NEODYMITE = ITEMS.register("raw_neodymite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEODYMITE_BAR = ITEMS.register("neodymite_bar",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
