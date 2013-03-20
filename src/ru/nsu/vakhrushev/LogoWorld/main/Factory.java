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

import java.util.HashMap;
import java.util.Map;


/**Factory class. Used to creating command objects.
 * @author Vakhrushev Maxim*/

public class Factory {

    static Logger logger = Logger.getLogger(Factory.class.getName());

    /** Collect created objects.
     * Key: command name
     * Value: object of class Object*/
    private Map <String, Object> classes = new HashMap<>();

    /**Create object and save it in HashMap
     * @param commandName Command name
     * @param myGame The Game model, for which we execute command
     * @return Created object
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException*/
    public Object create (String commandName, Game myGame) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        PropertyConfigurator.configure("logconfig.txt");

        String className = myGame.getFromProperty(commandName);
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
