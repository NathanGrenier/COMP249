public class Driver {
    public static void main(String[] args) throws Exception {
        LinkedList list = new LinkedList();
        Store s1 = new Store(10.0, "Kevin");
        Store s2 = new Store(5.0, "Nathan");
        Store s3 = new Store(11.1, "name");

        list.insert(s1);
        list.insert(s2);
        list.insert(s3);
        list.display();
        System.out.println();
        list.remove(s3);
        list.display();
    }
}
