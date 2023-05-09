import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
public class GameFuncs {
    public static void helpMessage(){
        System.out.println("The program must has these arguments before executing:");
        System.out.println("First Argument: Number of Players(Integer between 2-4)");
        System.out.println("Second Argument: Path of File Which Contains Point Configuration For Cards");
        System.out.println("Third Argument: Boolean Value(true or false) For Verboseness mode.");
        System.out.println("Next Arguments: Name and expertise level for every player.Format should be like this:{name} {N,R,E,H}");
        System.out.println();
        System.out.println("CONSTRAINTS:");
        System.out.println("Path cannot contain following characters: \\, /, :, *, ?, \", <, >, |.");
        System.out.println("Path must be given even the file does not exist or empty.");
        System.out.println("Path of file must be a text file with .txt extension.");
        System.out.println("Any 2 of the players must have different names.");
    }
    public static boolean checkInputs(String[] args){
        if(args.length == 0){
            helpMessage();
            return false;
        }
        if(args[0].compareToIgnoreCase("help")== 0){
            helpMessage();
            return false;
        }

        //Check number of players
        int numberOfPlayers;
        try{
            numberOfPlayers = Integer.parseInt(args[0]);
        }catch(Exception e){
            System.out.println("First argument must be a Integer!!");
            System.out.println("Execute program with no argument for help.");
            return false;
        }
        if(numberOfPlayers >4 || numberOfPlayers <2){
            System.out.println("Number of players must be between 2 and 4!!");
            System.out.println("Execute program with no argument for help.");
            return false;
        }

        //Check point file name
        if(args.length == 1){
            System.out.println("Please input a file path!!");
            return false;
        }
        Path path = Paths.get(args[1]);
        if(!Files.exists(path)){
            System.out.println("This file doesn't exist!!");
            return false;
        }
        if(!args[1].substring(args[1].length()-4, args[1].length()).equals(".txt")){
            System.out.println("File must be a text file!!");
            return false;
        }

        //Check verboseness
        if(args.length == 2){
            System.out.println("Please input a boolean for verboseness mode!!");
            return false;
        }
        boolean verboseness;
        if(args[2].equalsIgnoreCase("true")){
            verboseness = true;
        }else if(args[2].equalsIgnoreCase("false")){
            verboseness = false;
        }else{
            System.out.println("Verboseness mode must be a boolean(true or false)!!");
            return false;
        }

        //Check for players
        if(args.length < 3+(numberOfPlayers*2)){
            System.out.println("Please input name and player information for every player! Format should be like this:{name} {N,R,E,H}");
            return false;
        }
        boolean haveHumanPlayer = false;
        String[] expertises = {"N","R","H","E"};
        for(int i =4;i<3+(numberOfPlayers*2);i+=2){
            boolean isValid = false;
            for(int j = 0;j<expertises.length;j++){
                if(args[i].equals(expertises[j])) isValid = true;
                
                if(args[i].equals("J")){
                    if(haveHumanPlayer){
                        System.out.println("There can be only one human player!!");
                        return false;
                    }else haveHumanPlayer = true;       
                }
            }
            if(!isValid){
                System.out.println("One of the expertises is in incorrect form!!!");
                return false;
            }
        }
        String temp = args[3];
        for(int i = 5;i<3+(numberOfPlayers*2);i+=2){
            if(temp.equalsIgnoreCase(args[i])){
                System.out.println("Players must have unique names!!!");
                return false;
            }
        }
        return true;
    }
    public static String checkSuit(String suit){
        String[][] a = {{"♠","S","s"},{"♣","C","c"},{"♥","H","h"},{"♦","D","d"}};
        for(int i =0;i<4;i++)
            for(int j = 0;j<3;j++)
                if(suit.equals(a[i][j]))
                    return a[i][0];  
        return suit;
    }

    public static ArrayList<Player> setPlayers(String[] args,int numberOfPlayers,Board board){
        ArrayList<Player> result = new ArrayList<>();
        for(int i =4;i<3+(numberOfPlayers*2);i+=2){
            if(args[i].equalsIgnoreCase("N")){
                result.add(new NoviceBot(args[i-1]));
            }else if(args[i].equalsIgnoreCase("R")){
                result.add(new RegularBot(args[i-1],board));
            }else if(args[i].equalsIgnoreCase("E")){
                result.add(new ExpertBot(args[i-1],board));
            }else{
                result.add(new HumanPlayer(args[i-1]));
            }
        }
        return result;
    }

    public static String getExpertise(Player player){
        if(player instanceof HumanPlayer) return "H";
        if(player instanceof RegularBot) return "R";
        if(player instanceof ExpertBot) return "E";
        if(player instanceof NoviceBot) return "N";
        return "";
    }

}
