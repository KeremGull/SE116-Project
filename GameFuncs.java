import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
public class GameFuncs {
    public static void helpMessage(){
        System.out.println("The program must has these arguments before executing:");
        System.out.println("First Argument: Number of Players(Integer between 2-4)");
        System.out.println("Second Argument: Path of File Which Contains Point Configuration For Cards");
        System.out.println("Third Argument: Boolean Value(true or false) For Verboseness mode.");
        System.out.println("Next Arguments: A Name for Player(If there is one) or Bots' Difficulty levels");
        System.out.println();
        System.out.println("COSTRAINTS:");
        System.out.println("Bots' difficulty levels are Novice, Regular and Expert.");
        System.out.println("Player's name can't contain any spaces and can't be Novice, Regular or Expert");
        System.out.println("Path cannot contain following characters: \\, /, :, *, ?, \", <, >, |.");
        System.out.println("Path must be given even the file does not exist or empty.");
        System.out.println("Path of file must be a text file with .txt extension.");
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
        if(args.length < 3+numberOfPlayers){
            System.out.println("Please input name or bot level for every player!!!");
            return false;
        }
        boolean haveHumanPlayer = false;
        for(int i =3;i<3+numberOfPlayers;i++){
            if(!args[i].equalsIgnoreCase("novice") && 
            !args[i].equalsIgnoreCase("regular") && 
            !args[i].equalsIgnoreCase("expert")){
                if(haveHumanPlayer){
                    System.out.println("There can be only one human player!!");
                    return false;
                }else{
                    haveHumanPlayer = true;
                }
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
        for(int i =3;i<3+numberOfPlayers;i++){
            if(args[i].equalsIgnoreCase("Novice")){
                result.add(new NoviceBot("Çınar"));
            }else if(args[i].equalsIgnoreCase("Regular")){
                result.add(new RegularBot("Alperen",board));
            }else if(args[i].equalsIgnoreCase("Expert")){
                result.add(new ExpertBot("Kerem",board));
            }else{
                result.add(new HumanPlayer(args[i]));
            }
        }
        return result;
    }
}
