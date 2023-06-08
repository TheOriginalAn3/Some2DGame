package main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My2DGame");

        GamePannel gamePannel = new GamePannel();
        window.add(gamePannel);

        window.pack(); // Causes this Window to be resized in order to fit the child components and layouts



        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePannel.startGameThread();
    }
}
