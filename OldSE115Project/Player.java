import java.util.ArrayList;

public abstract class Player implements Viewable{
    private final String name;
    //hands represent all hands for the game
    private final ArrayList<Hand> hands;
    private final ArrayList<Card> capturePile;

    private int handCounter = 0;
    private int handIndex = -1;

    public Player(String input_name) {
        name = input_name;
        hands = new ArrayList<>();
        capturePile = new ArrayList<>();
    }
    //int turn parameter is very effective to know which hand will be used
    public abstract Card play(int turn);
    public void addCard(Card card){
        if(handCounter%4==0){
            handIndex++;
            hands.add(new Hand(handIndex+1));
        }
        hands.get(handIndex).addCard(card);
        handCounter++;
    }

    //1st capture method is casual capture method whereas 2nd one multiplies Card.point with 5
    public void capture(ArrayList<Card> cards){
        capturePile.addAll(cards);
    }
    public void capture(ArrayList<Card> cards, boolean isMisti){
        if(isMisti)
            for (Card card : cards)
                card.setPoint(card.getPoint()*5);

        capturePile.addAll(cards);
    }
    //this view method shows captured cards for the player (Geçici method capture doğru çalışıp çalışmadığıyla ilgili sonradan düzenleriz)
    @Override
    public void view(){
        System.out.printf("\n%s's Captured Cards\n", name);

        for(Card card : capturePile)
            System.out.printf(" %s |", card);

    }
    public String getName() {
        return name;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public ArrayList<Card> getCapturePile() {
        return capturePile;
    }
}
