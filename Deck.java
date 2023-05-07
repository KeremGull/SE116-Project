import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class Deck extends Pile{
    public Deck(){
        //Creating cards
        String[] suits= {"♠","♣","♥","♦"};
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","K","Q"};
        cards = new ArrayList<>();
        for(String suit : suits){
            for(String number : numbers){
                cards.add(new Card(suit,number));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public void cut(){
        Random random = new Random();
        int cut = random.nextInt(10,cards.size()-10);

        ArrayList<Card> cardsCutted = new ArrayList<>();
        for(int i =0;i<cards.size();i++){
            cardsCutted.add(cards.get((cut+i)%cards.size()));
        }
        cards = cardsCutted;
    }

}