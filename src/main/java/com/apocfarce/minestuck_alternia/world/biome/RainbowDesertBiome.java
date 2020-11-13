package com.apocfarce.minestuck_alternia.world.biome;

import java.util.ArrayList;
import java.util.List;

import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFeatureHandeler;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFillerBlockTypes;
import com.apocfarce.minestuck_alternia.world.gen.surfacebuilder.SurfaceBuilderHandeler;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredRandomFeatureList;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class RainbowDesertBiome extends Biome {
	protected RainbowDesertBiome() {
		super(new Biome.Builder()
				.surfaceBuilder(SurfaceBuilderHandeler.ColoredSands,SurfaceBuilder.SAND_CONFIG)
				.precipitation(Biome.RainType.NONE)
				.category(Biome.Category.DESERT)
				.temperature(0.5F)
				.depth(0.125F)
				.scale(0.05F)
				.temperature(2.0F)
				.downfall(0.0F)
				.waterColor(0x113355)
				.waterFogColor(0x115588)
				.parent((String)null)
				);
		
		List<ConfiguredRandomFeatureList<?>> biomeHives = new ArrayList<ConfiguredRandomFeatureList<?>>();
		
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.indigoHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.indigoHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.indigoHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));

		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.purpleHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.purpleHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.purpleHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));

		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(biomeHives, new ConfiguredFeature<NoFeatureConfig>(AlterniaFeatureHandeler.indigoHiveFeature1, IFeatureConfig.NO_FEATURE_CONFIG)),Placement.CHANCE_HEIGHTMAP, new ChanceConfig(50)));

		//this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 256)));
	      
		this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(AlterniaFillerBlockTypes.AlterniaNaturalStone, AlterniaBlocks.redRock.getDefaultState(), 50), Placement.COUNT_RANGE, new CountRangeConfig(10,0,0,256)));
	
		
		
		
	
		AlterniaDefaultFeatures.addCarvers(this);
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		AlterniaDefaultFeatures.addStoneVariants(this);
		AlterniaDefaultFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		
	      

		DefaultBiomeFeatures.addExtraDefaultFlowers(this);
		DefaultBiomeFeatures.addJungleGrass(this);
		DefaultBiomeFeatures.addMushrooms(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);
			
		
		
	}
	@Override
	public int getFoliageColor(BlockPos pos) {
		return(0x666666);
	}
	@Override
	public int getGrassColor(BlockPos pos) {
		return(0x666666);
	}


}
