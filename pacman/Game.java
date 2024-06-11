/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pacman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author rouaa
 */
public class Game implements KeyListener {

    Board board = new Board();
    
    Timer timer;
    char direction = 'L';
    static boolean flag = true;
    
    private Clip moveSound;
    private Clip winSound;
    private Clip loseSound;

    public Game() {
        JFrame frame = new JFrame();
        frame.setSize(420, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(board, BorderLayout.CENTER);
        frame.setTitle("Pac-Man");
        frame.setVisible(true);
        frame.addKeyListener(this);
          loadSounds();
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!board.title && board.lives > 0) {
                    if (flag) {
                        try {
                            Thread.sleep(2000);
                            flag = false;
                        } catch (InterruptedException ex) {
                            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    }
                    board.ghost1.move();
                    if (board.ghost1.getShape().intersects(board.pacman.getShape())) {
                        board.reset();
                        playLoseSound(); 
                    }
                    board.ghost2.move();
                    if (board.ghost2.getShape().intersects(board.pacman.getShape())) {
                        board.reset();

                    }
                    board.ghost3.move();
                    if (board.ghost3.getShape().intersects(board.pacman.getShape())) {
                        board.reset();

                    }
                    board.ghost4.move();
                    if (board.ghost4.getShape().intersects(board.pacman.getShape())) {
                        board.reset();

                    }

                    board.ghost1.updateState(board.states);
                    board.ghost2.updateState(board.states);
                    board.ghost3.updateState(board.states);
                    board.ghost4.updateState(board.states);

                    board.pacman.move(direction);
                    if(board.balls[board.pacman.x / 20][board.pacman.y / 20]){
                    board.balls[board.pacman.x / 20][board.pacman.y / 20] = false;
                    board.score++;
                    playWinSound(); 
                    }
                    board.pacman.updateState(board.states);

                }
            }
        });

        timer.start();

    }
    
    private void loadSounds() {
        try {
            
            AudioInputStream moveStream = AudioSystem.getAudioInputStream(new File("src\\Sounds\\MoveSound.wav"));
            moveSound = AudioSystem.getClip();
            moveSound.open(moveStream);

            AudioInputStream winStream = AudioSystem.getAudioInputStream(new File("src\\Sounds\\OpeningSound.wav"));
            winSound = AudioSystem.getClip();
            winSound.open(winStream);

            AudioInputStream loseStream = AudioSystem.getAudioInputStream(new File("src\\Sounds\\DieingSound.wav"));
            loseSound = AudioSystem.getClip();
            loseSound.open(loseStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playMoveSound() {
        if (moveSound != null) {
            moveSound.stop();
            moveSound.setFramePosition(0);
            moveSound.start();
        }
    }

    private void playWinSound() {
        if (winSound != null) {
            winSound.stop();
            winSound.setFramePosition(0);
            winSound.start();
        }
    }

    private void playLoseSound() {
        if (loseSound != null) {
            loseSound.stop();
            loseSound.setFramePosition(0);
            loseSound.start();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Game();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 'L';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 'R';
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 'U';
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 'D';
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            board.title = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
