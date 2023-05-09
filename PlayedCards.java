import java.util.ArrayList;

public class PlayedCards {

    private static ArrayList<Card> playedCards = new ArrayList<>();

    //Adds the chosen card to the playedCards list.
    public static void addCard(Card card) {
        playedCards.add(card);
    }

    //Returns the list of the cards that were played.
    public static ArrayList<Card> getCards() {
        return playedCards;
    }
}
