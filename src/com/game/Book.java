package com.game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.utils.EventsTree;

/**
 * Created by filipebraida on 31/05/16.
 */
public class Book {
    private Event eventActually;
    private EventsTree tree;
    private String description;
    private Player player;
    
    public Book(String description, EventsTree tree, Player player) {
        this.tree = tree;
        this.description = description;
        this.player = player;

        this.resetHistory();
    }

    public void resetHistory() {
        this.eventActually = this.tree.getEvent();
    }

    public String showHistory() {
        return this.eventActually.history();
    }

    public boolean isTheEnd() {
        return this.eventActually.isEndEvent();
    }

    public String showHistoryBook(){
        return this.description;
    }

    public boolean nextEvent(int number) {
        Choice choice = this.selectChoice(number);

        if(choice != null) {
            choice.executeChoice(player);

            if(player.isAlive()) {
                this.eventActually = choice.getEvent();
                this.eventActually.applyHistory(player);
            }
            else {
                Event gameOver = new BlankEvent("Game Over", new ArrayList<Choice>());
                this.eventActually = gameOver;
            }

            return true;
        }

        return false;
    }

    public Choice selectChoice(int number) {
        return this.eventActually.findChoice(number);
    }

    public Collection<Choice> nextEvents() {
        return this.eventActually.nextEvents();
    }
}
