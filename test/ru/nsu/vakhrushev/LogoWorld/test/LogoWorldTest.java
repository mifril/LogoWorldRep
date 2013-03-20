package ru.nsu.vakhrushev.LogoWorld.test;

import org.junit.Test;
import org.testng.Assert;
import ru.nsu.vakhrushev.LogoWorld.commands.*;
import ru.nsu.vakhrushev.LogoWorld.main.Factory;
import ru.nsu.vakhrushev.LogoWorld.main.Game;


/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 20.03.13
 * Time: 20:22
 */
public class LogoWorldTest {
    @Test
    public void testGame() throws Exception
    {
        Game myGame = new Game ();

        myGame.setWidth(5);
        Assert.assertEquals(myGame.getWidth(), 5);

        myGame.setHeight(5);
        Assert.assertEquals(myGame.getHeight(), 5);

        myGame.setField();
        myGame.setPosition(2, 3);
        Assert.assertEquals(myGame.getX(), 2);
        Assert.assertEquals(myGame.getY(), 3);

        myGame.move(2, "U");
        myGame.move(1, "R");
        Assert.assertEquals(myGame.getX(), 3);
        Assert.assertEquals(myGame.getY(), 1);

        Init initCommand = new Init();
        initCommand.execute("3 6 0 1", myGame);
        Assert.assertEquals(myGame.getWidth(), 3);
        Assert.assertEquals(myGame.getHeight(), 6);
        Assert.assertEquals(myGame.getX(), 0);
        Assert.assertEquals(myGame.getY(), 1);

        Move moveCommand = new Move();
        moveCommand.execute("U 2", myGame);
        moveCommand.execute("L 1", myGame);
        Assert.assertEquals(myGame.getWidth(), 3);
        Assert.assertEquals(myGame.getHeight(), 6);
        Assert.assertEquals(myGame.getX(), 2);
        Assert.assertEquals(myGame.getY(), 5);

        Teleport tpCommand = new Teleport();
        tpCommand.execute("1 4", myGame);
        Assert.assertEquals(myGame.getWidth(), 3);
        Assert.assertEquals(myGame.getHeight(), 6);
        Assert.assertEquals(myGame.getX(), 1);
        Assert.assertEquals(myGame.getY(), 4);
    }

    @Test
    public void testFactory () throws Exception
    {
        Game myGame = new Game ();
        Factory factory = new Factory();
        Command [] command = new Command[5];

        command[0] = (Command)factory.create("INIT", myGame);
        Assert.assertEquals(Init.class.getName(), "ru.nsu.vakhrushev.LogoWorld.commands.Init");
        command[1] = (Command)factory.create("DRAW", myGame);
        Assert.assertEquals(Draw.class.getName(), "ru.nsu.vakhrushev.LogoWorld.commands.Draw");
        command[2] = (Command)factory.create("WARD", myGame);
        Assert.assertEquals(Ward.class.getName(), "ru.nsu.vakhrushev.LogoWorld.commands.Ward");
        command[3] = (Command)factory.create("MOVE", myGame);
        Assert.assertEquals(Move.class.getName(), "ru.nsu.vakhrushev.LogoWorld.commands.Move");
        command[4] = (Command)factory.create("TELEPORT", myGame);
        Assert.assertEquals(Teleport.class.getName(), "ru.nsu.vakhrushev.LogoWorld.commands.Teleport");
    }

    @Test (expected = NullPointerException.class)
    public void testFactoryException1 () throws Exception
    {
        Game myGame = new Game ("/test/ru/nsu/vakhrushev/LogoWorld/main/badconfig.txt");
    }

    @Test (expected = ClassNotFoundException.class)
    public void testFactoryException2 () throws Exception
    {
        Game myGame = new Game ("ru/nsu/vakhrushev/LogoWorld/main/badconfig.txt");
        Factory factory = new Factory();
        Command command = (Command)factory.create("INIT", myGame);
    }

    @Test (expected = InstantiationException.class)
    public void testFactoryException3 () throws Exception
    {
        Game myGame = new Game ("ru/nsu/vakhrushev/LogoWorld/main/badconfig.txt");
        Factory factory = new Factory();
        Command command = (Command)factory.create("MOVE", myGame);
    }
}
