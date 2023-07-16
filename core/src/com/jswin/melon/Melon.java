package com.jswin.melon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Melon {
    private Rectangle rect;
    private Texture img;
    private TextureRegion imgR;
    private boolean falling;
    private float velocityUP, velocityRIGHT;
    private float rotation;

    public Melon(float x, float y){
        img=new Texture(Gdx.files.internal("melon.png"));
        imgR=new TextureRegion(img,0,0,104,114);

        rect=new Rectangle();
        rect.width=104;
        rect.height=114;
        rect.x=x-rect.width/2;
        rect.y=y-rect.height/2;

        falling=true;
        rotation=0;
        velocityUP=0;
        velocityRIGHT=0;
    }

    public void update(SpriteBatch batch, float dt){
        //check if visible and draw
        if(rect.x+rect.width>0 && rect.x<Gdx.graphics.getWidth() &&
        rect.y+rect.height>0 && rect.y<Gdx.graphics.getHeight()){
            batch.draw(imgR,rect.x,rect.y,0,0,rect.width,rect.height,1,1,rotation);
        }

        //gravity
        falling= rect.y > 0;
        if(falling){
            velocityUP-=10*dt;

            //rotate when falling
            if(rotation>=0){
                rotation+=10*dt;
            }
            else{
                rotation-=10*dt;
            }
        }
        else if(velocityUP<0){
            velocityUP=0;
        }

        //update position
        rect.x+=velocityRIGHT;
        rect.y+=velocityUP;
    }

    public void dispose(){
        img.dispose();
    }

    public void checkForCollisions(Melon m){
        if(rect.overlaps(m.rect)){
            //do smth
        }
    }
}
