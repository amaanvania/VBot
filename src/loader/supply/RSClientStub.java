package loader.supply;

import java.applet.AppletStub;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSClientStub implements AppletStub
{
    private URL docBase, codeBase;
    private HashMap<String, String> params;
    private final RSClientContext context;

    public RSClientStub(URL docBase, URL codeBase, HashMap<String, String> params)
    {
        context = new RSClientContext();
        this.docBase = docBase;
        this.codeBase = codeBase;
        this.params = params;
    }

    @Override
    public boolean isActive()
    {
        System.out.println("Call for active state");
        return true;
    }

    @Override
    public URL getDocumentBase()
    {
        System.out.println("Call for documentBase");
        return docBase;
    }

    @Override
    public URL getCodeBase()
    {
        System.out.println("Call for codebase");
        return codeBase;
    }

    @Override
    public String getParameter(String name)
    {
        System.out.println("Call for parameter " + name);
        return params.get(name);
    }

    @Override
    public RSClientContext getAppletContext()
    {
        Logger.getGlobal().log(Level.INFO, "Call for AppletContext");
        return context;
    }

    @Override
    public void appletResize(int width, int height)
    {
        System.out.println("Call for applet resize " + "(width=" + width + ", height=" + height + ")");
    }
}
