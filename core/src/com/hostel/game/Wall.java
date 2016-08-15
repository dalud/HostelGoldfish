/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hostel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author jaakk_000
 */
public class Wall {
    Texture tex;
    float x,y,width,height;
    
    public Wall(float xx, float yy, float wwidth, float wheight) {
        x=xx;
        y=yy;
        width=wwidth;
        height=wheight;
        
        tex=new Texture(Gdx.files.internal("wall.png"));
    }
    public void draw(SpriteBatch batch) {
        batch.draw(tex, x, y, width, height);
    }
    
}
