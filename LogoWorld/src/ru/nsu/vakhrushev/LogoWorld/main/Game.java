package ru.nsu.vakhrushev.LogoWorld.main;

import org.apache.log4j.Logger;

import java.io.OutputStream;
import java.io.PrintStream;


/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 04.03.13
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */


/**Game model class.
 * @author Vakhrushev Maxim*/
public class Game {

    private final static Logger logger = Logger.getLogger(Game.class);
    /** Width of game field */
    private int width = 0;
    /** Height of game field */
    private int height = 0;
    /** X coordinate of current position */
    private int x = 0;
    /** Y coordinate of current position */
    private int y = 0;
    /** Game mode */
    private Mode mode = Mode.DEFAULT;
    /** Game field. 'X' - current position, '.' - empty cell, '0' - drawed cell */
    private char field[][] = new char[height][width];

    /** Get game width
     * @return Game width */
    public int getWidth ()
    {
        return width;
    }
    /** Get game height
     * @return Game height*/
    public int getHeight ()
    {
        return height;
    }
    /** Get current X coordinate
     * @return Current X coordinate*/
    public int getX ()
    {
        return x;
    }
    /** Get current Y coordinate
     * @return Current Y coordinate*/
    public int getY ()
    {
        return y;
    }

    /** Set game width */
    public void setWidth (int newWidth)
    {
        width = newWidth;
    }
    /** Set game height */
    public void setHeight (int newHeight)
    {
        height = newHeight;
    }
    /** Set DEFAULT game mode */
    public void setDefaultMode ()
    {
        mode = Mode.DEFAULT;
    }
    /** Set DRAW game mode */
    public void setDrawMode ()
    {
        mode = Mode.DRAW;
    }
    /** Set current position
     * @param newX New X coordinate
     * @param newY New Y coordinate*/
    public void setPosition (int newX, int newY)
    {
        if (y < height && x < width)
        {
            field[y][x] = '.';
        }
        x = newX;
        y = newY;
        field[y][x] = 'X';
    }
    /** Set game field */
    public void setField()
    {
        field = new char[height][width];
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[i].length; j++)
            {
                field[i][j] = '.';
            }
        }
//        field[y][x] = 'X';
   }

    /** Print game field
     * @param outStream Output stream*/
    public void print(OutputStream outStream)
    {
        PrintStream stream = new PrintStream(outStream);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                stream.print(field[i][j]);
            }
            stream.print("\n");
        }
        stream.print("\n");
    }
    /** Move from current position
     * @param steps Number of steps
     * @param direction Direction of moving (L/R/D/U) */
    public void move (int steps, Direction direction)
    {
        if (mode == Mode.DRAW)
        {
            int tmp_steps = steps;
            switch (direction)
            {
                case L:
                    field[y][x] = '0';
                    while (0 != tmp_steps)
                    {
                        int tmpX = x - 1;
                        if (tmpX == -1)
                        {
                            tmpX = field[y].length - 1;
                            x = tmpX + 1;
                        }
                        field[y][tmpX] = '0';
                        tmp_steps--;
                        x = tmpX;
                    }
                    break;

                case R:
                    field[y][x] = '0';
                    while (0 != tmp_steps)
                    {
                        int tmpX = (x + 1) % width;
                        field[y][tmpX] = '0';
                        tmp_steps--;
                        x = tmpX;
                    }
                    break;

                case U:
                    field[y][x] = '0';
                    while (0 != tmp_steps)
                    {
                        int tmpY = y - 1;
                        if (tmpY == -1)
                        {
                            tmpY = field.length - 1;
                            y = tmpY + 1;
                        }
                        field[tmpY][x] = '0';
                        tmp_steps--;
                        y = tmpY;
                    }
                    break;

                case D:
                    field[y][x] = '0';
                    while (0 != tmp_steps)
                    {
                        int tmpY = (y + 1) % height;
                        field[tmpY][x] = '0';
                        tmp_steps--;
                        y = tmpY;
                    }
                    break;

                default:
                    System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
                    break;
            }
        }
        else
        {
            switch (direction)
            {
                case L:
                    field[y][x] = '.';

                    int tmpX = x - steps;
                    while (tmpX < 0)
                    {
                        tmpX += field[y].length;
                    }
                    x = tmpX;
                    field[y][x] = 'X';
                    break;
                case R:
                    field[y][x] = '.';
                    x = (x + steps) % width;
                    field[y][x] = 'X';
                    break;
                case U:
                    field[y][x] = '.';

                    int tmpY = y - steps;
                    while (tmpY < 0)
                    {
                        tmpY += field.length;
                    }
                    y = tmpY;
                    field[y][x] = 'X';
                    break;
                case D:
                    field[y][x] = '.';
                    y = (y + steps) % height;
                    field[y][x] = 'X';
                    break;
                default:
                    System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
                    break;
            }
        }
    }



}
