package com.jswin.melon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MelonSimulator extends ApplicationAdapter {
	SpriteBatch batch;
	Melon m;

	@Override
	public void create () {
		batch = new SpriteBatch();
		m=new Melon();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		m.update(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		m.dispose();
	}
}
