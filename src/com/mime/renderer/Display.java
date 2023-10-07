package com.mime.renderer;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mime.renderer.graphics.Render;
import com.mime.renderer.graphics.Screen;

public class Display extends Canvas implements Runnable {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Renderer";
	
	private Thread thread;
	private boolean running = false;
	private BufferedImage img;
	private Screen screen;
	private Render render;
	private int[] pixels;
	
	public Display() {
		screen = new Screen(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}
	
	private void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println("Starting...");
	}
	
	private void stop() {
		if(!running) return;
		running = false;
		thread.interrupt();
		System.out.println("Stopping...");
	}
	
	public void run() {
		while(running) {
			tick();
			render();
		}
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.render();
		for(int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		Display display = new Display();
		JFrame frame = new JFrame();
		frame.add(display);
		frame.pack();
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		System.out.println("Running...");
		display.start();
	}
}
