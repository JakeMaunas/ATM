import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Card_Information extends JPanel implements MouseListener, ActionListener {
    Card_Crud card = new Card_Crud();
    Card_Account account;
    Font Pixelfont;
    JLabel Name, account_name, BP, PIN;
    JButton add_name;
    Card_Information(int bp) {
        account = card.retrievecard(bp);
        try {
            Pixelfont = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")).deriveFont(17f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")));
        } catch (Exception e) {}
        //account
        //panel
        setLayout(null);
        setSize((int)(37.4*10),(30*10));
        setLocation((int)(10.5*10),(5*10));
        setBackground(Color.BLUE);
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        setBackground(Color.decode("#C1CFA1"));
        //back label
        JLabel Label = new JLabel("  Back");
        Label.setFont(Pixelfont.deriveFont(17));
        Label.setBounds((int)(0*10),(24*10),18*10,4*10);
        Label.setForeground(java.awt.Color.decode("#2F3645"));
        Label.setBackground(java.awt.Color.decode("#9CA986"));
        Label.setBorder(BorderFactory.createLineBorder(Color.black,1));
        Label.setOpaque(true);

        //name

        Name = new JLabel("  Name");
        Name.setFont(Pixelfont.deriveFont(Font.PLAIN, 18));
        Name.setBounds((int)(1*10),(int)(2*10),35*10,5*10);
        Name.setForeground(java.awt.Color.decode("#2F3645"));
        Name.setBackground(java.awt.Color.decode("#A0D683"));
        Name.setBorder(BorderFactory.createLineBorder(Color.black,1));
        Name.setOpaque(true);

        if(account.get_name().equals("NULL")){
            add_name = new JButton("ADD");
            add_name.setFont(Pixelfont.deriveFont(20));
            add_name.setBounds(140,28,100,30);
            add_name.setBackground(Color.decode("#757575"));
            add_name.setForeground(Color.decode("#D8D8D8"));
            add_name.setBorder(BorderFactory.createLineBorder(Color.black,1));
            add_name.addActionListener(this);
            add_name.setFocusable(false);
            add_name.setFocusPainted(false);
        }
        else{
            account_name = NAME();
        }

        //BP number
        JLabel BP_num = new JLabel("  BP");
        BP_num.setFont(Pixelfont.deriveFont(Font.PLAIN, 18));
        BP_num.setBounds((int)(1*10),(int)(9*10),35*10,5*10);
        BP_num.setForeground(java.awt.Color.decode("#2F3645"));
        BP_num.setBackground(java.awt.Color.decode("#A0D683"));
        BP_num.setBorder(BorderFactory.createLineBorder(Color.black,1));
        BP_num.setOpaque(true);

        BP = new JLabel(account.get_bp()+"");
        BP.setFont(Pixelfont.deriveFont(Font.PLAIN, 25));
        BP.setBounds(155,90,200,50);
        BP.setForeground(java.awt.Color.decode("#2F3645"));

        //PIN Number

        JLabel Pin_num = new JLabel("  PIN");
        Pin_num.setFont(Pixelfont.deriveFont(Font.PLAIN, 18));
        Pin_num.setBounds((int)(1*10),(int)(16*10),35*10,5*10);
        Pin_num.setForeground(java.awt.Color.decode("#2F3645"));
        Pin_num.setBackground(java.awt.Color.decode("#A0D683"));
        Pin_num.setBorder(BorderFactory.createLineBorder(Color.black,1));
        Pin_num.setOpaque(true);

        PIN = new JLabel("****");
        PIN.setFont(Pixelfont.deriveFont(Font.PLAIN, 25));
        PIN.setBounds(165,160,200,50);
        PIN.setForeground(java.awt.Color.decode("#2F3645"));
        PIN.addMouseListener(this);

        if (!account.get_name().equals("NULL")){
            add(account_name);
        }
        else{
            add(add_name);
        }
        add(BP);
        add(PIN);

        add(Pin_num);
        add(BP_num);
        add(Label);
        add(Name);
        setVisible(true);
    }

    public JLabel NAME(){
        JLabel label = new JLabel(account.get_name());
        label.setFont(Pixelfont.deriveFont(Font.PLAIN, 25));
        if (account.get_name().length() >= 1 && account.get_name().length() <= 6){
            label.setBounds(155,20,200,50);
        }
        else if (account.get_name().length() >= 7 && account.get_name().length() <= 9){
            label.setBounds(130,20,200,50);
        }
        else{
            label.setBounds(115,20,200,50);
        }
        label.setForeground(java.awt.Color.decode("#2F3645"));
        return label;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == PIN){
            PIN.setText(account.get_pin()+"");
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == PIN){
            PIN.setText("****");
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add_name){
            String name = JOptionPane.showInputDialog("Enter Your Name: ");
            if (name != null){
                if (JOptionPane.showConfirmDialog(null, "Are you sure with this name? Because you won't be able to change this", "Confirmation", JOptionPane.YES_NO_OPTION) == 0){
                    account.set_name(name);
                    card.UpdateName(account);
                    account_name = NAME();
                    remove(add_name);
                    repaint();
                    remove(Name);
                    add(account_name);
                    add(Name);
                    repaint();
                }else{}
            }else {
                JOptionPane.showMessageDialog(null, "Name should not be blank", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
