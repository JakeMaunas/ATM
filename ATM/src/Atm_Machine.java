import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

public class Atm_Machine extends JFrame implements ActionListener{
    Card_Crud card = new Card_Crud();
    Font Pixelfont;
    String input = "", pass = "";
    int BP = 0;
    boolean B1 = false;
    boolean B2 = false;
    int Tile = 10;

    Image icon = new ImageIcon("Pic/Icon.png").getImage();

    Money_Animation mon_ani;
    Animation ani;
    Card_Information card_info;

    //Account information
    JPanel holder, cash;
    JComboBox BOX;
    JPanel Display = new JPanel();
    JPanel Display2 = new JPanel();
    JPanel Display3;
    JPanel back;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, point, cancel, clear, enter, blank1, blank2;
    JButton l1, l2, l3, l4, l42, r1, r2, r3, r4;
    JLabel tl1, tl2, tl3, tl4, tl42, tr1, tr2, tr3, Balance;
    JLabel wlc, to, tag, createlog, Acc_Bal, Wallet;

    JTextField BP_Number, Pin, enter_pin, enter_withrawal, enter_deposite;
    Atm_Machine(){
        getContentPane().setBackground(Color.decode("#B5B7BB"));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((60*Tile),(70*Tile));
        setLocationRelativeTo(null);
        setIconImage(icon);
        //Font Pixel
        try{
            Pixelfont = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")).deriveFont(17f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")));
        }catch (Exception e){
        }
        //===============================
        //Display 1 Properties
        Display.setLayout(null);
        Display.setSize((int)(37.4*Tile),(30*Tile));
        Display.setLocation((int)(10.5*Tile),(5*Tile));
        Display.setBackground(Color.decode("#C1CFA1"));
        Display.setBorder(BorderFactory.createLineBorder(Color.black,1));

        //Display 2
        Display2.setLayout(null);
        Display2.setSize((int)(37.4*Tile),(30*Tile));
        Display2.setLocation((int)(10.5*Tile),(5*Tile));
        Display2.setBackground(Color.decode("#C1CFA1"));
        Display2.setBorder(BorderFactory.createLineBorder(Color.black,1));

        back = new JPanel();
        back.setLayout(null);
        back.setSize((int)(43*Tile),(27*Tile));
        back.setLocation((int)(7.5*Tile),(38*Tile));
        back.setBackground(Color.decode("#CCCCCC"));
        back.setBorder(BorderFactory.createLineBorder(Color.black,1));

        b1 = create_button("#757575","#D8D8D8", "1", (int)(10.5*Tile),(40*Tile),(7*Tile),(5*Tile));
        b2 = create_button("#757575","#D8D8D8", "2", (int)(18.5*Tile),(40*Tile),(7*Tile),(5*Tile));
        b3 = create_button("#757575","#D8D8D8", "3", (int)(26.5*Tile),(40*Tile),(7*Tile),(5*Tile));

        b4 = create_button("#757575","#D8D8D8", "4", (int)(10.5*Tile),(46*Tile),(7*Tile),(5*Tile));
        b5 = create_button("#757575","#D8D8D8", "5", (int)(18.5*Tile),(46*Tile),(7*Tile),(5*Tile));
        b6 = create_button("#757575","#D8D8D8", "6", (int)(26.5*Tile),(46*Tile),(7*Tile),(5*Tile));

        b7 = create_button("#757575","#D8D8D8", "7", (int)(10.5*Tile),(52*Tile),(7*Tile),(5*Tile));
        b8 = create_button("#757575", "#D8D8D8",  "8", (int)(18.5*Tile),(52*Tile),(7*Tile),(5*Tile));
        b9 = create_button("#757575", "#D8D8D8", "9", (int)(26.5*Tile),(52*Tile),(7*Tile),(5*Tile));

        cancel = create_button("#C7253E", "#181C14","CANCEL", (int)(38.5*Tile),(40*Tile),(9*Tile),(5*Tile));
        clear = create_button("#FFEB55","#181C14", "CLEAR", (int)(38.5*Tile),(46*Tile),(9*Tile),(5*Tile));
        enter = create_button("#6EC207", "#181C14","ENTER", (int)(38.5*Tile),(52*Tile),(9*Tile),(5*Tile));

        blank2 = create_button("#757575", "#D8D8D8", "", (int)(38.5*Tile),(58*Tile),(9*Tile),(5*Tile));
        blank1 = create_button("#757575", "#D8D8D8", "", (int)(10.5*Tile),(58*Tile),(7*Tile),(5*Tile));
        blank1.setEnabled(false);
        blank2.setEnabled(false);

        b0 = create_button("#757575", "#D8D8D8", "0", (int)(18.5*Tile),(58*Tile),(7*Tile),(5*Tile));
        point = create_button("#757575", "#D8D8D8", ".", (int)(26.5*Tile),(58*Tile),(7*Tile),(5*Tile));

        l1 = create_button("#757575","#D8D8D8", ">", (int)(3.5*Tile),(14*Tile),(6*Tile),(4*Tile));
        l2 = create_button("#757575","#D8D8D8", ">", (int)(3.5*Tile),(19*Tile),(6*Tile),(4*Tile));
        l3 = create_button("#757575","#D8D8D8", ">", (int)(3.5*Tile),(24*Tile),(6*Tile),(4*Tile));
        l4 = create_button("#757575","#D8D8D8", ">", (int)(3.5*Tile),(29*Tile),(6*Tile),(4*Tile));
        l42 = create_button("#757575","#D8D8D8", ">", (int)(3.5*Tile),(29*Tile),(6*Tile),(4*Tile));

        r1 = create_button("#757575","#D8D8D8", "<", (int)(49*Tile),(14*Tile),(6*Tile),(4*Tile));
        r2 = create_button("#757575","#D8D8D8", "<", (int)(49*Tile),(19*Tile),(6*Tile),(4*Tile));
        r3 = create_button("#757575","#D8D8D8", "<", (int)(49*Tile),(24*Tile),(6*Tile),(4*Tile));

        l1.setVisible(false);
        l42.setVisible(false);
        r1.setVisible(false);
        r2.setVisible(false);
        r3.setVisible(false);

        //Labels Area
        wlc = create_tag("Welcome", "#3C3D37", (int)(11*Tile), (int)(2.5*Tile), 400, 50);
        wlc.setFont(Pixelfont.deriveFont(Font.BOLD, 40));
        to = create_tag("To", "#3C3D37", (int)(17*Tile), (int)(5*Tile), 400, 50);
        to.setFont(Pixelfont.deriveFont(Font.BOLD, 30));
        tag = create_tag("ATM   SIMULATOR", "#557C56", (int)(10*Tile), (int)(7.5*Tile), 400, 50);
        tag.setFont(Pixelfont.deriveFont(Font.BOLD, 25));

        Acc_Bal = create_tag("BALANCE", "#3C3D37", (int)(8*Tile), (int)(6*Tile), 210, 50);
        Acc_Bal.setFont(Pixelfont.deriveFont(Font.BOLD, 30));
        Acc_Bal.setHorizontalAlignment(JLabel.CENTER);
        Acc_Bal.setVisible(false);

        createlog = create_tag("Create   Card", "#3C3D37", (int)(4.5*Tile), (int)(2.5*Tile), 400, 50);
        createlog.setFont(Pixelfont.deriveFont(Font.BOLD, 45));
        createlog.setVisible(false);
        //

        tl1 = create_label("Account information",(0), (9), (18*Tile), (4*Tile));
        tl2 = create_label("  Create Card",(0), (14), (18*Tile), (4*Tile));
        tl3 = create_label("  Insert Card",(0), (19), (18*Tile), (4*Tile));
        tl4 = create_label("  Back",(0), (24), (18*Tile), (4*Tile));
        tl42 = create_label("  Back",(0), (24), (18*Tile), (4*Tile));

        tr1 = create_label("           Balance",(20), (9), (18*Tile), (4*Tile));
        tr2 = create_label("         Withrawal",(20), (14), (18*Tile), (4*Tile));
        tr3 = create_label("          Deposite",(20), (19), (18*Tile), (4*Tile));

        //Create Part
        BP_Number = creat_textfield("Enter 5 digit BP Number", 37, 100, 300, 40, 17, 20);
        Pin = creat_textfield("Enter 4 digit Pin Number", 37, 160, 300, 40, 17, 20);
        enter_pin = creat_textfield("Enter Pin Number", 85, 160, 200, 40, 17, 20);
        enter_withrawal = creat_textfield("Enter Withrawal Ammount", 37, 110, 300, 55, 17, 23);
        enter_deposite = creat_textfield("Enter Deposite Ammount", 37, 110, 300, 55, 17, 23);

        BOX = create_Box();
        holder = Card_Holder();
        holder.setVisible(false);
        Display.add(wlc);
        Display.add(to);
        Display.add(tag);
        Display.add(createlog);
        Display.add(tl2);
        Display.add(tl3);
        Display.add(tl4);
        Display.add(BP_Number);
        Display.add(Pin);
        Display.add(enter_pin);
        //Display 2
        Display2.add(enter_deposite);
        Display2.add(enter_withrawal);
        Display2.add(tl1);
        Display2.add(tl42);
        Display2.add(tr1);
        Display2.add(tr2);
        Display2.add(tr3);
        Display2.add(Acc_Bal);
        Display2.setVisible(false);

        add(holder);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l42);
        add(r1);
        add(r2);
        add(r3);
        add(BOX);
        add(blank2);
        add(cancel);
        add(clear);
        add(enter);
        add(Display);
        add(Display2);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(blank1);
        add(b0);
        add(point);
        add(back);
        setVisible(true);
    }

    JButton create_button(String color, String color2, String text, int x, int y, int w, int h){
        JButton btn = new JButton(text);

        if(text.equals("<") || text.equals(">") || text.equals(".")){
            btn.setFont(new Font("Monospaced", Font.BOLD, 17));
        }else{
            btn.setFont(Pixelfont);
        }

        btn.setBounds(x,y,w,h);
        btn.setBorder(BorderFactory.createLineBorder(Color.black,1));
        btn.setFocusPainted(false);
        btn.setBackground(Color.decode(color));
        btn.setForeground(Color.decode(color2));
        btn.addActionListener(this);
        btn.setFocusable(false);
        return btn;
    }

    JLabel create_label(String Text, int x, int y, int w, int h){
        JLabel Label = new JLabel(Text);
        Label.setFont(Pixelfont.deriveFont(17));
        Label.setBounds((int)(x*Tile),(y*Tile),w,h);
        Label.setForeground(java.awt.Color.decode("#2F3645"));
        Label.setBackground(java.awt.Color.decode("#9CA986"));
        Label.setBorder(BorderFactory.createLineBorder(Color.black,1));
        Label.setOpaque(true);
        return Label;
    }

    JTextField creat_textfield(String ph, int x, int y, int width, int length, int font, int fontnew){
        JTextField textField = new JTextField();
        textField.setBounds((x),(y),width,length);
        textField.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1, true));
        textField.setFont(new Font("Monospaced", Font.PLAIN, font));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(Color.decode("#C1D8C3"));
        textField.setForeground(Color.decode("#686D76"));
        textField.setVisible(false);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setForeground(Color.decode("#343131"));
                if (textField.getText().equals(ph)) {
                    textField.setText("");
                    textField.setFont(Pixelfont.deriveFont(Font.BOLD, fontnew));
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setFont(new Font("Monospaced", Font.PLAIN, font));
                    textField.setForeground(Color.decode("#686D76"));
                    textField.setText(ph);
                }
            }
        });
        return textField;
    }

    JLabel create_tag(String text, String color, int x, int y, int w, int l){
        JLabel labe = new JLabel(text);
        labe.setBounds(x,y,w,l);
        labe.setForeground(Color.decode(color));
        return labe;
    }

    JComboBox create_Box(){
        JComboBox BOX = new JComboBox(card.GetCards());
        BOX.setBounds((int)(10.5*Tile),(int)(35.5*Tile),(9*Tile),(2*Tile));
        BOX.setFocusable(false);
        BOX.setBackground(Color.decode("#C1CFA1"));
        BOX.setForeground(Color.decode("#33372C"));
        BOX.setBorder(BorderFactory.createLineBorder(Color.black,2));
        BOX.setVisible(false);
        return BOX;
    }

    JPanel Card_Holder(){
        JPanel holder = new JPanel();
        JLabel line = new JLabel("       .    .    .      ");

        holder.setBounds((int)(31.7*Tile),(int)(35*Tile),(int)(8.5*Tile),(int)(2.5*Tile));
        holder.setBackground(Color.decode("#3C3D37"));
        holder.setBorder(BorderFactory.createLineBorder(Color.black,2));

        line.setBounds(0,-1,50,5);
        line.setForeground(Color.decode("#6EC207"));
        line.setBackground(Color.decode("#117554"));
        line.setBorder(BorderFactory.createLineBorder(Color.decode("#185519"),1));
        line.setOpaque(true);

        holder.add(line);
        return holder;
    }

    JLabel Account_Balance(int bp){
        Card_Account account = card.retrievecard(bp);
        JLabel label = new JLabel();
        label.setText("₱"+account.get_balance());
        label.setFont(new Font("Monospaced", Font.BOLD, 25));
        label.setBounds((int)(2.6*Tile),(12*Tile),320,50);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(java.awt.Color.decode("#2F3645"));
        label.setBackground(java.awt.Color.decode("#A0D683"));
        label.setBorder(BorderFactory.createLineBorder(Color.black,1));
        label.setOpaque(true);
        label.setVisible(true);
        return label;
    }

    JPanel Cash_Whole(){
        JPanel holder = new JPanel();

        holder.setBounds((int)(11.5*Tile),(int)(35.3*Tile),(int)(12*Tile),(int)(1.7*Tile));
        holder.setBackground(Color.decode("#3C3D37"));
        holder.setBorder(BorderFactory.createLineBorder(Color.decode("#626F47"),2));

        return holder;
    }

    JLabel wallet(){
        ImageIcon image = new ImageIcon("Pic/wallet.png");
        JLabel label = new JLabel(image);
        label.setVisible(false);
        label.setBounds(112,416,130,130);
        return label;
    }

    public void unsee_see_button(boolean flag){
        b1.setVisible(flag);
        b2.setVisible(flag);
        b3.setVisible(flag);
        b4.setVisible(flag);
        b5.setVisible(flag);
        b6.setVisible(flag);
        b7.setVisible(flag);
        b8.setVisible(flag);
        b9.setVisible(flag);
        b0.setVisible(flag);
        blank1.setVisible(flag);
        blank2.setVisible(flag);
        enter.setVisible(flag);
        cancel.setVisible(flag);
        clear.setVisible(flag);
        point.setVisible(flag);
    }

    //Actioon performed ==================================================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == blank1){
            Display.setVisible(false);
            l2.setVisible(false);
            l3.setVisible(false);
            l4.setVisible(false);
            Display2.setVisible(true);
            ani.setVisible(true);
            holder.setVisible(true);
            tl42.setVisible(true);
            tl1.setVisible(true);
            tr1.setVisible(true);
            tr2.setVisible(true);
            tr3.setVisible(true);
            l1.setVisible(true);
            l42.setVisible(true);
            r1.setVisible(true);
            r2.setVisible(true);
            r3.setVisible(true);

            try{
                remove(mon_ani);
                repaint();
            }catch (Exception b){}

            if(cash != null){
                remove(cash);
                repaint();
            }

            if(Wallet != null){
                remove(Wallet);
                repaint();
            }

            cash = Cash_Whole();
            add(cash);

            blank1.setEnabled(false);
        }
        if (e.getSource() == blank2){
            Display.setVisible(true);
            l2.setVisible(true);
            l3.setVisible(true);
            l4.setVisible(true);
            BP = 0;
            Display2.setVisible(false);
            holder.setVisible(false);
            tl42.setVisible(false);
            tl1.setVisible(false);
            tr1.setVisible(false);
            tr2.setVisible(false);
            tr3.setVisible(false);
            l1.setVisible(false);
            l42.setVisible(false);
            r1.setVisible(false);
            r2.setVisible(false);
            r3.setVisible(false);

            if (ani != null){
                remove(ani);
                repaint();
            }
            if (cash != null){
                remove(cash);
                repaint();
            }

            blank2.setEnabled(false);
        }
        //Upper Left button
        if (e.getSource() == l2){
            tl3.setVisible(false);
            tl2.setVisible(false);
            wlc.setVisible(false);
            to.setVisible(false);
            BOX.setVisible(false);
            enter_pin.setVisible(false);
            createlog.setVisible(true);
            tag.setVisible(false);
            BP_Number.setVisible(true);
            Pin.setVisible(true);
            BP_Number.setText("Enter 5 digit BP Number");
            Pin.setText("Enter 4 digit Pin Number");
            l3.setVisible(false);
            l2.setVisible(false);
        }
        if (e.getSource() == l3){
            remove(back);
            remove(holder);
            if(ani != null){
                remove(ani);
            }
            ani = new Animation();
            holder = Card_Holder();
            add(holder);
            add(ani);
            add(back);
            wlc.setVisible(true);
            to.setVisible(true);
            tag.setVisible(true);

            createlog.setVisible(false);
            tl3.setVisible(false);
            tl2.setVisible(false);
            BP_Number.setVisible(false);
            Pin.setVisible(false);
            l3.setVisible(false);
            l2.setVisible(false);

            BOX.setVisible(true);
            enter_pin.setText("Enter Pin Number");
            enter_pin.setVisible(true);
        }
        if (e.getSource() == l4){
            createlog.setVisible(false);
            tl3.setVisible(true);
            tl2.setVisible(true);
            wlc.setVisible(true);
            to.setVisible(true);
            tag.setVisible(true);
            BOX.setVisible(false);
            holder.setVisible(false);
            if(ani != null){
                ani.setVisible(false);
            }
            if (BP_Number.getText() != null || Pin.getText() != null || enter_pin.getText() != null){
                BP_Number.setText("");
                Pin.setText("");
                enter_pin.setText("");
                pass = "";
                enter_pin.repaint();
                BP_Number.repaint();
                Pin.repaint();
            }
            BP_Number.setVisible(false);
            Pin.setVisible(false);
            enter_pin.setVisible(false);
            l2.setVisible(true);
            l3.setVisible(true);
        }
        if (e.getSource() == l42){
            if(tl1.isVisible() && tl42.isVisible() && tr1.isVisible() && tr2.isVisible() && tr3.isVisible()){
                if (Display3 != null){
                    remove(Display3);
                    repaint();
                }
                blank2.setEnabled(true);
                blank2.doClick();
            }
            if(Acc_Bal.isVisible() && Balance.isVisible()){
                if (Balance != null){
                    Display2.remove(Balance);
                    repaint();
                }
                Acc_Bal.setVisible(false);
                blank1.setEnabled(true);
                blank1.doClick();
            }
            if(enter_withrawal.isVisible()){
                enter_withrawal.setVisible(false);
                blank1.setEnabled(true);
                blank1.doClick();
                unsee_see_button(true);
            }
            if(enter_deposite.isVisible()){
                enter_deposite.setVisible(false);
                blank1.setEnabled(true);
                blank1.doClick();
                unsee_see_button(true);
            }
            if(card_info != null && tl42.isVisible()){
                remove(card_info);
                blank1.setEnabled(true);
                blank1.doClick();
            }

        }
        if (e.getSource() == l1){
            tl1.setVisible(false);
            tr1.setVisible(false);
            tr2.setVisible(false);
            tr3.setVisible(false);
            l1.setVisible(false);
            r1.setVisible(false);
            r2.setVisible(false);
            r3.setVisible(false);

            Display2.setVisible(false);
            card_info = new Card_Information(BP);
            add(card_info);
        }
        //upper right and 1 upper left
        if(e.getSource() == r1){
            tl1.setVisible(false);
            tr1.setVisible(false);
            tr2.setVisible(false);
            tr3.setVisible(false);
            l1.setVisible(false);
            r1.setVisible(false);
            r2.setVisible(false);
            r3.setVisible(false);

            Acc_Bal.setVisible(true);
            Balance = Account_Balance(BP);
            Display2.add(Balance);
        }
        if(e.getSource() == r2) {
            tl1.setVisible(false);
            tr1.setVisible(false);
            tr2.setVisible(false);
            tr3.setVisible(false);
            l1.setVisible(false);
            r1.setVisible(false);
            r2.setVisible(false);
            r3.setVisible(false);

            enter_withrawal.setVisible(true);
            enter_withrawal.setText("Enter Withrawal Ammount");
        }
        if(e.getSource() == r3) {
            tl1.setVisible(false);
            tr1.setVisible(false);
            tr2.setVisible(false);
            tr3.setVisible(false);
            l1.setVisible(false);
            r1.setVisible(false);
            r2.setVisible(false);
            r3.setVisible(false);

            enter_deposite.setVisible(true);
            enter_deposite.setText("Enter Deposite Ammount");
        }
        //Numbers
        if (e.getSource() == b1){
            input += "1";
        }
        if (e.getSource() == b2){
            input += "2";
        }
        if (e.getSource() == b3){
            input += "3";
        }
        if (e.getSource() == b4){
            input += "4";
        }
        if (e.getSource() == b5){
            input += "5";
        }
        if (e.getSource() == b6){
            input += "6";
        }
        if (e.getSource() == b7){
            input += "7";
        }
        if (e.getSource() == b8){
            input += "8";
        }
        if (e.getSource() == b9){
            input += "9";
        }
        if (e.getSource() == b0){
            input += "0";
        }

        //Bottom Right Button
        if(e.getSource() == cancel){
            B1 = true;
        }
        if(e.getSource() == clear){
            B2 = true;
        }
        if(e.getSource() == enter){
            if (BP_Number.isVisible() || Pin.isVisible()){
                if (BP_Number.getText().length() == 5 && Pin.getText().length() == 4) {
                    Card_Account c = new Card_Account();
                    c.set_bp(Integer.parseInt(BP_Number.getText()));
                    c.set_pin(Integer.parseInt(Pin.getText()));
                    c.set_balance(0);
                    card.create_card(c);
                    l4.doClick();
                }else {
                    JOptionPane.showMessageDialog(null, "Please Complete all the requirments", "WARNING",JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (enter_pin.isVisible()){
                if(pass.length() == 4 && card.AtmLogin(Integer.parseInt((String)BOX.getSelectedItem()), Integer.parseInt(pass))){
                    BP = Integer.parseInt((String)BOX.getSelectedItem());
                    l4.doClick();
                    blank1.setEnabled(true);
                    blank1.doClick();
                }else {
                    enter_pin.setText("Enter Pin Number");
                    pass = "";
                    JOptionPane.showMessageDialog(null, "Enter Correct Pin Number", "WARNING",JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if(BOX != null && !BOX.isVisible()){
                remove(BOX);
                repaint();
                BOX = create_Box();
                add(BOX);
            }

            if(enter_deposite.isVisible()){
                if(Integer.parseInt(enter_deposite.getText()) <= 10000){
                    unsee_see_button(false);

                    card.Update_Deposite(BP, Double.parseDouble(enter_deposite.getText()));

                    int del = 100;
                    LinkedList<String> list = card.get_file(enter_deposite.getText());
                    remove(back);
                    remove(cash);
                    repaint();
                    for (int i = 0; i < list.size(); i++){
                        cash = Cash_Whole();
                        mon_ani = new Money_Animation(del, 1, list.get(i));
                        del += 30;
                        add(mon_ani);
                    }

                    cash = Cash_Whole();
                    Wallet = wallet();
                    Wallet.setVisible(true);
                    add(Wallet);
                    add(cash);
                    add(back);
                    enter_deposite.setText("Enter Deposite Ammount");
                }else{
                    JOptionPane.showMessageDialog(null, "You can Only Deposite 10,000", "WARNING",JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if(enter_withrawal.isVisible()){
                if(Integer.parseInt(enter_withrawal.getText()) <= 10000){
                    Card_Account acc = card.retrievecard(BP);
                    if(acc.get_balance() >= Double.parseDouble(enter_withrawal.getText())){
                        unsee_see_button(false);

                        card.Update_Withraw(BP, Double.parseDouble(enter_withrawal.getText()));

                        int del = -40;

                        LinkedList<String> list = card.get_file(enter_withrawal.getText());
                        remove(back);
                        remove(cash);
                        repaint();
                        for (int i = 0; i < list.size(); i++){
                            cash = Cash_Whole();
                            mon_ani = new Money_Animation(del, 2, list.get(i));
                            del -= 30;
                            add(mon_ani);
                        }

                        cash = Cash_Whole();
                        Wallet = wallet();
                        Wallet.setVisible(true);
                        add(Wallet);
                        add(cash);
                        add(back);
                        enter_withrawal.setText("Enter Withrawal Ammount");
                    }else{
                        JOptionPane.showMessageDialog(null, "Not Enough Balance", "WARNING",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "You can Only Withraw 10,000", "WARNING",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        //======================================================= All Text Field ==============================================================
        if (BP_Number.isFocusOwner() && BP_Number.getText().length() != 5 || BP_Number.isFocusOwner() && B1 || BP_Number.isFocusOwner() && B2){
            try {
                if (B1) {
                    StringBuffer sb = new StringBuffer(BP_Number.getText());
                    sb.deleteCharAt(sb.length() - 1);
                    input = "" + sb;
                    BP_Number.setText(input);
                    B1 = false;
                } else if (B2) {
                    BP_Number.setText("");
                    B2 = false;
                } else {
                    BP_Number.setText(BP_Number.getText().concat(input));
                }
            }catch (Exception a){}
            BP_Number.requestFocus();
        }
        if (Pin.isFocusOwner() && Pin.getText().length() != 4 || Pin.isFocusOwner() && B1 || Pin.isFocusOwner() && B2){
            try {
                if (B1) {
                    StringBuffer sb = new StringBuffer(Pin.getText());
                    sb.deleteCharAt(sb.length() - 1);
                    input = "" + sb;
                    Pin.setText(input);
                    B1 = false;
                } else if (B2) {
                    Pin.setText("");
                    B2 = false;
                } else {
                    Pin.setText(Pin.getText().concat(input));
                }
            }catch (Exception a){}
            Pin.requestFocus();
        }
        if (enter_pin.isFocusOwner() && enter_pin.getText().length() != 4 || enter_pin.isFocusOwner() && B1 || enter_pin.isFocusOwner() && B2){
            try {
                if (B1) {
                    StringBuffer sb = new StringBuffer(enter_pin.getText());
                    sb.deleteCharAt(sb.length() - 1);
                    input = "" + sb;
                    enter_pin.setText(input);
                    StringBuffer sb1 = new StringBuffer(pass);
                    sb1.deleteCharAt(sb1.length() - 1);
                    pass = "" + sb1;
                    B1 = false;
                } else if (B2) {
                    enter_pin.setText("");
                    pass = "";
                    B2 = false;
                } else {
                    enter_pin.setText(enter_pin.getText().concat("*"));
                    pass += input;
                }
            }catch (Exception a){}
            enter_pin.requestFocus();
        }
        if (enter_withrawal.isFocusOwner() && enter_withrawal.getText().length() != 5 || enter_withrawal.isFocusOwner() && B1 || enter_withrawal.isFocusOwner() && B2){
            try {
                if (B1) {
                    StringBuffer sb = new StringBuffer(enter_withrawal.getText());
                    sb.deleteCharAt(sb.length() - 1);
                    input = "" + sb;
                    enter_withrawal.setText(input);
                    B1 = false;
                } else if (B2) {
                    enter_withrawal.setText("");
                    B2 = false;
                } else {
                    enter_withrawal.setText(enter_withrawal.getText().concat(input));
                }
            }catch (Exception a){}
            enter_withrawal.requestFocus();
        }
        if (enter_deposite.isFocusOwner() && enter_deposite.getText().length() != 5 || enter_deposite.isFocusOwner() && B1 || enter_deposite.isFocusOwner() && B2){
            try {
                if (B1) {
                    StringBuffer sb = new StringBuffer(enter_deposite.getText());
                    sb.deleteCharAt(sb.length() - 1);
                    input = "" + sb;
                    enter_deposite.setText(input);
                    B1 = false;
                } else if (B2) {
                    enter_deposite.setText("");
                    B2 = false;
                } else {
                    enter_deposite.setText(enter_deposite.getText().concat(input));
                }
            }catch (Exception a){}
            enter_deposite.requestFocus();
        }
        input = "";
    }
}