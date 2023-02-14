public class Test {
    public static void main(String[] args) {

        try {
            System.out.println("Main try block");
            try {
                System.out.println("Nested try block 1");
                int num1 = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("Catch of nested try 1");
            }

            try {
                System.out.println("Nested try block 2");
                int num1 = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("catch of nested try 2");
            }
        } catch (Exception e) {
            System.out.println("Main catch");
        }

        try {
            try {
                try {

                } catch (ArithmeticException e) {
                    System.out.println("2nd level nested catch");
                }
            } catch (NullPointerException e) {

            }
        } catch (Exception e) {

        }

    }
}
