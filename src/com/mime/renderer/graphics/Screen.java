package com.mime.renderer.graphics;

import java.util.Random;

public class Screen extends Render {

	private Render test;
	
	public Screen(int width, int height) {
		super(width, height);
		test = new Render(254, 254);
		Random random = new Random();
		for(int i = 0; i < 254 * 254; i++) {
			test.pixels[i] = random.nextInt();
		}
	}
	
	public void render() {
		draw(test, 0, 0);
	}
	
}
