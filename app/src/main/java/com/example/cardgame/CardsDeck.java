package com.example.cardgame;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardsDeck {
    public static final String TAG = "CardsDeck";
    private ArrayList<String> cardDeck;
    private final int DECK_SIZE = 52;
    private List<String> list1;
    private List<String> list2;
    private int count = 0;

    private static final String[] symbols = new String[] {"heart", "diamond", "spade", "club"};
    private static final ArrayList<String> values = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"));

    public CardsDeck() {
        this.cardDeck = new ArrayList<>();
    }

    public void shuffle() {
        for (String symbol: symbols) {
            for (String value: values) {
                cardDeck.add("poker_" + symbol + "_" + value);
            }
        }
        Collections.shuffle(cardDeck);
        Log.d(TAG, "cardDeck: " + cardDeck);
        count = 0;
    }

    public void split() {
        list1 = cardDeck.subList(0, DECK_SIZE / 2);
        list2 = cardDeck.subList(DECK_SIZE / 2, DECK_SIZE);
    }

    public static Integer getWinner(String card1, String card2){
        String value1 = card1.split("_")[2];
        String value2 = card2.split("_")[2];
        if(values.indexOf(value1) < values.indexOf(value2)){
            return 2; // Card2 won.
        } else if (values.indexOf(value1) > values.indexOf(value2)) {
            return 1; // Card1 won.
        } else {
            return 0; // Draw.
        }
    }

    public String[] getTopCards() {
        String[] topCards = new String[2];
        topCards[0] = list1.get(count);
        topCards[1] = list2.get(count);
        count++;
        return topCards;
    }
}
