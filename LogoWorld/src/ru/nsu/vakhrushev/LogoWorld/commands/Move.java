package ru.nsu.vakhrushev.LogoWorld.commands;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.nsu.vakhrushev.LogoWorld.main.Direction;
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

    private final static Logger logger = Logger.getLogger(Move.class);

    /**Execute command MOVE.
     * @param arguments Arguments string.
     * @param myGame The Game model, for which we execute command.*/
    public void execute(String arguments, Game myGame)
    {
 //       PropertyConfigurator.configure("logconfig.txt");
        logger.info("Execute command MOVE.");

        StringBuilder sb = new StringBuilder();
        String strDirection = null;
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
                strDirection = sb.toString();
                if (!(strDirection.equals("L") || strDirection.equals("R") || strDirection.equals("U") || strDirection.equals("D")))
                {
                    System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
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
            for (int i = tmpPosition; i < args.length; i++)
            {
                if (! (Character.isWhitespace(args[i]) || args[i] == '\n'))
                {
                    System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                    return;
                }
            }
        }

        Direction direction;
        switch (strDirection)
        {
            case "L":
                direction = Direction.L;
                break;
            case "R":
                direction = Direction.R;
                break;
            case "U":
                direction = Direction.U;
                break;
            case "D":
                direction = Direction.D;
                break;
            default:
                System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
                return;
                
        }
        
        myGame.move(steps, direction);
    }
}

