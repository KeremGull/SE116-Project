import java.util.ArrayList;

public abstract class Player implements Viewable{
    private final String name;
    private final Pile capturePile;
    private int point;
    //hands represent all hands for the game
    private final ArrayList<Hand> hands;

    //Temp members for arrange hands
    private int handCounter = 0;
    private int handIndex = -1;

    public Player(String input_name) {
        name = input_name;
        hands = new ArrayList<>();
        capturePile = new Pile();
        point = 0;
    }
    //int turn parameter is very effective to know which hand will be used
    public abstract Card play(int turn);
    public void addCard(Card card){
        if(handCounter%4==0){
            handIndex++;
            hands.add(new Hand(handIndex+1));
        }
        hands.get(handIndex).addCard(card);
        handCounter++;
    }

    //1st capture method is casual capture method whereas 2nd one multiplies Card.point with 5
    public void capture(Pile cards){
        for(Card card : cards.getCards()){
            point += card.getPoint();
        }
        capturePile.addCards(cards);
    }
    public void capture(Pile cards, boolean isMisti){
        if(isMisti)
            for (Card card : cards.getCards()){
                point += card.getPoint() *5;
                capturePile.addCards(cards);
            }
        else{
            capture(cards);
        }
            
    }
    //this view method shows captured cards for the player (Geçici method capture doğru çalışıp çalışmadığıyla ilgili sonradan düzenleriz)
    @Override
    public void view(){
        
    }
    public String getName() {
        return name;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public Pile getCapturePile() {
        return capturePile;
    }
    public int getPoint(){
        return point;
    }
}
