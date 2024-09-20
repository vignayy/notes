package jaava.oops.topics;

import java.util.Objects;

class Laptop {

    String model;
    int price;
    String serialNo;

    //    toString() of Object Class
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }

    //    Equals and Hash
    public boolean equals(Object o) {           //Not included SerialNumber to compare
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return price == laptop.price && Objects.equals(model, laptop.model);
    }

    public int hashCode() {
        return Objects.hash(model, price, serialNo);
    }
}

public class ObjClassEqualsHash {
    public static void main(String[] args) {

        Laptop obj1 = new Laptop();
        obj1.model = "Lenovo";
        obj1.price = 1000;
        obj1.serialNo = "Len5J74I7";

        System.out.println(obj1);       // toString() Method

        Laptop obj2 = new Laptop();
        obj2.model = "HP";
        obj2.price = 1200;
        obj2.serialNo = "HP8KE66I7";

        boolean isSame = obj1.equals(obj2);
        System.out.println(isSame);

    }
}
