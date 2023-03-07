package lab6;

public class ZeroExistException extends Exception {
    ZeroExistException() {
        System.out.print("Exception: A zero exists in your integer array.");
    }

    ZeroExistException(String explanation) {
        this();
        System.out.println(" " + explanation);
    }

}
