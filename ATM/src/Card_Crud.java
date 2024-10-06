import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Card_Crud {
    File Cards;

    public Card_Crud(){
        Cards = new File("Cards.txt");
    }

    //============================================== ALL Cards Available ======================================================================
    public List<Card_Account> getallcards() {
        ArrayList<Card_Account> card_list = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new FileReader(Cards));
            while (scan.hasNextLine()) {
                try {
                    Card_Account account = new Card_Account();
                    account.set_name(scan.next());
                    scan.skip(" - ");
                    account.set_bp(Integer.parseInt(scan.next()));
                    scan.skip(" - ");
                    account.set_pin(Integer.parseInt(scan.next()));
                    scan.skip(" - ");
                    account.set_balance(Double.parseDouble(scan.next()));
                    card_list.add(account);
                } catch (Exception err) {
                    continue;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return card_list;
    }
    //============================================== Display Cards Available ==================================================================
    public String[] GetCards(){
        List<Card_Account> list = getallcards();
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).get_bp()+"";
        }
        return array;
    }
    //============================================== Create User ==============================================================================
    public void create_card(Card_Account card) {
        boolean flag = true;
        List<Card_Account> Cards_List = getallcards();
        FileWriter fw;
        BufferedWriter bw;

        for (Card_Account record : Cards_List) {
            try {
                if (record.get_bp() == card.get_bp()) {
                    throw new ErrorHandler("Duplicate BP_Number Error");
                }
            } catch (ErrorHandler err) {
                flag = false;
                JOptionPane.showMessageDialog(null, err.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (flag) {
            try {
                fw = new FileWriter(Cards, true);
                bw = new BufferedWriter(fw);

                bw.write(card.Write_to_File());
                bw.write("\n");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Your Card is Succesfully Created", "CONGRATS", JOptionPane.PLAIN_MESSAGE);
        }
    }
    // ================================================== ID confirmation ===============================================================
    public boolean AtmLogin(int bp, int pin) {
        List<Card_Account> Card_list = getallcards();
        for (Card_Account card : Card_list) {
            if (card.get_bp() == bp && card.get_pin() == pin) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
    //============================================== Retrieve User ======================================================================
    public Card_Account retrievecard(int bp) {
        Card_Account record = new Card_Account();
        List<Card_Account> card_list = getallcards();
        for (Card_Account user : card_list) {
            if (user.get_bp() == bp) {
                record.set_bp(user.get_bp());
                record.set_name(user.get_name());
                record.set_pin(user.get_pin());
                record.set_balance(user.get_balance());
                return record;
            }
        }
        return null;
    }
    //================================================= Update name =========================================================================
    public void UpdateName(Card_Account account){
        List<Card_Account> cc = getallcards();
        for(Card_Account se : cc){
            if(se.get_bp() == account.get_bp()){
                cc.set(cc.indexOf(se), account);
            }
        }
        String format = "";
        for (Card_Account san : cc) {
            format += san.Write_to_File() + "\n";
        }
        try {
            Formatter formatFile = new Formatter(Cards);
            formatFile.format("%S", format);
            formatFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //================================================= Update Deposite =========================================================================
    public void Update_Deposite(int BP, double ammount){
        List<Card_Account> cc = getallcards();
        for(Card_Account se : cc){
            if(se.get_bp() == BP){
                se.set_balance(ammount);
            }
        }

        String format = "";
        for (Card_Account san : cc) {
            format += san.Write_to_File() + "\n";
        }
        try {
            Formatter formatFile = new Formatter(Cards);
            formatFile.format("%S", format);
            formatFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //================================================= Update Withraw =========================================================================
    public void Update_Withraw(int BP, double ammount){
        List<Card_Account> cc = getallcards();
        for(Card_Account se : cc){
            if(se.get_bp() == BP){
                se.set_bal(ammount);
            }
        }

        String format = "";
        for (Card_Account san : cc) {
            format += san.Write_to_File() + "\n";
        }
        try {
            Formatter formatFile = new Formatter(Cards);
            formatFile.format("%S", format);
            formatFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //================================================= Update trsnsac =========================================================================
    public LinkedList<String> get_file(String ammounts){
        LinkedList<String> list = new LinkedList<String>();

        String ammount = ammounts;
        int amm = Integer.parseInt(ammount);
        while (amm > 1 || amm != 0){
            if (ammount.length() == 5 || ammount.length() == 4){
                while (ammount.length() > 3){
                    amm -= 1000;
                    ammount = amm+"";
                    list.add("Pic/1000.jpg");
                }
            }
            else if(ammount.length() == 3){
                while (ammount.length() > 2){
                    if (ammount.charAt(0) == '5' || ammount.charAt(0) == '6' || ammount.charAt(0) == '7' || ammount.charAt(0) == '8' || ammount.charAt(0) == '9'){
                        amm -= 500;
                        ammount = amm+"";
                        list.add("Pic/500.jpg");
                    }
                    if (ammount.charAt(0) == '4' || ammount.charAt(0) == '3' || ammount.charAt(0) == '2'){
                        amm -= 200;
                        ammount = amm+"";
                        list.add("Pic/200.jpg");
                    }
                    if (ammount.charAt(0) == '1'){
                        amm -= 100;
                        ammount = amm+"";
                        list.add("Pic/100.jpg");
                    }
                }
            }
            else if(ammount.length() == 2){
                while (ammount.length() > 1){
                    if (ammount.charAt(0) == '5' || ammount.charAt(0) == '6' || ammount.charAt(0) == '7' || ammount.charAt(0) == '8' || ammount.charAt(0) == '9'){
                        amm -= 50;
                        ammount = amm+"";
                        list.add("Pic/50.jpg");
                    }
                    if (ammount.charAt(0) == '4' || ammount.charAt(0) == '3' || ammount.charAt(0) == '2'){
                        amm -= 20;
                        ammount = amm+"";
                        list.add("Pic/20.jpg");
                    }
                    if (ammount.charAt(0) == '1'){
                        amm -= 10;
                        ammount = amm+"";
                        list.add("Pic/10.png");
                    }
                }
            }
            else if(ammount.length() == 1){
                while (!ammount.equals("0")){
                    if (ammount.charAt(0) == '5' || ammount.charAt(0) == '6' || ammount.charAt(0) == '7' || ammount.charAt(0) == '8' || ammount.charAt(0) == '9'){
                        amm -= 5;
                        ammount = amm+"";
                        list.add("Pic/5.png");
                    }
                    if (ammount.charAt(0) == '4' || ammount.charAt(0) == '3' || ammount.charAt(0) == '2' || ammount.charAt(0) == '1'){
                        amm -= 1;
                        ammount = amm+"";
                        list.add("Pic/1.png");
                    }
                }
            }
        }
        return list;
    }
}
