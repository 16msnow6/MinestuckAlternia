package com.apocfarce.minestuck_alternia.world.gen.layer;

import java.util.function.LongFunction;

import com.google.common.collect.ImmutableList;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.AddIslandLayer;
import net.minecraft.world.gen.layer.AddSnowLayer;
import net.minecraft.world.gen.layer.EdgeBiomeLayer;
import net.minecraft.world.gen.layer.EdgeLayer;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.ShoreLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

public class AlterniaLayerUtil extends LayerUtil {

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> buildAlterniaProcedure(WorldType worldTypeIn, OverworldGenSettings settings, LongFunction<C> contextFactory) {
		IAreaFactory<T> iareafactory = AlterniaSeedLayer.INSTANCE.apply(contextFactory.apply(1L));
		//add heat map
		iareafactory = AddSnowLayer.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
		//
		iareafactory = WarmWorldLayer.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
		//
		iareafactory = EdgeLayer.CoolWarm.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
		iareafactory = EdgeLayer.HeatIce.INSTANCE.apply(contextFactory.apply(2L), iareafactory);

      
		//add biomes
		iareafactory = (new AlterniaBiomeLayer(worldTypeIn, settings)).apply(contextFactory.apply(200L), iareafactory);

		iareafactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2002L), iareafactory);
		iareafactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2003L), iareafactory);
		
		iareafactory = AlterniaEdgeBiomeLayer.INSTANCE.apply(contextFactory.apply(1000), iareafactory);
		IAreaFactory<T> lvt_8_1_= iareafactory;
		

		//biome size stuff		
		int i = 4;
		if (settings != null) {
			i = settings.getBiomeSize();
		}

		if (worldTypeIn == WorldType.LARGE_BIOMES) {
			i = 6;
		}
		i = getModdedBiomeSize(worldTypeIn, i);
		for(int k = 0; k < i; ++k) {
			lvt_8_1_ = ZoomLayer.NORMAL.apply((IExtendedNoiseRandom<T>)contextFactory.apply((long)(1000 + k)), lvt_8_1_);
			if (k == 0) {
				lvt_8_1_ = AddIslandLayer.INSTANCE.apply((IExtendedNoiseRandom<T>)contextFactory.apply(3L), lvt_8_1_);
			}

			if (k == 1 || i == 1) {
				lvt_8_1_ = ShoreLayer.INSTANCE.apply((IExtendedNoiseRandom<T>)contextFactory.apply(1000L), lvt_8_1_);
			}
		}

		IAreaFactory<T> iareafactory5 = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10L), lvt_8_1_);
		return ImmutableList.of(lvt_8_1_, iareafactory5, lvt_8_1_);
	}

	public static Layer[] buildAlterniaProcedure(long seed, WorldType typeIn, OverworldGenSettings settings) {
	
		ImmutableList<IAreaFactory<LazyArea>> immutablelist = buildAlterniaProcedure(typeIn, settings, (p_215737_2_) -> {
			return new LazyAreaLayerContext(25, seed, p_215737_2_);
		});
		Layer layer = new Layer(immutablelist.get(0));
		Layer layer1 = new Layer(immutablelist.get(1));
		Layer layer2 = new Layer(immutablelist.get(2));
		return new Layer[]{layer, layer1, layer2};
	}
   
   
   
	protected static boolean isOcean(int biomeIn) {
		return biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN || biomeIn == DEEP_WARM_OCEAN || biomeIn == DEEP_LUKEWARM_OCEAN || biomeIn == DEEP_OCEAN || biomeIn == DEEP_COLD_OCEAN || biomeIn == DEEP_FROZEN_OCEAN;
	}


}