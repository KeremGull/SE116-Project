import java.util.ArrayList;

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
    public Card getCard(int i){
        return cards.get(i);
    }
    public ArrayList<Card> getCards(){return cards;}
}