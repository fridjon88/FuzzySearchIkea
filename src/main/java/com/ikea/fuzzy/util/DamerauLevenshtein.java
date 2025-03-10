package com.ikea.fuzzy.util;

public class DamerauLevenshtein {

    /**
     * Computes the Damerau-Levenshtein distance between two strings.
     * This distance metric includes:
     * 1. Insertion
     * 2. Deletion
     * 3. Substitution
     * 4. Transposition (swapping two adjacent characters)
     *
     * @param a First string
     * @param b Second string
     * @return The Damerau-Levenshtein distance
     */
    public static int compute(String a, String b) {
        // Create a 2D array to store the distances between substrings
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        // Initialize the first row and first column of the matrix
        // The first row represents distances between empty string and substrings of 'b'
        // The first column represents distances between empty string and substrings of 'a'
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j; // If 'a' is empty, all characters of 'b' need to be inserted
                } else if (j == 0) {
                    dp[i][j] = i; // If 'b' is empty, all characters of 'a' need to be deleted
                } else {
                    // Compute cost of substitution: 0 if characters are the same, 1 if they are different
                    int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

                    // Minimum of:
                    // 1. Deletion (dp[i-1][j] + 1)
                    // 2. Insertion (dp[i][j-1] + 1)
                    // 3. Substitution (dp[i-1][j-1] + cost)
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost);

                    // Check for transposition (swapping two adjacent characters)
                    // Only check if i > 1 and j > 1 to avoid out-of-bounds errors
                    if (i > 1 && j > 1 &&
                            a.charAt(i - 1) == b.charAt(j - 2) &&
                            a.charAt(i - 2) == b.charAt(j - 1)) {
                        // If characters a[i-1] and a[i-2] are swapped with b[j-1] and b[j-2],
                        // the cost is the same as dp[i-2][j-2] + 1 (for the transposition)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + cost);
                    }
                }
            }
        }

        // The final element dp[a.length()][b.length()] contains the Damerau-Levenshtein distance
        return dp[a.length()][b.length()];
    }
}