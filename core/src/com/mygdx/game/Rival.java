package com.mygdx.game;

public class Rival {

    private int rivalSpeed;
    private int rivalType;
    private int X;
    private int Y;

    public Rival(int speed, int type){
        this.rivalSpeed = speed;
        this.rivalType = type;
    }

    public int getSpeed(){
        return this.rivalSpeed;
    }

    public int getType(){
        return this.rivalType;
    }

    public void setSpeed(int givenSpeed){
        this.rivalSpeed = givenSpeed;
    }

    public void setType(int givenType){
        this.rivalType = givenType;
    }

    public int getX() {return  this.X;}

    public int getY() {return  this.Y;}

    public void setX(int newX) {
        if(newX > 0 && newX < 1920) {
            this.X = newX;
        }
    }

    public void setY(int newY) {
        if(newY > 0 && newY < 1080) {
            this.Y = newY;
        }
    }
}
