import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
public class FileOps {
    private static String PointsString;
    private static Path PointsPath;
    
    public static String getPointsString(){return PointsString;}
    public static Path getPointsPath(){return PointsPath;}
    public static void setPointPath(Path a){
        PointsPath = a;
        PointsString = "";
        Scanner sc = null;
        try{
            sc = new Scanner(a);
            while(sc.hasNextLine()){
                PointsString += sc.nextLine().trim() + "/n";
            }
        }catch(Exception e){
            System.out.println("Something bad happened while reading the points file.");
        }
    }
    public static int findCardPoint(String suit, String face){
        if(PointsString.equals("")) return 1;
        String[] lines = PointsString.split("/n");
        int point = 1;
        for(int i = 0;i<lines.length;i++){
            String lineSuit, lineFace;
            int linePoint;
            try{
            lineSuit = lines[i].substring(0,1);
            lineSuit = GameFuncs.checkSuit(lineSuit);
            lineFace = lines[i].substring(1,2);
            linePoint = Integer.parseInt(lines[i].split(" ")[1]);
            }catch(Exception e){
                continue;
            }
            if(lineSuit.equals(suit) || lineSuit.equals("*"))
                if(lineFace.equalsIgnoreCase(face) || lineFace.equals("*")){
                    point = linePoint;
                    break;
                }                
        }        

        return point;
    }


    //Top10 Players

    public static String readFile(String path){
        Scanner sc = null;
        String context="";
        try{
            sc = new Scanner(Paths.get(path));
            while(sc.hasNextLine()){
                context += sc.nextLine() +"\n";
            }
        }catch(Exception e){
            return context;
        }finally{
            return context;
        }
    }
    public static ArrayList<TopPlayer> getTopTen(String context){
        ArrayList<TopPlayer> topTen = new ArrayList<>();
        if(context.trim() == "") return topTen;    
        for(String i : context.split("\n")){
            i = i.trim();
            String[] temp = i.split(" ");
            try{
                TopPlayer player = new TopPlayer(temp[1].trim(), temp[2].trim(), Integer.parseInt(temp[3].trim()));
                topTen.add(player);
            }catch(Exception e){}
        }
        return topTen;
    }
    
    public static void sortTopTen(ArrayList<TopPlayer> topPlayers,ArrayList<Player> players){
        int peopleInTopTen = topPlayers.size();
        for(Player player : players){
            TopPlayer temp = new TopPlayer(player.getName(),GameFuncs.getExpertise(player) ,player.getPoint());
            topPlayers.add(temp);
            peopleInTopTen++;
        }
        if(peopleInTopTen == 0){
            return;
        }
            //Sorting algorithm I took this algorithm from our SE115 Lab11
        for(int i =1;i<peopleInTopTen;i++){
            int j = i;
            while(j>0 && (topPlayers.get(j-1).getPoint() < topPlayers.get(j).getPoint() ) ){
                TopPlayer temp = topPlayers.get(j);
                topPlayers.set(j, topPlayers.get(j-1));
                topPlayers.set(j-1,temp);
                j--;
            }
        }
    }
    
    public static void updateTopTen(ArrayList<TopPlayer> topPlayers){
        Formatter f = null;
        String content = "";
        try{
            f = new Formatter("TopPlayers.txt");
            for(int i =0;i<topPlayers.size()&&i<10;i++){
                content += (i+1)+". "+topPlayers.get(i).getName()+" "+topPlayers.get(i).getExpertise()+" "+topPlayers.get(i).getPoint()+" pts\n";
            }
            f.format("%s",content);
            System.out.println("Top players updated.");
        }catch(Exception e ){
            System.out.println("Something went wrong while program was updating TopPlayers.txt.");
        }finally{
            f.close();
        }
    }
}
