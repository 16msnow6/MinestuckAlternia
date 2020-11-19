package com.apocfarce.minestuck_alternia.world.gen.feature;


import com.apocfarce.minestuck_alternia.Minestuck_alternia;
import com.apocfarce.minestuck_alternia.world.gen.feature.structure.HiveStructure;
import com.apocfarce.minestuck_alternia.world.gen.feature.structure.HiveStructureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlterniaFeatures {
	
	public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, Minestuck_alternia.MOD_ID);
	
	public static RegistryObject<Structure<HiveStructureConfig>> HIVE = REGISTER.register("hive", () -> new HiveStructure(HiveStructureConfig::deserialize));
	
}
