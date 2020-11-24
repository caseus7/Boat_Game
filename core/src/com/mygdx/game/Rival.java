package com.mygdx.game;

public class Rival {

    //attributes of the rival class
    private int rivalSpeed;
    private int rivalType;
    private int health;
    private boolean alive;
    private int X;
    private int Y;

    //Initialise a rival object
    public Rival(int speed, int type){
        this.rivalSpeed = speed;
        this.rivalType = type;
        this.alive = true;
        this.health = 3;
    }

    //get the rival speed
    public int getSpeed(){
        return this.rivalSpeed;
    }

    //set the rival alive boolean
    public void setAlive(boolean newAlive){this.alive = newAlive;}

    //get the rival alive boolean
    public boolean isAlive(){return this.alive;}

    //get the rival type
    public int getType(){
        return this.rivalType;
    }

    //set the rival speed
    public void setSpeed(int givenSpeed){
        this.rivalSpeed = givenSpeed;
    }

    //set the rival type
    public void setType(int givenType){
        this.rivalType = givenType;
    }

    //get the X coord for the rival
    public int getX() {return  this.X;}

    //get the Y coord for the rival
    public int getY() {return  this.Y;}

    //get the health value for the rival
    public int getHealth(){
        return this.health;
    }

    //set the health value for the rival
    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    //set the X coord for the rival
    public void setX(int newX) {
        if(newX >= 0 && newX <= 1920) {
            this.X = newX;
        }
    }

    //set the Y coord for the rival
    public void setY(int newY) {
        if(newY >= 0 && newY <= 1080) {
            this.Y = newY;
        }
    }
}
