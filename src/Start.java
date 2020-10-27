import model.AddTaskModel;
import view.AppFrame;

import javax.swing.*;

/**
 * This is where the program starts
 * @author ganiyuisola
 * @version 19/10/2020
 */

public class Start {
    /**
     * @param args parameters passed to the program when the program is started.
     */

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new AppFrame(new AddTaskModel()));
    }
}
