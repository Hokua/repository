
// Import Java swing libraries:
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game class that creates the highest level functionality. Creates an Arena
 * object for all the main game functionality.
 */
public class Game implements Runnable {

    // Booleans to denote the game state:
    boolean mainScreen = true; // Starts as true to begin on the main screen
    boolean instructions = false;
    boolean inPlay = false;

    /*
     * Run method: The implemented method inherited from Runnable. Runs at start.
     * Does all the real work with the frame and panels.
     */
    public void run() {

        // Create the frame of the game (some nice rhyming):
        final JFrame frame = new JFrame("Snake");
        frame.setLocation(300, 300);

        // Functionality for mainScreen mode:
        if (mainScreen) {

            // Main screen:
            final JPanel welcomeScreen = new JPanel();
            frame.add(welcomeScreen, BorderLayout.CENTER);
            final JLabel welcome = new JLabel("Welcome!", JLabel.CENTER);
            welcome.setPreferredSize(new Dimension(200, 200));
            welcome.setFont(new Font("Verdana", Font.PLAIN, 15));
            welcomeScreen.add(welcome);

            // Select panel:
            final JPanel selectPanel = new JPanel();
            frame.add(selectPanel, BorderLayout.SOUTH);

            // Play Button:
            final JButton play = new JButton("Play");
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainScreen = false;
                    inPlay = true;
                    frame.dispose();
                    run();
                }
            });
            selectPanel.add(play);

            // Instructions Button:
            final JButton instructs = new JButton("Instructions");
            instructs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainScreen = false;
                    instructions = true;
                    frame.dispose();
                    run();
                }
            });
            selectPanel.add(instructs);

        }

        // Functionality for instructions mode:
        if (instructions) {

            // Title:
            final JPanel titlePanel = new JPanel();
            frame.add(titlePanel, BorderLayout.NORTH);
            final JLabel title = new JLabel("Instructions:", JLabel.CENTER);
            titlePanel.add(title);

            // Main text panel:
            final JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
            frame.add(textPanel, BorderLayout.CENTER);
            
            // All the text fit into JLabels
            final JLabel mainText = new JLabel("  ");
            final JLabel mainText0 = new JLabel("  This is the classic game of Snake… with a");
            final JLabel mainText1 = new JLabel("  few changes. You are playing as a snake (a");
            final JLabel mainText2 = new JLabel("  collection of moving rectangles).");
            final JLabel mainText3 = new JLabel("  ");
            final JLabel mainText4 = new JLabel("  You want to eat these peaches, their source");
            final JLabel mainText5 = new JLabel("  will get scarcer and they will start to");
            final JLabel mainText6 = new JLabel("  shrink!");
            final JLabel mainText7 = new JLabel("  ");
            final JLabel mainText8 = new JLabel("  You will also have the challenge of avoiding");
            final JLabel mainText9 = new JLabel("  enemies. These enemies include:");
            final JLabel mainText10 = new JLabel("  ");
            final JLabel mainText11 = new JLabel("  - The Rock (a large black rectangle that does");
            final JLabel mainText12 = new JLabel("    not move and when touched will diappear,");
            final JLabel mainText13 = new JLabel("    taking two chunks of your tail with it).");
            final JLabel mainText14 = new JLabel("  ");
            final JLabel mainText15 = new JLabel("  - The Buffoon (a medium sized yellow");
            final JLabel mainText16 = new JLabel("    rectangle moves around randomly -- don't");
            final JLabel mainText17 = new JLabel("    let it touch you or you will lose");
            final JLabel mainText18 = new JLabel("  ");
            final JLabel mainText19 = new JLabel("  - The Hunter (a small green rectangle that");
            final JLabel mainText20 = new JLabel("    will chase after you. Don't let it catch");
            final JLabel mainText21 = new JLabel("    you, you'll lose).");
            final JLabel mainText22 = new JLabel("  ");
            final JLabel mainText23 = new JLabel("  Since this game of Snake is a much higher");
            final JLabel mainText24 = new JLabel("  paced game than usual and has the addition of");
            final JLabel mainText25 = new JLabel("  enemies, you are allowed to run over your");
            final JLabel mainText26 = new JLabel("  tail! Think of it as merely slithering over");
            final JLabel mainText27 = new JLabel("  yourself.");
            final JLabel mainText28 = new JLabel("  ");

            //mainText0.setPreferredSize(new Dimension(500, 500));
            
            // Add all the text to the panel
            textPanel.add(mainText);
            textPanel.add(mainText0);
            textPanel.add(mainText1);
            textPanel.add(mainText2);
            textPanel.add(mainText3);
            textPanel.add(mainText4);
            textPanel.add(mainText5);
            textPanel.add(mainText6);
            textPanel.add(mainText7);
            textPanel.add(mainText8);
            textPanel.add(mainText9);
            textPanel.add(mainText10);
            textPanel.add(mainText11);
            textPanel.add(mainText12);
            textPanel.add(mainText13);
            textPanel.add(mainText14);
            textPanel.add(mainText15);
            textPanel.add(mainText16);
            textPanel.add(mainText17);
            textPanel.add(mainText18);
            textPanel.add(mainText19);
            textPanel.add(mainText20);
            textPanel.add(mainText21);
            textPanel.add(mainText22);
            textPanel.add(mainText23);
            textPanel.add(mainText24);
            textPanel.add(mainText25);
            textPanel.add(mainText26);
            textPanel.add(mainText27);
            textPanel.add(mainText28);
            
            

            // Button panel:
            final JPanel buttonPanel = new JPanel();
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Back button:
            final JButton back = new JButton("Home Screen");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainScreen = true;
                    instructions = false;
                    frame.dispose();
                    run();
                    // TODO:
                }
            });
            buttonPanel.add(back);

        }

        // Functionality for inPlay mode:
        if (inPlay) {

            // Score panel:
            final JPanel buttonPanel = new JPanel();
            frame.add(buttonPanel, BorderLayout.SOUTH);
            final JLabel score = new JLabel();

            // Main game arena:
            final Arena arena = new Arena(score);
            frame.add(arena, BorderLayout.CENTER);

            // Home Button:
            final JButton reset = new JButton("Home Screen");
            reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    arena.reset(); // or set to null?
                    mainScreen = true;
                    inPlay = false;
                    frame.dispose();
                    run();
                }
            });

            // Instructions Button:
            final JButton instructs = new JButton("Instructions");
            instructs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    instructions = true;
                    inPlay = false;
                    frame.dispose();
                    run();
                }
            });

            // Add elements to panel in desired order
            buttonPanel.add(reset);
            buttonPanel.add(instructs);
            buttonPanel.add(score);

            // Start the game:
            arena.reset();
        }

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * The main method. Calls the run() function that implements Runnable.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}