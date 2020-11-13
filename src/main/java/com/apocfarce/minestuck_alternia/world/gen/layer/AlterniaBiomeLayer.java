package com.apocfarce.minestuck_alternia.world.gen.layer;

import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBiomeProvider;
import com.google.common.collect.ImmutableList;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class AlterniaBiomeLayer implements IC0Transformer {
  private static final int MUSHROOM_FIELDS = Registry.BIOME.getId(Biomes.MUSHROOM_FIELDS);

   @SuppressWarnings("unchecked")
   private java.util.List<BiomeEntry>[] biomes = new java.util.ArrayList[BiomeType.values().length];
   private final OverworldGenSettings settings;

   public AlterniaBiomeLayer(WorldType p_i48641_1_, OverworldGenSettings p_i48641_2_) {
      for (BiomeType type : BiomeType.values()) {
         ImmutableList<BiomeEntry> biomesToAdd = AlterniaBiomeProvider.getBiomes(type);
         int idx = type.ordinal();

         if (biomes[idx] == null) biomes[idx] = new java.util.ArrayList<BiomeEntry>();
         if (biomesToAdd != null) biomes[idx].addAll(biomesToAdd);
      }

      int desertIdx = BiomeType.DESERT.ordinal();



      this.settings = p_i48641_2_;

   }

   public int apply(INoiseRandom context, int value) {
      if (this.settings != null && this.settings.getBiomeId() >= 0) {
         return this.settings.getBiomeId();
      } else {
         int i = (value & 3840) >> 8;
         value = value & -3841;
         if (!AlterniaLayerUtil.isOcean(value) && value != MUSHROOM_FIELDS) {
            switch(value) {
            case 1:
               return Registry.BIOME.getId(getWeightedBiomeEntry(BiomeType.DESERT, context).biome);
            case 2:
               return Registry.BIOME.getId(getWeightedBiomeEntry(BiomeType.WARM, context).biome);
            case 3:
               return Registry.BIOME.getId(getWeightedBiomeEntry(BiomeType.COOL, context).biome);
            case 4:
               return Registry.BIOME.getId(getWeightedBiomeEntry(BiomeType.ICY, context).biome);
            default:
               return MUSHROOM_FIELDS;
            }
         } else {
            return value;
         }
      }
   }

   protected BiomeEntry getWeightedBiomeEntry(BiomeType type, INoiseRandom context) {
      java.util.List<BiomeEntry> biomeList = biomes[type.ordinal()];
      int totalWeight = net.minecraft.util.WeightedRandom.getTotalWeight(biomeList);
      int weight = BiomeManager.isTypeListModded(type)?context.random(totalWeight):context.random(totalWeight / 10) * 10;
      return (BiomeEntry)net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
   }
}  