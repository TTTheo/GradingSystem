package gui;
import javax.swing.JOptionPane;

// All GUI windows should have this functionality
public interface FrameActions {
    public void openNext();             // Go to next frame
    public void openPrevious();         // Return to previous frame
    public void addActions();           // Add button handlers, etc.

    // Opens a popup window with a message
    // Use this method like this: FrameActions.alert("message here");
    public void alert(String message);
}