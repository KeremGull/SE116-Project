import java.util.ArrayList;
import java.util.List;

public class Pile {
    protected ArrayList<Card> cards;

    public Pile(){
        cards = new ArrayList<>();
    }
    public int size(){
        return cards.size();
    }
    public void addCard(Card a){
        cards.add(a);
    }
    public void addCards(Pile a){
        cards.addAll(a.getCards());
    }
    public void removeCard(Card a){
        cards.remove(a);
    }
    public void removeCards(Pile a){
        cards.removeAll(a.getCards());
    }
    public Card getCard(int i){
        return cards.get(i);
    }
    public ArrayList<Card> getCards(){return cards;}
}
