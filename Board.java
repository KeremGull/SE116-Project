import java.util.ArrayList;

public class Board extends Pile implements Viewable, Clearable {
    private Player lastCapturer;

    public Board() {
        cards = new ArrayList<>();
    }

    public Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

    public int getPoints() {
        int points = 0;
        for (Card card: cards) {
            points += card.getPoint();
        }
        return points;
    }

    //When a player plays a card to the board
    public void addCard(Card card, Player player){
        cards.add(card);
        PlayedCards.addCard(card);
        if(player.getClass().toString().equals("class ExpertBot"))
            PlayedCards.removeTopCard();

        //Checks for Misti
        if(cards.size()>1){
            if(cards.get(cards.size()-1).getFace().equals(cards.get(cards.size()-2).getFace())){

                if(cards.size()>2){
                    System.out.printf("\n%s captured the board!\n", player.getName());
                    player.capture(this);
                } else{
                    System.out.printf("\nMISTI!! (by %s)\n", player.getName());
                    player.capture(this, true);
                }

                lastCapturer = player;
                clear();
            } else if (card.getFace().equals("J")) {

                System.out.printf("\n%s played J to capture board\n", player.getName());
                player.capture(this);
                lastCapturer = player;

                clear();
            }
        }
    }
    @Override
    public void clear() {
        cards = new ArrayList<>();
    }

    @Override
    public void view() {
        for (int i = cards.size()-1; i>=0; i--) {
            if(i== cards.size()-1)
                System.out.println("\n"+ cards.get(i) );
            else
                System.out.printf(" %s |", cards.get(i));
        }
    }
    //After the last card is played any remaining uncaptured cards goes to player who captured latest
    public void remainingBoard(){
        if(cards.size() !=0){
            System.out.println("Remaining cards goes to "+ lastCapturer.getName());
            lastCapturer.capture(this);
            clear();
        }
    }
    public Player getLastCapturer() {
        return lastCapturer;
    }
}