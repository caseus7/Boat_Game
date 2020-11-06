package com.mygdx.game;

public class Rival {

    private int rivalSpeed;
    private int rivalType;

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

}
