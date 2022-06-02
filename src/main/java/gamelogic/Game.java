package gamelogic;


import gamelogic.Robot.Robot;
import gamelogic.map.GameBoard;
import gamelogic.map.ModelLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Ringer
 */
public class Game {
    ArrayList elementRegistry;
    private static Game instance;



    boolean continueGame;

    public List<Player> playerList = new ArrayList<>();


    public static void deleteInstance(){
        instance=null;
    }

    public static Game getInstance(){
        if(instance==null){
            instance = new Game();
        }
        return instance;
    }
    public static boolean instanceExists(){
        return instance!=null;
    }
    private Game() {
    }
    public boolean getContinueGame() {
        return continueGame;
    }
    /**
     * players join the game
     *
     * @return
     */

    public GameBoard board;
    /*
    public Player join(ClientHandler client) {
        Player player = new Player(client);
        for (Player existingPlayer : playerList) {
            if (existingPlayer.client.getClientID() == client.getClientID()) {
                return null;
            }

        }
        playerList.add(player);
        return player;
    }

     */



    public void setup(){
        //select map
        ModelLoader loader = new ModelLoader();
         board = loader.loadMap("map1.json");
        elementRegistry = board.getRegistry();
        for (Player player :playerList) {
            player.setRobot(new Robot());
        }

        //special cards (Todo)

        //setup Damage cards (Todo)

        //setup upgrade Cards(Todo)

        //setup Timer and Checkpoint Tokens

        //setup Energy cubes

        //place Robot


    }

    public void startGame(){
        setup();
        continueGame=true;
        gameLoop();

    }

    public void gameLoop(){
        while (continueGame){
            upgradePhase();
            programmingPhase();
            activationPhase();
        }
    }

    /**
     * Every player can buy upgrades with EnergyCubes
     */
    private void upgradePhase(){

    }

    /**
     * Draw cards and arrange them
     */
    private void programmingPhase(){

    }

    /**
     * @author Mark
     *
     * Every Element is activated
     */
    private void activationPhase(){

    }
    /**
     * returns all possible commands to play with
     *
     * @param player
     * @param commandNameString
     * @return LobbyMessage
     */
    /*
    public Message showHelp(ClientHandler player, String commandNameString) {
        System.out.println("Show reference card");
        Message message = new LobbyMessage(0, "/play (card name): play a card \n" +
                "/select (player name): select a player \n" +
                "/guess (card name): guess a players card \n" +
                "/discardpile (player name): view a players discard pile \n" +
                "/discardpile: view all players' discard piles \n" +
                "/reference (card name): view the action of a card \n" +
                "/reference: view the actions of all cards \n" +
                "/score (player name): check how many tokens of affection a player has" +
                "/score: check how many tokens of affection everyone has" +
                "/active: check which players are in the round");
        return message;
    }

     */

    /**
     * returns all players that are active at the moment
     *
     * @return LobbyMessage
     */
    /*
    public Message showActivePlayers() {
        System.out.println("Show active players");


        StringBuilder outputString = new StringBuilder("Active player(s): \n");
        for (Player player : playerList) {
            outputString.append(player.getClient().getClientName() + ", ");

        }

        return new LobbyMessage(0, outputString.toString());
    }


     */

    /**
     * check if the game continue or not
     *
     * @return
     */
    public boolean gameContinues() {
        return true;
    }
    public void winningGame() {
        System.out.println("Game Ends Now");
    }
}


