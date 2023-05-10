import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayedCards {

    private static final ArrayList<Card> playedCards = new ArrayList<>();
    public static final ArrayList<Card> mistiList = new ArrayList<>();
    private static final String[] faces = {"A","2","3","4","5","6","7","8","9","10","J","K","Q"};
    private static final Map<String, Integer> faceCounts = new HashMap<>();
    private static final Deck sampleDeck = new Deck();

    //Adds the chosen card to the playedCards list.
    public static void addCard(Card card) {
        playedCards.add(card);
        String face = card.getFace();

        if (faceCounts.containsKey(card.getFace())) {
            faceCounts.put(face, faceCounts.get(face) + 1);
        }
        else {
            faceCounts.put(face, 1);
        }
    }
    public static void removeTopCard(){
        playedCards.remove(playedCards.size()-1);
    }

    //Returns the list of the cards that were played.
    public static ArrayList<Card> getCards() {
        return playedCards;
    }

    public static ArrayList<String> findMostPlayedFaces() {        
        ArrayList<String> currFaces = new ArrayList<>();
        int occurrences = 0;
        for (Map.Entry<String, Integer> pair: faceCounts.entrySet()) {
            if (pair.getValue() > occurrences && pair.getValue() < 4) {
                occurrences = pair.getValue();
            }
        }

        for (Map.Entry<String, Integer> pair : faceCounts.entrySet()) {
            if (pair.getValue() == occurrences) {
                currFaces.add(pair.getKey());
            }
        }
        
        return currFaces;
    }

    public Card findChosenCard(Deck deck, String face) {
        for (Card card: deck.getCards()) {
            if (card.getFace().equals(face)) {
                return card;
            }
        }
        return null;
    }

    public static Map<String, Integer> getFaceCounts() {
        return faceCounts;
    }
}
    