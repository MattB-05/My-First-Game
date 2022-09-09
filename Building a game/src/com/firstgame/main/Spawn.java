package com.firstgame.main;

 

import java.util.Random;

import com.firstgame.main.Game.STATE;

public class Spawn 
{
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public static float scoreKeep = 0;
	public static boolean Tie = false;
	
	public Spawn(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void tick()
	{
		scoreKeep++;
		
		if (scoreKeep >= 500)
		{
			scoreKeep = 0;
			if (hud.getLevel().equalsIgnoreCase("BOSS"))
			{
				//first boss level end
				if (hud.getScore() >= 3500) hud.setLevel(6);
				else return;
				
				//second boss
				
			}
			else hud.setLevel((Float.valueOf(hud.getLevel())) + 1);
			
			
			if ((Float.valueOf(hud.getLevel())) == 2)
			{
				//Level 2
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			else if ((Float.valueOf(hud.getLevel())) == 3)
			{
				//Level 3
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			}
			else if ((Float.valueOf(hud.getLevel())) == 4)
			{
				//Level 4
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			
			else if ((Float.valueOf(hud.getLevel())) == 5)
			{
				//Level 5
				if (hud.getScore() <= 2000) hud.setLevel(4);
				else if(hud.getScore() >= 2500) 
				{
					handler.clearEnemies();
					hud.setLevel("BOSS"); 
					handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));
				}
			}
			else if ((Float.valueOf(hud.getLevel())) == 6)
			{
				//Win
				handler.clearEnemies();
				if(HUD.VS) Tie = true; 
				Game.gameState = STATE.Win;
				
			}
			
		}
	}

}
