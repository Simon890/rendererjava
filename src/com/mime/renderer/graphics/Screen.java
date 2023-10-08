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
		for(int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}
		for(int i = 0; i < 50; i++) {
			int anim = (int) (Math.sin((System.currentTimeMillis() + i * 2.5) % 2000.0 / 2000 * Math.PI * 4) * 80);
			int anim2 = (int) (Math.cos((System.currentTimeMillis() + i * 2.5) % 2000.0 / 2000 * Math.PI * 4) * 80);
			draw(test, (width - 254) / 2 + anim, (height - 254) / 2 - anim2);			
		}
	}
	
}
