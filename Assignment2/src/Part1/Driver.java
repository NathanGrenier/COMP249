package Part1;

import Part1.package1.*;
import Part1.package2.*;
import Part1.package3.*;

public class Driver {
    public static void main(String agrs[]) {
        WheeledTransportation transport = new WheeledTransportation();
        Train train = new Train();
        Metro metro = new Metro();
        System.out.println(transport);
        System.out.println(train);
        System.out.println(metro);
    }
}
