package com.hostel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;

public class Hostel extends ApplicationAdapter {
	private OrthographicCamera camera;
        private SpriteBatch batch;
        private Raoul raoul;
        private Texture floor1Image, objects, cursorTex;
        private Music hissiMusic;
        private Walls walls;
        ArrayList<Wall> wallList;
        //BitmapFont font;
        //public boolean mailInc;
        //public TextInput input;
        //public TextFieldStyle style;
        //public TextureRegion cursorTexReg;
        //public TextureRegionDrawable cursor;
        
                        
        @Override
        public void create() {
            camera = new OrthographicCamera();
            batch = new SpriteBatch();
            floor1Image = new Texture(Gdx.files.internal("1floor.png"));
            objects=new Texture(Gdx.files.internal("objects.png"));
            hissiMusic = Gdx.audio.newMusic(Gdx.files.internal("PnV.mp3"));
            raoul = new Raoul();
            walls=new Walls();
            wallList=walls.build();
            camera.setToOrtho(false, 330, 186);
            //font=new BitmapFont(Gdx.files.internal("font.fnt"));
            //cursorTex=new Texture(Gdx.files.internal("theAbyss.png"));
            //cursorTexReg=new TextureRegion(cursorTex,1,1);
            //cursor = new TextureRegionDrawable(cursorTexReg);
            //style = new TextFieldStyle(font, Color.BLACK, cursor, null,null);
            //input = new TextInput("say:", style);
            
            
            hissiMusic.setLooping(true);
            hissiMusic.play();
            
            //input.setPosition(2, 2);
            //style.cursor.setMinWidth(1);
        }
              
        @Override
        public void render() {          
            camera.update();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(camera.combined);
            
            batch.begin();
            batch.draw(floor1Image, 0, 0);
            raoul.draw(batch);
            batch.draw(objects,0,0);
            //input.draw(batch, 1);
            //input.roll();
            //if (mailInc) font.draw(batch, raoul.sayWhat(), raoul.raoul.x+20, raoul.raoul.y+25, 30, 0, true);
            batch.end();
            
            for(Wall wall:wallList){
                raoul.collide(wall);
            }
            raoul.move();
            
            //mailInc=raoul.talk();
            }
}
