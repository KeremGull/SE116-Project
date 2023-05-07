import java.util.Random;

public class NoviceBot extends Player{
    public NoviceBot(String name) {
        super(name);
    }

    @Override
    public Card play(int turn) {
        Random random = new Random();
        int chosenCard = random.nextInt(1, getHands().get(turn).size() + 1);
        Card playedCard = getHands().get(turn).getCard(chosenCard-1);
        getHands().get(turn).removeCard(playedCard);
        return playedCard;
    }
}
