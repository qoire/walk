package com.qoire.walk.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MSI\ysun on 6/13/14.
 */
public class Bob {
    public enum State {
        IDLE, WALKING, JUMPING, DYING
    }

    public static final float SPEED = 100;
    public static final float JUMP_VELOCITY = 10;
    public static final float SIZEX = 24;
    public static final float SIZEY = 28;

    Vector2 position = new Vector2();
    Vector2 acceleration = new Vector2();
    Vector2 velocity = new Vector2();
    Rectangle bounds = new Rectangle();
    State state = State.IDLE;
    boolean facingLeft = true;

    public Bob(Vector2 position) {
        this.position = position;
        this.bounds.height = SIZEY;
        this.bounds.width = SIZEX;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public float getSIZEX() {
        return SIZEX;
    }

    public float getSIZEY() {
        return SIZEY;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        this.state = newState;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }
}
