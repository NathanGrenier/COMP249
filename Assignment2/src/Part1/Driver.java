// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Driver class. Its purpose is to test the classes made in the Part1 package.
// -----------------------------------------------------

package Part1;

import Part1.package1.*;
import Part1.package2.*;
import Part1.package3.*;
import Part1.package4.*;
import Part1.package5.*;
import Part1.package6.*;

public class Driver {

    public static void findLeastAndMostExpensiveAircraft(Object[] objArr) {
        Aircraft[] aircraftArr = new Aircraft[0];

        // Create array of Aircraft objects
        for (Object obj : objArr) {
            if (obj instanceof Aircraft) {
                Aircraft aircraft = (Aircraft) obj;

                /*
                 * Different way of implementing the deep copy (Add 1 object at a time)
                 * Aircraft[] temp = new Aircraft[aircraftArr.length + 1];
                 * for (int i = 0; i < aircraftArr.length; i++) {
                 * temp[i] = aircraftArr[i].clone();
                 * }
                 * temp[temp.length - 1] = aircraft.clone();
                 * 
                 * aircraftArr = new Aircraft[temp.length];
                 * for (int i = 0; i < temp.length; i++) {
                 * aircraftArr[i] = temp[i].clone();
                 * }
                 */

                Aircraft[] temp = aircraftArr.clone();
                aircraftArr = new Aircraft[temp.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    aircraftArr[i] = temp[i].clone();
                }
                aircraftArr[aircraftArr.length - 1] = aircraft.clone();
            }
        }

        // If there are no aircrafts
        if (aircraftArr.length == 0) {
            System.out.println("User did not supply any aircrafts.");
            return;
        }

        Aircraft leastExpensive = aircraftArr[0]; // These values could be cloned, but they will be deleted once out of
                                                  // scope.
        Aircraft mostExpensive = aircraftArr[0];
        // Loop over Aircraft objects and find the least and most expensive ones
        for (Aircraft aircraft : aircraftArr) {
            if (aircraft.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = aircraft;
            }
            if (aircraft.getPrice() < leastExpensive.getPrice()) {
                leastExpensive = aircraft;
            }
        }

        System.out
                .println("Most Expensive Aircraft: " + mostExpensive + "\nLeast Expensive Aircraft: " + leastExpensive);
        return;
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

        Object[] objArr = { wheeledTransportation1, wheeledTransportation2, wheeledTransportation3, train1, train2, tram1, tram2, metro1, monowheel1, aircraft1, aircraft2, aircraft3, ww2Plane1, ww2Plane2, ww2Plane3,ferry1 };

        // Print object fields
        for (Object obj : objArr) {
            System.out.println(obj);
        }

        System.out.println();

        // Testing equality:
        System.out.println("Testing equality: 2 objects of the same class with same values | "
                + wheeledTransportation2.equals(wheeledTransportation3));
        System.out
                .println("Testing equality: 2 objects of different classes | " + metro1.equals(wheeledTransportation1));
        System.out.println(
                "Testing equality: 2 objects of the same class with different values | " + tram1.equals(tram2));

        System.out.println();

        Object[] testArr1 = { wheeledTransportation1, train1, tram1, metro1, monowheel1, aircraft1, aircraft2,
                ww2Plane1, ww2Plane2, ww2Plane3, ferry1, wheeledTransportation2, wheeledTransportation3, train2,
                tram2 };
        Object[] testArr2 = { wheeledTransportation1, wheeledTransportation2, wheeledTransportation3, train1, train2,
                tram1, tram2, metro1, monowheel1, ferry1, wheeledTransportation1, wheeledTransportation2,
                wheeledTransportation3, train1, train2,
                tram1, tram2, metro1, monowheel1, ferry1 };
        Object[] singleAircraft = { aircraft1 };

        System.out.println("Multiple Aircrafts: ");
        findLeastAndMostExpensiveAircraft(testArr1);

        System.out.println("No Aircrafts: ");
        findLeastAndMostExpensiveAircraft(testArr2);

        System.out.println("Single Aircraft: ");
        findLeastAndMostExpensiveAircraft(singleAircraft);

    }
}
