/**
 * An application that investigates the recursion limit of the quicksort algorithm
 * utilizing three, different size arrays & produces a chart to visualize the results of the analysis.
 * @author Foothill, Nicholas Lee
 */
public class QSRecursion
{
    /**
     * 1. MIN_LIMIT and MAX_LIMIT static variable declarations of data type
     *    integer for the Min and Max recursion limits.
     * 2. ARRAY_SIZES is an array with capacities from 20,000 to 20,000,000.
     * 3. An array RUCUR_LIMIT for recursion limit
     * 4. and an array results for results.
     */
    final static int MIN_LIMIT = 2, MAX_LIMIT = 300;
    final static int [] ARRAY_SIZES = {20000, 175000, 350000, 1000000, 10000000, 20000000};
    final static int [] RECUR_LIMITS = new int[MAX_LIMIT / 2];
    static Double [] results = new Double[RECUR_LIMITS.length * ARRAY_SIZES.length];

    public static void main(String[] args)
    {
        Integer[] testArray;
        int index = 0;
        int randomInt;
        int recursionLimit = MIN_LIMIT;
        long startTime;
        long estimatedTime;

        /**
         * To convert time duration to seconds
         */
        double secondConv;

        /**
         * Fill the recursion limits' array with data.
         */
        int i = 0;
        while(recursionLimit <= MAX_LIMIT && i < RECUR_LIMITS.length)
        {
            RECUR_LIMITS[i] = recursionLimit;
            recursionLimit += 2;
            i++;
        }

        /**
         * Start the quicksort algorithm & assign data to the results array.
         */

        for (int j = 0; j < RECUR_LIMITS.length; j++)
        {

            /**
             * Begin quicksort algorithm
             */
            FHsort.setRecursionLimit(RECUR_LIMITS[j]);
            System.out.println("Recursion Limit: " + RECUR_LIMITS[j] + "\n");

            /**
             * Loop through the test array.
             */
            for (int testIndex = 0; testIndex < ARRAY_SIZES.length; testIndex++)
            {
                int testSize = ARRAY_SIZES[testIndex];
                Integer[] arrayOfInts = new Integer[testSize];

                /**
                 * Second loop for generating random data.
                 */
                for (int k = 0; k < testSize; k++)
                {
                    randomInt = (int) (Math.random() * testSize);
                    arrayOfInts[k] = randomInt;
                }

                /**
                 * Clone the test array
                 */
                testArray = arrayOfInts.clone();

                /**
                 * Capture the start time
                 */
                startTime = System.nanoTime();

                /**
                 * Call FHsort and perform calculations
                 */
                FHsort.quickSort(testArray);

                /**
                 * Stop the timer
                 */
                estimatedTime = System.nanoTime() - startTime;

                /**
                 * report algorithm time
                 */
                System.out.println("QuickSort Algorithm elapsed time: "
                        + " Array Size: " + testSize + ", "
                        + TimeConverter.convertTimeToString(estimatedTime)
                        + " = " + estimatedTime + "ns");

                /**
                 * Calculate and convert timed results from results array to seconds
                 */
                secondConv = (double)estimatedTime / 1e9;
                results [index * ARRAY_SIZES.length + testIndex] = secondConv;
            }

            /**
             * Print results
             */
            System.out.println();
        }
    }
}