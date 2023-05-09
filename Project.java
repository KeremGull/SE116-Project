import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
public class Project {
    public static void main(String[] args) {
        if(!GameFuncs.checkInputs(args)){
            return;
        }
        Path pointsPath = Paths.get(args[1]);
        FileOps.setPointPath(pointsPath);
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
        int numberOfPlayers = Integer.parseInt(args[0]);
        boolean verboseMode = Boolean.parseBoolean(args[2]);
        ArrayList<Player> players = GameFuncs.setPlayers(args, numberOfPlayers, new Board());
        //Dealing cards to players
        for(int i=51; i>47; i--)
            Board.boardAddCardToPlayer(deck.getCards().get(i));
        for(int i=deck.getCards().size()-5; i>=0;i--)
            players.get((47-i)% players.size()).addCard(deck.getCards().get(i));

        System.out.println("\n-----------------------------------------------------------------------------\n");

        //Main game!
        System.out.println("Start!");
        Board.view();
        System.out.println();

        for(int turn=0; turn< 12/players.size();turn++){
            for(int i=0;i<4;i++){
                for(Player player : players){
                    Board.boardAddCardToPlayer(player.play(turn), player);
                    Board.view();
                    System.out.println();
                }
            }
            if(!verboseMode){
                for(Player player : players){
                    Board.boardAddCardToPlayer(player.play(turn), player);
                    Board.view();
                    System.out.println();
                }
            }
        }
        Board.remainingBoard();

        System.out.println("\n-----------------------------------------------------------------------------\n");
        for (Player player : players) {
            player.view();
            System.out.println(player.getPoint());
        }
        String context = FileOps.readFile("TopPlayers.txt");
        ArrayList<TopPlayer> topPlayers = FileOps.getTopTen(context);
        FileOps.sortTopTen(topPlayers, players);
        FileOps.updateTopTen(topPlayers);


    }
}