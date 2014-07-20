/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.util.Hashtable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
/**
* @author tr266@cornell.edu
*/
 
public class MediaPlayer extends javax.swing.JPanel {
    public static Player mediaPlayer;
    public static JFrame mediaTest;
    static final int MIN = -7;
    static final int MAX = 7;
    static final int INIT = 0;    //initial frames per second
    public static String subjectID;
    
    static ArrayList<Double> times = new ArrayList<Double>();
    static ArrayList<Integer> ratings = new ArrayList<Integer>();
    
    
    public MediaPlayer(URL mediauUrl) {

        setLayout(new BorderLayout());//new BoxLayout(this, BoxLayout.PAGE_AXIS)

        try {

            mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(mediauUrl));

            Component video=mediaPlayer.getVisualComponent();

            Component control=mediaPlayer.getControlPanelComponent();

            if (video!=null) {
                add(video, BorderLayout.CENTER);          // place the video component in the panel
            }

            add(control, BorderLayout.SOUTH);            // place the control in  panel
            
            //mediaPlayer.start();
        } catch (Exception e) {

        }

    }
 
    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.showOpenDialog(null);

        URL mediaUrl=null;

        try {

            mediaUrl = fileChooser.getSelectedFile().toURI().toURL();

        } catch (MalformedURLException ex) {

            System.out.println(ex);

        }

        mediaTest = new JFrame( "EmoRate" );
        mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        //menubar
        JMenuBar menubar = new JMenuBar();
        mediaTest.setJMenuBar(menubar);
        JMenu file = new JMenu("File");
        menubar.add(file);
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        event6 e6 = new event6();
        save.addActionListener(e6);
        
        event2 e2 = new event2();
        exit.addActionListener(e2);
        
        JMenu help = new JMenu("Help");
        menubar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        
        event3 e3 = new event3();
        about.addActionListener(e3);

        MediaPlayer mediaPanel = new MediaPlayer( mediaUrl );
        //mediaTest.add( mediaPanel );
        mediaTest.setSize( 800, 800 ); // set the size of the player
        mediaTest.setLocationRelativeTo(null);
        mediaTest.setVisible( true );
        
        JPanel ratingInstruments = new JPanel();
        ratingInstruments.setLayout(new BoxLayout(ratingInstruments, BoxLayout.PAGE_AXIS));
        
        JLabel question = new JLabel("Please continually rate how your partner made you feel.");
        question.setForeground(Color.red);
        question.setFont(new Font("Serif", Font.PLAIN, 24));
        //Create the slider
        final JSlider emoRating = new JSlider(JSlider.HORIZONTAL,MIN, MAX, INIT);
        emoRating.setMajorTickSpacing(1);
        //emoRating.setMinorTickSpacing(1);
        emoRating.setPaintTicks(true);
        
        //Create the label table
        Hashtable labelTable = new Hashtable();
        labelTable.put( MIN, new JLabel("Negative") );
        labelTable.put( MAX, new JLabel("Positive") );
        labelTable.put( new Integer(0), new JLabel("Neutral") );
        emoRating.setLabelTable( labelTable );
        emoRating.setPaintLabels(true);
        // ActionListener for slider
        emoRating.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent ce) {
            times.add(mediaPlayer.getMediaTime().getSeconds()); 
            ratings.add(((JSlider) ce.getSource()).getValue());
            System.out.println(((JSlider) ce.getSource()).getValue());
            System.out.println(mediaPlayer.getMediaTime().getSeconds());
            }
        });
        
        //add the video and the slider to the ratingInstruments panel
        ratingInstruments.add(mediaPanel);
        ratingInstruments.add(question);
        ratingInstruments.add(emoRating);
        
        mediaTest.add(ratingInstruments);
        emoRating.requestFocus();
        InputMap im = emoRating.getInputMap();
        System.out.println(im.keys());
        im.put(KeyStroke.getKeyStroke("shift 1"),"mOne");
        im.put(KeyStroke.getKeyStroke("shift 2"),"mTwo");
        im.put(KeyStroke.getKeyStroke("shift 3"),"mThree");
        im.put(KeyStroke.getKeyStroke("shift 4"),"mFour");
        im.put(KeyStroke.getKeyStroke("shift 5"),"mFive");
        im.put(KeyStroke.getKeyStroke("shift 6"),"mSix");
        im.put(KeyStroke.getKeyStroke("shift 7"),"mSeven");
        im.put(KeyStroke.getKeyStroke("0"),"Zero");
        im.put(KeyStroke.getKeyStroke("1"),"One");
        im.put(KeyStroke.getKeyStroke("2"),"Two");
        im.put(KeyStroke.getKeyStroke("3"),"Three");
        im.put(KeyStroke.getKeyStroke("4"),"Four");
        im.put(KeyStroke.getKeyStroke("5"),"Five");
        im.put(KeyStroke.getKeyStroke("6"),"Six");
        im.put(KeyStroke.getKeyStroke("7"),"Seven");
        
        class MyAction extends AbstractAction {
            int value=0;
            public MyAction(int v) {
              super("my action");
              value = v;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
              emoRating.setValue(value);
            }
        }
        MyAction mOne = new MyAction(-1);
        MyAction mTwo = new MyAction(-2);
        MyAction mThree = new MyAction(-3);
        MyAction mFour = new MyAction(-4);
        MyAction mFive = new MyAction(-5);
        MyAction mSix = new MyAction(-6);
        MyAction mSeven = new MyAction(-7);
        MyAction Zero = new MyAction(0);
        MyAction One = new MyAction(1);
        MyAction Two = new MyAction(2);
        MyAction Three = new MyAction(3);
        MyAction Four = new MyAction(4);
        MyAction Five = new MyAction(5);
        MyAction Six = new MyAction(6);
        MyAction Seven = new MyAction(7);
        
        emoRating.getActionMap().put("mOne",mOne);
        emoRating.getActionMap().put("mTwo",mTwo);
        emoRating.getActionMap().put("mThree",mThree);
        emoRating.getActionMap().put("mFour",mFour);
        emoRating.getActionMap().put("mFive",mFive);
        emoRating.getActionMap().put("mSix",mSix);
        emoRating.getActionMap().put("mSeven",mSeven);
        emoRating.getActionMap().put("Zero",Zero);
        emoRating.getActionMap().put("One",One);
        emoRating.getActionMap().put("Two",Two);
        emoRating.getActionMap().put("Three",Three);
        emoRating.getActionMap().put("Four",Four);
        emoRating.getActionMap().put("Five",Five);
        emoRating.getActionMap().put("Six",Six);
        emoRating.getActionMap().put("Seven",Seven);
        
        
    }
    
    static public class event6 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            subjectID = JOptionPane.showInputDialog("Enter Subject ID:");
            try {
                FileWriter outFile = new FileWriter(subjectID + ".txt");
                PrintWriter out = new PrintWriter(outFile);
                out.println("time(seconds),ratings(-100.100)");
                // Write text to file                
                for(int i=0;i<times.size();i++){  
                  out.println(times.get(i)+","+ratings.get(i));
                }
                out.close();
            }catch(IOException ex){
                System.exit(0);
            }
        }
    }
    
    static public class event2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    static public class event3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            SettingsWindow gui = new SettingsWindow (mediaTest);
            gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            gui.setSize(500,50);
            gui.setLocation(300,300);
            gui.setVisible(true);
            
        }
    }
    
}