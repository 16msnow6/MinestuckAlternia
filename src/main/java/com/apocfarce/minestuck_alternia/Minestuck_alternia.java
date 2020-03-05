package com.apocfarce.minestuck_alternia;


import com.apocfarce.minestuck_alternia.Item.AlterniaItems;
import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import com.apocfarce.minestuck_alternia.world.DimensionsHandeler;
import com.apocfarce.minestuck_alternia.world.biome.AlterniaBiomeHandeler;
import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBioimeProviderHandeler;
import com.apocfarce.minestuck_alternia.world.gen.GenTypesHandeler;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFeatureHandeler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.feature.Feature;
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
	public static String MOD_ID = "minestuck_alternia";

    public Minestuck_alternia() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::SendInterModCom);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ReciveInterModCom);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void preInit(final FMLCommonSetupEvent event){
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
    }
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
    private void SendInterModCom(final InterModEnqueueEvent event){
    }
    private void ReciveInterModCom(final InterModProcessEvent event){

    }
    
    @Mod.EventBusSubscriber(modid = "minestuck_alternia", bus=Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeRegistryEvents{
        @SubscribeEvent
    	public static void registerDimensionTypes(final RegisterDimensionsEvent event) {
        	DimensionsHandeler.registerDimensionTypes();
        } 
 
	}


    @Mod.EventBusSubscriber(modid = "minestuck_alternia",bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            AlterniaBlocks.registerBlocks(blockRegistryEvent);
        } 
        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> ItemRegistryEvent) {
            AlterniaItems.registerItems(ItemRegistryEvent); 
        }
        @SubscribeEvent
        public static void onFeatureRegistry(final RegistryEvent.Register<Feature<?>> featureRegistryEvent) {
        	AlterniaFeatureHandeler.registerFeatures(featureRegistryEvent);
        }
        @SubscribeEvent
        public static void onDimensionRegistry(final RegistryEvent.Register<ModDimension> DimensionRegistryEvent) {
            DimensionsHandeler.registerDimensions(DimensionRegistryEvent); 
        }
        @SubscribeEvent
        public static void onGenTypeRegistry(final RegistryEvent.Register<ChunkGeneratorType<?, ?>> GenTypeRegistryEvent) {
        	GenTypesHandeler.registerChunkGenerators(GenTypeRegistryEvent);
        }
        @SubscribeEvent
        public static void onBiomeTypeRegistry(final RegistryEvent.Register<BiomeProviderType<?, ?>> biomeTypeRegistryEvent) {
        	AlterniaBioimeProviderHandeler.RegisterBiomeProviderTypes(biomeTypeRegistryEvent);
        }
        @SubscribeEvent
        public static void onBiomeRegistry(final RegistryEvent.Register<Biome> biomeRegistryEvent) {
        	AlterniaBiomeHandeler.RegisterBiomes(biomeRegistryEvent);
        }

        
    }

}
