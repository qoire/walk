package com.qoire.walk.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MSI\ysun on 6/13/14.
 */
public class Block {
    static final float SIZE = 48;

    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Block(Vector2 pos) {
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getSIZE() {
        return SIZE;
    }
}
