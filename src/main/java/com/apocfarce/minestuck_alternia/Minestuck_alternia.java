package main.java.com.apocfarce.minestuck_alternia;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.apocfarce.minestuck_alternia.Item.AlterniaItems;
import main.java.com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import main.java.com.apocfarce.minestuck_alternia.world.AlterniaDimensionsHandeler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("minestuck_alternia")
public class Minestuck_alternia {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public Minestuck_alternia() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::SendInterModCom);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ReciveInterModCom);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void preInit(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    private void SendInterModCom(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }
    private void ReciveInterModCom(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        //LOGGER.info("Got IMC {}", event.getIMCStream().
        //        map(m->m.getMessageSupplier().get()).
        //        collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    

	@Mod.EventBusSubscriber(modid = "minestuck_alternia", bus=Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeRegistryEvents{
        @SubscribeEvent
    	public static void registerDimensionTypes(final RegisterDimensionsEvent event) {
        	LOGGER.info("HELLO from Register DimensionType");
        	AlterniaDimensionsHandeler.registerDimensionTypes();
        }
	}
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
            AlterniaBlocks.registerBlocks(blockRegistryEvent);
        } 
        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            LOGGER.info("HELLO from Register Item");
            AlterniaItems.registerItems(itemRegistryEvent); 
        }
        @SubscribeEvent
        public static void onDimensionRegistry(final RegistryEvent.Register<ModDimension> dimensionRegistryEvent) {
            LOGGER.info("HELLO from Register Dimension");
        	AlterniaDimensionsHandeler.registerDimensions(dimensionRegistryEvent);
        	AlterniaDimensionsHandeler.registerDimensionTypes();
        }
    }

}
