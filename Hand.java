import java.util.ArrayList;

public class Hand implements Viewable {
    private final Pile cards;
    private final int handNumber;

    public Hand(int input_handNumber) {
        cards = new Pile();
        handNumber = input_handNumber;
    }

    @Override
    public void view() {
        System.out.printf("Hand %d:\n", handNumber);
        int counter = 1;
        for (Card card : cards.getCards())
            System.out.printf("%d) %s \n", counter++, card);

    }
    public void remove(Card card){
        cards.removeCard(card);
    }
    //addCard methoduna daha iyi bir isim verilip interface yapılabilir
    // Player için kart dağıtılırken kullandım
    // ve Board için kart dağıtılırken ve oynanırken kullandım
    //Burda da full hande ekleme yaptım
    public void addCard(Card card){
        cards.addCard(card);
    }

    public Pile getCards() {
        return cards;
    }
}
