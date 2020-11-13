package com.apocfarce.minestuck_alternia.world.biome;

import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBiomeProvider;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaBiomeHandeler {
//	public static Biome AlterniaTest;
	public static Biome AlterniaPlains;
	
	public static Biome mirrageForest;
	public static Biome pyralForest;
	public static Biome mixedForest;
	
	public static Biome ColoredDesert;
	
	public static void RegisterBiomes(Register<Biome> event) {
		IForgeRegistry<Biome> registry = event.getRegistry();
//		AlterniaTest =register(registry,new ResourceLocation("minestuck_alternia:alternia_test"), new AlterniaTestBiome(),BiomeType.WARM,10);
		mirrageForest = register(registry,new ResourceLocation("minestuck_alternia:mirrage_forest"), new MirrageWoodsBiome(),BiomeType.WARM,10);
		pyralForest = register(registry,new ResourceLocation("minestuck_alternia:pyral_forest"), new PyralWoodsBiome(),BiomeType.WARM,10);
		AlterniaPlains = register(registry,new ResourceLocation("minestuck_alternia:alternia_plains"), new AlterniaPlains(),BiomeType.COOL,30);
		mixedForest = register(registry,new ResourceLocation("minestuck_alternia:mixed_forest"), new MixedWoodsBiome(),BiomeType.WARM,10);
		
		ColoredDesert = register(registry,new ResourceLocation("minestuck_alternia:colored_desert"), new RainbowDesertBiome(),BiomeType.DESERT,40);
		//coarseDesert
		//
		//volcanicWastes
		//redMountians
		
	}
	public static Biome registerEdgeBiome(IForgeRegistry<Biome> registry,ResourceLocation name,Biome biome,Biome parent) {
		biome.setRegistryName(name);
		registry.register(biome);
		return biome;
	}
	public static Biome register(IForgeRegistry<Biome> registry,ResourceLocation name,Biome biome,BiomeType type,int weight) {
		biome.setRegistryName(name);
		registry.register(biome);
		AlterniaBiomeProvider.AddBiome(biome, type, weight);
		return biome;
	}
	

}
