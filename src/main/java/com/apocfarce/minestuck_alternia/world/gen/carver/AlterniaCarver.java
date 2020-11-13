package com.apocfarce.minestuck_alternia.world.gen.carver;

import java.util.function.Function;

import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class AlterniaCarver extends CaveWorldCarver {

	   public static final WorldCarver<ProbabilityConfig> CAVE = register("cave", new AlterniaCarver(ProbabilityConfig::deserialize, 256));

	public AlterniaCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_, int p_i49929_2_) {
		super(p_i49929_1_, p_i49929_2_);
	    this.carvableBlocks = ImmutableSet.of(
	    		AlterniaBlocks.darkStone,AlterniaBlocks.redRock,
	    		Blocks.DIRT,Blocks.GRASS_BLOCK,Blocks.SAND, Blocks.COARSE_DIRT, Blocks.GRAVEL,Blocks.PODZOL,
	    		Blocks.RED_CONCRETE_POWDER,Blocks.PINK_CONCRETE_POWDER,Blocks.CYAN_CONCRETE_POWDER,Blocks.LIGHT_BLUE_CONCRETE_POWDER,Blocks.RED_CONCRETE,Blocks.PINK_CONCRETE,Blocks.CYAN_CONCRETE,Blocks.LIGHT_BLUE_CONCRETE
	    		);
	    this.carvableFluids = ImmutableSet.of(Fluids.WATER);
	}
	//COPPIED FROM WORLD CARVER BECAUSE I'M TO LAZY TO MAKE A REAL REGISTRY ATM
	//PROBIBLY WILL REGRET THIS LATER
	private static <C extends ICarverConfig, F extends WorldCarver<C>> F register(String key, F carver) {
		return (F)(Registry.<WorldCarver<?>>register(Registry.CARVER, key, carver));
	}


}
