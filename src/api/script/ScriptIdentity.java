package api.script;

public class ScriptIdentity
{
    private String name;
    private String authors[];
    private String description;
    private double version;
    private Class<?> className;

    ScriptIdentity(String name, String authors[], String description, double version, Class<?> className)
    {
        this.name = name;
        this.authors = authors;
        this.description = description;
        this.version = version;
        this.className = className;
    }

    ScriptIdentity(ScriptManifest manifest, Class<?> className)
    {
        this(manifest.name(), manifest.authors(), manifest.description(), manifest.version(), className);
    }

    /**
     * @return script name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return script author(s)
     */
    public String[] getAuthors()
    {
        return authors;
    }

    /**
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    public double getVersion()
    {
        return version;
    }

    public Class<?> getClassName()
    {
        return className;
    }

    public Script asScript()
    {
        try
        {
            Object instance = className.newInstance();
            if (instance instanceof Script)
            {
                return (Script) instance;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
