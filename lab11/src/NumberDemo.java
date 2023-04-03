import java.util.ArrayList;

public class NumberDemo {
    ArrayList<Integer> numberStore;
    
    public NumberDemo() {
        this.numberStore = new ArrayList<>();
    }

    public NumberDemo(ArrayList<Integer> numberStore) {
        this.numberStore = numberStore;
    }

    public ArrayList<Integer> getNumberStore() {
        return this.numberStore;
    }

    public void setNumberStore(ArrayList<Integer> numberStore) {
        this.numberStore = (ArrayList<Integer>) numberStore.clone();
    }

    public void printStore() {
        for (int n:this.numberStore) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public int getSize() {
        return this.numberStore.size();
    }

    public int checkNumber(int num) {
        return this.numberStore.indexOf(num);
    }

    public boolean emptyCheck() {
        return this.numberStore.isEmpty();
    }

    public void addNumber(int num) {
        this.numberStore.add(num);
    }
    
    public void addNumber(int pos, int num) {
        this.numberStore.add(pos, num);
    }

    public void removeNumber(int num) {
        for (int n:this.numberStore) {
            if (n == num) {
                this.numberStore.remove(this.numberStore.indexOf(n));
                return;
            }
        }
    }

    public static boolean compareStores(NumberDemo list1, NumberDemo list2) {
        if (list1.getSize() != list2.getSize()) {
            return false;
        }
        for (int i=0; i < list1.getSize(); i++) {
            if (list1.numberStore.get(i) != list2.numberStore.get(i)) {
                return false;
            }
        }
        return true;
    } 

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> arrLst1 = new ArrayList<>();
        arrLst1.add(100);
        NumberDemo list1 = new NumberDemo(arrLst1);
        NumberDemo list2 = new NumberDemo();

        ArrayList<Integer> getNumberStore = list1.getNumberStore();
        list2.setNumberStore(list1.getNumberStore());
        System.out.print("List1; Size: " + list1.getSize() + " | ");
        list1.printStore();
        System.out.print("List2; Size: " + list2.getSize() + " | ");
        list2.printStore();

        System.out.println(list1.checkNumber(10));
        System.out.println(list2.emptyCheck());

        list1.addNumber(1000);
        list2.addNumber(0, 5);

        System.out.print("List1; Size: " + list1.getSize() + " | ");
        list1.printStore();
        System.out.print("List2; Size: " + list2.getSize() + " | ");
        list2.printStore();

        list1.removeNumber(1000);

        System.out.print("list1; Size: " + list1.getSize() + " | ");
        list1.printStore();

        System.out.println(compareStores(list1, list2));
        
        list1.addNumber(0, 5);

        System.out.print("list1; Size: " + list1.getSize() + " | ");
        list1.printStore();
        
        System.out.println(compareStores(list1, list2));
        

        
        
        
    }
}
