package ru.nsu.vakhrushev.LogoWorld.main;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 02.03.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**Factory class. Used to creating command objects.
 * @author Vakhrushev Maxim*/

public class Factory {

    private final static Logger logger = Logger.getLogger(Factory.class);

    /** Collect created objects.
     * Key: command name
     * Value: object of class Object*/
    private Map <String, Object> classes = new HashMap<>();
    private Properties prop = new Properties ();

    /**Constructor. Read property from file ru/nsu/vakhrushev/LogoWorld/main/config.txt
     * @throws java.io.IOException */
    public Factory () throws IOException
    {
        logger.info("Reading property from ru/nsu/vakhrushev/LogoWorld/main/config.txt.");
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("ru/nsu/vakhrushev/LogoWorld/main/config.txt"));
    }
    /**Constructor Read property from file
     * @param fileName File with property
     * @throws IOException*/
    public Factory (String fileName) throws IOException
    {
//        PropertyConfigurator.configure("logconfig.txt");
        logger.info("Reading property from " + fileName + ".");
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName));
//        prop.load(getClass().getResourceAsStream(fileName));
    }

    /**Create object and save it in HashMap
     * @param commandName Command name
     * @param myGame The Game model, for which we execute command
     * @return Created object
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException*/
    public Object create (String commandName, Game myGame) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String className = prop.getProperty(commandName, "Unknown command");
        Object command;
        if (className.equals("Unknown command"))
        {
            System.out.println("Unknown command");
            return null;
        }
        if (!classes.containsKey(className))
        {
            Class c = Class.forName(className);
            command = c.newInstance();
            logger.info("Create object of class " + className);
            classes.put(className, command);
        }
        else
        {
            command = classes.get(className);
        }
        return command;
    }
}
