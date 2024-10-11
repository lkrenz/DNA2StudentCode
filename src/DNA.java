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

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {

        ArrayList<Integer> sequences = new ArrayList<>();
        int max = 0;
        int strLength = STR.length();

        for (int i = 0; i  < sequence.length(); i++) {

            int j = 0;
            while (j >= 0 && j < sequences.size()) {
                if (STR.charAt(sequences.get(j) % strLength) == sequence.charAt(i)) {
                    sequences.set(j, sequences.get(j) + 1);
                }
                else {
                    int length = sequences.get(j) / strLength;
                    if (length > max) {
                        max = length;
                    }
                    sequences.remove(j);
                    j--;
                    if (j < 0) {
                        break;
                    }
                }
                j++;
            }
            if (sequence.charAt(i) == STR.charAt(0)) {
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
