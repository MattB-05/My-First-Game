package com.firstgame.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject
{
	private float alpha = 1;
	private Handler handler;
	private Color color;

	public Trail(int x, int y, ID id, Color color, Handler handler) 
	{
		super(x, y, id);
		
		this.color = color;
	}

	public void tick() 
	{
		
	}

	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
	}

	private AlphaComposite makeTransparent(float alpha)
	{
		int type = AlphaComposite.SRC_OVER;
		
		return AlphaComposite.getInstance(type, alpha);
	}
	
	public Rectangle getBounds() 
	{
		return null;
	}

}
