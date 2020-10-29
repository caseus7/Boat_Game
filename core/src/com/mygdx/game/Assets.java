package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Texture boat_sheet;
    public static TextureRegion[] boat_frames;
    public static TextureRegion current_frame;
    public static Animation boat_animation;

    public static void load(){

        boat_sheet = new Texture(Gdx.files.internal("Boat clone - Merged.png"));
        TextureRegion[][] temp = TextureRegion.split(boat_sheet, 64,64);
        boat_frames = new TextureRegion[4];

        int index = 0;
        for(int i = 0; i<2; i++){
            for(int j = 0; j<2; j++){
                boat_frames[index++] = temp[i][j];
            }
        }
        for(int i = 0; i<4; i++){
            boat_frames[i].flip(false,true);
        }

        boat_animation = new Animation(0.2F, boat_frames);
    }
}
