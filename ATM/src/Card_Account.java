public class Card_Account {
    private int BP;
    private int PIN;
    private double Balance;
    private String name;

    public void set_name(String name){
        this.name = name;
    }
    public void set_bp(int bp){
        BP = bp;
    }
    public void set_pin(int pin){
        PIN = pin;
    }
    public void set_balance(double cash){
        Balance += cash;
    }
    public void set_bal(double cash){
        Balance -= cash;
    }
    public String get_name(){
        return name;
    }
    public int get_bp(){
        return BP;
    }
    public int get_pin(){
        return PIN;
    }
    public double get_balance(){
        return Balance;
    }

    public String Write_to_File(){
        if (name == null){
            return "NULL" + " - " + BP + " - " + PIN + " - " + Balance;
        }else{
            return name + " - " + BP + " - " + PIN + " - " + Balance;
        }
    }
}
