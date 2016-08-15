package com.hostel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 *
 * @author Jaakko Niska
 */
public class Raoul /*implements TextInputListener*/ {
    public Rectangle raoul;
    private final Texture raoulImage;
    private TextureRegion currentFrame;
    float stateTime = 0f;
    float speed=15;
    int dir;
    Sound thud;
    boolean w,a,s,d,wa,as,sd,wd;
    float delay = 10;
    String message;
       
    public Raoul(){
        raoul = new Rectangle();
        raoulImage = new Texture(Gdx.files.internal("raoulDuke.png"));
        raoul.x = 50;
        raoul.y = 7;
        raoul.width = 25;
        raoul.height = 25;
        currentFrame = new TextureRegion(raoulImage);
        thud=Gdx.audio.newSound(Gdx.files.internal("kick.wav"));
        dir=0;
        w=a=s=d=wa=as=sd=wd=false;
    }
    
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, raoul.x, raoul.y);
    }
    public void move(){
        float tick = Gdx.graphics.getDeltaTime();
        
        w = Gdx.input.isKeyPressed(Input.Keys.UP);
        a = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        s = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        d = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        wa = Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT);
        as = Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN);
        sd = Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        wd = Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        
        if(wa==true) {dir=5; speed=12;}
        else if(as==true) {dir=6; speed=12;}
        else if(sd==true) {dir=7; speed=12;}
        else if(wd==true) {dir=8; speed=12;}
        else if(a==true) {dir=2; speed=15;}
        else if(s==true) {dir=3; speed=15;}
        else if(d==true) {dir=4; speed=15;}
        else if(w==true) {dir=1; speed=15;}
        else dir=0;
        
        switch(dir){
            case 0:
                speed=0;
                break;
            case 1:
                raoul.y += speed * tick;
                anim(1);
                break;
            case 2:
                raoul.x -= speed * tick;
                anim(2);
                break;
            case 3:
                raoul.y -= speed * tick;
                anim(3);
                break;
            case 4:
                raoul.x += speed * tick;
                anim(4);
                break;
            case 5:
                raoul.x -= speed*tick;
                raoul.y += speed*tick;
                anim(2);
                break;
            case 6:
                raoul.y-=speed*tick;
                raoul.x-=speed*tick;
                anim(2);
                break;
            case 7:
                raoul.x+=speed*tick;
                raoul.y-=speed*tick;
                anim(4);
                break;
            case 8:
                raoul.x+=speed*tick;
                raoul.y+=speed*tick;
                anim(4);
                break;               
        }
        //SCREEN EDGES
        if(raoul.x < 2 && dir==2 || raoul.x < 2 && dir==1 || raoul.x < 2 && dir==5 || raoul.x < 2 && dir==6) {
            thud.play();
            raoul.x +=4;
        }
        if(raoul.x > 330 - 30 && dir==4 || raoul.x > 330 - 30 && dir==3 || raoul.x > 330 - 30 && dir==1 || raoul.x > 330 - 30 && dir==7 || raoul.x > 330 - 30 && dir==8) {
            thud.play();
            raoul.x -=4;
        }
        else if(raoul.y < 0 && dir==2 || raoul.y < 0 && dir==3 || raoul.y < 0 && dir==6 || raoul.y < 0 && dir==7) {
            thud.play();
            raoul.y+=4;
        }
        else if(raoul.y > 148 && dir==1 || raoul.y > 148 && dir==5 || raoul.y > 148 && dir==8) {
            thud.play();
            raoul.y-=4;
        }
    }
    public void collide(Wall wall) {
        Rectangle colli=new Rectangle(wall.x,wall.y,wall.width,wall.height);
        
        if(raoul.overlaps(colli)){
            thud.play();
            switch(dir){
                case 1: 
                    raoul.y-=4;
                    break;
                case 2: 
                    raoul.x+=4;
                    break;
                case 3:
                    raoul.y+=4;
                    break;
                case 4:
                    raoul.x-=4;
                    break;
                case 5:
                    raoul.x+=4;
                    raoul.y-=4;
                    break;
                case 6:
                    raoul.x+=4;
                    raoul.y+=4;
                    break;
                case 7:
                    raoul.x-=4;
                    raoul.y+=4;
                    break;
                case 8:
                    raoul.x-=4;
                    raoul.y-=4;
                    break;                    
            }            
        }
    }
    
    public void anim(int state) {
        int frame_cols = 8;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();
        
        Animation anim;
        Texture animSheet;
        animSheet = raoulImage;
        TextureRegion[] animFrames;
        
        if(state==1) animSheet = new Texture(Gdx.files.internal("raoulWalkUp.png"));
        if(state==2) animSheet = new Texture(Gdx.files.internal("raoulWalkLeft.png"));
        if(state==3) animSheet = new Texture(Gdx.files.internal("raoulWalkFront.png"));
        if(state==4) animSheet = new Texture(Gdx.files.internal("raoulWalkRight.png"));
        
        TextureRegion[][] tmp = TextureRegion.split(animSheet, animSheet.getWidth()/frame_cols, animSheet.getHeight()/frame_rows);
        animFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.075f, animFrames);
        stateTime += tick;
        currentFrame = anim.getKeyFrame(stateTime, true);
    }
    
    /*public boolean talk(){
        float tick=Gdx.graphics.getDeltaTime();
        delay+=tick;
                
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            Gdx.input.getTextInput(this, "Say:", null, null);
        }
        while (delay<5) {
            return true;            
        }
        return false;
    }
    public String sayWhat(){
        return message;
    }

    @Override
    public void input(String text) {
        message=text;
        delay=0;        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void canceled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
