package main.java.com.apocfarce.minestuck_alternia.world.biome.provider;

import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;

public class AlterniaBiomeProviderType<C extends IBiomeProviderSettings, T extends BiomeProvider> extends BiomeProviderType<C,T> {
   public static final AlterniaBiomeProviderType<AlterniaBiomeSettings, AlterniaBiomeProvider> ALTERNIA = register("minestuck_alternia:alternia", AlterniaBiomeProvider::new, AlterniaBiomeSettings::new);
   
   
   
   public AlterniaBiomeProviderType(Function<C, T> factoryIn, Supplier<C> settingsFactoryIn, ResourceLocation keyIn) {
	  super(factoryIn,settingsFactoryIn,keyIn);
   }

   public static <C extends IBiomeProviderSettings, T extends BiomeProvider> AlterniaBiomeProviderType<C, T> register(String key, Function<C, T> factory, Supplier<C> settingsFactory) {
      ResourceLocation resourcelocation = new ResourceLocation(key);
      AlterniaBiomeProviderType<C, T> biomeprovidertype = new AlterniaBiomeProviderType<>(factory, settingsFactory, resourcelocation);
      IRegistry.field_212625_n.put(resourcelocation, biomeprovidertype);
      return biomeprovidertype;
   }



}