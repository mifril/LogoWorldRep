package ru.nsu.vakhrushev.LogoWorld.commands;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.nsu.vakhrushev.LogoWorld.main.Game;
import ru.nsu.vakhrushev.LogoWorld.main.Mode;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 02.03.13
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
/** Class for DRAW command.
 *  Implements interface Command.
 *  @author Vakhrushev Maxim
 *  @see ru.nsu.vakhrushev.LogoWorld.commands.Command*/
public class Draw implements Command {

    private final static Logger logger = Logger.getLogger(Draw.class);

    /**Execute command DRAW.
     * @param arguments Arguments string.
     * @param myGame The Game model, for which we execute command.*/
    public void execute(String arguments, Game myGame)
    {
//        PropertyConfigurator.configure("logconfig.txt");
        logger.info("Execute command DRAW.");

        if (null != arguments)
        {
            System.out.println("Error. Arguments in command DRAW!");
            logger.error("Trying to execute null command!");
            return;
        }
        myGame.setDrawMode();
    }
}
