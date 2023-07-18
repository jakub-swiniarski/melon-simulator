package com.jswin.melon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MelonSimulator extends ApplicationAdapter {
	SpriteBatch batch;
	boolean fullscreen;
	List<Melon> melon;
	float dt;
	int id;
	public Rectangle mouse;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;

		melon=new ArrayList<Melon>();
		id=0;

		mouse= new Rectangle();
		mouse.width=1;
		mouse.height=1;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		dt=Gdx.graphics.getDeltaTime();

		mouse.x=Gdx.input.getX();
		mouse.y=Gdx.graphics.getHeight()-Gdx.input.getY();

		//grab and move melons around
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			for(Melon m: melon){
				m.grabCheck(mouse);
			}
		}

		//spawn melons
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
			Melon newMelon = new Melon(mouse.x, mouse.y,id);
			melon.add(newMelon);
			id++;
		}

		//fullscreen on/off
		//----------------------------------------------------------------------
		//TURNED OFF CUZ MELONS SPAWN IN THE WRONG PLACE WHEN IN FULLSCREEN
		//THIS ALSO HAPPENS WHEN THE WINDOW IS MAXIMIZED
		//----------------------------------------------------------------------
		/*if(Gdx.input.isKeyJustPressed(Input.Keys.F11)){
			fullscreen=!fullscreen;
		}
		if(fullscreen){
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
		else{
			Gdx.graphics.setWindowedMode(1280,720);
		}*/

		//rendering
		batch.begin();
		for (Melon m : melon) {
			m.update(batch, dt);
			for (Melon m2 : melon){
				m.collisionCheck(m2);
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
