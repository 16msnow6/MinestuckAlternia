package com.apocfarce.minestuck_alternia.world.gen.feature;


import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaFeatureHandeler {
	public static Feature<NoFeatureConfig> PyralTreeFeature=new PyralTreeFeature(NoFeatureConfig::deserialize, false);
	public static Feature<NoFeatureConfig> PyralTreeLargeFeature=new PyralTreeLargeFeature(NoFeatureConfig::deserialize, false);


	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
		IForgeRegistry<Feature<?>> registry = event.getRegistry();
		
		PyralTreeFeature = register(registry,"minestuck_alternia:pyral_tree",PyralTreeFeature);
		PyralTreeLargeFeature = register(registry,"minestuck_alternia:pyral_tree_large",PyralTreeLargeFeature);
		
	}

	
	
	private static<FC extends IFeatureConfig> Feature<FC> register(IForgeRegistry<Feature<?>> registry,ResourceLocation key, Feature<FC> featureIn){
		registry.register(featureIn.setRegistryName(key));
		return(featureIn);
	}	
	private static<FC extends IFeatureConfig> Feature<FC> register(IForgeRegistry<Feature<?>> registry,String key, Feature<FC> featureIn){
		return register(registry,new ResourceLocation(key), featureIn);
	}
}
