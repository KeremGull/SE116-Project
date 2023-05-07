import java.util.Random;

public class RegularBot extends Player{
    private Board playsOn;
    public RegularBot(String name, Board playsOn) {
        super(name);
        this.playsOn = playsOn;
    }

    @Override
    public Card play(int turn) {

        Hand currentHand = getHands().get(turn);
        if (isBoardEmpty()) {
            if (hasJoker(getHands().get(turn))) {
                return(findCard(currentHand, "J"));
            }
            return(getRandomCard(currentHand));
        }
        else {
            Card matchingCard = findCard(currentHand, playsOn.getTopCard().getSuit(), playsOn.getTopCard().getFace());
            if(matchingCard != null && (matchingCard.getPoint() + playsOn.getPoints() > 0 && matchingCard.getPoint() > 10)) {
                return(matchingCard);
            }
            if(hasJoker(getHands().get(turn)) && (10 + playsOn.getPoints() > 0)) {
                return(findCard(currentHand, "J"));
            }
            return(getRandomCard(currentHand));
        }
    }

    public boolean isBoardEmpty() {
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

    public Card getRandomCard(Hand hand) {
        Random random = new Random();
        return(hand.getCard(random.nextInt(0, hand.size())));
    }

}
