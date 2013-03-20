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
/** Class for MOVE command.
 *  Implements interface Command.
 *  @author Vakhrushev Maxim
 *  @see ru.nsu.vakhrushev.LogoWorld.commands.Command*/
public class Move implements Command  {

    static Logger logger = Logger.getLogger(Move.class.getName());

    /**Execute command MOVE.
     * @param arguments Arguments string.
     * @param myGame The Game model, for which we execute command.*/
    public void execute(String arguments, Game myGame)
    {
        PropertyConfigurator.configure("logconfig.txt");
        logger.info("Execute command MOVE.");

        StringBuilder sb = new StringBuilder();
        String direction = null;
        char args[] = arguments.toCharArray();
        int tmpPosition = args.length;
        int steps = 0;

        for (int i = 0; i < args.length; i++)
        {
            if (!(Character.isAlphabetic(args[i]) || Character.isWhitespace(args[i])))
            {
                System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ') && sb.length() != 0)
            {
                direction = sb.toString();
                if (!(direction.equals("L") || direction.equals("R") || direction.equals("U") || direction.equals("D")))
                {
                    System.out.println("2Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                    return;
                }
                sb.setLength(0);
                tmpPosition = i;
                break;
            }
            if (Character.isAlphabetic(args[i]))
            {
                sb.append(args[i]);
            }
        }
        sb.setLength(0);
        for (int i = tmpPosition; i < args.length; i++)
        {
            if (! (Character.isDigit(args[i]) || Character.isWhitespace(args[i]) || args[i] == '\n'))
            {
                System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ' || args[i] == '\n') && sb.length() != 0)
            {
                steps = Integer.decode(sb.toString());
                tmpPosition = i;
                break;
            }
            if (Character.isDigit(args[i]))
            {
                sb.append(args[i]);
            }
            tmpPosition = i;
        }
        if (tmpPosition == args.length - 1 && sb.length() != 0)
        {
            steps = Integer.decode(sb.toString());
        }
        else
        {
            System.out.println("ST = " + steps);
            for (int i = tmpPosition; i < args.length; i++)
            {
                if (! (Character.isWhitespace(args[i]) || args[i] == '\n'))
                {
                    System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                    return;
                }
            }
        }
        myGame.move(steps, direction);
    }
}

