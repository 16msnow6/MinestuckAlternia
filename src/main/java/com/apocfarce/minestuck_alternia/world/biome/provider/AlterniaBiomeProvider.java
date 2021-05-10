package com.apocfarce.minestuck_alternia.world.biome.provider;

import com.apocfarce.minestuck_alternia.world.biome.AlterniaBiomeFeatures;
import com.apocfarce.minestuck_alternia.world.biome.AlterniaBiomes;
import com.apocfarce.minestuck_alternia.world.gen.layer.AlterniaLayerUtil;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.layer.Layer;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.*;

public class AlterniaBiomeProvider extends BiomeProvider {
	private final Layer genBiomes;
	private static final Map<BiomeType, List<BiomeEntry>> biomes = new EnumMap<>(BiomeType.class);
	private static final Map<BiomeType, List<BiomeEntry>> unmodifiableBiomes = new EnumMap<>(BiomeType.class);
   	private static final Set<Biome> biomeSet = new HashSet<>();
   
	public AlterniaBiomeProvider(AlterniaBiomeProviderSettings settings) {
		super(Collections.unmodifiableSet(biomeSet));
		this.genBiomes = AlterniaLayerUtil.buildAlterniaProcedure(settings.getSeed(), settings.getWorldType(), settings.getGeneratorSettings());
	}
	
	public static void initBiomeList() {
		//removed as they add overworld features i dont want
		//addVanilla(Biomes.BADLANDS, BiomeType.DESERT, 10);
		//addVanilla(Biomes.DESERT,BiomeType.DESERT, 20);
		
		addBiome(AlterniaBiomes.MIRRAGE_FOREST.get(), BiomeType.COOL, 10);
		addBiome(AlterniaBiomes.PYRAL_FOREST.get(), BiomeType.COOL, 10);
		addBiome(AlterniaBiomes.MIXED_FOREST.get(), BiomeType.COOL, 10);
		addBiome(AlterniaBiomes.ALTERNIA_PLAINS.get(), BiomeType.WARM, 30);
		addBiome(AlterniaBiomes.VOLCANIC_FIELD.get(), BiomeType.WARM,10);
		addBiome(AlterniaBiomes.SHRUBLAND.get(), BiomeType.WARM,10);
		addBiome(AlterniaBiomes.COLORED_DESERT.get(), BiomeType.DESERT, 30);
	}
	
	private static void addVanilla(Biome biome, BiomeType type, int weight) {
		//This is needed so that alternia structures are generated correctly. (alternia biomes also has this, but elsewhere)
		// This will not have an effect on the biomes in the overworld
		AlterniaBiomeFeatures.addStructures(biome);
		AlterniaBiomeFeatures.addCarvers(biome);
		AlterniaBiomeFeatures.addOres(biome);
		addBiome(biome, type, weight);
	}
	
	private static void addBiome(Biome biome, BiomeType type, int weight) {
		biomes.computeIfAbsent(type, AlterniaBiomeProvider::makeList).add(new BiomeEntry(biome,weight));
		biomeSet.add(biome);
	}
	
	private static List<BiomeEntry> makeList(BiomeType type) {
		List<BiomeEntry> list = new ArrayList<>();
		unmodifiableBiomes.put(type, Collections.unmodifiableList(list));
		return list;
	}
	
	public static List<BiomeEntry> getBiomes(BiomeType biomeType) {
		
		return unmodifiableBiomes.get(biomeType);
	}
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return genBiomes.func_215738_a(x, z);
	}
}