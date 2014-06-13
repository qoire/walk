package com.qoire.walk;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.qoire.walk.model.Block;
import com.qoire.walk.model.Bob;

/**
 * Created by MSI\ysun on 6/13/14.
 */
public class WorldRenderer {
    private World world;
    private OrthographicCamera cam;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(World world) {
        this.world = world;
        this.cam = new OrthographicCamera(10, 7);
        this.cam.position.set(5, 3.5f, 0);
        this.cam.update();
    }

    public void render() {
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Block block : world.getBlocks()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x + rect.x;
            float y1 = block.getPosition().y + rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }
        // render Bob
        Bob bob = world.getBob();
        Rectangle rect = bob.getBounds();
        float x1 = bob.getPosition().x + rect.x;
        float y1 = bob.getPosition().y + rect.y;
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(x1, y1, rect.width, rect.height);
        debugRenderer.end();

    }
}