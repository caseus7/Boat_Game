package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreen implements Screen{

    private BoatGame game;
    OrthographicCamera camera;
    SpriteBatch batch;

    float stateTime;

    int waterIndex1 = 1024;
    int waterIndex2 = -512;
    int waterIndex3 = 0;
    int waterIndex4 = 512;

    String selected = "start";

    BitmapFont font = new BitmapFont(Gdx.files.internal("BULKYPIX.fnt"),true);

    public MenuScreen(BoatGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();

        stateTime = 0F;
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

        batch.draw(Assets.current_frame, 1300,300,512,512);
        batch.draw(Assets.lIndicator, 1430,1000,256,-256);
        font.draw(batch, "Speed: " + BoatTypes.getStats(GameScreen.currentBoat)[0], 1650, 450);
        font.draw(batch, "Health: " + BoatTypes.getStats(GameScreen.currentBoat)[1], 1650, 550);

        batch.draw(Assets.caseusLogo,734,450, 512,-512);

        if(selected == "start") {
            batch.draw(Assets.startSelected, 734, 700, 512, -512);
            batch.draw(Assets.exit, 734, 900, 512, -512);
        }
        if(selected == "exit"){
            batch.draw(Assets.start, 734, 700, 512, -512);
            batch.draw(Assets.exitSelected, 734, 900, 512, -512);
        }

        batch.end();
    }

    private void generalUpdate() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN)){
            selected = "exit";
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)){
            selected = "start";
        }

        if(selected == "start" && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            game.setScreen(new GameScreen(game));
        }

        if(selected == "exit" && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyJustPressed((Input.Keys.L))){
            if(GameScreen.currentBoat < 9) {
                GameScreen.currentBoat += 1;
            }
            else{
                GameScreen.currentBoat = 0;
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
