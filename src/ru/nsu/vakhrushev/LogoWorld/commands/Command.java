package ru.nsu.vakhrushev.LogoWorld.commands;

import ru.nsu.vakhrushev.LogoWorld.main.Game;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 04.03.13
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */

/** Command interface */
public interface Command {

    public void execute(String args, Game myGame);

}
