package Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by AlexVR on 7/1/2018.
 */

public class DisplayScreen {

	//member data
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;
    private JLabel score;
    private int ScoreHeight=30;
    private JLabel gameover;
    
    //Constructor
    public DisplayScreen(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;



        createDisplay();
    }

    //member functions
    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height+ScoreHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(new Color(255,0,255));
   

        try {
            frame.setIconImage(ImageIO.read(new File("res/Sheets/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        canvas.setBackground(new Color(255,0,255));
        canvas.setBounds(0,ScoreHeight,width,(height+ScoreHeight));

        score=new JLabel("0.00000");
        score.setBounds(0, 0, 100, 30);
    	score.setOpaque(false);

        //score.setSize(10, 10);
        //score.setFont(new Font());
        gameover = new JLabel("Game Over");
    	gameover.setBounds(0, 0, width, height);
    	gameover.setFont(new Font("Serif", Font.ITALIC, 24));
    	gameover.setOpaque(false);
    	gameover.setVisible(false);
    	frame.add(gameover);
        frame.add(score);
        frame.add(canvas);
        frame.pack();
    }
    public void gameOver() {
    	gameover.setVisible(true);
    	System.out.println("GameOver");
    }
    public Canvas getCanvas(){
        return canvas;
    }
    public JLabel getScore(){
        return score;
    }
    public JFrame getFrame(){
        return frame;
    }

}
