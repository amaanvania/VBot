package loader.parsers;

import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public abstract class Parser
{
    private JSONParser parser;
    private FileReader JSONFile;

    public Parser(String path)
    {
        parser = new JSONParser();
        try
        {
            JSONFile = new FileReader(path);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Parse method implementation
     */
    public abstract void parse();

    public JSONParser getParser()
    {
        return parser;
    }

    public FileReader fetchFile()
    {
        return JSONFile;
    }
}
