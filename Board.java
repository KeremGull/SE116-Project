import java.util.ArrayList;

public class Board implements Viewable, Clearable {
    //cards on the board
    private Pile cards;
    private Player lastCapturer;

    public Board() {
        cards = new Pile();
    }

    //When a player adds a card to the board
    public void addCard(Card card, Player player){
        cards.addCard(card);

        //Checks for Misti
        if(cards.getCards().size()>1){
            if(cards.getCard(cards.size()-1).getFace().equals(cards.getCard(cards.size()-2).getFace())){

                if(cards.size()>2){
                    System.out.printf("\n%s captured the board!\n", player.getName());
                    player.capture(cards);
                } else{
                    System.out.printf("\nMISTI!! (by %s)\n", player.getName());
                    player.capture(cards, true);
                }

                lastCapturer = player;
                clear();
            } else if (card.getFace().equals("J")) {

                System.out.printf("\n%s played J to capture board\n", player.getName());
                player.capture(cards);
                lastCapturer = player;

                clear();
            }
        }
    }

    public void addCard(Card card){
        cards.addCard(card);
    }
    @Override
    public void clear() {
        cards = new Pile();
    }

    @Override
    public void view() {
        for (int i = cards.size()-1; i>=0; i--) {
            if(i== cards.size()-1)
                System.out.println("\n"+ cards.getCard(i) );
            else
                System.out.printf(" %s |", cards.getCard(i));
        }
    }
    //After the last card is played any remaining uncaptured cards goes to player who captured latest
    public void remainingBoard(){
        if(cards.size() !=0){
            System.out.println("Remaining cards goes to "+ lastCapturer.getName());
            lastCapturer.capture(cards);
            clear();
        }
    }
    public Pile getCards() {
        return cards;
    }

    public Player getLastCapturer() {
        return lastCapturer;
    }
}