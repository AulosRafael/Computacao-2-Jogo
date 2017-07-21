package com.game;
/**
 * Created by filipebraida on 31/05/16.
 */
public class Character {
	private int life;
    private int attack;

	public Character(int life, int attack) {
        this.life = life;
        this.attack = attack;
    }

    public void battleAttack(Character enemy) {
    	if (this.getAttack() > enemy.getAttack())
    		this.takeDamage(); // gol do player
    }

    public void battleDefense(Character enemy) {
    	if (this.getAttack() < enemy.getAttack())
    		enemy.takeDamage(); // gol do adversario
    }    
    
    
    public boolean isAlive() {
        if(this.life > 0)
            return true;

        return false;
    }

    public void takeDamage() {
        this.life += 1;
    }

    public int getLife() {
    	return this.life;
    }
    
    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
		this.attack = attack;
	}
}
