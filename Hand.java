import java.util.ArrayList;

public class Hand extends Pile implements Viewable {
    private final int handNumber;

    public Hand(int input_handNumber) {
        cards = new ArrayList<>();
        handNumber = input_handNumber;
    }

    @Override
    public void view() {
        System.out.printf("Hand %d:\n", handNumber);
        int counter = 1;
        for (Card card : cards)
            System.out.printf("%d) %s \n", counter++, card);

    }
}
