package com.firstgame.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class BasicEnemy extends GameObject
{

	public BasicEnemy(int x, int y, ID id) 
	{
		super(x, y, id);
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		if (y <= 0 || y>= Game.HEIGHT -50 ) velY *= -1;
		if (x <= 0 || x>= Game.WIDTH -26 ) velX *= -1;
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.red);
		g.fillRect(x,y, 16, 16);
	}
	
}
