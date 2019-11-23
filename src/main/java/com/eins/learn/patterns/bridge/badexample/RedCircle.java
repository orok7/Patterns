package com.eins.learn.patterns.bridge.badexample;

public class RedCircle implements DrawAPI {
	@Override
	public void draw(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
	}
}