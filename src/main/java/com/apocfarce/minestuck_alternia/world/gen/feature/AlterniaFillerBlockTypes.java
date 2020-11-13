package com.apocfarce.minestuck_alternia.world.gen.feature;

import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;

import net.minecraft.world.gen.feature.OreFeatureConfig;

public class AlterniaFillerBlockTypes {
	public static final OreFeatureConfig.FillerBlockType AlterniaNaturalStone = OreFeatureConfig.FillerBlockType.create("alternia_natural_stone", "alternia_natural_stone", state -> state.getBlock() == AlterniaBlocks.darkStone);
}
