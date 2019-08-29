package main.java.com.apocfarce.minestuck_alternia.world.biome;

import net.minecraft.world.gen.area.IArea;

public final class BiomeProcedure<A extends IArea> {
    public final A noise;
    public final A block;

    private BiomeProcedure(A noise, A block) {
        this.noise = noise;
        this.block = block;
    }

}