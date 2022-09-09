package com.firstgame.main;

 

import java.awt.Graphics;
import java.awt.Color;

public class HUD 
{
	
	public static float HEALTH = 100;
	
	public static float P2HEALTH = 100;
	
	public static boolean VS= false;
	
	private float score = 0;
	private String level = "1";
	
	public void tick()
	{
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		score++;
	}
	
	public void render(Graphics g)
	{
		//base
		g.setColor(Color.red);
		g.fillRect(16, 16, 200, 32);
		
		//current health 
		g.setColor(Color.green);
		g.fillRect(15, 15,(int) HEALTH*2, 32);
		
		//border
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + (int)score, 15, 64);
		
		if (level.equalsIgnoreCase("boss")) g.drawString("Level: " + level, 15, 80);
		else g.drawString("Level: " + Math.round(Float.valueOf(level)), 15, 80);
		
		//VS HUD for P2
		if(VS)
		{
			//base
			g.setColor(Color.red);
			g.fillRect(416, 16, 200, 32);
			
			//current health 
			g.setColor(Color.pink);
			g.fillRect(415, 15,(int) P2HEALTH*2, 32);
			
			//border
			g.setColor(Color.white);
			g.drawRect(415, 15, 200, 32);
			
			g.drawString("Score: " + (int)score, 415, 64);
		
			
			if (level.equalsIgnoreCase("boss")) g.drawString("Level: " + level, 415, 80);
			else g.drawString("Level: " + Math.round(Float.valueOf(level)), 415, 80);
		}
		
	}
	
	public float getScore()
	{
		return score;
	}
	
	public void setScore(float score)
	{
		this.score = score;
	}
	
	public String getLevel()
	{
		return level;
	}
	
	public void setLevel(float level)
	{
		this.level = String.valueOf(level);
	}
	
	public void setLevel(String level)
	{
		this.level = level;
	}

}
