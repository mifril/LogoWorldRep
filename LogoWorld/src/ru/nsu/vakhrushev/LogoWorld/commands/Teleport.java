package ru.nsu.vakhrushev.LogoWorld.commands;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.nsu.vakhrushev.LogoWorld.main.Game;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 02.03.13
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
/** Class for TELEPORT command.
 *  Implements interface Command.
 *  @author Vakhrushev Maxim
 *  @see ru.nsu.vakhrushev.LogoWorld.commands.Command*/
public class Teleport implements Command  {

    private final static Logger logger = Logger.getLogger(Teleport.class);

    /**Execute command TELEPORT.
     * @param arguments Arguments string.
     * @param myGame The Game model, for which we execute command.*/
    public void execute(String arguments, Game myGame)
    {
 //       PropertyConfigurator.configure("logconfig.txt");
        logger.info("Execute command TELEPORT.");

        StringBuilder sb = new StringBuilder();
        char args[] = arguments.toCharArray();
        Integer argCount = 0;
        int tmpX = 0, tmpY = 0;

        for (int i = 0; i < args.length; i++)
        {
            if (! (Character.isDigit(args[i]) || Character.isWhitespace(args[i]) || args[i] == '\n'))
            {
                System.out.println("Incorrect arguments in command TELEPORT. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ' || args[i] == '\n') && sb.length() != 0)
            {
                switch (argCount)
                {
                    case 0:
                        tmpX = Integer.decode(sb.toString());
                        argCount++;
                        break;
                    case 1:
                        tmpY = Integer.decode(sb.toString());
                        argCount++;
                        break;
                    default:
                        System.out.println("Incorrect arguments in command TELEPORT. Incorrect number of arguments");
                        return;
                }
                sb.setLength(0);
            }
            if (Character.isDigit(args[i]))
            {
                sb.append(args[i]);
            }
        }
        if (sb.length() != 0)
        {
            tmpY = Integer.decode(sb.toString());
            argCount++;
        }
        if (argCount == 2 && tmpX <= myGame.getWidth() && tmpY <= myGame.getHeight())
        {
            myGame.setPosition(tmpX, tmpY);
        }
        else
        {
            System.out.println("Incorrect arguments in command TELEPORT. . Incorrect number of arguments or coordinates");
        }
    }
}



