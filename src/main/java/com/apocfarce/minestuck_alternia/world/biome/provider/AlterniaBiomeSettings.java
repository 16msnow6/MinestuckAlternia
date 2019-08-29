package main.java.com.apocfarce.minestuck_alternia.world.biome.provider;

import main.java.com.apocfarce.minestuck_alternia.world.gen.AlterniaGenSettings;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.storage.WorldInfo;

public class AlterniaBiomeSettings implements IBiomeProviderSettings {
   private WorldInfo worldInfo;
   private AlterniaGenSettings generatorSettings;

   public AlterniaBiomeSettings setWorldInfo(WorldInfo info) {
      this.worldInfo = info;
      return this;
   }

   public AlterniaBiomeSettings setGeneratorSettings(AlterniaGenSettings settings) {
      this.generatorSettings = settings;
      return this;
   }

   public WorldInfo getWorldInfo() {
      return this.worldInfo;
   }

   public AlterniaGenSettings getGeneratorSettings() {
      return this.generatorSettings;
   }
}