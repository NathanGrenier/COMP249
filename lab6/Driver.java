package lab6;

public class Driver {
    public static void main(String args[]) throws ZeroExistException {
        int[] data1 = { 1, 2, 3, 4 };
        int[] data2 = { 1, 214, 35, 40, 0 };
        int[] data3 = { 1, 2, 3, 12, 74, 56, 89, 75, 0 };
        int[] data4 = { 5, 6, 82, 54, 0, 1, 75 };
        int[] data5 = { 0, 0, 0, 0 };
        Zero obj1 = new Zero(data1, 3);
        Zero obj2 = new Zero(data2, 2);
        Zero obj3 = new Zero(data3, 8);
        Zero obj4 = new Zero(data4, 4);
        Zero obj5 = new Zero(data5, 1);

        obj1.printer(obj1.number);
        System.out.println();
        obj2.printer(obj2.number);
        System.out.println();
        obj3.printer(obj3.number);
        System.out.println();
        obj4.printer(obj4.number);
        System.out.println();
        obj5.printer(obj5.number);
    }
}
