package loader.parsers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class InjectionParser extends Parser
{
    private JSONArray classInjectors, methodCallbacks;

    public InjectionParser(String path)
    {
        super(path);
    }

    @Override
    public void parse()
    {
        JSONObject raw = null;
        try
        {
            raw = (JSONObject) super.getParser().parse(super.fetchFile());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (raw != null)
        {
            JSONObject configuration = (JSONObject) raw.get("inject");
            System.out.println("Configuration parameters: " + configuration.toJSONString());
            classInjectors = (JSONArray) configuration.get("classes");
            methodCallbacks = (JSONArray) configuration.get("callbacks");
        }
    }

    public JSONArray getClassInjectors()
    {
        return classInjectors;
    }
}
