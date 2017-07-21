package com.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import com.game.BattleChoice;
import com.game.BlankChoice;
import com.game.BlankEvent;
import com.game.Choice;
import com.game.Enemy;
import com.game.Event;

public class Utils {
	private static int it = 0;
	private static ArrayList<String> lines;
	private static EventsTree tree;
	
	public Utils() {
		tree = new EventsTree();
	}
	
	public EventsTree createEventsTree() {
		String path = System.getProperty("user.dir");
    	//ArrayList<String> lines = new ArrayList<String>();
		lines = this.lerArquivo(path + "/src/com/utils/book.txt");
		
		return this.create();
	}
	
	private EventsTree create() {	
		while (it < lines.size()) {
			String line = lines.get(it);
			
			if (line.contains("<evento>")) {
				line = lines.get(++it);
				String description = "";
				Collection<Choice> choices = new ArrayList<Choice>();
				
				while (!line.contains("</evento>")) {		        
					
					if (line.contains("<text>"))
						description += this.getText(description, "</text>");
					
					else if (line.contains("<escolha>"))
						this.getBlankChoices(choices);
					
					else if (line.contains("<batalha>"))
						this.getBattleChoice(choices);
					
					line = lines.get(++it);
				}
				
				Event event = new BlankEvent(description, choices);
				tree.setEvent(event);	
					
				return tree;
			}
			
			line = lines.get(++it);
		}

		return tree;
	}

	private String getText(String text, String tag) {
		String line = lines.get(++it);

		while (!line.contains(tag)) {
			text += line.replaceAll("\t", "");
			line = lines.get(++it);
		}
		
		it--;
		return text;
	}	
	
	private Choice getEnd(String text, String result) {
		EventsTree finalTree = new EventsTree();
		Event eventFinal = new BlankEvent(result, new ArrayList<Choice>());
		finalTree.setEvent(eventFinal);
		tree.insertEventsTree(finalTree);
		Choice choice = new BlankChoice(text.replaceAll("\t", ""), eventFinal);
		
		return choice;
	}
	
	private Choice getBlankEvent(String text, String result){
		EventsTree subTree = this.create();
		tree.insertEventsTree(subTree);
		Event eventForChoice = subTree.getEvent();
		Choice choice = new BlankChoice(text.replaceAll("\t", ""), eventForChoice, result);
		
		return choice;
	}
	
	private void getBlankChoices(Collection<Choice> choices){ 
		String text = "", result = "";
		String line = lines.get(it);
		
		while (!line.contains("</escolha>")) {

			if (line.contains("<text>"))
				text += this.getText(text, "</text>");

			else if (line.contains("<result>"))
				result += this.getText(result, "</result>");
			
			// recursividade
			else if (line.contains("<evento>"))
				choices.add(this.getBlankEvent(text, result));
			
			else if (line.contains("</end>"))
				choices.add(this.getEnd(text, result));
				
			line = lines.get(++it);
		}
	}
	
	private void getBattleChoice(Collection<Choice> choices) {
		String text = "", result = "", type = "";
		String line = lines.get(it);
		while (!line.contains("</batalha>")) {

			if (line.contains("<text>"))
				text += this.getText(text, "</text>");

			else if (line.contains("<tipo>"))
				type += this.getText(type, "</tipo>");			
			
			else if (line.contains("<result>"))
				result += this.getText(result, "</result>");
						
			// recursividade
			else if (line.contains("<evento>")) {
				Enemy enemy = new Enemy(0, 0);
				choices.add(this.getBattleEvent(enemy, text, type, result));
			}
			
			else if (line.contains("</end>"))
				choices.add(this.getEnd(text, result));
				
			line = lines.get(++it);
		}
	}
	
	private Choice getBattleEvent(Enemy enemy, String text, String type, String result) {
		EventsTree subTree = this.create();
		tree.insertEventsTree(subTree);
		Event eventForChoice = subTree.getEvent();
		Choice choice = new BattleChoice(text.replaceAll("\t", ""), eventForChoice, enemy, type, result);
		
		return choice;
	}
	
 	private ArrayList<String> lerArquivo(String file) {
		try {
			BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			ArrayList<String> lines = new ArrayList<String>();
			String temp = lerArq.readLine();
			
			while (temp != null) {
				lines.add(temp);
				temp = lerArq.readLine();
			}
			
			lerArq.close();
			return lines;
		}
		
		catch (IOException e) {
			System.err.println("Erro na abertura do arquivo: " + e.getMessage());
		}
		
		return null;
	}
}