package com.apocfarce.minestuck_alternia.world.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;
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
//     this.addStructure(Feature.VILLAGE, new VillageConfig("village/plains/town_centers", 6));
//      this.addStructure(Feature.PILLAGER_OUTPOST, new PillagerOutpostConfig(0.004D));
      this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
      this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
      DefaultBiomeFeatures.addCarvers(this);
      DefaultBiomeFeatures.addStructures(this);
      DefaultBiomeFeatures.addLakes(this);
      DefaultBiomeFeatures.addMonsterRooms(this);
      DefaultBiomeFeatures.func_222283_Y(this);
      DefaultBiomeFeatures.addStoneVariants(this);
      DefaultBiomeFeatures.addOres(this);
      DefaultBiomeFeatures.addSedimentDisks(this);
      DefaultBiomeFeatures.func_222299_R(this);
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