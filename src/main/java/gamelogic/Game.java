package gamelogic;


import gamelogic.cards.Card;
import gamelogic.cards.DeckSerializer;
import gamelogic.cards.damage_card.*;
import gamelogic.cards.damage_card.TrojanHorse;
import gamelogic.map.MapName;
import gamelogic.robot.Robot;
import gamelogic.map.GameBoard;
import gamelogic.map.ModelLoader;
import newmessages.Message;
import newmessages.MessageActivePhase;
import server.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Mark Ringer
 */
public class Game {
    ArrayList elementRegistry;
    private static Game instance;
    public Timer timer;

    private Stack<Spam> spamDrawPile;
    private Stack<TrojanHorse> trojanHorseDrawPile;
    private Stack<Virus> virusDrawPile;
    private Stack<Worm> wormDrawPile;
    private MapName mapName;
    private Stack<Card> upgradeWarehouse;
    private ArrayList<Card> upgradeShop;
    boolean continueGame;
    public List<Player> playerList = new ArrayList<>();
    public Stack<Spam> getSpamDrawPile(){
        return spamDrawPile;
    }
    public Stack<TrojanHorse> getTrojanHorseDrawPile(){
        return trojanHorseDrawPile;
    }
    public Stack<Virus> getVirusDrawPile(){
        return virusDrawPile;
    }
    public Stack<Worm> getWormDrawPile(){
        return wormDrawPile;
    }
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
    public Player join(Client client) {
        Player player = new Player(client);
        for (Player existingPlayer : playerList) {
            if (existingPlayer.getClient().getClientID() == client.getClientID()) {
                return null;
            }

        }
        playerList.add(player);
        return player;
    }

    /**
     * @author Ringer
     * Does the setup for a new Game, loads the map and creates the different
     * card Decks
     */
    public void setup() throws IOException {
        DeckSerializer deckSerializer = new DeckSerializer();
        //select map
        ModelLoader loader = new ModelLoader();
        try {
            board = loader.loadMap("dizzy_highway.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        elementRegistry = board.getRegistry();
        for (Player player :playerList) {
            player.setRobot(new Robot(new Position(1,1)));
        }

        //TODO special cards

        //TODO setup Damage cards
        //Setup Spam
        for(int i = 0; i < 38; i++)
            spamDrawPile.add(new Spam());
        //Setup Virus
        for(int i = 0; i < 18; i++)
            virusDrawPile.add(new Virus());
        //Setup TrojanHorse
        for(int i = 0; i < 12; i++)
            trojanHorseDrawPile.add(new TrojanHorse());
        //Setup Worm
        for(int i = 0; i<6; i++)
            wormDrawPile.add(new Worm());

        //setup upgradeWarehouse
        upgradeWarehouse = deckSerializer.builtDeck(mapName.toString());

        //setup upgradeShop
        upgradeShop = new ArrayList<Card>();
        for(int i = 0;i< playerList.size();i++){
            upgradeShop.add(upgradeWarehouse.pop());
        }


        //TODO setup Checkpoint Tokens

        //Timer
        timer = new Timer();

        //TODO setup Energy cubes


        //TODO place Robot
        for (Player player:playerList) {
            player.placeRobot();
        }


    }

    public void startGame() throws IOException {
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
        if(upgradeShop.size()== playerList.size()){
            for (Card card:upgradeShop) {
                card.discard();
            }
        }
        while(upgradeShop.size()< playerList.size()){
            upgradeShop.add(upgradeWarehouse.pop());
        }



    }

    /**
     * @author Ringer
     * @param message
     *
     * sends the Message to all players in playerList
     */

    private void sendToAll(Message message){
        for (Player player:playerList) {
            player.sendMessage(message);
        }
    }

    /**
     * @author Ringer
     * Draw cards and arrange them
     */
    private void programmingPhase(){
        sendToAll(new MessageActivePhase(1));


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


