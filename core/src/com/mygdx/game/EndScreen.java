package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EndScreen implements Screen{

    private BoatGame game;
    OrthographicCamera camera;
    SpriteBatch batch;

    float stateTime;

    int waterIndex1 = 1024;
    int waterIndex2 = -512;
    int waterIndex3 = 0;
    int waterIndex4 = 512;

    String selected = "again";

    public static String place = "first";

    BitmapFont font = new BitmapFont(Gdx.files.internal("BULKYPIX.fnt"),true);


    public EndScreen(BoatGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();

        stateTime = 0F;

        font.getData().setScale(4,4);
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

        batch.setProjectionMatrix(camera.combined);

        stateTime += Gdx.graphics.getDeltaTime();
        Assets.current_frame = (TextureRegion) Assets.boat_animations[GameScreen.currentBoat].getKeyFrame(stateTime, true);

        batch.begin();

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

        if(place == "first") {
            batch.draw(Assets.first, 734, 450, 512, -512);
            font.draw(batch, "VICTORY!", 650, 300);
        }
        if(place == "second") {
            batch.draw(Assets.second, 734, 450, 512, -512);
            font.draw(batch, "WELL DONE!", 650, 300);
        }
        if(place == "third") {
            batch.draw(Assets.third, 734, 450, 512, -512);
            font.draw(batch, "PODIUM FINISH!", 650, 300);
        }
        if(place == "fourth") {
            batch.draw(Assets.fourth, 734, 450, 512, -512);
            font.draw(batch, "Everyone is a WINNER!", 650, 300);
        }

        if(selected == "again") {
            batch.draw(Assets.againSelected, 734, 900, 512, -512);
            batch.draw(Assets.menu, 734, 1100, 512, -512);
        }
        if(selected == "menu"){
            batch.draw(Assets.again, 734, 900, 512, -512);
            batch.draw(Assets.menuSelected, 734, 1100, 512, -512);
        }

        batch.end();
    }

    private void generalUpdate() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN)){
            selected = "menu";
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)){
            selected = "again";
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            place = "second";
            System.err.println(place);
        }

        if(selected == "again" && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            game.setScreen(new GameScreen(game));
        }

        if(selected == "menu" && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            game.setScreen(game.menuScreen);
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
