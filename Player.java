import java.util.ArrayList;
import java.util.Random;

public abstract class Player implements Viewable{
    private final String name;
    private final Pile capturePile;
    private int point;
    //hands represent all hands for the game
    private final ArrayList<Hand> hands;

    //Temp members for arrange hands
    private int handCounter = 0;
    private int handIndex = -1;

    public Player(String input_name) {
        name = input_name;
        hands = new ArrayList<>();
        capturePile = new Pile();
        point = 0;
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
    public void capture(Pile cards){
        for(Card card : cards.getCards()){
            point += card.getPoint();
        }
        capturePile.addCards(cards);
    }
    public void capture(Pile cards, boolean isMisti){
        if(isMisti)
            for (Card card : cards.getCards()){
                point += card.getPoint() *5;
                capturePile.addCards(cards);
            }
        else{
            capture(cards);
        }
            
    }
    //this view method shows captured cards for the player (Geçici method capture doğru çalışıp çalışmadığıyla ilgili sonradan düzenleriz)
    @Override
    public void view(){
        
    }
    public String getName() {
        return name;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public Pile getCapturePile() {
        return capturePile;
    }
    public int getPoint(){
        return point;
    }

    public boolean isBoardEmpty(Board playsOn) {
        if (playsOn.getCards().size() == 0) {
            return true;
        }
        return false;
    }

    public boolean hasJoker(Hand hand) {
        for (Card card: hand.getCards()) {
            if (card.getFace().equals("J")) {
                return true;
            }
        }
        return false;
    }

    /*
    This method returns a desired card.
    If the desired card does not exist, it returns null.
    */
    public Card findCard(Hand hand, String suit, String face) {
        for (Card card: hand.getCards()) {
            if (card.getFace().equals(face) && card.getSuit().equals(suit)) {
                return card;
            }
        }
        return null;
    }

    public Card findCard(Hand hand, String face) {
        for (Card card: hand.getCards()) {
            if (card.getFace().equals(face)) {
                return card;
            }
        }
        return null;
    }

    public Card findCardWithLeastPts(Hand hand) {
        Card currentCard = hand.getCard(0);
        for (int i = 1; i < hand.size(); i++) {
            if (hand.getCard(i).getPoint() < currentCard.getPoint()) {
                currentCard = hand.getCard(i);
            }
        }
        return currentCard;
    }

    public Card findCardWithMostPts(Hand hand) {
        Card currentCard = hand.getCard(0);
        for (int i = 1; i < hand.size(); i++) {
            if (hand.getCard(i).getPoint() > currentCard.getPoint()) {
                currentCard = hand.getCard(i);
            }
        }
        return currentCard;
    }

    public boolean hasNonJoker(Hand hand) {
        for (Card card: hand.getCards()) {
            if (!card.getFace().equals("J")) {
                return true;
            }
        }
        return false;
    }

    public Card getRandomCard(Hand hand) {
        Random random = new Random();
        return(hand.getCard(random.nextInt(0, hand.size())));
    }

}
