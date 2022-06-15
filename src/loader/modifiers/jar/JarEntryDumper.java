package loader.modifiers.jar;

import api.bridge.insertion.IApplet;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import loader.modifiers.Injector;
import loader.parsers.InjectionParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarEntryDumper extends Injector
{
    private HashMap<String, ClassNode> classes;
    private HashMap<String, byte[]> resources;

    public JarEntryDumper(ZipFile zipFile) throws IOException
    {
        classes = new HashMap<>();
        resources = new HashMap<>();
        parse(zipFile);
        inject();
    }

    private void parse(ZipFile zip) throws IOException
    {
        Enumeration<? extends ZipEntry> entries = zip.entries();

        while (entries.hasMoreElements())
        {
            ZipEntry entry = entries.nextElement();

            if (entry.getName().endsWith(".class"))
            {
                ClassReader reader = new ClassReader(zip.getInputStream(entry));
                ClassNode node = new ClassNode();
                reader.accept(node, ClassReader.EXPAND_FRAMES);
                classes.put(node.name, node);
            }
            else
            {
                InputStream stream = zip.getInputStream(entry);
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                byte data[] = new byte[1024];
                int count;

                while ((count = stream.read(data, 0, 1024)) != -1)
                {
                    os.write(data, 0, count);
                }

                resources.put(entry.getName(), os.toByteArray());
                stream.close();
                os.close();
            }
        }

        zip.close();
    }

    public File dumpResources(File jar) throws IOException
    {
        if (jar.exists())
        {
            jar.delete();
        }

        jar.createNewFile();
        JarOutputStream output = new JarOutputStream(new FileOutputStream(jar));

        classes.values().forEach(c ->
        {
            try
            {
                dumpClass(output, c.name, c);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        resources.forEach((name, bytes) ->
        {
            try
            {
                dumpResource(output, name, bytes);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        output.close();

        return jar;
    }

    private void dumpClass(JarOutputStream out, String name, ClassNode cn) throws IOException
    {
        JarEntry entry = new JarEntry(name + ".class");
        out.putNextEntry(entry);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cn.accept(writer);
        out.write(writer.toByteArray());
    }

    private void dumpResource(JarOutputStream out, String name, byte[] bytes) throws IOException
    {
        JarEntry entry = new JarEntry(name);
        out.putNextEntry(entry);
        out.write(bytes);
    }

    @Override
    public void inject()
    {
        classes.values().forEach(c ->
        {


            if (c.superName.equals("java/applet/Applet"))
            {
                Logger.getGlobal().log(Level.INFO, "Subclassing Applet");
                setSuperclass(c, IApplet.class.getName().replace('.', '/'));
            }

        });

        InjectionParser injectionParser = new InjectionParser("resources/InjectionMaps");
        injectionParser.parse();
        Logger.getGlobal().log(Level.INFO, "Injecting interfaces...");

        injectionParser.getClassInjectors().forEach(classInjector ->
        {
            final JSONObject jsonClass = (JSONObject) classInjector;
            final ClassNode node = classes.get(jsonClass.get("name"));

            if (node != null)
            {
                node.interfaces.add(String.valueOf(jsonClass.get("interface")));

                JSONArray getters = (JSONArray) jsonClass.get("getters");
                if (getters != null)
                {
                    getters.forEach(getter ->
                    {
                        JSONObject jsonField = (JSONObject) getter;
                        if (jsonField.get("container") == null)
                        {
                            injectGetter(node, String.valueOf(jsonField.get("name")), String.valueOf(jsonField.get("getter")), String.valueOf(jsonField.get("signature")), Boolean.valueOf(String.valueOf(jsonField.get("static"))));
                        }
                        else
                        {
                            injectGetter(node, classes.get(jsonField.get("container")), String.valueOf(jsonField.get("name")), String.valueOf(jsonField.get("getter")), String.valueOf(jsonField.get("signature")), Boolean.valueOf(String.valueOf(jsonField.get("static"))));
                        }
                    });
                }

                JSONArray setters = (JSONArray) jsonClass.get("setters");
                if (setters != null)
                {
                    setters.forEach(setter ->
                    {
                        JSONObject jsonField = (JSONObject) setter;
                        injectGetter(node, String.valueOf(jsonField.get("name")), String.valueOf(jsonField.get("getter")), String.valueOf(jsonField.get("signature")), Boolean.valueOf(String.valueOf(jsonField.get("static"))));
                    });
                }
            }
        });
    }
}
