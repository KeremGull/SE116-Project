import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Deck {
    private ArrayList<Card> cards;
    public Deck(){
        //Creating cards
        String[] suits= {"♠","♣","♥","♦"};
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","K","Q"};
        cards = new ArrayList<Card>();
        for(int i=0;i<suits.length;i++){
            for(int j =0;j<numbers.length;j++){
                cards.add(new Card(suits[i],numbers[j]));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public void cut(){
        Random random = new Random();
        int cut = random.nextInt(10,cards.size()-10);
        System.out.println(cut);
        ArrayList<Card> cardsCutted = new ArrayList<Card>();
        for(int i =0;i<cards.size();i++){
            cardsCutted.add( cards.get( (cut+i)%cards.size() ) );
        }
        cards = cardsCutted;
        
    }

    public ArrayList<Card> getCards(){return cards;}
}
