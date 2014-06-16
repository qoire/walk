package com.qoire.walk.desktop;

        import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
        import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
        import com.qoire.walk.walk;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        config.resizable = false;
        config.title = "walk";

        new LwjglApplication(new walk(), config);
    }
}
