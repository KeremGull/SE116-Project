import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Deck extends Pile{
    public Deck(){
        //Creating cards
        super();
        String[] suits= {"♠","♣","♥","♦"};
        String[] faces = {"A","2","3","4","5","6","7","8","9","10","J","K","Q"};
        for(int i=0;i<suits.length;i++){
            for(int j =0;j<faces.length;j++){
                int point = FileOps.findCardPoint(suits[i],faces[j]);
                addCard(new Card(suits[i],faces[j],point));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public void cut(){
        Random random = new Random();
        int cut = random.nextInt(10,cards.size()-10);
        ArrayList<Card> cardsCutted = new ArrayList<Card>();
        for(int i =0;i<cards.size();i++){
            cardsCutted.add( cards.get( (cut+i)%cards.size() ) );
        }
        cards = cardsCutted;
        
    }
}
