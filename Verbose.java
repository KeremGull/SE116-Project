import java.util.ArrayList;

public class Verbose {
    private final ArrayList<Player> players;
    private final ArrayList<StringBuilder> turnSummaries;
    private int counter = 4;
    public Verbose(ArrayList<Player> players){
        this.players = players;
        turnSummaries = new ArrayList<>();
    }
    public void summary(int turn){
        StringBuilder sb = new StringBuilder("Hand "+ (turn+1) +":\n");
        for (Player player : players) {
            sb.append(player.getName() +": ")
                    .append(String.format("{%s, %s, %s, %s} "
                                    , player.getVerboseHands().get(turn).getCard(0)
                                    , player.getVerboseHands().get(turn).getCard(1)
                                    , player.getVerboseHands().get(turn).getCard(2)
                                    , player.getVerboseHands().get(turn).getCard(3)))
                    .append("Score "+ player.getPoint() +";\n");

        }
        System.out.println("\n\nSummary of the turn: ");

        for(int i=1; i<5; i++){
            sb.append("\n"+ i +". ");

            for(int j=0; j< players.size(); j++){
                Card card = PlayedCards.getCards().get(counter++);
                sb.append(card.toStringWithoutPoint());
                if(isMisti(card))
                    sb.append("!");

                sb.append(" ");
            }
        }
        turnSummaries.add(sb);
        System.out.println(turnSummaries.get(turn));

    }
    public void gameSummary(){
        counter = 4;
        for(int turn=0; turn< 12/players.size();turn++){
            summary(turn);
        }
    }
    private boolean isMisti(Card card){
        for (Card mistiedCard : PlayedCards.mistiList) {
            if(mistiedCard.toString().equals(card.toString()))
                return true;
        }
        return false;
    }
    public void succintSummary(){
        System.out.println("\nPoints at the end of the turn:");
        for(Player player: players){
            System.out.print(""+player.getName()+": "+player.getPoint()+"pts    ");
        }
        System.out.println();
    }
}
