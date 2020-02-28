package com.apocfarce.minestuck_alternia.world.biome;

import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBiomeProvider;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaBiomeHandeler {
	public static Biome AlterniaTest;
	public static Biome AlterniaDeepForest;
	public static Biome AlterniaWoods;
	public static Biome AlterniaPlains;
	
	
	public static void RegisterBiomes(Register<Biome> event) {
		IForgeRegistry<Biome> registry = event.getRegistry();
//		AlterniaTest =register(registry,new ResourceLocation("minestuck_alternia:alternia_test"), new AlterniaTestBiome(),BiomeType.WARM,10);
		AlterniaDeepForest = register(registry,new ResourceLocation("minestuck_alternia:alternia_deep_forest"), new AlterniaDeepForestBiome(),BiomeType.WARM,30);
		AlterniaPlains = register(registry,new ResourceLocation("minestuck_alternia:lternia_plains"), new AlterniaPlains(),BiomeType.COOL,30);
		AlterniaWoods = registerEdgeBiome(registry,new ResourceLocation("minestuck_alternia:alternia_woods"),new AlterniaWoodsBiome(),AlterniaDeepForest);
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
