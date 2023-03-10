package Page;

import ClientThread.BidThread;
import Event.*;

import Page.GamePagePanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Component.*;
import Page.GamePagePanel.RemainTimer;

import static Event.MainTimer.randNumber;
import static Event.MainTimer.randTitle;
import static Frame.MainFrame.client;
import static Page.GamePagePanel.OnChat.input;
import static Page.GamePagePanel.ScrollChatting.vertical;

public class GamePageView extends JPanel {
    public static JLabel[] card = new JLabel[52];
    public static JLabel[] cardDack = new JLabel[52];
    public static JLabel CenterCard = new CardView(590, 150, 141, 200, "wait");
    public static JPanel currentChatting = new Chat();
    public static JPanel onChat = new OnChat();
    public static JLabel alarm = new Alarm();
    public static boolean on = false;
    public static JPanel connectUser = new ConnectUserPanel();
    public static JScrollPane Chatting = new ScrollChatting();
    public static JLabel coin = new CurrentCoin();
    public static javax.swing.Timer mainTimer = new javax.swing.Timer(5000, new MainTimer());
    public static JLabel timerNum = new RemainTimer(5);;
    public static JLabel button = new Bid();
    public GamePageView(){

//        setFocusable(true);
//        requestFocus();
        Color background = new Color(255, 255, 255);
        setLayout(null);
        setBorder(null);
        setBounds(0, 0, 1280, 720);
        setBackground(background);
        add(alarm);

        CenterCard.setOpaque(false);
        add(CenterCard);
        add(button);

        add(Chatting);
        add(onChat);
        onChat.setVisible(on);

        char[] title = {'A', 'B', 'C', 'D'};
        int[] h = {200, 250, 300, 350};
        add(new CurrentTitle(1150, 160, "보유한 카드"));
        for(int i = 0; i < 52; i++){
            cardDack[i] = new CardView(800 + (i%13)*35, h[i / 13], 35, 50, title[i / 13] + String.valueOf((i%13)+1));
            add(cardDack[i]);
            cardDack[i].setVisible(false);
        }

        add(new CurrentTitle(1150, 410, "남아있는 카드"));
        h[0] = 450; h[1] = 500; h[2] = 550; h[3] = 600;
        for(int i = 0; i < 52; i++){
            card[i] = new CardView(800 + (i%13)*35, h[i / 13], 35, 50, title[i / 13] + String.valueOf((i%13)+1));
            add(card[i]);
        }

        add(coin);
        add(connectUser);
        add(timerNum);

        add(new GamePage());
        requestFocus();
        setFocusable(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Thread bidThread = new BidThread(client.getSocket());
                bidThread.start();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!on){
                        vertical.setValue(vertical.getMaximum());
                        GamePageView.onChat.setVisible(true);
                        input.requestFocus();
                    }
                    on = !on;
                }
            }
        });
    }
    class CurrentTitle extends JLabel{
        CurrentTitle(int x, int y, String title){
            setText(title);
            setBounds(x, y, 150, 30);
            setFont(FontStyle.BidFont);
            setForeground(new Color(154, 254, 132));
        }
    }
}
