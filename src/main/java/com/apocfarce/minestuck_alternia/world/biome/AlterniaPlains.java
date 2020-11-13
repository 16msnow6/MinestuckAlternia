package com.apocfarce.minestuck_alternia.world.biome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apocfarce.minestuck_alternia.Utils.MultipleFeatureChanceHelper;
import com.apocfarce.minestuck_alternia.world.gen.feature.AlterniaFeatureHandeler;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredRandomFeatureList;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public final class AlterniaPlains extends Biome {
	protected AlterniaPlains() {
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(Biome.RainType.RAIN)
				.category(Biome.Category.PLAINS)
				.depth(0.125F)
				.scale(0.05F)
				.temperature(0.8F)
				.downfall(0.4F)
				.waterColor(0x113355)
				.waterFogColor(0x115588)
				.parent((String)null));

      

		this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
		this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);

		List<ConfiguredRandomFeatureList<?>> biomeHives = new ArrayList<ConfiguredRandomFeatureList<?>>();
		
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.burgundyHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.burgundyHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.burgundyHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.bronzeHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.3F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.bronzeHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.3F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.bronzeHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.3F));

		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.goldHiveFeature1,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.goldHiveFeature2,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		biomeHives.add(new ConfiguredRandomFeatureList<NoFeatureConfig>(AlterniaFeatureHandeler.goldHiveFeature3,IFeatureConfig.NO_FEATURE_CONFIG,0.1F));
		
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(biomeHives, new ConfiguredFeature<NoFeatureConfig>(AlterniaFeatureHandeler.burgundyHiveFeature3, IFeatureConfig.NO_FEATURE_CONFIG)),Placement.CHANCE_HEIGHTMAP, new ChanceConfig(50)));

		AlterniaDefaultFeatures.addCarvers(this);
		AlterniaDefaultFeatures.addStoneVariants(this);
		AlterniaDefaultFeatures.addOres(this);
		
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.func_222283_Y(this);
		
		DefaultBiomeFeatures.addSedimentDisks(this);
		DefaultBiomeFeatures.addOakTreesFlowersGrass(this);
		DefaultBiomeFeatures.addMushrooms(this);
		DefaultBiomeFeatures.addReedsAndPumpkins(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);
		
	}
	@Override
	public int getFoliageColor(BlockPos pos) {
		return(0x9955DD);
	}
	@Override
	public int getGrassColor(BlockPos pos) {
		return(0x004411);
	}
}