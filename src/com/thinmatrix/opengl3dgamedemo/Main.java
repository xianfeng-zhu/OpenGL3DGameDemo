package com.thinmatrix.opengl3dgamedemo;

import com.thinmatrix.opengl3dgamedemo.entities.Camera;
import com.thinmatrix.opengl3dgamedemo.entities.Entity;
import com.thinmatrix.opengl3dgamedemo.models.TextureModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.DisplayManager;
import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;
import com.thinmatrix.opengl3dgamedemo.models.RawModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.Renderer;
import com.thinmatrix.opengl3dgamedemo.shader.StaticShader;
import com.thinmatrix.opengl3dgamedemo.textures.ModelTexture;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader staticShader = new StaticShader();
        Renderer renderer = new Renderer(staticShader);

        // 顶点坐标
        float[] vertices = {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0,

                -0.5f, 0.5f, 1,
                -0.5f, -0.5f, 1,
                0.5f, -0.5f, 1,
                0.5f, 0.5f, 1,

                0.5f, 0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, -0.5f, 1,
                0.5f, 0.5f, 1,

                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                -0.5f, -0.5f, 1,
                -0.5f, 0.5f, 1,

                -0.5f, 0.5f, 1,
                -0.5f, 0.5f, 0,
                0.5f, 0.5f, 0,
                0.5f, 0.5f, 1,

                -0.5f, -0.5f, 1,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, -0.5f, 1

        };

        // texture坐标点
        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        // 顶点顺序
        int[] indices = {
                0, 1, 3,
                3, 1, 2,
                4, 5, 7,
                7, 5, 6,
                8, 9, 11,
                11, 9, 10,
                12, 13, 15,
                15, 13, 14,
                16, 17, 19,
                19, 17, 18,
                20, 21, 23,
                23, 21, 22
        };

        RawModel model = loader.loadToVao(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("dog0"));
        TextureModel textureModel = new TextureModel(model, texture);

        Entity entity = new Entity(textureModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {
//            entity.increasePosition(0, 0, -0.0001f);
//            entity.increaseRotation(0, 0, 360f);
            entity.increaseRotation(0.025f, 0.025f, 0.025f);
            renderer.prepare();
            camera.move();
            staticShader.start();
            staticShader.loadViewMatrix(camera);
            renderer.render(entity, staticShader);
            staticShader.stop();

            DisplayManager.updateDisplay();
        }

        staticShader.cleanUp();
        loader.cleanUp();

        DisplayManager.closeDisplay();
    }
}
