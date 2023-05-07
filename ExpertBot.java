public class ExpertBot extends Player{
    private Board playsOn;
    public ExpertBot(String name, Board playsOn) {
        super(name);
        this.playsOn = playsOn;
    }

    @Override
    public Card play(int turn) {return null;}

}
