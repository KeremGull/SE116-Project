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
        System.out.printf("\n%s's turn: \n", getName());
        Hand currentHand = getHands().get(turn);
        Card card = currentHand.getCard(0);

        if (isBoardEmpty(playsOn)) {
            if (hasNonJoker(currentHand)) {
                card = findBestCard(currentHand);
                currentHand.removeCard(card);
                return(card);
            }
            card = findCard(currentHand, "J");
            currentHand.removeCard(card);
            return(card);
        }
        else {
            Card matchingCard = findCard(currentHand,playsOn.getTopCard().getFace());
            if(matchingCard != null) {
                if (hasJoker(currentHand) && matchingCard.getPoint() > findBestJoker(currentHand).getPoint() && matchingCard.getPoint() + playsOn.getPoints() > 0) {
                    card = matchingCard;
                    currentHand.removeCard(card);
                    return(card);
                }

                else if (!hasJoker(currentHand) && matchingCard.getPoint() + playsOn.getPoints() > 0) {
                    card = matchingCard;
                    currentHand.removeCard(card);
                    return(card);
                }
            }
            if(hasJoker(currentHand) && (findBestJoker(currentHand).getPoint() + playsOn.getPoints() > 0)) {
                card = findBestJoker(currentHand);
                currentHand.removeCard(card);
                return(card);
            }
            card = findBestCard(currentHand);
            currentHand.removeCard(card);
            return(card);
        }
    }

    public Card findBestCard(Hand hand) {
        int mostOccurrence = 0;
        ArrayList<Card> mostOccurredCards = new ArrayList<>();
        for (Card card: hand.getCards()) {
            for (Map.Entry<String, Integer> pair: PlayedCards.getFaceCounts().entrySet()) {
                if (pair.getKey().equals(card.getFace()) && !pair.getKey().equals("J") && pair.getValue() > mostOccurrence) {
                    mostOccurrence = pair.getValue();
                }
            }
        }

        for (Card card : hand.getCards()) {
            for (Map.Entry<String, Integer> pair: PlayedCards.getFaceCounts().entrySet()) {
                if (pair.getValue() == mostOccurrence && pair.getKey().equals(card.getFace())) {
                    mostOccurredCards.add(card);

                }
            }
        }
        if(mostOccurredCards.size() == 0){
            return findCardWithLeastPts(hand);
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

    public Card findBestJoker(Hand hand) {
        Card bestJoker = findCard(hand, "J");
        for (Card card: hand.getCards()) {
            if (card.getFace().equals("J") && card.getPoint() > bestJoker.getPoint()) {
                bestJoker = card;
            }
        }
        return bestJoker;
    }
}
