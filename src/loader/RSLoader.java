package loader;

import loader.parsers.ConfigParser;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class RSLoader
{
    private HashMap<String, String> parameters;

    public RSLoader()
    {
        loadConfig();
    }

    private void loadConfig()
    {
        ConfigParser parser = new ConfigParser("resources/Configuration");
        parser.parse();

        parameters = new HashMap<>();

        parser.getParameters().forEach(parameter ->
        {
            Set<String> set = ((JSONObject) parameter).keySet();
            Iterator iterator = set.iterator();

            /*
             * maps each ClientConfiguration element
             * to the applet's stub parameters
             */
            while (iterator.hasNext())
            {
                String key = iterator.next().toString();
                String value = ((JSONObject) parameter).get(key).toString();
                parameters.put(key, value);
            }
        });
    }

    public HashMap<String, String> getParameters()
    {
        return parameters;
    }
}
