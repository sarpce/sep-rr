package client_package;

import client_application.ClientApplication;
import client_package.client_gamelogic.Game;
import client_package.client_gamelogic.ThisCPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gamelogic.Color;
import newmessages.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan
 */
public abstract class Client implements ClientObject{
    protected ArrayList<Message> toSendList = new ArrayList<>();
    public void addToSendList(Message message){
        toSendList.add(message);
    }

    protected List<ClientObject> clientList;
    //TODO playerlist as filter for clients that are playing
    protected List<ClientObject> playerList;
    protected ClientApplication clientApplication;

    protected ThisCPlayer player;
    protected Game game;

    {
        try {
            game = Game.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String group;
    protected String name = "";
    protected int figure, id;
    protected Socket socket;
    protected boolean isListening, isReady, isForList, isBasic;
    public Client(){
    }

    public Color getRoboColor(){
        switch (figure){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.ORANGE;

            case 5:
                return Color.PURPLE;

            case 6:
                return Color.YELLOW;

            default:throw new IllegalArgumentException();
        }
    }

    public Client(ClientApplication clientApplication, boolean isBasic){
        setClientApplication(clientApplication);
        setIsBasic(isBasic);
        setClientList(new ArrayList<>());
        game.setClient(this);
    }



    public ClientObject clientFromId(int inp){
        for(ClientObject client : this.clientList){
            if(client.getId()==inp){
                return client;
            }
        }
        return null;
    }


    public void sendSelf(Message temp){
        addToSendList(temp);
        Message message = toSendList.get(0);

        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
            String toSend = message.toJSON().toString().replaceAll("\n","").trim() + "\n";
            System.out.println("SENT :: " + toSend);
            writer.write(toSend);
            writer.write("\n");
            writer.flush();

            /*
            char[] arr = toSend.toCharArray();
            String print = "";
            int count = 0;
            for (char c:arr) {
                dataOutputStream.writeInt((int)c);
                print+=c;
                count++;
            }
            dataOutputStream.flush();
            System.out.println("SENT: " + print+"count: "+count);

             */
            toSendList.remove(message);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientObject> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<ClientObject> playerList) {
        this.playerList = playerList;
    }

    public void setPlayer(ThisCPlayer player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void listen();
    public abstract void process(JsonObject jsonObject);
    protected Runnable basicListener = new Runnable() {
        @Override
        public void run() {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                DataInputStream dataInputStream= new DataInputStream(bufferedInputStream);
                BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

                while(isListening){


                    TimeUnit.MILLISECONDS.sleep(10);
                    System.out.println("LASFDLKASDFGLAFSDG");
                    String inputString = "";
                    boolean isEnded = false;
                    int readChars =dataInputStream.available();

                    while(!isEnded && readChars>0){
                        String input = reader.readLine();
                        if(input.equals("\n" )|| input.equals("")){
                            isEnded = true;
                        }

                        else
                            inputString += input;
                        readChars--;
                    }

                    if(inputString!="")
                    /*
                    if(dataInputStream.available()>0)
                    System.out.println("waiting Chars: "+ dataInputStream.available());
                    while (!isEnded && dataInputStream.available() > 0) {
                        int in = dataInputStream.readInt();
                       // System.out.println(in);


                        char a = (char)in;
                        //TODO possible reason for fail

                        if((int) a == 10){
                            isEnded=true;
                        }


                        inputString+=a;
                    }
                    if(dataInputStream.available()>0)
                    System.out.println("still aviable in data: " +dataInputStream.available());
                    if(socket.getInputStream().available()>0)
                    System.out.println("still aviable in socket: " +socket.getInputStream().available());
                    if (inputString.length()>0)
                    System.out.println("message length: "+inputString.length());
                     */
                    if(!inputString.equals("")){

                            String[] strings = inputString.split("\n");
                            for (String string :strings
                                 ) {
                                System.out.println("RECEIVED: " + inputString);
                                JsonObject jsonObject =  new Gson().fromJson(string, JsonObject.class);
                                process(jsonObject);


                            }

                        isEnded=false;

                    }
                }


            }catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    };



    /*GETTER SETTER
    *
    *
    *
    *
    *
    *
    *
    *
    */

    public Game getGame() {
        return game;
    }

    public List<ClientObject> getClientList(){
        return this.clientList;
    }
    public void setClientList(List<ClientObject> clientList){
        this.clientList =clientList;
    }

    public ClientApplication getClientApplication() {
        return clientApplication;
    }

    public void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Socket getSocket(){
        return this.socket;
    }

    public void setSocket(Socket socket){
        this.socket=socket;
    }

    public boolean isListening() {
        return isListening;
    }

    public void setIsListening(boolean listening) {
        isListening = listening;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {
        isReady = ready;
    }

    public boolean isForList() {
        return isForList;
    }

    public void setIsForList(boolean forList) {
        isForList = forList;
    }


    public boolean isBasic() {
        return isBasic;
    }

    public void setIsBasic(boolean basic) {
        isBasic = basic;
    }


    public ThisCPlayer getPlayer() {
      return player;
    }
}
