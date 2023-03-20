public class Driver {
    public static void main(String[] args) throws Exception {
        System.out.println(SINCheck.checkRecursivly("982241314"));
        System.out.println(SINCheck.checkRecursivly("154439215"));
        System.out.println();
        System.out.println(SINCheck.haveSameHash("982241314", "154439215"));
    }
}
