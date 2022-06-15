package loader.parsers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigParser extends Parser
{
    private static final Logger LOGGER = Logger.getLogger(ConfigParser.class.getName());
    private JSONArray parameters;

    public ConfigParser(String path)
    {
        super(path);
    }

    @Override
    public void parse()
    {
        try
        {
            JSONObject raw = (JSONObject) super.getParser().parse(super.fetchFile());
            if (raw != null)
            {
                JSONObject configuration = (JSONObject) raw.get("configuration");
                parameters = (JSONArray) configuration.get("parameters");
            }
            else
            {
                LOGGER.log(Level.SEVERE, "configuration object null");
            }
        } catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Unable to parse configuration file " + e.getMessage());
        }
    }

    public JSONArray getParameters()
    {
        return parameters;
    }
}
