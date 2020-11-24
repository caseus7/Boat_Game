package com.mygdx.game;

public class BoatGrid {

    //The 2D array that is the grid
    private Integer[][] grid;

    public BoatGrid(){
        //Initialising the grid and filling it with values
        this.grid = new Integer[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                    this.grid[i][j] = 4;
                }
            }
        //Positioning the boats on the grid (3 is the player)
        this.grid[2][0] = 0;
        this.grid[2][1] = 3;
        this.grid[2][2] = 1;
        this.grid[2][3] = 2;
    }

    //Function to find the row and column of a boat (boat decided by given number)
    public Integer[] findBoat(Integer boatNum){
        Integer[] output = new Integer[2];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if(grid[i][j] == boatNum){
                    output[0] = i;
                    output[1] = j;
                    return output;
                }
            }
        }
        return output;
    }

    //Prints the grid in debug, used in testing
    public void printGrid(){
        String line1 = Integer.toString(this.grid[0][0]) + "," + Integer.toString(this.grid[0][1]) + "," + Integer.toString(this.grid[0][2]) + "," + Integer.toString(this.grid[0][3]);
        String line2 = Integer.toString(this.grid[1][0]) + "," + Integer.toString(this.grid[1][1]) + "," + Integer.toString(this.grid[1][2]) + "," + Integer.toString(this.grid[1][3]);
        String line3 = Integer.toString(this.grid[2][0]) + "," + Integer.toString(this.grid[2][1]) + "," + Integer.toString(this.grid[2][2]) + "," + Integer.toString(this.grid[2][3]);
        String line4 = Integer.toString(this.grid[3][0]) + "," + Integer.toString(this.grid[3][1]) + "," + Integer.toString(this.grid[3][2]) + "," + Integer.toString(this.grid[3][3]);

        System.err.println("--------------------");
        System.err.println(line1);
        System.err.println(line2);
        System.err.println(line3);
        System.err.println(line4);
        System.err.println("--------------------");
    }

    //Removing a boat from the grid (once it has capsized)
    public void removeBoat(Integer boatNum){
        Integer[] coords = this.findBoat(boatNum);
        this.grid[coords[0]][coords[1]] = 4;
    }

    //Moving a boat (used in player boat movement)
    public Boolean moveBoat(Integer boatNum, String direction){
        if(boatNum < 1 || boatNum > 4){
            return false;
        }
        Integer[] startingCoords = this.findBoat(boatNum);
        if(direction == "up" && startingCoords[0] > 0){
            if(this.grid[startingCoords[0] - 1][startingCoords[1]] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0] - 1][startingCoords[1]] = boatNum;
                return true;
            }
        }
        if(direction == "down" && startingCoords[0] < 3){
            if(this.grid[startingCoords[0] + 1][startingCoords[1]] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0] + 1][startingCoords[1]] = boatNum;
                return true;
            }
        }
        if(direction == "left" && startingCoords[1] > 0){
            if(this.grid[startingCoords[0]][startingCoords[1] - 1] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0]][startingCoords[1] - 1] = boatNum;
                return true;
            }
        }
        if(direction == "right" && startingCoords[1] < 3){
            if(this.grid[startingCoords[0]][startingCoords[1] + 1] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0]][startingCoords[1] + 1] = boatNum;
                return true;
            }
        }
        return false;
    }

    //Moving a rival, used in rival boat movement
    public Boolean moveRival(Integer boatNum, String direction, Rival boat){
        if(boatNum < 1 || boatNum > 4){
            return false;
        }
        Integer[] startingCoords = this.findBoat(boatNum);
        if(direction == "up" && startingCoords[0] > 0){
            if(this.grid[startingCoords[0] - 1][startingCoords[1]] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0] - 1][startingCoords[1]] = boatNum;
//                boat.setY(boat.getY() - 270);
                return true;
            }
        }
        if(direction == "down" && startingCoords[0] < 3){
            if(this.grid[startingCoords[0] + 1][startingCoords[1]] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0] + 1][startingCoords[1]] = boatNum;
//                boat.setY(boat.getY() + 270);
                return true;
            }
        }
        if(direction == "left" && startingCoords[1] > 0){
            if(this.grid[startingCoords[0]][startingCoords[1] - 1] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0]][startingCoords[1] - 1] = boatNum;
//                boat.setX(boat.getX() - 480);
                return true;
            }
        }
        if(direction == "right" && startingCoords[1] < 3){
            if(this.grid[startingCoords[0]][startingCoords[1] + 1] == 4){
                this.grid[startingCoords[0]][startingCoords[1]] = 4;
                this.grid[startingCoords[0]][startingCoords[1] + 1] = boatNum;
//                boat.setX(boat.getX() + 480);
                return true;
            }
        }
        return false;
    }

}
