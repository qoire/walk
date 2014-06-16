package com.qoire.walk.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.qoire.walk.model.World;
import com.qoire.walk.model.Block;
import com.qoire.walk.model.Bob;

/**
 * Created by MSI\ysun on 6/13/14.
 */
public class WorldRenderer {

    private static final float CAMERA_WIDTH = 16f;
    private static final float CAMERA_HEIGHT = 9f;
    private static final float RUNNING_FRAME_DURATION = 0.06f;

    private World world;
    private OrthographicCamera cam;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    /** private textures **/

    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;

    /** Texture regions **/
    private TextureRegion bobIdleLeft;
    private TextureRegion bobIdleRight;
    private TextureRegion blockTexture;
    private TextureRegion bobFrame;

    /** private animations **/
    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public WorldRenderer(World world) {
        this.world = world;
        this.cam = new OrthographicCamera(1280, 720);
        this.cam.position.set(CAMERA_WIDTH/2f, CAMERA_HEIGHT/2f, 0);
        this.cam.update();
        spriteBatch = new SpriteBatch();
        loadTextures();
    }

    public void render() {
        moveCamera();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
            drawBlocks();
            drawBob();
        spriteBatch.end();

        if (debug) {
            drawDebug();
        }
    }

    private void moveCamera() {
        Bob bob = world.getBob();
        if (bob.getPosition().x > CAMERA_WIDTH/2f) {
            cam.position.set(bob.getPosition().x, CAMERA_HEIGHT + 100, 0);
            cam.update();
        }
    }

    private void loadTextures() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/texture.pack"));
        bobIdleLeft = atlas.findRegion("bob-01");
        bobIdleRight = atlas.findRegion("bob-01");
        bobIdleRight.flip(true, false);

        //block texture
        blockTexture = atlas.findRegion("block");

        //add in running frames left
        TextureRegion[] walkLeftFrames = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkLeftFrames[i] = atlas.findRegion("bob-0" + (i + 2));
        }

        walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);
        TextureRegion[] walkRightFrames = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true, false);
        }
        walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);


    }

    private void drawBob() {
        Bob bob = world.getBob();
        bobFrame = bob.isFacingLeft() ? bobIdleLeft : bobIdleRight;

        if (bob.getState().equals(Bob.State.WALKING)) {
            bobFrame = bob.isFacingLeft() ? walkLeftAnimation.getKeyFrame(bob.getStateTime(), true) : walkRightAnimation.getKeyFrame(bob.getStateTime(), true);
        }

        spriteBatch.draw(bobFrame, bob.getPosition().x, bob.getPosition().y, bob.getSIZEX(), bob.getSIZEY());

    }

    private void drawBlocks() {
        for (Block block : world.getBlocks()) {
            spriteBatch.draw(blockTexture, block.getPosition().x, block.getPosition().y, block.getSIZE(), block.getSIZE());
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
