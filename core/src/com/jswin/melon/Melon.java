package com.jswin.melon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Melon {
    private final Rectangle rect;
    private final Texture img;
    private final TextureRegion imgR;
    private boolean falling;
    private float velocityUP, velocityRIGHT;
    private float rotation;
    private final int id;

    public Melon(float x, float y, int id){
        img=new Texture(Gdx.files.internal("melon.png"));
        imgR=new TextureRegion(img,0,0,104,114);

        this.id=id;

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
            // ----------IMPORTANT----------
            // ORIGIN X AND ORIGIN Y ARE
            // USED TO SHIFT THE ROTATION
            // AXIS!!!
            //------------------------------
            batch.draw(imgR,rect.x,rect.y,rect.width/2,rect.height/2,rect.width,rect.height,1,1,rotation);
        }

        //gravity
        falling= rect.y > 0;
        if(falling){
            velocityUP-=10;

            //rotate when falling
            if(rotation>=0){
                rotation+=10*dt;
            }
            else{
                rotation-=10*dt;
            }
        }
        if(rect.y<=0){
            rect.y=0;
            velocityUP=0;
        }

        //update position
        rect.x+=velocityRIGHT*dt;
        rect.y+=velocityUP*dt;

        //left & right borders
        if(rect.x<=0){
            rect.x=0;
            velocityRIGHT*=-0.5;
        }
        else if(rect.x+rect.width>=Gdx.graphics.getWidth()){
            rect.x=Gdx.graphics.getWidth()-rect.width;
            velocityRIGHT*=-0.5;
        }

        if(velocityRIGHT>0){
            velocityRIGHT-=5;
        }
        else if(velocityRIGHT<0){
            velocityRIGHT+=5;
        }

        //BOUNCE UP AFTER FALLING
        //ROTATE WHEN MOVING
        //LEFT CLICK - PICK MELON UP
    }

    public void dispose(){
        img.dispose();
    }

    public void checkForCollisions(Melon m){
        if(rect.overlaps(m.rect) && id!=m.id){
            //retarded, fix later
            if(rect.x>m.rect.x){
                velocityRIGHT+=Math.abs(rect.x-m.rect.x);
                m.velocityRIGHT-=Math.abs(rect.x-m.rect.x);
            }
            else{
                velocityRIGHT-=Math.abs(rect.x-m.rect.x);
                m.velocityRIGHT+=Math.abs(rect.x-m.rect.x);
            }

            if(rect.y>m.rect.y){
                velocityUP+=Math.abs(rect.y-m.rect.y);
                m.velocityUP-=Math.abs(rect.y-m.rect.y);
            }
            else{
                velocityUP-=Math.abs(rect.y-m.rect.y);
                m.velocityUP+=Math.abs(rect.y-m.rect.y);
            }
        }
    }
}
