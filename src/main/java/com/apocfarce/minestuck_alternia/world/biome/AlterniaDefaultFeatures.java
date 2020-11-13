package com.apocfarce.minestuck_alternia.world.biome;

import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import com.apocfarce.minestuck_alternia.world.gen.carver.AlterniaCarver;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFillerBlockTypes;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;

public class AlterniaDefaultFeatures {

	public static void addStoneVariants(Biome biomeIn) {
		biomeIn.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, AlterniaBlocks.redRock.getDefaultState(), 50), Placement.COUNT_RANGE, new CountRangeConfig(10,0,0,256)));
	}
	public static void addOres(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, Blocks.COAL_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, Blocks.IRON_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, Blocks.GOLD_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, Blocks.REDSTONE_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 16)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, Blocks.DIAMOND_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 16)));
		biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, Blocks.LAPIS_ORE.getDefaultState(), 7), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(1, 16, 16)));
	}
	
	public static void addCarvers(Biome biomeIn) {
		biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(AlterniaCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(AlterniaCarver.CANYON, new ProbabilityConfig(0.02F)));
	}
}
