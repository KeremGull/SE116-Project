import java.util.ArrayList;
public class Project {
    public static void main(String[] args) {
        Deck a = new Deck();
        for(Card i : a.getCards()){
            System.out.print(i.getSuit()+i.getFace()+" ");
        }
        a.shuffle();
        System.out.println("Shuffling...");
        for(Card i : a.getCards()){
            System.out.print(i.getSuit()+i.getFace()+" ");
        }

        a.cut();
        for(Card i : a.getCards()){
            System.out.print(i.getSuit()+i.getFace()+" ");
        }
    }
}
