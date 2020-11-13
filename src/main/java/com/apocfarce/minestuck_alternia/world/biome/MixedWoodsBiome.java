package com.apocfarce.minestuck_alternia.world.biome;

import java.util.ArrayList;
import java.util.List;

import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFeatureHandeler;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredRandomFeatureList;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MixedWoodsBiome extends Biome {

	protected MixedWoodsBiome() {
		super(new Biome.Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT,SurfaceBuilder.GRASS_DIRT_SAND_CONFIG)
				.precipitation(Biome.RainType.RAIN)
				.category(Biome.Category.FOREST)
				.temperature(0.5F)
				.depth(0.2F)
				.scale(0.2F)
				.temperature(0.25F)
				.downfall(0.8F)
				.waterColor(0x113355)
				.waterFogColor(0x115588)
				.parent((String)null)
				);
		
		List<ConfiguredRandomFeatureList<?>> biomeHives = new ArrayList<ConfiguredRandomFeatureList<?>>();
		
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.jadeHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.jadeHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.jadeHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.tealHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.tealHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.3F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.tealHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));

		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.ceruleanHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.3F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.ceruleanHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.3F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.ceruleanHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));

		
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(biomeHives, new ConfiguredFeature<NoFeatureConfig>(AlterniaFeatureHandeler.tealHiveFeature1, IFeatureConfig.NO_FEATURE_CONFIG)),Placement.CHANCE_HEIGHTMAP, new ChanceConfig(100)));

		
		
		AlterniaDefaultFeatures.addCarvers(this);
	      DefaultBiomeFeatures.addStructures(this);
	      DefaultBiomeFeatures.addLakes(this);
	      DefaultBiomeFeatures.addMonsterRooms(this);
	      AlterniaDefaultFeatures.addStoneVariants(this);
	      AlterniaDefaultFeatures.addOres(this);
	      DefaultBiomeFeatures.addSedimentDisks(this);
	      
	      
	      addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,new MultipleRandomFeatureConfig(new Feature[]{Feature.FANCY_TREE,AlterniaFeatureHandeler.pyralTreeFeature,AlterniaFeatureHandeler.pyralTreeLargeFeature}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG,IFeatureConfig.NO_FEATURE_CONFIG,IFeatureConfig.NO_FEATURE_CONFIG},new float[]{0.05F,0.34F,0.34F},AlterniaFeatureHandeler.pyralTreeFeature, IFeatureConfig.NO_FEATURE_CONFIG),Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
	      addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,new MultipleRandomFeatureConfig(new Feature[]{Feature.FANCY_TREE,AlterniaFeatureHandeler.mirrageTreeFeature,AlterniaFeatureHandeler.mirrageTreeLargeFeature}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG,IFeatureConfig.NO_FEATURE_CONFIG,IFeatureConfig.NO_FEATURE_CONFIG},new float[]{0.05F,0.34F,0.34F},AlterniaFeatureHandeler.mirrageTreeFeature, IFeatureConfig.NO_FEATURE_CONFIG),Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1F, 1)));

	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(AlterniaBlocks.pyralGrass.getDefaultState()), Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)));

	      DefaultBiomeFeatures.addExtraDefaultFlowers(this);
	      DefaultBiomeFeatures.addJungleGrass(this);
	      DefaultBiomeFeatures.addMushrooms(this);
	      DefaultBiomeFeatures.addSprings(this);
	      DefaultBiomeFeatures.addFreezeTopLayer(this);
		

		
	}
	@Override
	public int getFoliageColor(BlockPos pos) {
		return(0xAA55EE);
	}
	@Override
	public int getGrassColor(BlockPos pos) {
		return(0x004455);
	}


}
