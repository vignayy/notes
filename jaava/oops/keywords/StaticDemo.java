package jaava.oops.keywords;

class BlockA {
    static {
        System.out.println("Static Block A");
    }

    {
        System.out.println("Instance Block A");
    }

    BlockA() {
        System.out.println("Constructor A");
    }
}

class BlockB extends BlockA {
    static {
        System.out.println("Static Block B");
    }

    {
        System.out.println("Instance Block B");
    }

    BlockB() {
        System.out.println("Constructor B");
    }
}

public class StaticDemo {
    public static void main(String[] args) {
        BlockB b = new BlockB();
    }
}
