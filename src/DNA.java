import java.util.ArrayList;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Liam Krenz
 *</p>
 */

public class DNA {

    public static int STRCount(String sequence, String STR) {

        // Any value that will be calculated more than once is calculated before iteration
        ArrayList<Integer> sequences = new ArrayList<>();
        int max = 0;
        int strLength = STR.length();
        boolean continueSequence;
        char[] strArray = STR.toCharArray();
        char startChar = STR.charAt(0);


        // Iterates through every letter in the sequence
        for (int i = 0; i < sequence.length(); i++) {
            continueSequence = false;
            char currentChar = sequence.charAt(i);

            // Goes through every sequence
            for (int j = 0; j < sequences.size(); j++) {
                int index = sequences.get(j);

                // If the current letter matches the next letter in a given sequence, it is incremented
                if (strArray[index % strLength] == currentChar) {
                    sequences.set(j, index + 1);
                    if (index % strLength == 0) {
                        continueSequence = true;
                    }
                }
                else {
                    // If the current letter doesn't match, the sequence is terminated and totalled
                    int length = index / strLength;
                    if (length > max) {
                        max = length;
                    }
                    sequences.remove(j); // I might want to move away from arraylists, this is linear time
                    j--;
                    if (sequences.isEmpty()) {
                        break;
                    }
                }
            }
            // Adds a new sequence if the current letter is the start letter and it wouldn't repeat another sequence.
            if (!continueSequence && currentChar == startChar) {
                sequences.add(1);
            }
        }

        // Iterates through any sequences left at the end and totals them.
        if (!sequences.isEmpty()) {
            for (Integer length : sequences) {
                if (length / strLength > max) {
                    max = length / strLength;
                }
            }
        }
        return max;
    }
}
