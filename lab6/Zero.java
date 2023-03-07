package lab6;

public class Zero {
    int[] data;
    int number;

    public Zero(int[] data, int number) {
        this.data = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i];
        }
        this.number = number;
    }

    public void printer(int number) throws ZeroExistException {
        for (int i = 0; i < number; i++) {
            if (this.data[i] == 0) {
                throw new ZeroExistException("The current number is equal to zero.");
            }
            System.out.println(this.data[i]);
        }
    }
}
