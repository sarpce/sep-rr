package client_package.basicClient;
import client_application.*;
import client_package.Client;
import com.google.gson.JsonObject;
import newmessages.*;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class MessageProcessor extends client_package.MessageProcessor {
    BasicClient basicClient;
    public MessageProcessor(Client client){
        super(client);
    }
    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        process(jsonObject, true);
    }
    /*
    public void process(JsonObject jsonObject){
        MessageType messageType = MessageType.valueOf(jsonObject.get("type").getAsString());

        switch (messageType){
            case HELLOCLIENT -> {
                MessageHelloClient messageHelloClient = new MessageHelloClient(jsonObject);
                if(messageHelloClient.protocol.equals("Version 0.1")){
                    System.out.println("EVENT :: Correct communication protocol verified.");

                }
                else{
                    System.out.println("ERROR :: False communication protocol.");
                }
            }
            case ALIVE -> {
                basicClient.sendSelf(new MessageAlive());
            }
            case WELCOME -> {
                MessageWelcome messageWelcome = new MessageWelcome(jsonObject);
                basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.GOTID, new TaskInt1(messageWelcome.clientID)));
            }
            case ERROR -> {
                MessageError messageError = new MessageError(jsonObject);
                System.out.println(messageError.error);
                if(messageError.error.equals("ERROR :: Figure already taken.")){
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.FIGURETAKEN, new TaskContent()));
                }
            }
            case PLAYERADDED -> {
                MessagePlayerAdded messagePlayerAdded = new MessagePlayerAdded(jsonObject);
                if (messagePlayerAdded.clientID==basicClient.id){
                    basicClient.name= messagePlayerAdded.name;
                    basicClient.figure= messagePlayerAdded.figure;
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.LAUNCHLOBBY, new TaskContent()));
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
                }
                else{
                    basicClient.playerList.add(new BasicClient(messagePlayerAdded.clientID, messagePlayerAdded.name, messagePlayerAdded.figure));
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
                }
            }
            case RECEIVEDCHAT -> {
                MessageReceivedChat messageReceivedChat = new MessageReceivedChat(jsonObject);
                if(messageReceivedChat.isPrivate){
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.CHATMESSAGE, new TaskString1("[Private] " + messageReceivedChat.from + " :: " + messageReceivedChat.message)));
                }
                else{
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.CHATMESSAGE, new TaskString1(messageReceivedChat.from + " :: " + messageReceivedChat.message)));
                }
            }
            case PLAYERSTATUS -> {
                MessagePlayerStatus messagePlayerStatus = new MessagePlayerStatus(jsonObject);
                if(messagePlayerStatus.clientID==basicClient.id) {
                    basicClient.isReady = messagePlayerStatus.ready;
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.UPDATEREADYBUTTON, new TaskBoolean(messagePlayerStatus.ready)));
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
                }
                else{
                    basicClient.clientFromId(messagePlayerStatus.clientID).isReady = messagePlayerStatus.ready;
                    basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
                }

            }
            case SELECTMAP -> {
                MessageSelectMap messageSelectMap = new MessageSelectMap(jsonObject);
                basicClient.clientApplication.addAndExecuteTask(new Task(TaskType.AVAILABLEMAPS, new TaskJsonArray(messageSelectMap.availableMaps)));
            }
            case DEFAULT -> {
            }
            default -> {
            }
        };


    }
    */
}
