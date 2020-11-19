package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;


public class Assets {

    public static Texture sprite_sheet;
    public static TextureRegion[] sprite_frames;
    public static TextureRegion current_frame;
    public static TextureRegion rival_current_frame_1;
    public static TextureRegion rival_current_frame_2;
    public static TextureRegion rival_current_frame_3;
    public static Animation[] boat_animations;
    public static TextureRegion[][] boats;


    public static void load(){

        sprite_sheet = new Texture(Gdx.files.internal("SpriteSheet.png"));
        TextureRegion[][] temp = TextureRegion.split(sprite_sheet, 64,64);
        sprite_frames = new TextureRegion[56];
        boats = new TextureRegion[10][4];
        boat_animations = new Animation[10];

        int index = 0;
        for(int i = 0; i<14; i++){
            for(int j = 0; j<4; j++){
                sprite_frames[index++] = temp[i][j];
            }
        }
        for(int i = 0; i<56; i++) {
            sprite_frames[i].flip(false, true);
        }

        index = 0;
        for(int i = 0;i<10; i++) {
            for (int j = 0; j < 4; j++) {
                boats[i][j] = sprite_frames[index++];
            }
        }

        for(int i = 0;i<10;i++){
            boat_animations[i] = new Animation(0.3F,(Object[]) boats[i]);
        }

    }

}
