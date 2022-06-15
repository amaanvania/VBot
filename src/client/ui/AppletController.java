package client.ui;

import api.bridge.insertion.IClient;
import client.ui.components.viewers.ViewerManager;
import loader.modifiers.replacements.BotFocusListener;
import loader.modifiers.replacements.BotMouseListener;
import loader.supply.RSClientStub;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppletController extends JPanel
{
    private Dimension DIMENSIONS = new Dimension(765, 503);
    private BotFocusListener focusListener;
    private BotMouseListener mouseListener;
    private ViewerManager viewerManager;
    private RSClientStub stub;
    private Applet applet;
    private IClient client;

    public AppletController(Class<?> client, HashMap<String, String> parameters)
    {
        this.setBackground(Color.black);
        try
        {
            create(client, parameters);
            this.add(applet);
            focusListener = new BotFocusListener(this);
            mouseListener = new BotMouseListener(this);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        viewerManager = new ViewerManager();
    }

    public Applet getApplet()
    {
        return applet;
    }

    public IClient getClient(){
        return client;
    }
    public BotMouseListener getMouseListener()
    {
        return mouseListener;
    }

    public BotFocusListener getFocusListener()
    {
        return focusListener;
    }

    public ViewerManager getViewerManager()
    {
        return viewerManager;
    }

    public void destroy()
    {
        System.out.println("Destroying applet");
        applet.stop();
        applet.destroy();
    }

    private void create(Class<?> client, HashMap<String, String> parameters) throws Exception
    {
        Logger.getGlobal().log(Level.INFO, "Creating applet");

        Object panelContainer = client.newInstance();
        applet = (Applet) ((JPanel) panelContainer).getComponents()[0];
        Field f = client.getDeclaredField("b");
        f.setAccessible(true);
        Object c = f.get(panelContainer);
        this.client = (IClient) c;
        //applet = (Applet) client.newInstance();

        assert applet != null;

        stub = new RSClientStub(new URL(parameters.get("documentBase")), new URL(parameters.get("codeBase")), parameters);
        stub.getAppletContext().setApplet(applet);

        applet.setStub(stub);
        applet.setLayout(new FlowLayout());
        applet.setPreferredSize(DIMENSIONS);
    }

    public void start()
    {
        applet.init();
        applet.start();
    }
}
