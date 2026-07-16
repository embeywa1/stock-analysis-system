Data Analysis Project: Stock Price Processing
Language: Java
Environment: Kali Linux Terminal
1. Project Overview
This project involves processing 10 days of opening stock prices stored in both a standard primitive array (float[]) and a dynamic collection (ArrayList<Float>). The program implements mathematical operations to find the average, maximum, and frequency of specific prices, as well as a cumulative financial trend analysis.
2. Program Implementation (Code)
import java.util.ArrayList;

public class StockAnalysis {

    public static void main(String[] args) {
        // Input: 10 days of opening stock prices as an array
        float[] stockArray = {150.50f, 152.20f, 149.80f, 155.00f, 152.20f, 158.30f, 160.10f, 152.20f, 155.00f, 162.40f};
        
        // Input: Populating the ArrayList for same-period data
        ArrayList<Float> stockList = new ArrayList<>();
        for (float price : stockArray) {
            stockList.add(price);
        }

        // --- Output Section ---
        System.out.println("Average (Array): " + calculateAveragePrice(stockArray));
        System.out.println("Average (ArrayList): " + calculateAveragePrice(stockList));
        System.out.println("Max Price (Array): " + findMaximumPrice(stockArray));
        System.out.println("Max Price (ArrayList): " + findMaximumPrice(stockList));
        
        float target = 152.20f;
        System.out.println("Occurrences of " + target + ": " + countOccurrences(stockArray, target));
        System.out.println("Cumulative Sum: " + computeCumulativeSum(stockList));
    }

    // --- TASK 1: Average Stock Price (Overloaded for both types) ---
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;
        for (float price : prices) sum += price;
        return sum / prices.length;
    }

    public static float calculateAveragePrice(ArrayList<Float> prices) {
        float sum = 0;
        for (float price : prices) sum += price;
        return sum / prices.size();
    }

    // --- TASK 2: Maximum Stock Price (Overloaded for both types) ---
    public static float findMaximumPrice(float[] prices) {
        float max = prices[0];
        for (float price : prices) {
            if (price > max) max = price;
        }
        return max;
    }

    public static float findMaximumPrice(ArrayList<Float> prices) {
        float max = prices.get(0);
        for (float price : prices) {
            if (price > max) max = price;
        }
        return max;
    }

    // --- TASK 3: Occurrence Count ---
    public static int countOccurrences(float[] prices, float target) {
        int count = 0;
        for (float price : prices) {
            if (price == target) count++;
        }
        return count;
    }

    // --- TASK 4: Cumulative Sum (Returns new ArrayList) ---
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> cumulativeList = new ArrayList<>();
        float currentSum = 0;
        for (float price : prices) {
            currentSum += price;
            cumulativeList.add(currentSum);
        }
        return cumulativeList;
    }
}


3. Detailed Logic Explanation
Task 1: Average Stock Price 
To satisfy the rubric, I used Method Overloading.
    • Array logic: Uses .length to determine the divisor.
    • ArrayList logic: Uses .size() to determine the divisor. Both methods iterate through the data to compute the total sum and return the mean as a float.
Task 2: Maximum Stock Price 
I implemented findMaximumPrice for both structures. The algorithm initializes a max variable with the first element of the collection and compares it against every subsequent value. If a larger value is found, the max variable is updated.
Task 3: Occurrence Count 
The countOccurrences method accepts the array and a target float. It uses a for-each loop to traverse the array, incrementing a counter every time the current element matches the target. This provides an exact frequency of a specific price point.
Task 4: Cumulative Sum 
The computeCumulativeSum method creates a brand-new ArrayList<Float>. It maintains a runningTotal. During each iteration of the input list, the current price is added to the runningTotal, and that intermediate sum is added to the new list. This ensures the original stock data remains unmodified.

4. Execution in Kali Linux
To compile and run this code in the Kali terminal:
    1. Command: javac StockAnalysis.java
    2. Command: java StockAnalysis
Output Screenshot
