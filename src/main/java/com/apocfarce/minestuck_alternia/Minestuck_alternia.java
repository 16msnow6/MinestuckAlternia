package main.java.com.apocfarce.minestuck_alternia;


import main.java.com.apocfarce.minestuck_alternia.Item.AlterniaItems;
import main.java.com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import main.java.com.apocfarce.minestuck_alternia.world.AlterniaDimensionsHandeler;
import main.java.com.apocfarce.minestuck_alternia.world.biome.AlterniaBiomeHandeler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
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
	public static final String MODID = "minestuck_alternia";
	

    public Minestuck_alternia() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::SendInterModCom);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ReciveInterModCom);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void preInit(final FMLCommonSetupEvent event) {}
    private void doClientStuff(final FMLClientSetupEvent event) {}
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
    private void SendInterModCom(final InterModEnqueueEvent event){}
    private void ReciveInterModCom(final InterModProcessEvent event){}
    
    @Mod.EventBusSubscriber(modid = "minestuck_alternia", bus=Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeRegistryEvents{
        @SubscribeEvent
    	public static void registerDimensionTypes(final RegisterDimensionsEvent event) {
        	System.out.println("HELLO from Register DimensionType");
        	AlterniaDimensionsHandeler.registerDimensionTypes();
        }
	}
    
    
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

            System.out.println("HELLO from Register Block");
            AlterniaBlocks.registerBlocks(blockRegistryEvent);
        } 
        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            System.out.println("HELLO from Register Item");
            AlterniaItems.registerItems(itemRegistryEvent); 
        }
        @SubscribeEvent
        public static void onDimensionRegistry(final RegistryEvent.Register<ModDimension> dimensionRegistryEvent) {
            System.out.println("HELLO from Register Dimension");
        	AlterniaDimensionsHandeler.registerDimensions(dimensionRegistryEvent);
        	AlterniaDimensionsHandeler.registerDimensionTypes();
        }
        @SubscribeEvent
        public static void onBiomeRegistry(final RegistryEvent.Register<Biome> biomeRegistryEvent) {
        	System.out.println("HELLO from Register Biome");
        	AlterniaBiomeHandeler.registerBiomes(biomeRegistryEvent);
        }

    }

}
