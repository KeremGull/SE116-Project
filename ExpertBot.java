import java.util.ArrayList;
import java.util.Map;


public class ExpertBot extends Player{
    private Board playsOn;
    public ExpertBot(String name, Board playsOn) {
        super(name);
        this.playsOn = playsOn;
    }

    @Override
    public Card play(int turn) {
        Hand currentHand = getHands().get(turn);
        Card card;
        currentHand.view();

        if (isBoardEmpty(playsOn)) {
            if (hasNonJoker(currentHand)) {
                card = findBestCard(currentHand);
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
            card = findBestCard(currentHand);
            PlayedCards.addCard(card);
            currentHand.removeCard(card);
            return(card);
        }
    }

    public Card findBestCard(Hand hand) {
        int mostOccurrence = 0;
        ArrayList<Card> mostOccurredCards = new ArrayList<>();
        for (Card card: hand.getCards()) {
            for (Map.Entry<String, Integer> pair: PlayedCards.getFaceCounts().entrySet()) {
                if (pair.getKey().equals(card.getFace()) && pair.getValue() > mostOccurrence) {
                    mostOccurrence = pair.getValue();
                }
            }
        }

        for (Card card : hand.getCards()) {
            for (Map.Entry<String, Integer> pair: PlayedCards.getFaceCounts().entrySet()) {
                if (pair.getValue() == mostOccurrence && pair.getKey().equals(card.getFace())) {
                    if(isBoardEmpty(playsOn)){
                        mostOccurredCards.add(card);
                    }

                }
            }
        }
        if(mostOccurredCards.size() == 0){
            return getRandomCard(hand);
        }

        Card bestCard = mostOccurredCards.get(0);
        if (mostOccurredCards.size() == 1) {
            return bestCard;
        }
        for (int i = 1; i < mostOccurredCards.size(); i++) {
            if (mostOccurredCards.get(i).getPoint() < bestCard.getPoint()) {
                bestCard = mostOccurredCards.get(i);
            }
        }
        return bestCard;
    }
}
