package com.game;

import java.util.Random;

/**
 * Created by filipebraida on 31/05/16.
 */
public class BattleChoice extends Choice {
	private String type;
	private Enemy enemy;
    
	public BattleChoice(String description, Event event, Enemy enemy, String type) {
        super(description, event);
        this.type = type;
        this.enemy = enemy;
    }

	public BattleChoice(String description, Event event, Enemy enemy, String type, String result) {
        super(description, event, result);
        this.type = type;
        this.enemy = enemy;
    }	

	public Enemy getEnemy() {
		return this.enemy;
	}	
	
	public String getType() {
		return this.type;
	}
	
    @Override
    public void executeChoice(Character character) {
    	Random gerador = new Random();
    	int lifePlayer = character.getLife();
    	int lifeEnemy = enemy.getLife();
    	
    	character.setAttack(gerador.nextInt(100));
    	this.getEnemy().setAttack(gerador.nextInt(100));
    	
    	if (this.getType() == "ataque") character.battleAttack(this.getEnemy());
      	else character.battleDefense(this.getEnemy());
    	
		if (character.getLife() > lifePlayer || enemy.getLife() > lifeEnemy) 
			System.out.println("GOOOOOOOLLLLLLLLL!!!");
		
		else System.out.println("Defendeuuuu!!!");
    }
}
