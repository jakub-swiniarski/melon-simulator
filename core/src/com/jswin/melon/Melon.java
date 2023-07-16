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
    private int id;

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
            batch.draw(imgR,rect.x,rect.y,0,0,rect.width,rect.height,1,1,rotation);
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

        //LOSE VELOCITY AFTER TIME!!!
        //BOUNCE UP
    }

    public void dispose(){
        img.dispose();
    }

    public void checkForCollisions(Melon m){
        if(rect.overlaps(m.rect) && id!=m.id){
            //retarded, fix later
            velocityRIGHT-=10*Math.abs(rect.x-m.rect.x);
            velocityUP-=10*Math.abs(rect.y-m.rect.y);

            m.velocityRIGHT+=10*Math.abs(rect.x-m.rect.x);
            m.velocityUP+=10*Math.abs(rect.y-m.rect.y);
        }
    }
}
