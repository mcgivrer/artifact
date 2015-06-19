package com.neet.artifact.game.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.neet.artifact.game.resources.ContentManager;
import com.webcontext.game.framework.entity.MapObject;
import com.webcontext.game.framework.gfx.tilemap.TileMap;

public class Explosion extends MapObject {
	
	private BufferedImage[] sprites;
	
	private boolean remove;
	
	private Point[] points;
	private int speed;
	private double diagSpeed;
	
	public Explosion(TileMap tm, int x, int y) {
		
		super(tm);
		
		this.x = x;
		this.y = y;
		
		width = 30;
		height = 30;
		
		speed = 2;
		diagSpeed = 1.41;
		
		sprites = ContentManager.getImageBank("Explosion")[0];
		
		animation.setFrames(sprites);
		animation.setDelay(6);
		
		points = new Point[8];
		for(int i = 0; i < points.length; i++) {
			points[i] = new Point(x, y);
		}
		
	}

	public boolean shouldRemove() { return remove; }
	
	public void draw(Graphics2D g) {
		setMapPosition();
		for(int i = 0; i < points.length; i++) {
			g.drawImage(
				animation.getImage(),
				(int) (points[i].x + xmap - width / 2),
				(int) (points[i].y + ymap - height / 2),
				null
			);
		}
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void update(long delta) {
		animation.update(delta);
		if(animation.hasPlayedOnce()) {
			remove = true;
		}
		points[0].x += speed;
		points[1].x += diagSpeed;
		points[1].y += diagSpeed;
		points[2].y += speed;
		points[3].x -= diagSpeed;
		points[3].y += diagSpeed;
		points[4].x -= speed;
		points[5].x -= diagSpeed;
		points[5].y -= diagSpeed;
		points[6].y -= speed;
		points[7].x += diagSpeed;
		points[7].y -= diagSpeed;
	}
	
}