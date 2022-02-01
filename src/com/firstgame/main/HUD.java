package com.firstgame.main;

import java.awt.Graphics;
import java.awt.Color;

public class HUD 
{
	
	public static int HEALTH = 100;
	
	public void tick()
	{
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
	}
	
	public void render(Graphics g)
	{
		//base
		g.setColor(Color.red);
		g.fillRect(15, 15, 200, 32);
		
		//current health 
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH*2, 32);
		
		//border
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	}

}
