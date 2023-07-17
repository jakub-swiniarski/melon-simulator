package com.jswin.melon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MelonSimulator extends ApplicationAdapter {
	SpriteBatch batch;
	boolean fullscreen;
	List<Melon> melon;
	Vector3 mousePos;
	float dt;
	int id;

	//REMEMBER TO USE DELTA TIME FOR MOVEMENT

	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;

		melon=new ArrayList<Melon>();
		id=0;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		dt=Gdx.graphics.getDeltaTime();

		//spawn melons
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
			mousePos=new Vector3(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY(),0);
			Melon newMelon = new Melon(mousePos.x, mousePos.y,id);
			melon.add(newMelon);
			id++;
		}

		//fullscreen on/off
		if(Gdx.input.isKeyJustPressed(Input.Keys.F11)){
			fullscreen=!fullscreen;
		}
		if(fullscreen){
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
		else{
			Gdx.graphics.setWindowedMode(1280,720);
		}

		//rendering
		batch.begin();
		for (Melon m : melon) {
			m.update(batch, dt);
			for (Melon m2 : melon){
				m.checkForCollisions(m2);
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Melon m : melon) {
		 	m.dispose();
		}
	}
}
