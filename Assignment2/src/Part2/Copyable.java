// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Copyable interface. It allows all classes that extend it to be copied. This is useful in the Driver class where we copy an array of objects, all of which implement Copyable.
// -----------------------------------------------------

package Part2;

/**
 * The Copyable interface allows an class that implements it to be copied. To
 * copy an object that is Copyable, call the makeCopy method on it.
 * 
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public interface Copyable {
    /**
     * Each class must overide the makeCopy method with its own version of it. The
     * overwritten method of each class will return its type and use the class' copy
     * constructor to make the copy.
     * 
     * @return Object
     */
    public abstract Copyable makeCopy();
}
