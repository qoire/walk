package com.qoire.walk.model;

import com.badlogic.gdx.math.Vector2;
import com.qoire.walk.model.Block;
import com.qoire.walk.model.Bob;

import java.util.ArrayList;

/**
 * Created by MSI\ysun on 6/13/14.
 */
public class World {
    ArrayList<Block> blocks = new ArrayList<Block>();
    Bob bob;

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Bob getBob() {
        return bob;
    }

    public World() {
        createDemoWorld();
    }

    private void createDemoWorld() {
        bob = new Bob(new Vector2(7, 2));

        //gen top
        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(new Vector2(i, 6)));
        }

        //gen bottom
        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(new Vector2(i, 0)));
        }

        //gen bottom second layer
        for (int i = 3; i < 10; i++) {
            blocks.add(new Block(new Vector2(i, 1)));
        }
        blocks.add(new Block(new Vector2(9, 2)));
        blocks.add(new Block(new Vector2(9, 3)));
        blocks.add(new Block(new Vector2(9, 4)));
        blocks.add(new Block(new Vector2(9, 5)));

        blocks.add(new Block(new Vector2(6, 3)));
        blocks.add(new Block(new Vector2(6, 4)));
        blocks.add(new Block(new Vector2(6, 5)));

    }
}
