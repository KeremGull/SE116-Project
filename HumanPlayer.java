import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Card play(int turn) {
        System.out.println("\n"+ getName()+" choose a card to play");
        getHands().get(turn).view();

        while (true){
            Scanner sc = new Scanner(System.in);
            try{
                int chosenCard = sc.nextInt();
                Card playedCard = getHands().get(turn).getCard(chosenCard-1);
                getHands().get(turn).removeCard(playedCard);
                return playedCard;

            }catch (InputMismatchException err){
                System.out.println("Please enter integer!");
            }catch (IndexOutOfBoundsException err){
                System.out.println("Please enter your number in given range!");
            }
        }

    }
}