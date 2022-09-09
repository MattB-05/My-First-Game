package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.firstgame.main.Game.STATE;

public class MenuEffect extends GameObject
{
	private Handler handler;
	
	Random r = new Random();
	
	float red = r.nextFloat();
	float green = r.nextFloat();
	float blue = r.nextFloat();

	Color randomColor = new Color(red, green, blue);
	
	public MenuEffect(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		boolean vertical = r.nextBoolean();
		
		if(!vertical) velX = (r.nextInt(5- -5)+1);
		if(vertical) velY = (r.nextInt(5- -5)+1);
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() 
	{
		if (Game.gameState != STATE.Game)
		{
			x += velX;
			y += velY;
			
			if (y <= 0 || y>= Game.HEIGHT -50 ) handler.removeObject(this);
			if (x <= 0 || x>= Game.WIDTH -26 ) handler.removeObject(this);
		
			handler.addObject(new Trail(x,y, ID.Trail, randomColor, 16, 16, 0.05f, handler));
		}
		
		
		

		
	}

	public void render(Graphics g) 
	{
		if (Game.gameState != STATE.Game)
		{
			g.setColor(randomColor);
			g.fillRect((int)x,(int)y, 16, 16);
		}
		
	}
	

	

}
