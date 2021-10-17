package edu.brown.cs.student.main.ReplCommands.CommandRunnables;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface ReplRunnable {
    public void runCommand(String[] args) throws IOException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
