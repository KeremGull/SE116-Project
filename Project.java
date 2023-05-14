import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Project {
    public static void main(String[] args) {
        if(!GameFuncs.checkInputs(args)){
            return;
        }
        Path pointsPath = Paths.get(args[1]);
        FileOps.setPointPath(pointsPath);

        Deck deck = new Deck();
        deck.shuffle();
        deck.cut();

        //Instantiating game objects (Board and Player)
        Board board = new Board();
        int numberOfPlayers = Integer.parseInt(args[0]);
        boolean verboseMode = Boolean.parseBoolean(args[2]);
        ArrayList<Player> players = GameFuncs.setPlayers(args, numberOfPlayers,board);
        //Dealing cards to players
        for(int i=51; i>47; i--){
            board.addCard(deck.getCards().get(i));
            PlayedCards.addCard(deck.getCards().get(i));
        }

        for(int i=deck.getCards().size()-5; i>=0;i--)
            players.get((47-i)% players.size()).addCard(deck.getCards().get(i));

        Verbose verbose = new Verbose(players);

        //Main game!
        System.out.println("Start!");
        board.view();
        System.out.println();

        for(int turn=0; turn< 12/players.size();turn++){
            for(int i=0;i<4;i++){
                for(Player player : players){
                    board.addCard(player.play(turn), player);
                    board.view();
                    System.out.println();
                }
            }
            if(verboseMode){
                verbose.summary(turn);
                board.view();
            }else{
                verbose.succintSummary();
                board.view();

            }
                
        }
        board.remainingBoard();
        if(verboseMode)
            verbose.gameSummary();  

        System.out.println("\n-----------------------------------------------------------------------------\n");
        for (Player player : players) {
            player.view();
            System.out.printf("%s: %dpts\n",player.getName(), player.getPoint());
        }
        String context = FileOps.readFile("TopPlayers.txt");
        ArrayList<TopPlayer> topPlayers = FileOps.getTopTen(context);
        FileOps.sortTopTen(topPlayers, players);
        FileOps.updateTopTen(topPlayers);


    }
}