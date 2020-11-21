package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.BoatGame;
import sun.security.util.Debug;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameScreen implements Screen {

    private BoatGame game;
    OrthographicCamera camera;
    SpriteBatch batch;

    float stateTime;
    int boatX;
    int boatY;
    public static int currentBoat = 0;

    int waterIndex1 = 1024;
    int waterIndex2 = -512;
    int waterIndex3 = 0;
    int waterIndex4 = 512;

    int ropeIndex1 = 1024;
    int ropeIndex2 = -512;
    int ropeIndex3 = 0;
    int ropeIndex4 = 512;

    float raceDist = 0;

    int finIndex = -512;

    int playerSpeed = 4;

    BitmapFont font = new BitmapFont(Gdx.files.internal("BULKYPIX.fnt"),true);

    Rival[] otherBoats;

    BoatGrid gameGrid = new BoatGrid();

    Obstacle[] Obstacles;

    ObstacleGrid obstacleGrid = new ObstacleGrid();

    String previousMove = "";

    long[] rivalMoveTimers = {0,0,0};

    int obstacletimer;
    int obstacleMovingTimer;
    boolean obstacleMoving;
    int obstacleNum;

    boolean[] warnings;

    int playerHealth = 3;
    int playerIFrames = 0;
    boolean playerIFramesMoving = false;

    int[] rivalIFrames = {0,0,0};
    boolean[] rivalIFramesMoving = {false,false,false};
    boolean[] rivalAlreadyRemoved = {false, false, false};

    int check = 0;

    Integer[] finalPos = {5,5,5};

    public GameScreen(BoatGame game) {
        this.game = game;

        raceDist = 0;

        gameGrid.printGrid();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();
        stateTime = 0F;

        boatX = 592;
        boatY = 540;

        otherBoats = new Rival[3];
        for(int i = 0; i<3; i++){
            int temp = ThreadLocalRandom.current().nextInt(0,10);
            otherBoats[i] = new Rival(4, temp);
            otherBoats[i].setY(540);
        }
        otherBoats[0].setX(112);
        otherBoats[1].setX(1072);
        otherBoats[2].setX(1552);

        Obstacles = new Obstacle[4];
        for(int i = 0; i<4; i++){
            int temp = ThreadLocalRandom.current().nextInt(0,5);
            Obstacles[i] = new Obstacle(temp, 5);
            Obstacles[i].setColumn(i);
            Obstacles[i].resetY();
        }

        obstacletimer = 0;
        obstacleMovingTimer = 0;
        obstacleMoving = false;

        warnings = new boolean[4];
        for(int i =0;i<4;i++){
            warnings[i] = false;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 0.95F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        generalUpdate();

        stateTime += Gdx.graphics.getDeltaTime();
        Assets.current_frame = (TextureRegion) Assets.boat_animations[currentBoat].getKeyFrame(stateTime, true);
        Assets.rival_current_frame_1 = (TextureRegion) Assets.boat_animations[otherBoats[0].getType()].getKeyFrame(stateTime, true);
        Assets.rival_current_frame_2 = (TextureRegion) Assets.boat_animations[otherBoats[1].getType()].getKeyFrame(stateTime, true);
        Assets.rival_current_frame_3 = (TextureRegion) Assets.boat_animations[otherBoats[2].getType()].getKeyFrame(stateTime, true);

        for(int i = 0; i < 4; i++) {
            Obstacles[i].setCurrentFrame((TextureRegion) Obstacles[i].getAnim().getKeyFrame(stateTime, true));
        }

        batch.setProjectionMatrix(camera.combined);

        batch.begin();


        if(true) {
            batch.draw(Assets.sprite_frames[50], 0, waterIndex1, 512, 512);
            batch.draw(Assets.sprite_frames[50], 512, waterIndex1, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1024, waterIndex1, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1536, waterIndex1, 512, 512);

            batch.draw(Assets.sprite_frames[50], 0, waterIndex2, 512, 512);
            batch.draw(Assets.sprite_frames[50], 512, waterIndex2, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1024, waterIndex2, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1536, waterIndex2, 512, 512);

            batch.draw(Assets.sprite_frames[50], 0, waterIndex3, 512, 512);
            batch.draw(Assets.sprite_frames[50], 512, waterIndex3, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1024, waterIndex3, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1536, waterIndex3, 512, 512);

            batch.draw(Assets.sprite_frames[50], 0, waterIndex4, 512, 512);
            batch.draw(Assets.sprite_frames[50], 512, waterIndex4, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1024, waterIndex4, 512, 512);
            batch.draw(Assets.sprite_frames[50], 1536, waterIndex4, 512, 512);
        }
        if(raceDist >= 484){
            batch.draw(Assets.sprite_frames[55],-16,finIndex,512,512);
            batch.draw(Assets.sprite_frames[55],464,finIndex,512,512);
            batch.draw(Assets.sprite_frames[55],944,finIndex,512,512);
            batch.draw(Assets.sprite_frames[55],1424,finIndex,512,512);
        }

        batch.draw(Assets.sprite_frames[54],704,ropeIndex1,512,512);
        batch.draw(Assets.sprite_frames[54],224,ropeIndex1,512,512);
        batch.draw(Assets.sprite_frames[54],1184,ropeIndex1,512,512);

        batch.draw(Assets.sprite_frames[54],704,ropeIndex2,512,512);
        batch.draw(Assets.sprite_frames[54],224,ropeIndex2,512,512);
        batch.draw(Assets.sprite_frames[54],1184,ropeIndex2,512,512);

        batch.draw(Assets.sprite_frames[54],704,ropeIndex3,512,512);
        batch.draw(Assets.sprite_frames[54],224,ropeIndex3,512,512);
        batch.draw(Assets.sprite_frames[54],1184,ropeIndex3,512,512);

        batch.draw(Assets.sprite_frames[54],704,ropeIndex4,512,512);
        batch.draw(Assets.sprite_frames[54],224,ropeIndex4,512,512);
        batch.draw(Assets.sprite_frames[54],1184,ropeIndex4,512,512);

        batch.draw(Assets.current_frame, boatX,boatY,256,256);

        if(otherBoats[0].isAlive()) {
            batch.draw(Assets.rival_current_frame_1, otherBoats[0].getX(), otherBoats[0].getY(), 256, 256);
        }
        if(otherBoats[1].isAlive()) {
            batch.draw(Assets.rival_current_frame_2, otherBoats[1].getX(), otherBoats[1].getY(), 256, 256);
        }
        if(otherBoats[2].isAlive()) {
            batch.draw(Assets.rival_current_frame_3, otherBoats[2].getX(), otherBoats[2].getY(), 256, 256);
        }

        batch.draw(Obstacles[0].getCurrentFrame(), 372, Obstacles[0].getY(), -256, -256);
        batch.draw(Obstacles[1].getCurrentFrame(), 852, Obstacles[1].getY(), -256, -256);
        batch.draw(Obstacles[2].getCurrentFrame(), 1332, Obstacles[2].getY(), -256, -256);
        batch.draw(Obstacles[3].getCurrentFrame(), 1812, Obstacles[3].getY(), -256, -256);

        if(playerHealth == 3) {
            batch.draw(Assets.heart, 1920, 120, -128, -128);
        }
        if(playerHealth >= 2) {
            batch.draw(Assets.heart, 1792, 120, -128, -128);
        }
        if(playerHealth >= 1) {
            batch.draw(Assets.heart, 1664, 120, -128, -128);
        }

        if(warnings[0]) {
            batch.draw(Assets.warning, 372, 256, -256, -256);
        }
        if(warnings[1]) {
            batch.draw(Assets.warning, 852, 256, -256, -256);
        }
        if(warnings[2]) {
            batch.draw(Assets.warning, 1332, 256, -256, -256);
        }
        if(warnings[3]) {
            batch.draw(Assets.warning, 1812, 256, -256, -256);
        }

        font.draw(batch, "Distance: " + Double.toString(Math.floor(raceDist)) + "m",30,30);
        batch.end();
    }

    public void generalUpdate(){

        if(otherBoats[0].isAlive() == false && otherBoats[1].isAlive() == false && otherBoats[2].isAlive() == false){
            EndScreen.place = "first";
            game.setScreen(game.endScreen);
        }

        if(raceDist >= 498){
            for(int i = 0;i<3;i++){
                if(otherBoats[i].isAlive()){
                    Integer[] coords = gameGrid.findBoat(i);
                    finalPos[i] = coords[0];
                }
            }

            Integer myPos = gameGrid.findBoat(3)[0];

            if(myPos == 0){
                EndScreen.place = "first";
                game.setScreen(game.endScreen);
            }
            if(myPos == 1){
                Integer count = 0;
                for(int i = 0;i<3;i++){
                    if(finalPos[i] < 1){
                        count +=1;
                    }
                }
                if(count == 0){
                    EndScreen.place = "first";
                    game.setScreen(game.endScreen);
                }
                else if(count == 1){
                    EndScreen.place = "second";
                    game.setScreen(game.endScreen);
                }
                else if(count == 2){
                    EndScreen.place = "third";
                    game.setScreen(game.endScreen);
                }
                else if(count == 3){
                    EndScreen.place = "fourth";
                    game.setScreen(game.endScreen);
                }
            }
            if(myPos == 2){
                Integer count = 0;
                for(int i = 0;i<3;i++){
                    if(finalPos[i] < 2){
                        count +=1;
                    }
                }
                if(count == 0){
                    EndScreen.place = "first";
                    game.setScreen(game.endScreen);
                }
                else if(count == 1){
                    EndScreen.place = "second";
                    game.setScreen(game.endScreen);
                }
                else if(count == 2){
                    EndScreen.place = "third";
                    game.setScreen(game.endScreen);
                }
                else if(count == 3){
                    EndScreen.place = "fourth";
                    game.setScreen(game.endScreen);
                }
            }
            if(myPos == 3){
                Integer count = 0;
                for(int i = 0;i<3;i++){
                    if(finalPos[i] < 3){
                        count +=1;
                    }
                }
                if(count == 0){
                    EndScreen.place = "first";
                    game.setScreen(game.endScreen);
                }
                else if(count == 1){
                    EndScreen.place = "second";
                    game.setScreen(game.endScreen);
                }
                else if(count == 2){
                    EndScreen.place = "third";
                    game.setScreen(game.endScreen);
                }
                else if(count == 3){
                    EndScreen.place = "fourth";
                    game.setScreen(game.endScreen);
                }
            }
        }

        if(playerIFrames == 0){
            playerIFramesMoving = false;
        }

        for(int i = 0; i<3;i++){
            if(otherBoats[i].isAlive()) {
                if (rivalIFrames[i] == 0) {
                    rivalIFramesMoving[i] = false;
                }

                if (rivalIFramesMoving[i]) {
                    rivalIFrames[i] -= 1;
                }

                if (rivalIFrames[i] == 0) {
                    Integer[] coords = gameGrid.findBoat(i);
                    //            System.err.println(obstacleGrid.findVal(coords[0],coords[1]));
                    if (obstacleGrid.findVal(coords[0], coords[1]) == 2) {
                        otherBoats[i].setHealth(otherBoats[i].getHealth() - 1);
                        rivalIFrames[i] = 60;
                        rivalIFramesMoving[i] = true;
                    }
                }
            }

            if(otherBoats[i].getHealth() <= 0 && rivalAlreadyRemoved[i] == false){
                otherBoats[i].setAlive(false);
                gameGrid.removeBoat(i);
                rivalAlreadyRemoved[i] = true;
            }
        }

        if(playerIFramesMoving){
            playerIFrames -= 1;
        }

        if(playerIFrames == 0) {
            Integer[] coords = gameGrid.findBoat(3);
//            System.err.println(obstacleGrid.findVal(coords[0],coords[1]));
            if (obstacleGrid.findVal(coords[0],coords[1]) == 2){
                playerHealth -= 1;
                playerIFrames = 60;
                playerIFramesMoving = true;
            }
        }

        for(int i = 0; i<3; i++){
            if(otherBoats[i].isAlive()) {
                Integer[] coords = gameGrid.findBoat(i);
                otherBoats[i].setX((480 * coords[1]) + 112);
                otherBoats[i].setY((270 * coords[0]));
            }
        }

        if(Gdx.input.isKeyJustPressed((Input.Keys.M))){
            if(currentBoat < 9) {
                currentBoat += 1;
            }
            else{
                currentBoat = 0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerSpeed = 200;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) == false){
            playerSpeed = 4;
        }

        if(waterIndex1 >= 1536){
            waterIndex1 = -512;
        }

        if(waterIndex2 >= 1536){
            waterIndex2 = -512;
        }

        if(waterIndex3 >= 1536){
            waterIndex3 = -512;
        }

        if(waterIndex4 >= 1536){
            waterIndex4 = -512;
        }


        if(ropeIndex1 >= 1536){
            ropeIndex1 = -512;
        }

        if(ropeIndex2 >= 1536){
            ropeIndex2 = -512;
        }

        if(ropeIndex3 >= 1536){
            ropeIndex3 = -512;
        }

        if(ropeIndex4 >= 1536){
            ropeIndex4 = -512;
        }

        if(finIndex < 132) {
            raceDist += 0.025*playerSpeed;
            ropeIndex4+=playerSpeed;
            ropeIndex3+=playerSpeed;
            ropeIndex2+=playerSpeed;
            ropeIndex1+=playerSpeed;
            waterIndex1+=playerSpeed;
            waterIndex2+=playerSpeed;
            waterIndex3+=playerSpeed;
            waterIndex4+=playerSpeed;
        }
        else if(boatY > -512){
            boatY -= playerSpeed;
        }

        if(raceDist >= 484 && finIndex < 132){
            finIndex += playerSpeed;
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            gameGrid.printGrid();
            if(gameGrid.moveBoat(3, "up")){
                boatY -= 270;
            }
            gameGrid.printGrid();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            gameGrid.printGrid();
            if(gameGrid.moveBoat(3, "left")) {
                boatX -= 480;
            }
            gameGrid.printGrid();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            gameGrid.printGrid();
            if(gameGrid.moveBoat(3, "down")) {
                boatY += 270;
            }
            gameGrid.printGrid();
        }if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            gameGrid.printGrid();
            if(gameGrid.moveBoat(3, "right")) {
                boatX += 480;
            }
            gameGrid.printGrid();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            Obstacles[0].RandomiseType();
            Obstacles[1].RandomiseType();
            Obstacles[2].RandomiseType();
            Obstacles[3].RandomiseType();
        }

        obstacletimer += 1;

        if(obstacletimer > 30) {
            if(obstacleMoving == false) {
                obstacleNum = ThreadLocalRandom.current().nextInt(0,4);;
                warnings[obstacleNum] = true;
                Obstacles[obstacleNum].RandomiseType();
                obstacleGrid.Incoming(obstacleNum);
                obstacleMoving = true;
            }
            obstacleMovingTimer += 1;
            if(obstacleMovingTimer > 30){
                warnings[obstacleNum] = false;
                Obstacles[obstacleNum].incrementY();
                if(check > 0) {
                    obstacleGrid.ObstacleMove(obstacleNum);
                }
                obstacleMovingTimer = 0;
                check += 1;
            }
            if(Obstacles[obstacleNum].getY() > 1100){
                obstacleGrid.Passed(obstacleNum);
                obstacleMoving = false;
                obstacleMovingTimer = 0;
                Obstacles[obstacleNum].resetY();
                obstacletimer = 0;
                check = 0;
            }
        }

        for (int i = 0;i < 3; i++){
            if(otherBoats[i].isAlive()) {
                rivalMoveTimers[i] += 1;
                if (rivalMoveTimers[i] > (otherBoats[i].getSpeed() * 30)) {
                    gameGrid.printGrid();
                    rivalMoveTimers[i] = 0;
                    Integer[] startingCoords = gameGrid.findBoat(i);
                    if (obstacleGrid.safe(startingCoords[0], startingCoords[1]) == false) {

                        if (obstacleGrid.safe(startingCoords[0], startingCoords[1] + 1) && previousMove != "left") {
                            if (gameGrid.moveRival(i, "right", otherBoats[i])) {
                                previousMove = "right";
                                break;
                            }
                        } else if (obstacleGrid.safe(startingCoords[0], startingCoords[1] - 1) && previousMove != "right") {
                            if (gameGrid.moveRival(i, "left", otherBoats[i])) {
                                previousMove = "left";
                                break;
                            }
                        } else if (obstacleGrid.safe(startingCoords[0] - 1, startingCoords[1]) && previousMove != "up") {
                            if (gameGrid.moveRival(i, "down", otherBoats[i])) {
                                previousMove = "down";
                                break;
                            }
                        } else if (obstacleGrid.safe(startingCoords[0] + 1, startingCoords[1]) && previousMove != "down") {
                            if (gameGrid.moveRival(i, "up", otherBoats[i])) {
                                previousMove = "up";
                                break;
                            }
                        }

                        if (gameGrid.moveRival(i, "down", otherBoats[i]) && previousMove != "up") {
                            previousMove = "down";
                            break;
                        } else if (gameGrid.moveRival(i, "up", otherBoats[i]) && previousMove != "down") {
                            previousMove = "up";
                            break;
                        } else if (gameGrid.moveRival(i, "left", otherBoats[i]) && previousMove != "right") {
                            previousMove = "left";
                            break;
                        } else if (gameGrid.moveRival(i, "right", otherBoats[i]) && previousMove != "left") {
                            previousMove = "right";
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
