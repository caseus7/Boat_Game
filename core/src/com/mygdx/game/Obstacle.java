package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {

    //Attributes of each obstacle
    private int type;
    private int speed;
    private int column;
    private int Y;
    private int index;

    //Animations and textures
    public Animation[] duck_animations;
    public TextureRegion[][] ducks;
    public TextureRegion duck_current_frame;
    public TextureRegion log1;
    public TextureRegion log2;
    public TextureRegion log3;

    //Based on the type and the speed initialise an obstacle
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

        //setting the log sprites
        this.log1 = Assets.sprite_frames[51];
        this.log2 = Assets.sprite_frames[52];
        this.log3 = Assets.sprite_frames[53];

        //setting the duck animations
        duck_animations[0] = new Animation(0.3F,(Object[]) ducks[0]);
        duck_animations[1] = new Animation(0.3F,(Object[]) ducks[1]);
        duck_animations[2] = new Animation(0.3F,log1);
        duck_animations[3] = new Animation(0.3F,log2);
        duck_animations[4] = new Animation(0.3F,log3);
    }

    //Get the Y coord of the obstacle
    public int getY(){
        return  this.Y;
    }

    //Increment the Y coord of the obstacle (move it down one in the grid)
    public void incrementY(){
        this.Y += 270;
    }

    //Reset the Y coord of the obstacle (move it back to the top)
    public void resetY(){
        this.Y = -284;
    }

    //Set the current frame for the duck animation
    public void setCurrentFrame(TextureRegion givenFrame){
        this.duck_current_frame = givenFrame;
    }

    //Get the animation
    public Animation getAnim(){
        return this.duck_animations[this.type];
    }

    //Get the current animation frame
    public TextureRegion getCurrentFrame(){
        return this.duck_current_frame;
    }

    //Randomise the type of obstacle
    public void RandomiseType(){
        this.type = ThreadLocalRandom.current().nextInt(0,5);
    }

    //Get the column the obstacle is in
    public int getColumn() {return this.column;}

    //Set the column the obstacle is in
    public void setColumn(int newColumn) {this.column = newColumn;}
}
