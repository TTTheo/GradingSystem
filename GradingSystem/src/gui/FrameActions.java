package gui;

// All GUI windows should have this functionality
public interface FrameActions {
    public void openNext();             // Go to next frame
    public void openPrevious();         // Return to previous frame
    public void addActions();           // Add button handlers
    public void alert(String message);  // Display a message
}