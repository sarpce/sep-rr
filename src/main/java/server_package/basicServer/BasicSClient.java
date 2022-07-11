package server_package.basicServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gamelogic.Game;
import newmessages.*;
import server_package.SClient;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicSClient extends SClient {

    public BasicSClient(BasicServer server, int id, Socket socket){
        super(server, id, socket, true);
        setNamed(false);
        setListening(false);
        setReady(false);
    }
    Runnable listener = new Runnable() {
        @Override
        public void run() {
            int counter = 0;
            while(getIsListening() && !getServer().getIsTerminated()  ){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    counter++;
                    if(counter>=500){
                        sendSelf(new MessageAlive());
                        counter=0;
                    }
                    String inputString = "";
                    boolean isEnded = false;
                    int i=0;
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                    DataInputStream dataInputStream= new DataInputStream(bufferedInputStream);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

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

                    if(inputString !="")
                    /*
                    while (!isEnded && dataInputStream.available() > 0) {
                        char a = (char)dataInputStream.readInt();
                        if((int) a == 10){
                            isEnded=true;
                        }
                        inputString+=String.valueOf(a);
                    }

                     */
                    if(!inputString.equals("")){

                            String[] strings = inputString.split("\n");
                            for (String string :strings
                            ) {
                                System.out.println("RECEIVEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD: " + inputString);
                                JsonObject jsonObject =  new Gson().fromJson(string, JsonObject.class);
                                process(jsonObject, true);
                            }

                        isEnded=false;

                    }
                }
                catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                } catch (ClientNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };
    @Override
    public void listen(){
        setListening(true);
        Thread thread = new Thread(listener);
        thread.setDaemon(true);
        thread.start();
    }
    @Override
    public void sendProtocolCheck(){
        sendSelf(new MessageHelloClient(PROTOCOL_VERSION));
    }
    @Override
    public void disconnect(){
        if(getIsListening()) {
            setListening(false);
            try {
                getSocket().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void removeClientFromList(){
        getServer().setCurrentClients(getServer().getCurrentClients()-1);
        getServer().getClientList().remove(this);
    }
    @Override
    public void shutDownClient(){
        disconnect();
        removeClientFromList();
    }

    @Override
    public void process(JsonObject jsonObject, boolean isBasic) throws ClientNotFoundException, IOException {
        super.process(jsonObject, true);
    }

    @Override
    public void checkValues(String name, int figure){
        boolean available = true;
        for(SClient sClient : getServer().getClientList()){
            if(sClient.getIsNamed() && sClient.getFigure()==figure){
                available=false;
                break;
            }
        }
        if(available){
            setNamed(true);
            setName(name);
            setFigure(figure);
            sendAll(new MessagePlayerAdded(getId(), getName(), getFigure()));
            System.out.println("Client "+name +" | "+ id +" joined the Game");
            Game.getInstance().join(this);
        }
        else{
            sendSelf(new MessageError("ERROR :: Figure already taken."));
        }
    }
    @Override
    public void sendPreviousInfo(){
        for(SClient sClient : getServer().getClientList()){
            if(sClient.getIsNamed() && sClient.getId()!=this.getId()){
                System.out.println(sClient.getId() + sClient.getName() + sClient.getFigure() +  sClient.getIsReady());
                sendSelf(new MessagePlayerAdded(sClient.getId(), sClient.getName(), sClient.getFigure()));
                sendSelf(new MessagePlayerStatus(sClient.getId(), sClient.getIsReady()));
            }
        }
    }
}
