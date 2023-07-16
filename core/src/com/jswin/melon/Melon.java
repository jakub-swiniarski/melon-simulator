package com.jswin.melon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Melon {
    private Rectangle rect;
    private Texture img;
    private boolean falling;
    private float velocityUP, velocityRIGHT;

    public Melon(float x, float y){
        img=new Texture(Gdx.files.internal("melon.png"));

        rect=new Rectangle();
        rect.width=104;
        rect.height=114;
        rect.x=x-rect.width/2;
        rect.y=y-rect.height/2;

        falling=true;
        velocityUP=0;
        velocityRIGHT=0;
    }

    public void update(SpriteBatch batch, float dt){
        //draw
        batch.draw(img,rect.x,rect.y);

        //gravity
        if(falling){
            velocityUP-=10*dt;
        }

        //update position
        rect.x+=velocityRIGHT;
        rect.y+=velocityUP;
    }

    public void dispose(){
        img.dispose();
    }

    public void checkForCollisions(){

    }
}
