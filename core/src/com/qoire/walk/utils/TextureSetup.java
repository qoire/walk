package com.qoire.walk.utils;


import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by Yao on 6/15/2014.
 */
public class TextureSetup {
    public static void setup() {
        TexturePacker.process("images", "images/textures", "texture.pack");
        //process textures
    }
}
