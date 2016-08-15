/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hostel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaakk_000
 */
public class Walls {
    ArrayList<Wall> walls=new ArrayList<Wall>();
    Wall wall;
    Wall[] list;
    int color,x,y,wx,wy,width,height,i,j;
    Pixmap colli;
    int[][] popul=new int[185][330];
        
    public ArrayList build() {
        colli=new Pixmap(Gdx.files.internal("wallMask.png"));        
        
        while(y<colli.getHeight()){
            while(x<colli.getWidth()){
                color=colli.getPixel(x, y);
                if(color==255 && wx==0){
                    wx=x;
                    wy=y;
                    i=wy;
                    j=wx;
                    while(width==0){
                        color=colli.getPixel(x,y);
                        if(color!=255){
                            width=x-wx;
                            x-=1;
                            while(height==0){
                                color=colli.getPixel(x, y);
                                y+=1;
                                if(color!=255){
                                    height=y-wy;
                                    wall=new Wall(wx,wy,width,height);
                                    if(popul[wy][wx]==0) {
                                        walls.add(wall);
                                    }
                                    for(int k=0; k<height; k++){
                                        for(int l=0; l<width; l++){
                                            popul[i][j]=1;
                                            j+=1;
                                        }
                                        j=wx;
                                        i+=1;
                                    }    
                                    x+=1;
                                    y=y-height;
                                }                                
                            }
                            height=0;
                        }
                        x+=1;
                    }
                    width=0;
                }
                x+=1;
                wx=0;
            }
            x=0;
            y+=1;
        }
        return walls;
    }
}
