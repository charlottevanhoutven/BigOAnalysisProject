package solution;

/**
 * BigOh.java
 */
import java.util.Random;
import java.util.Scanner;
import util.Algorithms;

/**
 * Contains methods to analyze time complexity of algorithms.
 * 
 * @author Mitch Parry
 * @version 2014-01-28
 * 
 */
public class BigOh
{
    private static final double MILLISECONDS_PER_SECOND = 1000.0;
    
    private Random rand;

    /**
     * No-args constructor initializes the random using current time.
     */
    public BigOh()
    {
        rand = new Random();
    }

    /**
     * Constructor takes an Random object to initialize the randomness of the
     * algorithms.
     * 
     * @param rand
     *            the random number generator
     */
    public BigOh(Random rand)
    {
        this.rand = rand;
    }

    /**
     * robustTimeAlgorithm returns the minimum time it takes to run the chosen
     * algorithm over 5 trials.
     * 
     * @param choice
     *            The index of the algorithm to use
     * @param n
     *            The size of the problem
     * @return the time in seconds
     */
    public double robustTimeAlgorithm(int choice, int n)
    {
        
        double time1 = timeAlgorithm(choice, n);
        double time2 = timeAlgorithm(choice, n);
        double time3 = timeAlgorithm(choice, n);
        double time4 = timeAlgorithm(choice, n);
        double time5 = timeAlgorithm(choice, n);
        
        if (time1 < time2 && time1 < time3 && time1 < time4 && time1 < time5)
        {
            return time1;
        }
        if (time2 < time1 && time2 < time3 && time2 < time4 && time2 < time5)
        {
            return time2;
        }
        if (time3 < time1 && time3 < time2 && time3 < time4 && time3 < time5)
        {
            return time3;
        }
        if (time4 < time1 && time4 < time2 && time4 < time3 && time4 < time5)
        {
            return time4;
        }
        else 
        {
            return time5;
        }
    }

    /**
     * timeAlgorithm returns the time it takes to run the algorithm once.
     * 
     * @param choice
     *            The index of the algorithm to use
     * @param n
     *            The size of the problem
     * @return the time in seconds
     */
    public double timeAlgorithm(int choice, int n)
    {
        // make sure that the garbage collector doesn't run
        // during timing. (Do this first.)
        System.gc();

        double a = System.currentTimeMillis();
        runAlgorithm(choice, n);
        double b = System.currentTimeMillis();
        double time = (b - a) / MILLISECONDS_PER_SECOND;
        return time;
    }

    /**
     * runAlgorithm selects the algorithm to run based on <code>choice</code>.
     * 
     * @param choice
     *            The number representing the algorithm choice
     * @param numElements
     *            The size of the problem
     * @return The result of the algorithm
     */
    public int runAlgorithm(int choice, int numElements)
    {
        
        if (choice == 1)
        {
            int a = Algorithms.alg1(numElements, this.rand);
            return a;
            
        }
        else if (choice == 2)
        {
            int a = Algorithms.alg2(numElements, this.rand);
            return a;
        }
        else if (choice == 3)
        {
            int a = Algorithms.alg3(numElements, this.rand);
            return a;
        }
        else if (choice == 4)
        {
            int a = Algorithms.alg4(numElements, this.rand);
            return a;
        }
        else if (choice == 5)
        {
            int a = Algorithms.alg5(numElements, this.rand);
            return a;
        }
        else if (choice == 6)
        {
            int a = Algorithms.alg6(numElements, this.rand);
            return a;
        }
        else 
        {
            return -1;
        }
    }

    /**
     * bigOhFunc returns the Big-Oh function for algorithm and problem size
     * parameters.
     * 
     * @param choice
     *            The number representing the algorithm choice
     * @param n
     *            The problem size.
     * @return The Big-Oh function for problem size, n.
     */
    public double bigOhFunc(int choice, double n)
    {
        if (choice == 1)
        {
            return n;
        }
        else if (choice == 2)
        {
            return (n * n * n);
        }
        else if (choice == 3)
        {
            return (n * n);
        }
        else if (choice == 4)
        {
            return (n * n);
        }
        else if (choice == 5)
        {
            return (n * n * n * n * n);
        }
        else 
        {
            return (n * n * n * n);
        }
    }

    /**
     * estimateTiming takes an algorithm choice, problem size and timing, and
     * estimates the timing for a second problem size.
     * 
     * @param choice
     *            The number representing the algorithm choice
     * @param n1
     *            The first problem size
     * @param t1
     *            The first timing
     * @param n2
     *            The second problem size
     * @return The estimated timing for the second problem size
     */
    public double estimateTiming(int choice, int n1, double t1, int n2)
    {
        double t2;
        if (choice == 1)
        {
            t2 = t1 * n2 / n1;
        }
        else if (choice == 2)
        {
            t2 = t1 * Math.pow(n2, 3) / Math.pow(n1, 3);
        }
        else if (choice == 3)
        {
            t2 = t1 * Math.pow(n2, 2) / Math.pow(n1, 2);
        }
        else if (choice == 4)
        {
            t2 = t1 * Math.pow(n2, 2) / Math.pow(n1, 2);
        }
        else if (choice == 5)
        {
            t2 = t1 * Math.pow(n2, 5) / Math.pow(n1, 5);
        }
        else
        {
            t2 = t1 * Math.pow(n2, 4) / Math.pow(n1, 4);            
        }
        return t2;
    }

    /**
     * percentError returns the percent error in an estimate.
     * 
     * @param correct
     *            the correct value
     * @param estimate
     *            the estimated value
     * @return the percent error
     */
    public double percentError(double correct, double estimate)
    {
        double error = 0.0;
        error = (estimate - correct) / correct;
        return error;
    }

    /**
     * computePercentError takes an algorithm choice, and two problem sizes and
     * computes the error in estimating the timing of the second problem using
     * the timing of the first.
     * 
     * @param choice
     *            The number representing the algorithm choice
     * @param n1
     *            The first problem size
     * @param n2
     *            The second problem size
     * @return the percent error in estimating t2 given n1 and n2.
     */
    public double computePercentError(int choice, int n1, int n2)
    {
        double emptime = robustTimeAlgorithm(choice, n1); 
        double emptime2 = robustTimeAlgorithm(choice, n2);
        double esttime = estimateTiming(choice, n1, emptime, n2);
        double error = percentError(emptime2, esttime);
        return error;
        
    }

    /**
     * Main method.
     * 
     * @param args
     *            Command line arguments not used.
     */
    public static void main(String[] args)
    {
        int choice;
        int numElements = 0;
        Scanner keyInput = new Scanner(System.in);
        BigOh bo = new BigOh();

        // run the fragments
        choice = menu(keyInput);
        while (choice != 7)
        {
            if (choice >= 1 && choice <= 6)
            {
                System.out.print("How many elements: ");
                numElements = keyInput.nextInt();
                double time = bo.timeAlgorithm(choice, numElements);
                long milliseconds = (long) (time * MILLISECONDS_PER_SECOND);
                System.out.println("The time for alg" + choice + " with n="
                    + numElements + " is " + milliseconds + " ms.\n\n");
            }
            choice = menu(keyInput);
        }
        System.out.println("Quitting");
    }

    /**
     * Prints the menu and prompts for input.
     * 
     * @param keyInput
     *            The scanner to read input
     * @return the number read
     */
    public static int menu(Scanner keyInput)
    {
        int choice = -1;

        System.out.println();
        System.out.println("       1.  Method #1 ");
        System.out.println("       2.  Method #2 ");
        System.out.println("       3.  Method #3 ");
        System.out.println("       4.  Method #4 ");
        System.out.println("       5.  Method #5 ");
        System.out.println("       6.  Method #6 ");
        System.out.println("       7.  Quit \n");
        System.out.print("Enter your choice: ");
        choice = keyInput.nextInt();
        return choice;
    }
}
