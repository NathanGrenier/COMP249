import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Driver {
    public static void main(String args[]) {
        ArrayList<Employer> employers = new ArrayList<>();

        for (int i=0; i<10; i++) {
            employers.add(new Employer());
        }

        try (
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.txt"));
            ) 
        {
            for (Employer e:employers) {
                out.writeObject(e);
            }
        } catch (IOException e) {
            e.toString();
            System.exit(0);
        }
    
        // Change Objects
        employers.get(0).setName("Changed Name");
        employers.get(1).setID("123454678");
        employers.get(2).setSalary(100000);

        ArrayList<Employer> newEmployers = new ArrayList<Employer>();
        try (
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.txt"));
            ) 
        {
            while (true) {
                newEmployers.add((Employer)in.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Old Content From File:");
            for (Employer employer:newEmployers) {
                System.out.println(employer);
            }
        } catch (IOException e) {
            e.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.txt",false));
            ) 
        {
            for (Employer e:employers) {
                out.writeObject(e);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

        try (
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.txt"));
            ) 
        {
            newEmployers.clear();
            while (true) {
                newEmployers.add((Employer) in.readObject());
            }
        } catch (EOFException e) {
            System.out.println("New Objects:");
            for (Employer employer: newEmployers) {
                System.out.println(employer);
            }
        }
        catch(IOException e) {
            e.getStackTrace();
        } catch(ClassNotFoundException e) {
            e.getStackTrace();
        }
    }
}
