package com.mygdx.game;

public class BoatTypes {

    //Values for speed and health of each boat type
    public static Integer[] getStats(Integer typeNum){
        if(typeNum == 0){
            return new Integer[]{4, 3};
        }
        if(typeNum == 1){
            return new Integer[]{3, 2};
        }
        if(typeNum == 2){
            return new Integer[]{2, 1};
        }
        if(typeNum == 3){
            return new Integer[]{5, 4};
        }
        if(typeNum == 4){
            return new Integer[]{6, 5};
        }
        if(typeNum == 5){
            return new Integer[]{6, 1};
        }
        if(typeNum == 6){
            return new Integer[]{5, 2};
        }
        if(typeNum == 7){
            return new Integer[]{5, 3};
        }
        if(typeNum == 8){
            return new Integer[]{2, 4};
        }
        if(typeNum == 9){
            return new Integer[]{1, 5};
        }
        return new Integer[]{0,0};
    }

}
