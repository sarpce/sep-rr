package client_application;

import client_package.Client;
import com.google.gson.JsonObject;
import gamelogic.Game;
import gamelogic.Player;
import gamelogic.Position;
import gamelogic.game_elements.Checkpoint;
import gamelogic.game_elements.ElementName;
import gamelogic.game_elements.robot.Robot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import newmessages.MessageGameFinished;
import newmessages.MessageSelectedCard;
import newmessages.MessageSetStartingPoint;
import server_package.Server;

/**
 * @author Sarp Cagin Erdogan, Qinyi, Vivian
 *
 */
public class ClientGameBasicController {
    boolean startingSubmitActive, chooseProgrammingActive;

    public ClientApplication clientApplication;
    int currentChosen;

    @FXML
    private AnchorPane winnerScene;
    @FXML
    private AnchorPane loserScene;

    @FXML
    private Button lobbyButton;
    @FXML
    private ScrollPane scrollAvailableProgramming;

    @FXML
    private ScrollPane scrollOtherRegisters;

    @FXML
    private ScrollPane scrollPaneGameBoard;

    @FXML
    private StackPane stackOwnProgramming;


    @FXML
    void startButton(ActionEvent event) {

    }
    @FXML
    private TextField startingCoordinates;

    @FXML
    void submitButton(ActionEvent event) {

        if(startingCoordinates.getText().trim()!="" && startingSubmitActive) {
            String[] singleCoordinate ={};
            String toCheck =startingCoordinates.getText().trim();
            boolean formatGood = false;
            for(int i = 0; i<toCheck.length(); i++){
                if(toCheck.charAt(i)=='/'){
                    formatGood=true;
                }
            }
            if(!formatGood){
                startingCoordinates.setText("GIVE PROPER COORDINATE BVITCH ");
            }
            else {
                singleCoordinate = toCheck.split("/");

                if (singleCoordinate.length != 2) {
                    startingCoordinates.setText("GIVE PROPER COORDINATE BVITCH ");
                } else {
                    boolean proper = false;
                    int x = -1, y = -1;
                    try {
                        x = Integer.parseInt(singleCoordinate[0]);
                        y = Integer.parseInt(singleCoordinate[1]);
                        proper=true;
                    } catch (NumberFormatException e) {
                        startingCoordinates.setText("write number idiot");
                    }
                    if(proper) {
                        Position pos = new Position(y, x);

                        startingSubmitActive=false;
                        sendStartPoint(pos);
                    }

                }
            }


        }


    }

    public void updateProgrammingCards(GridPane gridPane){
        stackOwnProgramming.getChildren().clear();
        stackOwnProgramming.getChildren().add(gridPane);
    }
    public void updateOtherRegisters(GridPane gridPane){
        scrollOtherRegisters.setContent(gridPane);
    }

    public void updateHandCards(GridPane gridPane){
        scrollAvailableProgramming.setContent(gridPane);
    }



    public void updateGameBoard(GridPane gridPane){
        scrollPaneGameBoard.setContent(gridPane);
    }
    public void sendStartPoint(Position position){
        clientApplication.basicClient.sendSelf(new MessageSetStartingPoint(position));

    }
    public void resetSubmitStartingPoint(){
        startingCoordinates.setText("");
        startingSubmitActive=true;
    }
    public void activateCardSelection(boolean bo){
        if(bo){
        chooseProgrammingActive=true;} else{chooseProgrammingActive=false;}

    }
    public void resetRegisterCards() {
    stackOwnProgramming.getChildren().clear();
    }

    public void selectCard(){
        GridPane gridPane = (GridPane) stackOwnProgramming.getChildren().get(0);
        System.out.println("Chosen: " + currentChosen + "into register: " + gridPane.getColumnCount());
        Server.serverLogger.info("Chosen: " + currentChosen + " into register: " + gridPane.getColumnCount());
        clientApplication.getClient().getPlayer().selectCard(currentChosen, gridPane.getColumnCount());
        chooseProgrammingActive=true;
    }

    @FXML
    public void mouseClicked(MouseEvent mouseEvent) {
        if(chooseProgrammingActive){
            chooseProgrammingActive=false;
            Node clicked = mouseEvent.getPickResult().getIntersectedNode();
            System.out.println("CLICKED NODE IS: " + clicked.toString());
            Server.serverLogger.info("CLICKED NODE IS: " + clicked.toString());
            boolean inside = true;
            Node requestedParent = scrollAvailableProgramming.getContent();

            while (clicked != null && clicked.getParent() != null && !clicked.getParent().equals(requestedParent)) {
                inside=false;
                //System.out.println("Current = " + clicked.toString() + " from class " + clicked.getClass().toString() + " with parent " + clicked.getParent());
                clicked = clicked.getParent();
                if (clicked.getParent() != null && clicked.getParent().equals(requestedParent)) {
                    inside = true;
                    break;
                }
            }

            if (inside) {

                int x = GridPane.getColumnIndex(clicked);
                currentChosen=x;
                //System.out.println("Index: " + currentChosen);
                GridPane own = (GridPane) stackOwnProgramming.getChildren().get(0);
                System.out.println("Chosen card is \"" + clientApplication.getClient().getPlayer().getHandCards().get(currentChosen).getCardName() + "\" for register \"" + (own.getColumnCount()) + "\"");
                Server.serverLogger.info("Chosen card is \"" + clientApplication.getClient().getPlayer().getHandCards().get(currentChosen).getCardName() + "\" for register \"" + (own.getColumnCount()) + "\"");
                clientApplication.getClient().sendSelf(new MessageSelectedCard(clientApplication.getClient().getPlayer().getHandCards().get(currentChosen).getCardName().toString(), own.getColumnCount() ));
            }
            else{
                chooseProgrammingActive=true;
            }
        }
        

    }
    public void init(){
        stackOwnProgramming.getChildren().clear();
        stackOwnProgramming.getChildren().add(new GridPane());
        scrollPaneGameBoard.setContent(new GridPane());
        scrollAvailableProgramming.setContent(new GridPane());
        scrollOtherRegisters.setContent(new GridPane());
        startingSubmitActive=true;
        chooseProgrammingActive=false;
    }

    public void triggerGameFinishedScene(){
        //TODO: Check how to get the clientID of the winner
        /*
        MessageGameFinished messageGameFinished = new MessageGameFinished(0);
        for(Player player:Game.getInstance().getPlayerList()) {
            if (messageGameFinished.clientID == player.getClient().getId()) {
                winnerScene.setVisible(true);
            } else {
                loserScene.setVisible(true);
            }
        }

         */
    }

    public void goLobby(ActionEvent event){
        clientApplication.launchBasicLobby();
    }


}
