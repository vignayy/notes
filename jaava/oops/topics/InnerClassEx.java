package jaava.oops.topics;

class OuterCls {
    int age;

    public void show() {
        System.out.println("in show");
    }

//	class InnerCls { public void config(){ System.out.println("in config"); } } // Non - Static Inner Class

    class InnerCls {
        public void config() {
            System.out.println("in config");
        }
    }
}

abstract class AbstractInner {          // Abstract Class
    abstract public void ride();       // Abstract Method
}

public class InnerClassEx {
    public static void main(String[] args) {

//        OuterCls outerObj = new OuterCls();
//        outerObj.show();

//        OuterCls.InnerCls innerObj = outerObj.new InnerCls();   // if B is Non Static: need object of Outer Class
//        innerObj.config();

        OuterCls.InnerCls innerObj = new OuterCls().new InnerCls();   // if B is Non Static: need object of Outer Class
        innerObj.config();

//        OuterCls.InnerCls innerObj = new OuterCls.InnerCls();
//        innerObj.config();

        OuterCls objAnony = new OuterCls() {           //Anonymous Inner Class -> To override Once
            public void show() {
                System.out.println("in Anonymous InnerClass - new show");
            }
        };
        objAnony.show();

        AbstractInner objAbstrct = new AbstractInner() {        //Implementing Abstract Method using Anonymous Inner Class -> To Implement Once
            public void ride() {
                System.out.println("Wroom Wrooom..!");
            }
        };
        objAbstrct.ride();
    }
}
