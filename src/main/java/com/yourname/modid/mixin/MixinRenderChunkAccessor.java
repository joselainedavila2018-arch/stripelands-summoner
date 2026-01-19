package com.yourname.modid.mixin;

import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@SideOnly(Side.CLIENT)
@Mixin(RenderChunk.class)
public interface MixinRenderChunkAccessor {
    @Accessor("position")
    BlockPos.MutableBlockPos getPosition();

    @Accessor("boundingBox")
    AxisAlignedBB getBoundingBox();

    @Accessor("boundingBox")
    void setBoundingBox(AxisAlignedBB axisAlignedBB);

    @Accessor("mapEnumFacing")
    BlockPos.MutableBlockPos[] getMapEnumFacing();

    @Invoker("initModelviewMatrix")
    void initModelviewMatrix();

    @Invoker("stopCompileTask")
    void stopCompileTask();
}
