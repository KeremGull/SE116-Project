public class TopPlayer {
    private String name;
    private String expertise;
    private int point;
    
    public TopPlayer(){
        this("Default","H",0);
    }
    public TopPlayer(String name,String expertise,int point){
        this.name = name;
        this.expertise = expertise;
        this.point = point;
    }

    public String getName(){return name;}
    public String getExpertise(){return expertise;}
    public int getPoint(){return point;}
}
