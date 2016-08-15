/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hostel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import java.util.ArrayList;

/**
 *
 * @author jaakk_000
 */
public class TextInput extends TextField{
    String typed;
    char kirjain;
    ArrayList<Character> sana;
    InputProcessor mo;
    StringBuilder builder = new StringBuilder();
    boolean flag;
    
    public TextInput(String text, TextFieldStyle style) {
        super(text, style);
        
        
    }
    public void roll(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            flag=true;
            //mo.
            
            while (flag){        
                    if (mo.keyTyped(kirjain)){
                        builder.append(kirjain);
                    }
                    //typed=builder.toString();
                    //this.appendText(typed);
                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) flag=false;
            }
        }
    }
}
