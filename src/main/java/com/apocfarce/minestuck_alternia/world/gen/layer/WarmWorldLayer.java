package com.apocfarce.minestuck_alternia.world.gen.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public enum WarmWorldLayer  implements IC0Transformer {
    INSTANCE;

    public int apply(INoiseRandom context, int value) {
       if (value!=1 && context.random(3) == 0) {
          value=1;
       }
       return value;
    }
 }
