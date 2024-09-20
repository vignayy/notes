package jaava.adv;

enum Laptops {
    //	Macbook(2000), XPS(2200), Surface(1500), ThinkPad(1800);
    Macbook(2000), XPS(2200), Surface, ThinkPad(1800);

    private int price;

    Laptops() {
        price = 500;
    }

    Laptops(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        System.out.println("in Laptops" + this.name());
    }
}

public class EnumClass {
    public static void main(String[] args) {

//    	Laptops lap=Laptops.Macbook;
//    	System.out.println(lap+ " : "+lap.getPrice());

        for (Laptops lap : Laptops.values()) {
            System.out.println(lap + " : " + lap.getPrice());
        }
    }
}

