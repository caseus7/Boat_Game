package com.mygdx.game;

public class ObstacleGrid {

    private Integer[][] grid;

    public ObstacleGrid(){
        this.grid = new Integer[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                this.grid[i][j] = 0;
            }
        }
    }

    public void Incoming(Integer column){
        this.grid[0][column] = 1;
        this.grid[1][column] = 1;
        this.grid[2][column] = 1;
        this.grid[3][column] = 1;
    }

    public void ObstacleMove(Integer column){
        if(this.grid[1][column] == 2){
            this.grid[1][column] = 0;
            this.grid[2][column] = 2;
        }
        else if(this.grid[2][column] == 2){
            this.grid[2][column] = 0;
            this.grid[3][column] = 2;
        }
        else if(this.grid[3][column] == 2){
            this.grid[3][column] = 0;
        }
        else{
            this.grid[0][column] = 2;
        }
    }

    public void Passed(Integer column){
        this.grid[0][column] = 0;
        this.grid[1][column] = 0;
        this.grid[2][column] = 0;
        this.grid[3][column] = 0;
    }

    public boolean safe(int row, int column){
        if(row > 3 || row < 0 || column > 3 || column < 0){
            return false;
        }

        if(this.grid[row][column] == 1 || this.grid[row][column] == 2){
            return false;
        }
        else{
            return true;
        }
    }

    public void printGrid(){
        String line1 = Integer.toString(this.grid[0][0]) + "," + Integer.toString(this.grid[0][1]) + "," + Integer.toString(this.grid[0][2]) + "," + Integer.toString(this.grid[0][3]);
        String line2 = Integer.toString(this.grid[1][0]) + "," + Integer.toString(this.grid[1][1]) + "," + Integer.toString(this.grid[1][2]) + "," + Integer.toString(this.grid[1][3]);
        String line3 = Integer.toString(this.grid[2][0]) + "," + Integer.toString(this.grid[2][1]) + "," + Integer.toString(this.grid[2][2]) + "," + Integer.toString(this.grid[2][3]);
        String line4 = Integer.toString(this.grid[3][0]) + "," + Integer.toString(this.grid[3][1]) + "," + Integer.toString(this.grid[3][2]) + "," + Integer.toString(this.grid[3][3]);

        System.err.println(line1);
        System.err.println(line2);
        System.err.println(line3);
        System.err.println(line4);
    }

}
