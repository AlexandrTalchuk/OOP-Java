public class Float {
    private int price;
    private String adress;

    public Float(int price, String adress){
        this.price = price;
        this.adress = adress;
    }

    public String GetInformation()
    {
        return this.adress + " Price: " + this.price;
    }

    public boolean Match(int max)
    {
        if (this.price<=max){
            return true;
        }
        else {
            return false;
        }
    }
}
