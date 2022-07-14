package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.basicClient.BasicClient;
import client_package.client_gamelogic.OtherClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerAdded extends Message{

    public int clientID;
    public String name;
    public int figure;

    /**
     * @param clientID
     * @param name
     * @param figure
     */
    public MessagePlayerAdded(int clientID, String name, int figure) {
        super();
        this.clientID = clientID;
        this.name = name;
        this.figure = figure;
        type = "PlayerAdded";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("name", new JsonPrimitive(name));
        jsonObject.add("figure", new JsonPrimitive(figure));
        content = jsonObject;
        //Server.serverLogger.info("Created Player Added Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayerAdded(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
        //Server.serverLogger.info("Created Player Added Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            Client.clientLogger.info("activating playerAdded");
            if (this.clientID == client.getId()){
                client.setName(this.name);
                client.setFigure(this.figure);
                client.getGame().join(client);
                Client.clientLogger.info("This client:\n" +
                        "ID:="+client.getId() +"\n"+
                        "Name:="+name +"\n" +
                        "Figure:=" +figure);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.LAUNCHLOBBY, new TaskContent()));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }else{
                OtherClient otherClient = new OtherClient(client.getGame(),this.clientID, this.figure,this.name);
                Client.clientLogger.info("New client:\n" +
                        "ID="+otherClient.getClientID() +"\n"+
                        "Name="+name +"\n" +
                        "Figure=" +figure);
                client.getClientList().add(otherClient);
                Client.clientLogger.debug("New Client added to "+ client + "client.clientList");
                Client.clientLogger.debug("client.clientList:= "+ client.getClientList());
                client.getGame().join(otherClient);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
        }
        else{
            //ADVANCED
        }

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.clientID == client.getId()){
                client.setName(this.name);
                client.setFigure(this.figure);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.LAUNCHLOBBY, new TaskContent()));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }else{
                client.getClientList().add(new BasicClient(this.clientID, this.name, this.figure));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
            client.sendSelf(new MessageSetStatus(true));
        }
        else{
            //ADVANCED
        }

    }

}
