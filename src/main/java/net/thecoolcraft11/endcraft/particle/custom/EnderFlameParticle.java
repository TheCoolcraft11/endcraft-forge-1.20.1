package net.thecoolcraft11.endcraft.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnderFlameParticle extends TextureSheetParticle {


    protected EnderFlameParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet, double dx, double dy, double dz) {

        super(pLevel, pX, pY, pZ, dx, dy, dz);
        this.friction = 0.8F;
        this.xd = dx;
        this.yd = dy;
        this.zd = dz;
        this.quadSize *= 0.85F;
        this.lifetime = 20;
        this.setSpriteFromAge(spriteSet);


        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;

        this.setSpriteFromAge(spriteSet);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.fadeOut();
        if (this.age++ >= this.lifetime) {
            this.remove();
        }

    }
    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new EnderFlameParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
