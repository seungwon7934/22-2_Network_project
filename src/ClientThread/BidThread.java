package ClientThread;

import GameData.ClientUserData;
import Lee.Login;

import java.io.PrintWriter;
import java.net.Socket;

public class BidThread extends Thread{
    Socket socket = null;
    private ClientUserData userData;
    public BidThread( Socket socket) {
        this.socket = socket;
        this.userData = new ClientUserData();
    }

    @Override
    public void run() {
        try {
            if(userData.coin < userData.userBid + 5) {
                userData.userBid += 5;
                String userName = userData.userName;
                System.out.println("send: " + userName + "/" + userData.userBid);
                PrintWriter outMsg = new PrintWriter(socket.getOutputStream(), true);
                outMsg.println("RegisterBid" + "/" + userName + "/" + userData.userBid);
            }
        } catch (Exception e) {}
    }
}
