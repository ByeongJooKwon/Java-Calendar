import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
 
public class back extends JFrame {
    JScrollPane scrollPane;
    ImageIcon icon;
 
    public back() {
        icon = new ImageIcon("src/jpj/client/image/가을.jpg");   
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
       
       
       
        JButton button = new JButton("버튼");
        background.add(button);
        scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(610,400);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new back();
    }
}