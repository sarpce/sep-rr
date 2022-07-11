package client_package.client_gamelogic.game_elements.robot;


import client_package.ClientObject;
import client_package.client_gamelogic.CPlayer;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.game_elements.ElementName;
import client_package.client_gamelogic.game_elements.GameElement;

import gamelogic.Direction;
import gamelogic.Position;

import java.io.IOException;

/**
 * @author Qinyi, Mark
 * creates a robot character for every player
 */
public class Robot extends GameElement {

    private boolean isPlaced = false;

    //private final Color color;

    public final int figure;
    private Game game = Game.getInstance();

    private Direction directionFacing;
    private Position position;
    private String name;
    private CPlayer player;

    public Robot(int figure, CPlayer player) throws IOException {
        super(ElementName.ROBOT);
        this.player = player;
        this.figure = figure;
        this.game = Game.getInstance();

    }

    public Position getPosition() {
        return position;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    /**
     * @author Mark Ringer
     * only use for placing the Robot the first time on GameBoard
     * @param y
     * @param x
     */
    public void placeRobot(int y, int x){
        isPlaced = true;
        directionFacing = Direction.EAST;
        try {
            moveRobotTo(y,x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @author Mark Ringer
     * used to set move the Robot freely on the Map
     * @param y
     * @param x
     */

    public void moveRobotTo(int y, int x) throws IOException {
        if(position != null){
            Game.getInstance().getMap().getGameField(position.getY(),position.getX()).removeRobot();
        }

        Game.getInstance().getMap().getGameField(y,x).removeRobot();
        Game.getInstance().getMap().getGameField(y,x).addRobot(this);
        position = new Position(y,x);
        System.out.println("added Robot");


    }


    private void setPosition(Position position) {
        this.position = position;
    }


    /**
     * @param position
     * @param direction
     * @param figure
     * @author Ringer
     * update the location of the robot
     */
    public Robot (ClientObject client,Position position, Direction direction, int figure) throws IOException {
        this.figure = figure;
        game =client.getGame();
        directionFacing = direction;
        this.position = position;
        this.orientations.add(direction);

    }

    public CPlayer getPlayer() {
        return player;
    }

    public void setPlayer(CPlayer player) {
        this.player = player;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean left(){
        //TODO Animation
        directionFacing.left();
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */
    public boolean right(){
        //TODO Animation
        directionFacing.right();
        return true;
    }

    /**
     * @author Ringer
     * @return boolean
     */

    public boolean uTurn(){
        //TODO Animation
        directionFacing.right();
        directionFacing.right();
        return true;
    }



    /**
     * @uthor Ringer
     */
    public void reboot() {
        //TODO Check rules if Register is continued
        //TODO case not answered
    }

    /**
     * @return the name of the robot
     */
    public String getName(){
        return name;
    }

    public String toString(){
        return this.name;
    }
    /**
     * @return the max Lives of a robot
     */
    public int getMaxLives(){
        return 3;
    }

    public void backward(int i) {
    }

    public void forward(int i) {
    }

    public int getFigure() {
        return figure;
    }
}
