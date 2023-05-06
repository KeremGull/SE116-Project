import java.util.ArrayList;

public class Project {
    public static void main(String[] args) {
        Deck deck = new Deck();
        for(Card i : deck.getCards()){
            System.out.print(i.getSuit()+i.getFace()+" ");
        }
        deck.shuffle();
        System.out.println("\nShuffling...");
        for(Card i : deck.getCards()){
            System.out.print(i.getSuit()+i.getFace()+" ");
        }
        System.out.println("\nCutting...");
        deck.cut();
        for(Card i : deck.getCards()){
            System.out.print(i.getSuit()+i.getFace()+" ");
        }

        //Instantiating game objects (Board and Player)
        Board board = new Board();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Cinar"));
        players.add(new HumanPlayer("Kerem"));
        players.add(new HumanPlayer("Melih"));

        //Dealing cards to players
        for(int i=51; i>47; i--)
            board.addCard(deck.getCards().get(i));
        for(int i=deck.getCards().size()-5; i>=0;i--)
            players.get((47-i)% players.size()).addCard(deck.getCards().get(i));

        System.out.println("\n-----------------------------------------------------------------------------\n");

        //Main game!
        System.out.println("Start!");
        board.view();
        System.out.println();

        for(int turn=0; turn< 12/players.size();turn++)
            for(int i=0;i<4;i++)
                for(Player player : players){
                    board.addCard(player.play(turn), player);
                    board.view();
                    System.out.println();
                }
        board.remainingBoard();

        System.out.println("\n-----------------------------------------------------------------------------\n");
        for (Player player : players) {
            player.view();
        }

    }
}