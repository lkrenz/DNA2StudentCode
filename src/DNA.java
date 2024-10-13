import java.util.ArrayList;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {


    public static int STRCount(String sequence, String STR) {
        ArrayList<Integer> sequences = new ArrayList<>();
        int max = 0;
        int strLength = STR.length();
        boolean continueSequence;

        for (int i = 0; i < sequence.length(); i++) {
            continueSequence = false;
            char currentChar = sequence.charAt(i);

            for (int j = 0; j < sequences.size(); j++) {
                int index = sequences.get(j);
                if (STR.charAt(index % strLength) == currentChar) {
                    sequences.set(j, index + 1);
                    if (index % strLength == 0) {
                        continueSequence = true;
                    }
                }
                else {
                    int length = index / strLength;
                    if (length > max) {
                        max = length;
                    }
                    sequences.remove(j);
                    j--;
                    if (sequences.isEmpty() && j < 0) {
                        break;
                    }
                }
            }
            if (!continueSequence && currentChar == STR.charAt(0)) {
                sequences.add(1);
            }
        }
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
