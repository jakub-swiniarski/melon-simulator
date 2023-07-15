package com.jswin.melon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Melon {
    private Rectangle rect;
    private Texture img;

    public Melon(float x, float y){
        img=new Texture(Gdx.files.internal("melon.png"));
        rect=new Rectangle();
        rect.width=104;
        rect.height=114;
        rect.x=x;
        rect.y=y;
    }

    public void update(SpriteBatch batch){
        batch.draw(img,rect.x,rect.y);
    }

    public void dispose(){
        img.dispose();
    }

    public void checkForCollisions(){

    }
}
