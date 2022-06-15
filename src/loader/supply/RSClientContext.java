package loader.supply;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class RSClientContext implements AppletContext
{
    private final Map<String, InputStream> streams;
    private Vector<Applet> applets;
    private Applet applet;

    public RSClientContext()
    {
        streams = new HashMap<>();
        applets = new Vector<>();
        applets.add(applet);
    }

    @Override
    public AudioClip getAudioClip(URL url)
    {
        return Applet.newAudioClip(url);
    }

    @Override
    public Image getImage(URL url)
    {
        try
        {
            return ImageIO.read(url);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Applet getApplet(String name)
    {
        System.out.println("Call for applet " + name);
        return applet;
    }

    @Override
    public Enumeration<Applet> getApplets()
    {
        System.out.println("Call for applet enumeration");
        return applets.elements();
    }

    @Override
    public void showDocument(URL url)
    {
        System.out.println("Call to show document " + url.getPath());
        if (Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException("Unable to open document " + url.getPath());
            }
        }
    }

    @Override
    public void showDocument(URL url, String target)
    {
        System.out.println("Call to show document " + url.getPath());
        if (Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException("Unable to open document " + url.getPath());
            }
        }
    }

    @Override
    public void showStatus(String status)
    {
        System.out.println("Call to status " + status);
    }

    @Override
    public void setStream(String key, InputStream stream) throws IOException
    {
        if (streams.containsKey(key))
        {
            streams.remove(key);
        }
        streams.put(key, stream);
    }

    @Override
    public InputStream getStream(String key)
    {
        return streams.get(key);
    }

    @Override
    public Iterator<String> getStreamKeys()
    {
        return streams.keySet().iterator();
    }

    public void setApplet(Applet applet)
    {
        this.applet = applet;
    }
}
