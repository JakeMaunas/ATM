import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Animation extends JPanel implements ActionListener {
    final int Width = 600;
    final int height = 700;
    Image card;
    Timer timer;
    int xvelocity = 5;
    int x = 110;
    int y = -48;

    Animation(){
        setBounds((int)(28*10),(int)(35*10),(23*10),(5*10));
        setBackground(Color.BLACK);
        setOpaque(false);
        card = new ImageIcon("Pic/Credit_Card.png").getImage();
        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(card, x, y,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= 13){
            x = x - xvelocity;
        }
        repaint();
    }
}
