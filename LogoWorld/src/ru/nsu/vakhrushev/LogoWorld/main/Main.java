package ru.nsu.vakhrushev.LogoWorld.main;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.nsu.vakhrushev.LogoWorld.commands.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 02.03.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */

/** Main class. Control game */
public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main (String args[])
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            PropertyConfigurator.configure("logconfig.txt");
            logger.info("\n------------------------------------------\n");
            logger.info("Entering application.");

            if (args.length != 1)
            {
                System.err.println("Arguments list : <configuration file name>");
                logger.error("Incorrect arguments.");
                return;
            }

            Game myGame = new Game(args[0]);
            Factory factory = new Factory();
            Command command;
            String exitCommand = "EXIT";
            int step = 1;


            System.out.println("Commands:\n" +
                    "1.INIT <width> <height> <x> <y>\n" +
                    "2.MOVE [L|R|U|D] <steps>\n" +
                    "3.TELEPORT <x> <y>\n" +
                    "4.DRAW\n" +
                    "5.WARD\n\n" +
                    "Firstly, you should enter: INIT <width> <height> <x> <y>");

            while (true)
            {
                System.out.println("Enter your command:");
                String str = reader.readLine();
                if (str.equals(exitCommand))
                {
                    logger.info("Execute command EXIT.");
                    break;
                }
                if (str.length() < 4)
                {
                    System.out.println("Incorrect command: " + str);
                    continue;
                }
                String commandName;
                commandName = str.substring(0, 4);
                int lastIndex = 4;
                if (!commandName.equals("INIT") && !commandName.equals("MOVE") && !commandName.equals("DRAW")
                        && !commandName.equals("WARD") && str.length() > 8)
                {
                    commandName = str.substring(0, 8);
                    lastIndex = 8;
                    if (!commandName.equals("TELEPORT"))
                    {
                        System.out.println("Incorrect command: " + str);
                        continue;
                    }
                }

                if (step == 1 && !commandName.equals("INIT"))
                {
                    System.out.println("Firstly, you should enter: INIT <width> <height> <x> <y>");
                    continue;
                }

                command = (Command)factory.create(commandName, myGame);
                if (null != command)
                {
                    String arguments = null;
                    if (lastIndex + 1 < str.length())
                    {
                        arguments = str.substring(lastIndex + 1, str.length());
                    }

                    System.out.println("Args : " + arguments);
                    command.execute(arguments, myGame);
                    if (step == 1 && myGame.getHeight() <= 0 || myGame.getWidth() <= 0)
                    {
                        continue;
                    }
                    myGame.print(System.out);
                    step++;
                }
                else
                {
                    System.out.println("Incorrect command: " + str);
                }
            }
            logger.info("Exiting application.");
            logger.info("\n------------------------------------------\n");
        }
        catch (Exception e)
        {
            logger.error("Catch exception:" + e.getClass());
            logger.error(e.getStackTrace());
            logger.info("Exiting application.");
            logger.info("\n------------------------------------------\n");
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
    }
}