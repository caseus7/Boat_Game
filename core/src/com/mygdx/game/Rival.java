package com.mygdx.game;

public class Rival {

    private int rivalSpeed;
    private int rivalType;
    private int health;
    private boolean alive;
    private int X;
    private int Y;

    public Rival(int speed, int type){
        this.rivalSpeed = speed;
        this.rivalType = type;
        this.alive = true;
        this.health = 3;
    }

    public int getSpeed(){
        return this.rivalSpeed;
    }

    public void setAlive(boolean newAlive){this.alive = newAlive;}

    public boolean isAlive(){return this.alive;}

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

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    public void setX(int newX) {
        if(newX >= 0 && newX <= 1920) {
            this.X = newX;
        }
    }

    public void setY(int newY) {
        if(newY >= 0 && newY <= 1080) {
            this.Y = newY;
        }
    }
}
