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
        Card card;
        if (isBoardEmpty(playsOn)) {
            if (hasNonJoker(getHands().get(turn))) {
                card = findCardWithLeastPts(currentHand);
                PlayedCards.addCard(card);
                currentHand.removeCard(card);
                return(card);
            }
            card = findCard(currentHand, "J");
            PlayedCards.addCard(card);
            currentHand.removeCard(card);
            return(card);
        }
        else {
            Card matchingCard = findCard(currentHand, playsOn.getTopCard().getSuit(), playsOn.getTopCard().getFace());
            if(matchingCard != null && (matchingCard.getPoint() + playsOn.getPoints() > 0 && matchingCard.getPoint() > 10)) {
                card = matchingCard;
                PlayedCards.addCard(card);
                currentHand.removeCard(card);
                return(card);
            }
            if(hasJoker(getHands().get(turn)) && (10 + playsOn.getPoints() > 0)) {
                card = findCard(currentHand, "J");
                PlayedCards.addCard(card);
                currentHand.removeCard(card);
                return(card);
            }
            card = getRandomCard(currentHand);
            PlayedCards.addCard(card);
            currentHand.removeCard(card);
            return(card);
        }
    }
}
