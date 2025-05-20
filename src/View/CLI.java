package View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import GameManagers.Game;

public class CLI extends View {

    private static final List<Character> validActions = Arrays.asList('a', 's', 'w', 'd', 'e', 'q');
    public CLI(){
        super();
    }

    @Override
    public void display(String msg){
        System.out.println(msg);
    }

    public void runGame(){
        Game game = Game.getInstance(this);
        Scanner scanner = new Scanner(System.in);
        display("Welcome to a DnD (or game of thrones for some reason) game!");
        display("Please choose a character class:");
        display("0. Jon Snow (Warrior) , 1. The Hound (Warrior) , 2. Melisandre (Mage) , 3. Thoros of Myr (Mage) , 4. Arya Stark (Rouge) , 5. Bronn (Rouge), 6. Ygritte (Hunter)");
        int playerType = getPlayerType(scanner);
        game.choosePlayer(playerType);
        game.start();
        // gameplay loop
        while(game.getStatus() == Game.GameStatus.RUNNING){
            display("starting level " + game.getLevelNum());
            while(!game.isLevelFinished() && game.getStatus() == Game.GameStatus.RUNNING){
                display(game.getBoard().toString());
                display(game.PlayerDescription());
                // combat info
                // level up notification if needed
                display("Please choose an action:");
                char action = getPlayerAction(scanner);
                game.playerAction(action);
                game.enemiesTurn();
            }
            if(game.getStatus() != Game.GameStatus.LOST){
                display("Level " + game.getLevelNum() + " finished!");
                game.NextLvl();
            }
        }
        if (game.getStatus() == Game.GameStatus.WON){
            display("Congratulations! You have won the game!");
        }
        else{
            display("Game over! You have lost!");
        }
    }

    private char getPlayerAction(Scanner scanner){ 

        while(true){
            char action = scanner.next().charAt(0);
            if(validActions.contains(action)){
                return action;
            }
            display("Invalid input, please choose one of the following: a, s, w, d, e, q");
        }
    }

    public int getPlayerType(Scanner scanner){
        while(true){
            int playerType = scanner.nextInt();
            if(playerType >= 0 && playerType <= 6){
                return playerType;
            }
            display("Invalid input, please choose a number between 0 and 6");
        }
    }
    
}
