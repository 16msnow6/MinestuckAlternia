package com.apocfarce.minestuck_alternia.world.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public final class AlterniaWoodsBiome extends Biome {
   public AlterniaWoodsBiome() {
      super((new Biome.Builder())
    		  .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
    		  .precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.1F).scale(0.2F)
    		  .temperature(0.95F)
    		  .downfall(0.8F)
    		  .waterColor(4159204)
    		  .waterFogColor(329011)
    		  .parent((String)null));
      this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
      this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
      DefaultBiomeFeatures.addCarvers(this);
      DefaultBiomeFeatures.addStructures(this);
      DefaultBiomeFeatures.addLakes(this);
      DefaultBiomeFeatures.addMonsterRooms(this);
      DefaultBiomeFeatures.addStoneVariants(this);
      DefaultBiomeFeatures.addOres(this);
      DefaultBiomeFeatures.addSedimentDisks(this);
      DefaultBiomeFeatures.func_222290_D(this);
      DefaultBiomeFeatures.addExtraDefaultFlowers(this);
      DefaultBiomeFeatures.addJungleGrass(this);
      DefaultBiomeFeatures.addMushrooms(this);
      DefaultBiomeFeatures.addReedsAndPumpkins(this);
      DefaultBiomeFeatures.addSprings(this);
      DefaultBiomeFeatures.addJunglePlants(this);
      DefaultBiomeFeatures.addFreezeTopLayer(this);
   }
   
	@Override
	public int getFoliageColor(BlockPos pos) {
		return(0xAA55EE);
	}
	@Override
	public int getGrassColor(BlockPos pos) {
		return(0x004433);
	}
}