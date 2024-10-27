package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_entity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class orb {
    public orb(@NotNull PoseStack matrices,
                       @NotNull MultiBufferSource vertexConsumers,
                       int light,
                       @NotNull Entity entity){
        if (entity instanceof nightmare_entity entity1){
            float s = 360-entity.tickCount;
            float r = s;
            r /= 360;
            s /= 120;
            matrices.scale(s, s, s);

            {
                matrices.pushPose();
                this.black_hold(matrices,vertexConsumers,light,0,0,entity,r);
                matrices.popPose();
            }
            {
                matrices.pushPose();
                this.renderCircle3(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                matrices.popPose();
            }
            {
                matrices.pushPose();
                this.renderCircle4(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                matrices.popPose();
            }
            {
                matrices.pushPose();
                this.renderCircle6(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                matrices.popPose();
            }
            {
                matrices.pushPose();
                this.renderCircle5(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                matrices.popPose();
            }
            {
                matrices.pushPose();
                this.renderCircle7(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                matrices.popPose();
            }
            {
                matrices.pushPose();
                this.renderCircle8(matrices,vertexConsumers,light,0,0,0,0.2f,entity);
                matrices.popPose();
            }
        }
    }
    public void renderCircle3(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 16; // 垂直段数
        int horizontalSegments = 16; // 水平段数

        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void black_hold(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,Entity entity,float rgb) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.t());
        int segments = 64; // 圆的段数
        float radius = 0.5f; // 圆的半径
        // poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 3)));
        poseStack.mulPose(Axis.ZP.rotationDegrees((float) (entity.tickCount * 3)));
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount * 3)));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, 0).setColor(rgb, 0, 0, rgb).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, 0).setColor(rgb, 0, 0, rgb).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
        }
    }
    public void renderCircle6(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 4; // 垂直段数
        int horizontalSegments = 4; // 水平段数
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle5(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 8; // 垂直段数
        int horizontalSegments = 8; // 水平段数
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle4(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 22; // 垂直段数
        int horizontalSegments = 22; // 水平段数
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle7(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 24; // 垂直段数
        int horizontalSegments = 24; // 水平段数
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle8(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 2; // 垂直段数
        int horizontalSegments = 2; // 水平段数
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
}
