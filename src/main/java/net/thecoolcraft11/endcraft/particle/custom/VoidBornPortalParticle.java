package net.thecoolcraft11.endcraft.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VoidBornPortalParticle extends TextureSheetParticle {
    private final double xStart;
    private final double yStart;
    private final double zStart;

    protected VoidBornPortalParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet, double dx, double dy, double dz) {

        super(pLevel, pX, pY, pZ, dx, dy, dz);
        this.xStart = this.x;
        this.yStart = this.y;
        this.zStart = this.z;
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
    public void move(double pX, double pY, double pZ) {
        this.setBoundingBox(this.getBoundingBox().move(pX, pY, pZ));
        this.setLocationFromBoundingbox();
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.x = this.xStart + this.xd * (double)f2;
            this.y = this.yStart + this.yd * (double)f2 + (double)(1.0F - f);
            this.z = this.zStart + this.zd * (double)f2;
            this.setPos(this.x, this.y, this.z);
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
            return new VoidBornPortalParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
