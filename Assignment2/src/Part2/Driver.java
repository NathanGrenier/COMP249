package Part2;

import Part2.package1.*;
import Part2.package2.*;
import Part2.package3.*;
import Part2.package4.*;
import Part2.package5.*;
import Part2.package6.*;

public class Driver {

    public static Object[] copyTheObject(Object[] objArr) {
        Object[] copyArr = new Object[objArr.length];

        for (int i = 0; i < objArr.length; i++) {
            // Every class implements or inheritcs the makeCopy() method from the copyable
            // interface. Therefore, we can type cast the objects to Copyable
            copyArr[i] = ((Copyable) objArr[i]).makeCopy();
        }
        return copyArr;
    }

    public static void main(String agrs[]) {
        WheeledTransportation wheeledTransportation1 = new WheeledTransportation();
        WheeledTransportation wheeledTransportation2 = new WheeledTransportation(2, 100);
        WheeledTransportation wheeledTransportation3 = new WheeledTransportation(wheeledTransportation2);
        Train train1 = new Train();
        Train train2 = new Train(2, 100, 2, "Start", "End");
        Tram tram1 = new Tram();
        Tram tram2 = new Tram(2, 2, 2, "Start", "End", 10, 1957);
        Metro metro1 = new Metro();
        Monowheel monowheel1 = new Monowheel();
        Aircraft aircraft1 = new Aircraft();
        Aircraft aircraft2 = new Aircraft(10000, 30000);
        Aircraft aircraft3 = new Aircraft(aircraft2);
        WW2Plane ww2Plane1 = new WW2Plane();
        WW2Plane ww2Plane2 = new WW2Plane(20000, 10000, true);
        WW2Plane ww2Plane3 = new WW2Plane(15000, 10000, false);
        Ferry ferry1 = new Ferry();

        Copyable[] objArr = { wheeledTransportation1, wheeledTransportation2, wheeledTransportation3, train1, train2,
                tram1, tram2, metro1, monowheel1, aircraft1, aircraft2, aircraft3, ww2Plane1, ww2Plane2, ww2Plane3,
                ferry1 };

        // Print object fields
        for (Object obj : objArr) {
            System.out.println(obj);
        }

        System.out.println();

        Object[] copyArr = copyTheObject(objArr);

        // Print object fields
        for (Object obj : copyArr) {
            System.out.println(obj);
        }

        System.out.println();

        // Verify if copy was deep
        ((Ferry) copyArr[copyArr.length - 1]).setMaxSpeed(-1);
        System.out.println(objArr[objArr.length - 1]);
        System.out.println(copyArr[copyArr.length - 1]);
    }
}
