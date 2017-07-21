package com.game;
/**
 * Created by filipebraida on 31/05/16.
 */
public abstract class Choice {
    private String description;
    private String result;
    private Event event;
    private int number;

    public Choice(String description, Event event) {
        this.event = event;
        this.description = description;
        this.result = "";
    }
    
    public Choice(String description, Event event, String result) {
        this.event = event;
        this.description = description;
        this.result = result;
    }

    public String getDescription() {
        return number + ": "+ this.description;
    }  
    
    public void defineNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public Event getEvent() {
        return this.event;
    }

    public String getResult() {
        return this.result;
    }    
    
    public abstract void executeChoice(Character character);
}
