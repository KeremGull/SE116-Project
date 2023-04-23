import java.util.ArrayList;

public class Hand implements Viewable {
    private final ArrayList<Card> cards;
    private final int handNumber;

    public Hand(int input_handNumber) {
        cards = new ArrayList<>();
        handNumber = input_handNumber;
    }

    @Override
    public void view() {
        System.out.printf("Hand %d:\n", handNumber);
        int counter = 1;
        for (Card card : cards)
            System.out.printf("%d) %s \n", counter++, card);

    }
    public void remove(Card card){
        cards.remove(card);
    }
    //addCard methoduna daha iyi bir isim verilip interface yapılabilir
    // Player için kart dağıtılırken kullandım
    // ve Board için kart dağıtılırken ve oynanırken kullandım
    //Burda da full hande ekleme yaptım
    public void addCard(Card card){
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
