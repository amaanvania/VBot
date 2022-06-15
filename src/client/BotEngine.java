package client;

import api.BotContext;
import api.bridge.insertion.IClient;
import api.input.VirtualKeyboard;
import api.input.VirtualMouse;
import api.interfaces.listeners.game.GameMessageListener;
import api.interfaces.listeners.game.PacketActionListener;
import api.script.Script;
import client.ui.AppletController;
import client.ui.ClientFrame;
import client.ui.components.ViewerObject;
import loader.RSLoader;
import loader.access.RSClassLoader;
import loader.modifiers.jar.JarEntryDumper;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;

public class BotEngine
{
    private static BotEngine instance;
    public ClientFrame frame;
    private RSLoader loader;
    private RSClassLoader classLoader;
    private AppletController controller;
    private String hashCode;
    private Script script;
    private Applet applet;
    private VirtualKeyboard keyboard;
    private VirtualMouse mouse;

    public BotEngine()
    {
        instance = this;
    }

    public void setupWindow()
    {
        frame = new ClientFrame();
        frame.setBackground(Color.BLACK);

    }

    public void downloadClient()
    {
        Logger.getGlobal().log(Level.INFO, "Downloading Client");
        loader = new RSLoader();
    }

    public void injectClient()
    {
        Logger.getGlobal().log(Level.INFO, "Injecting Client");
        try
        {
            JarEntryDumper dumper = new JarEntryDumper(new ZipFile("Client.jar"));

            Logger.getGlobal().log(Level.INFO, "Setting up the ClassLoader");
            classLoader = new RSClassLoader(dumper.dumpResources(new File("modified.jar")));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setInstance(BotEngine instance) {
        BotEngine.instance = instance;
    }

    public void parseArguments(String[] arguments) {
        for (int index = 0; index < arguments.length; index++) {
            final String key = arguments[index];
            String value = "";
            if (!key.isEmpty() && key.charAt(0) == '-') {
                if (index + 1 < arguments.length) {
                    value = sanitize(arguments[index + 1]);
                }
                switch (key.substring(1)) {
                    case "uuid":
                        if (value.equals("random")) {
                            //setUUID(UUID.randomUUID().toString().replace("-", "").substring(0, (r.nextInt(6) + 7)).toUpperCase());
                        } else {
                            //setUUID(value);
                        }
                        break;
                    case "vanilla":
                        //setVanilla(true);
                        break;
                    case "proxy":
                        if(value.contains(":") && value.split(":").length >= 2) {
                            String[] split = value.split(":");
                            if(split.length == 4) {
                                setProxy(split[0], split[1], split[2], split[3]);
                                System.out.println("Proxy set to: " + split[0] + ":" + split[1]);
                            } else {
                                setProxy(split[0], split[1]);
                                System.out.println("Proxy set to: " + split[0] + ":" + split[1]);
                            }
                        } else {
                            //Bot.LOGGER.warn("Invalid proxy parameters. Correct usage: host:port:user:pass");
                        }
                        break;
                    case "login":
                        //TODO login(user, pass);
                        break;
                    case "profile":
                        if(value.contains("!") && value.split("!").length >= 2){
                            String[] split = value.split("!");
                            StringBuilder sb = new StringBuilder();
                            String[] splitted = split[2].split("-");
                            sb.append(splitted[0]);
                            sb.append(" - ");
                            sb.append(splitted[1]);
                        }
                        break;
                    case "break":
                        break;
                }
            }
        }
    }

    private String sanitize(String value) {
        if (value != null && !value.isEmpty()) {
            return value.replaceAll("[\"']", "");
        }
        return "";
    }

    public void init(){
        controller = new AppletController(classLoader.loadClass(loader.getParameters().get("client")), loader.getParameters());
        keyboard = new VirtualKeyboard(controller.getApplet());
        mouse = new VirtualMouse(controller.getApplet());
        frame.add(controller, BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                frame.setVisible(false);
                controller.destroy();
                super.windowClosed(e);
                e.getWindow().dispose();
            }
        });

        frame.revalidate();
        frame.pack();
        frame.setLocationRelativeTo(frame.getOwner());
        frame.setTitle("VBot 1.0");
        frame.setVisible(true);

        controller.start();

    }
    public void setProxy(String host, String port) {
        setProxy(host, port, null, null);
    }

    public void setProxy(String host, String port, String user, String pass) {
        System.setProperty("socksProxyHost", host);
        System.setProperty("socksProxyPort", port);
        if (user != null && pass != null) {
            System.setProperty("java.net.socks.username", user);
            System.setProperty("java.net.socks.password", pass);
            Authenticator.setDefault(new ProxyAuth(user, pass));
        }
    }
    public ArrayList<ViewerObject> getFrameViewers()
    {
        return frame.getViewers();
    }

    public Script getRunningScript()
    {
        return script;
    }

    public void setRunningScript(Script script)
    {
        Thread t = new Thread(script);
        t.start();
        this.script = script;
    }

    public String getHashCode()
    {
        return hashCode;
    }

    public void setHashCode(String code)
    {
        hashCode = code;
    }

    public RSClassLoader getClassLoader()
    {
        return classLoader;
    }

    public static BotEngine getEngine()
    {
        return instance;
    }

    public Applet getApplet()
    {
        return controller.getApplet();
    }

    public IClient getClient(){
        return controller.getClient();
    }
    public static VirtualKeyboard getKeyboard() {
        return instance.keyboard;
    }

    public static VirtualMouse getMouse() {
        return instance.mouse;
    }

    public AppletController getAppletController()
    {
        return controller;
    }

    public void dispatchMessage(String sender, int type, String message)
    {
        if (script != null && script instanceof GameMessageListener)
        {
            ((GameMessageListener)script).messageReceived(sender, type, message);
        }
    }

    public void dispatchLogin(String paramString1, String paramString2, String paramString3){
        if (script != null && script instanceof GameMessageListener)
        {
        }
    }

    public void dispatchAction(int actionID, int cmd1, int cmd2, int cmd3)
    {
        if (script != null && script instanceof PacketActionListener)
        {
            ((PacketActionListener)script).actionInvoked(actionID, cmd1, cmd2, cmd3);
        }
    }

    public class ProxyAuth extends Authenticator {
        private PasswordAuthentication auth;

        private ProxyAuth(String user, String password) {
            auth = new PasswordAuthentication(user, password == null ? new char[]{} : password.toCharArray());
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return auth;
        }
    }
}
