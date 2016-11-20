package com.thinmatrix.opengl3dgamedemo;

import com.thinmatrix.opengl3dgamedemo.renderengine.DisplayManager;
import com.thinmatrix.opengl3dgamedemo.renderengine.Loader;
import com.thinmatrix.opengl3dgamedemo.renderengine.RawModel;
import com.thinmatrix.opengl3dgamedemo.renderengine.Renderer;
import com.thinmatrix.opengl3dgamedemo.shader.StaticShader;

import org.lwjgl.opengl.Display;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader staticShader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        RawModel model = loader.loadToVao(vertices, indices);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            staticShader.start();
            renderer.render(model);
            staticShader.stop();

            DisplayManager.updateDisplay();
        }

        staticShader.cleanUp();
        loader.cleanUp();

        DisplayManager.closeDisplay();
    }
}
