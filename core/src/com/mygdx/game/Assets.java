package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    //All the assets used in the game
    public static Texture sprite_sheet;
    public static TextureRegion[] sprite_frames;
    public static TextureRegion current_frame;
    public static TextureRegion rival_current_frame_1;
    public static TextureRegion rival_current_frame_2;
    public static TextureRegion rival_current_frame_3;
    public static Animation[] boat_animations;
    public static TextureRegion[][] boats;
    public static Texture warning;
    public static Texture heart;
    public static Texture start;
    public static Texture startSelected;
    public static Texture exit;
    public static Texture exitSelected;
    public static Texture lIndicator;
    public static Texture caseusLogo;
    public static Texture first;
    public static Texture second;
    public static Texture third;
    public static Texture fourth;
    public static Texture menu;
    public static Texture menuSelected;
    public static Texture again;
    public static Texture againSelected;


    public static void load(){

        //Initialising all the assets
        warning = new Texture(Gdx.files.internal("New Piskel-1.png.png"));
        start = new Texture(Gdx.files.internal("New Piskel-3.png.png"));
        startSelected = new Texture(Gdx.files.internal("New Piskel-4.png.png"));
        exit = new Texture(Gdx.files.internal("New Piskel-5.png.png"));
        exitSelected = new Texture(Gdx.files.internal("New Piskel-6.png.png"));
        lIndicator = new Texture(Gdx.files.internal("New Piskel-7.png.png"));
        heart = new Texture(Gdx.files.internal("New Piskel-2.png.png"));
        sprite_sheet = new Texture(Gdx.files.internal("SpriteSheet.png"));
        caseusLogo = new Texture(Gdx.files.internal("CaseusLogo.png"));
        first = new Texture(Gdx.files.internal("New Piskel-8.png.png"));
        second = new Texture(Gdx.files.internal("New Piskel-10.png.png"));
        third = new Texture(Gdx.files.internal("New Piskel-11.png.png"));
        fourth = new Texture(Gdx.files.internal("New Piskel-12.png.png"));
        again = new Texture(Gdx.files.internal("again.png"));
        againSelected = new Texture(Gdx.files.internal("againSelected.png"));
        menu = new Texture(Gdx.files.internal("menu.png"));
        menuSelected = new Texture(Gdx.files.internal("menuSelected.png"));
        TextureRegion[][] temp = TextureRegion.split(sprite_sheet, 64,64);
        sprite_frames = new TextureRegion[56];
        boats = new TextureRegion[10][4];
        boat_animations = new Animation[10];

        //Splitting up the sprite sheet (we ended up with a lot more sprites
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

        //Initialising the boat animations
        for(int i = 0;i<10;i++){
            boat_animations[i] = new Animation(0.3F,(Object[]) boats[i]);
        }

    }

}
