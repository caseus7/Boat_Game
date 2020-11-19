package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {

    private int type;
    private int speed;
    private int column;
    private int Y;
    private int index;
    public Animation[] duck_animations;
    public TextureRegion[][] ducks;
    public TextureRegion duck_current_frame;
    public TextureRegion log1;
    public TextureRegion log2;
    public TextureRegion log3;

    public Obstacle(int type, int speed){
        this.ducks = new TextureRegion[2][5];
        this.duck_animations = new Animation[5];
        log1 = new TextureRegion();
        log2 = new TextureRegion();
        log3 = new TextureRegion();
        this.type = type;
        this.speed = speed;
        this.index = 40;
        for(int i = 0;i<2; i++) {
            for (int j = 0; j < 5; j++) {
                ducks[i][j] = Assets.sprite_frames[index++];
            }
        }

        this.log1 = Assets.sprite_frames[51];
        this.log2 = Assets.sprite_frames[52];
        this.log3 = Assets.sprite_frames[53];


        duck_animations[0] = new Animation(0.3F,(Object[]) ducks[0]);
        duck_animations[1] = new Animation(0.3F,(Object[]) ducks[1]);
        duck_animations[2] = new Animation(0.3F,log1);
        duck_animations[3] = new Animation(0.3F,log2);
        duck_animations[4] = new Animation(0.3F,log3);
    }
    public int getY(){
        return  this.Y;
    }
    public void incrementY(){
        this.Y += 270;
    }
    public void resetY(){
        this.Y = -284;
    }

    public void setCurrentFrame(TextureRegion givenFrame){
        this.duck_current_frame = givenFrame;
    }

    public Animation getAnim(){
        return this.duck_animations[this.type];
    }

    public TextureRegion getCurrentFrame(){
        return this.duck_current_frame;
    }

    public void RandomiseType(){
        this.type = ThreadLocalRandom.current().nextInt(0,5);
    }

    public int getColumn() {return this.column;}

    public void setColumn(int newColumn) {this.column = newColumn;}
}
