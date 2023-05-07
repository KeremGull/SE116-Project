import java.nio.file.Path;
import java.util.Scanner;
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
}
