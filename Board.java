import java.util.ArrayList;

public class Board extends Pile {
    private static Player lastCapturer;
    private static ArrayList<Card> cards;

    public Board() {
        cards = new ArrayList<>();
    }

    public static Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

    public static int getPoints() {
        int points = 0;
        for (Card card: cards) {
            points += card.getPoint();
        }
        return points;
    }

    public static void boardAddCardToPlayer(Card a){
        cards.add(a);
    }

    //When a player plays a card to the board
    public static void boardAddCardToPlayer(Card card, Player player){
        cards.add(card);
        Board boardInstance = new Board();

        //Checks for Misti
        if(cards.size()>1){
            if(cards.get(cards.size()-1).getFace().equals(cards.get(cards.size()-2).getFace())){

                if(cards.size()>2){
                    System.out.printf("\n%s captured the board!\n", player.getName());
                    player.capture(boardInstance);
                } else{
                    System.out.printf("\nMISTI!! (by %s)\n", player.getName());
                    player.capture(boardInstance, true);
                }

                lastCapturer = player;
                clear();
            } else if (card.getFace().equals("J")) {

                System.out.printf("\n%s played J to capture board\n", player.getName());
                player.capture(boardInstance);
                lastCapturer = player;

                clear();
            }
        }
    }
    
    public static void clear() {
        cards = new ArrayList<>();
    }

    public static void view() {
        for (int i = cards.size()-1; i>=0; i--) {
            if(i== cards.size()-1)
                System.out.println("\n"+ cards.get(i) );
            else
                System.out.printf(" %s |", cards.get(i));
        }
    }
    //After the last card is played any remaining uncaptured cards goes to player who captured latest
    public static void remainingBoard(){
        Board boardInstance = new Board();
        if(cards.size() !=0){
            System.out.println("Remaining cards goes to "+ lastCapturer.getName());
            lastCapturer.capture(boardInstance);
            clear();
        }
    }
    public Player getLastCapturer() {
        return lastCapturer;
    }
}