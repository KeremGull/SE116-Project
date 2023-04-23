public class Card{
    private String suit ;
    private String face ;
    private int point;
    //Constructors
    public Card(String input_suit, String input_face){
        face = input_face;
        suit = input_suit;
        point = 1;
    }
    public Card(String input_suit, String input_face,int input_point){
        face = input_face;
        suit = input_suit;
        point = input_point;
    }

    //Setters and getters
    public void setSuit(String input){suit = input;}
    public void setFace(String input){face = input;}
    public void setPoint(int input){point = input;}

    public String getFace(){return face;}
    public String getSuit(){return suit;}
    public int getPoint(){return point;}

    @Override
    public String toString() {
        return String.format("%s%s %dpts", getSuit(), getFace(), getPoint());
    }
}
