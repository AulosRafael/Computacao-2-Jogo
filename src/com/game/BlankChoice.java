package com.game;
/**
 * Created by filipebraida on 31/05/16.
 */
public class BlankChoice extends Choice {
    public BlankChoice(String description, Event event) {
        super(description, event);
    }
    
	public BlankChoice(String description, Event event, String result) {
        super(description, event, result);
    }

    @Override
    public void executeChoice(Character character) {
    	System.out.println(this.getResult());
    }
}
