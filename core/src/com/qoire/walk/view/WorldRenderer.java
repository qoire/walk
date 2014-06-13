package com.qoire.walk.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.qoire.walk.model.World;
import com.qoire.walk.model.Block;
import com.qoire.walk.model.Bob;

/**
 * Created by MSI\ysun on 6/13/14.
 */
public class WorldRenderer {

    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    private World world;
    private OrthographicCamera cam;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    /** private textures **/
    private Texture bobTex;
    private Texture blockTex;

    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width/CAMERA_WIDTH;
        ppuY = (float)height/CAMERA_HEIGHT;
    }

    public WorldRenderer(World world) {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH/2f, CAMERA_HEIGHT/2f, 0);
        this.cam.update();
        spriteBatch = new SpriteBatch();
        loadTextures();
    }

    public void render() {
        spriteBatch.begin();
            drawBlocks();
            drawBob();
        spriteBatch.end();

        if (debug) {
            drawDebug();
        }
    }

    private void loadTextures() {
        bobTex = new Texture(Gdx.files.internal("images/bob_01.png"));
        blockTex = new Texture(Gdx.files.internal("images/block.png"));
    }

    private void drawBob() {
        Bob bob = world.getBob();
        spriteBatch.draw(bobTex, bob.getPosition().x * ppuX, bob.getPosition().y * ppuY, bob.getSIZE() * ppuX, bob.getSIZE() * ppuY);
    }

    private void drawBlocks() {
        for (Block block : world.getBlocks()) {
            spriteBatch.draw(blockTex, block.getPosition().x * ppuX, block.getPosition().y * ppuY, block.getSIZE() * ppuX, block.getSIZE() * ppuY);
        }
    }

    private void drawDebug() {
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
