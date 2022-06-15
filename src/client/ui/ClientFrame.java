package client.ui;

import api.BotContext;
import api.script.Script;
import client.BotEngine;
import client.ui.components.BotButton;
import client.ui.components.ViewerObject;
import client.ui.components.viewers.impl.Cursor;
import client.ui.components.viewers.impl.*;
import client.ui.components.viewers.impl.text.Packet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class ClientFrame extends JFrame
{
    private JToolBar toolBar;
    private ArrayList<ViewerObject> viewers;
    public BotButton play;
    public BotButton pause;
    public BotButton stop;
    BotButton settings;
    BotButton tools;
    BotButton input;


    public ClientFrame()
    {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        createToolBar();
        this.setPreferredSize(new Dimension(765, 503 + toolBar.getPreferredSize().height + 40));
        this.add(toolBar, BorderLayout.PAGE_START);
    }

    public BotButton getPlay(){
        return play;
    }

    public BotButton getPause() {
        return pause;
    }

    public BotButton getStop() {
        return stop;
    }

    public BotButton getSettings() {
        return settings;
    }

    public BotButton getTools() {
        return tools;
    }

    private void proxyForm(){
        JFrame ff = new JFrame("Proxy");
        ff.setSize(400,400);
        ff.setMinimumSize(new Dimension(400,400));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ff.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

       // ff.setResizable(false);


        JLabel name = new JLabel("Proxy Ip:");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(80, 20);
        name.setLocation(100, 100);
        ff.add(name);

        JTextField tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(80, 20);
        tname.setLocation(200, 100);
        ff.add(tname);

        JLabel name2 = new JLabel("Port:");
        name2.setFont(new Font("Arial", Font.PLAIN, 20));
        name2.setSize(80, 20);
        name2.setLocation(100, 150);
        ff.add(name2);

        JTextField tname2 = new JTextField();
        tname2.setFont(new Font("Arial", Font.PLAIN, 15));
        tname2.setSize(80, 20);
        tname2.setLocation(200, 150);
        ff.add(tname2);

        JLabel name3 = new JLabel("User:");
        name3.setFont(new Font("Arial", Font.PLAIN, 20));
        name3.setSize(80, 20);
        name3.setLocation(100, 200);
        ff.add(name3);

        JTextField tname3 = new JTextField();
        tname3.setFont(new Font("Arial", Font.PLAIN, 15));
        tname3.setSize(80, 20);
        tname3.setLocation(200, 200);
        ff.add(tname3);

        JLabel name4 = new JLabel("Pass:");
        name4.setFont(new Font("Arial", Font.PLAIN, 20));
        name4.setSize(80, 20);
        name4.setLocation(100, 250);
        ff.add(name4);

        JTextField tname4 = new JTextField();
        tname4.setFont(new Font("Arial", Font.PLAIN, 15));
        tname4.setSize(80, 20);
        tname4.setLocation(200, 250);
        ff.add(tname4, BorderLayout.CENTER);

        JTextField tname5 = new JTextField();
        tname5.setFont(new Font("Arial", Font.PLAIN, 15));
        tname5.setSize(80, 20);
        tname5.setLocation(200, 260);
        ff.add(tname5);


        JButton submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setMaximumSize(new Dimension(80,20));
        submit.setLocation(140, 300);





        submit.addActionListener(e -> {
            try {
                if(tname.getText().length() >= 8 && tname2.getText().length() <= 4 && tname.getText().length() >= 7 && tname2.getText().length() > 0) {
                    if (tname3.getText().length() == 0 || tname4.getText().length() == 0) {
                        BotEngine.getEngine().setProxy(tname.getText(), tname2.getText());
                    } else {
                        BotEngine.getEngine().setProxy(tname.getText(), tname2.getText(), tname3.getText(), tname4.getText());
                    }
                    JOptionPane.showMessageDialog(null, "Success");
                    ff.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to set proxy");
                }
            } catch(Exception g){
                JOptionPane.showMessageDialog(null, "Unable to set proxy");
            }
        });
        ff.add(submit, BorderLayout.SOUTH);


        ff.setVisible(true);



    }



    private void createToolBar()
    {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setFocusable(false);
        toolBar.setBackground(new Color(19, 6, 6));
        toolBar.setVisible(true);

        play = new BotButton(loadResourceImage("resources/images/play.png"), "Open Script Selector / Resume Script");
        pause = new BotButton(loadResourceImage("resources/images/pause.png"), "Pause script");
        stop = new BotButton(loadResourceImage("resources/images/stop.png"), "Stop script");
        settings = new BotButton(loadResourceImage("resources/images/menu.png"), "View");
        tools = new BotButton(loadResourceImage("resources/images/tools.png"), "Tools");

        JPopupMenu view = new JPopupMenu();
        viewers = new ArrayList<>();

        ViewerObject cursor = new ViewerObject("Cursor", new Cursor());
        cursor.setSelected(true);
        view.add(cursor);
        viewers.add(cursor);

        ViewerObject objects = new ViewerObject("Ground Items", new Objects());
        view.add(objects);
        viewers.add(objects);

        ViewerObject location = new ViewerObject("Location", new Location());
        view.add(location);
        viewers.add(location);

        ViewerObject lowCpu = new ViewerObject("Low CPU", new LowCPU());
        view.add(lowCpu);
        viewers.add(lowCpu);

        ViewerObject npcs = new ViewerObject("NPCs", new Npcs());
        view.add(npcs);
        viewers.add(npcs);

        ViewerObject inventory = new ViewerObject("Inventory", new Inventory());
        view.add(inventory);
        viewers.add(inventory);

        ViewerObject players = new ViewerObject("Players", new PlayerViewer());
        view.add(players);
        viewers.add(players);

        ViewerObject packets = new ViewerObject("Packet", new Packet());
        view.add(packets);
        viewers.add(packets);

        ViewerObject GameObjects = new ViewerObject("GameObjects", new GameObjects());
        view.add(GameObjects);
        viewers.add(GameObjects);

        ViewerObject menurow = new ViewerObject("MenuRow", new MenuRow());
        view.add(menurow);
        viewers.add(menurow);

        ViewerObject interfaces = new ViewerObject("Interfaces", new Interface());
        view.add(interfaces);
        viewers.add(interfaces);

        play.addNewActionListener(e -> {
            try{

            }catch (Exception ee){
                ee.printStackTrace();
            }

        });
        toolBar.add(play);

        pause.addNewActionListener(e -> {
            Script script = BotContext.engine.getRunningScript();
            if (script != null)
            {
                script.setScriptState(Script.ScriptState.PAUSED);
                play.setEnabled(true);
                pause.setEnabled(false);
                stop.setEnabled(true);
            }

        });
        pause.setEnabled(false);
        toolBar.add(pause);

        stop.addNewActionListener(e -> {
            Script script = BotContext.engine.getRunningScript();
            if (script != null)
            {
                script.setScriptState(Script.ScriptState.STOPPED);
                stop.setEnabled(false);
                pause.setEnabled(false);
                play.setEnabled(true);
            }
        });
        stop.setEnabled(false);
        toolBar.add(stop);
//
//        tools.addNewActionListener(e -> {
//
//            if(!BotContext.client.isLoggedIn())
//                proxyForm();
//            else JOptionPane.showMessageDialog(null,"Log out to change ip");
//        });
//        tools.setEnabled(true);
//        toolBar.add(tools);
        //view.add(text);
        //view.add(overlay);

        settings.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                view.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(Box.createHorizontalStrut(this.getWidth() / 4));
        toolBar.add(settings);


    }

    public ArrayList<ViewerObject> getViewers()
    {
        return viewers;
    }

    private ImageIcon loadResourceImage(String ResourcePath)
    {
        try
        {
            return new ImageIcon(ImageIO.read(new File(ResourcePath)).getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
