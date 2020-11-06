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
    int currentBoat = 0;

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

    public GameScreen(BoatGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();
        stateTime = 0F;

        boatX = 464;
        boatY = 500;

        otherBoats = new Rival[3];
        for(int i = 0; i<3; i++){
            int temp = ThreadLocalRandom.current().nextInt(0,10);
            otherBoats[i] = new Rival(4, temp);
        }

        System.err.println(otherBoats[0].getType());
        System.err.println(otherBoats[1].getType());
        System.err.println(otherBoats[2].getType());

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

        batch.draw(Assets.rival_current_frame_1, -16,boatY,512,512);
        batch.draw(Assets.rival_current_frame_2, 944,boatY,512,512);
        batch.draw(Assets.rival_current_frame_3, 1424,boatY,512,512);

        batch.draw(Assets.current_frame, boatX,boatY,512,512);


        font.draw(batch, "Distance: " + Double.toString(Math.floor(raceDist)) + "m",30,30);
        batch.end();
    }

    public void generalUpdate(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && boatX != -16){
            boatX -= 480;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D) && boatX != 1424){
            boatX += 480;
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
            playerSpeed = 8;
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
