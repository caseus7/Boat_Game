package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {

    private BoatGame game;
    OrthographicCamera camera;
    SpriteBatch batch;

    float stateTime;
    int boatX;

    public GameScreen(BoatGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();
        stateTime = 0F;

        boatX = 704;

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
        Assets.current_frame = (TextureRegion) Assets.boat_animation.getKeyFrame(stateTime, true);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(Assets.current_frame, boatX,284,512,512);
        batch.end();
    }

    public void generalUpdate(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            boatX -= 100;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            boatX += 100;
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