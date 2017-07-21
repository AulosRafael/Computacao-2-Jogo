package com.utils;

import com.game.Event;

public class EventsTree {
	private Event event;	
	private EventsTree firstSon;
	private EventsTree nextBrother;
	

	// GET and SET
	public Event getEvent() {
		return this.event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public EventsTree getFirstSon() {
		return firstSon;
	}

	public void setFirstSon(EventsTree firstSon) {
		this.firstSon = firstSon;
	}

	public EventsTree getNextBrother() {
		return nextBrother;
	}

	public void setNextBrother(EventsTree nextBrother) {
		this.nextBrother = nextBrother;
	}

	// METODOS
	public void insertEventsTree(EventsTree newEventTree) {
		newEventTree.setNextBrother(this.getFirstSon());
		this.setFirstSon(newEventTree);
	}
}
