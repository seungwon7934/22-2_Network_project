package ClientThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnect {
    private Socket socket;
    private BufferedReader inMsg;
    private PrintWriter outMsg;
    public void connectServer(){
        try{
            this.socket = new Socket("127.0.0.1", 1111);
            System.out.println("[Client]Server 연결 성공!!");
            this.inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outMsg = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){}
    }
    public BufferedReader getInMsg(){
        return inMsg;
    }
    public PrintWriter getOutMsg(){
        return outMsg;
    }
    public Socket getSocket(){
        return socket;
    }
    public void endSocket(){
        try{
            this.inMsg.close();
            this.outMsg.close();
            this.socket.close();
        }catch (Exception e){}
    }
}
