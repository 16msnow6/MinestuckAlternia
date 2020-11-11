package com.apocfarce.minestuck_alternia.world.biome;

import com.apocfarce.minestuck_alternia.Minestuck_alternia;
import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBiomeProvider;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaBiomes {
	
	public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, Minestuck_alternia.MOD_ID);
	
	public static final RegistryObject<Biome> ALTERNIA_PLAINS = REGISTER.register("alternia_plains", AlterniaPlains::new);
	
	public static final RegistryObject<Biome> MIRRAGE_FOREST = REGISTER.register("mirrage_forest", MirrageWoodsBiome::new);
	public static final RegistryObject<Biome> PYRAL_FOREST = REGISTER.register("pyral_forest", PyralWoodsBiome::new);
	public static final RegistryObject<Biome> MIXED_FOREST = REGISTER.register("mixed_forest", MixedWoodsBiome::new);
	
	public static final RegistryObject<Biome> COLORED_DESERT = REGISTER.register("colored_desert", RainbowDesertBiome::new);
	
}