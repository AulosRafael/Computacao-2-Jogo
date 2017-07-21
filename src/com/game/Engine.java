package com.game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.utils.EventsTree;
import com.utils.Utils;


/**
 * Created by filipebraida on 31/05/16.
 */
public class Engine {
    public static void main(String[] args) throws IOException {
        Book book = Engine.createBook();

        System.out.println(book.showHistoryBook());

        Scanner in = new Scanner(System.in);

        do {
            System.out.println(book.showHistory());

            System.out.println("Escolha:  ");

            for(Choice choice : book.nextEvents()) {
                System.out.println(choice.getDescription());
            }

            int i;
            do {
                i = in.nextInt();
            } while(!book.nextEvent(i));
            
        } while(!book.isTheEnd());

        System.out.println(book.showHistory());
        in.close();
    }

    public static Book createBook() {
    	Utils utils = new Utils();
    	EventsTree tree = utils.createEventsTree();
    	
    	Character Flamengo = new Player(10, 100);    	
    	Book livro = new Book("Partida: ", tree, (Player) Flamengo);
        return livro;
    }
}
