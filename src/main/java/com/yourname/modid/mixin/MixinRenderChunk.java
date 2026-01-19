package com.yourname.modid.mixin;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.nio.FloatBuffer;

@SideOnly(Side.CLIENT)
@Mixin(RenderChunk.class)
public class MixinRenderChunk {
    /**
     * @author
     */
    @Overwrite
    private void preRenderBlocks(BufferBuilder bufferBuilderIn, BlockPos pos)
    {
        bufferBuilderIn.begin(7, DefaultVertexFormats.BLOCK);
        bufferBuilderIn.setTranslation((float)(-pos.getX()), (float)(-pos.getY()), (float)(-pos.getZ()));
    }

    @Overwrite
    public void setPosition(int p_189562_1_, int p_189562_2_, int p_189562_3_) {
        if (p_189562_1_ != ((MixinRenderChunkAccessor)this).getPosition().getX() || p_189562_2_ != ((MixinRenderChunkAccessor)this).getPosition().getY() || p_189562_3_ != ((MixinRenderChunkAccessor)this).getPosition().getZ()) {
            ((MixinRenderChunkAccessor)this).stopCompileTask();
            ((MixinRenderChunkAccessor)this).getPosition().setPos(p_189562_1_, p_189562_2_, p_189562_3_);
            ((MixinRenderChunkAccessor)this).setBoundingBox(new AxisAlignedBB((float)p_189562_1_, (float)p_189562_2_, (float)p_189562_3_, (float)(p_189562_1_ + 16), (float)(p_189562_2_ + 16), (float)(p_189562_3_ + 16)));

            for(EnumFacing enumfacing : EnumFacing.values()) {
                ((MixinRenderChunkAccessor)this).getMapEnumFacing()[enumfacing.ordinal()].setPos(((MixinRenderChunkAccessor)this).getPosition()).move(enumfacing, 16);
            }

            ((MixinRenderChunkAccessor)this).initModelviewMatrix();
        }

    }
}
