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
//
//        // Any value that will be calculated more than once is calculated before iteration
//        ArrayList<Integer> sequences = new ArrayList<>();
//        int max = 0;
//        int strLength = STR.length();
//        boolean continueSequence;
//        char[] strArray = STR.toCharArray();
//        char startChar = STR.charAt(0);
//        int sequenceLength = sequence.length();
//
//        // Iterates through every letter in the sequence
//        for (int i = 0; i < sequenceLength; i++) {
//            continueSequence = false;
//            char currentChar = sequence.charAt(i);
//
//            // Goes through every sequence
//            for (int j = 0; j < sequences.size(); j++) {
//                int index = sequences.get(j);
//
//                // If the current letter matches the next letter in a given sequence, it is incremented
//                if (strArray[index % strLength] == currentChar) {
//                    sequences.set(j, index + 1);
//                    if (index % strLength == 0) {
//                        continueSequence = true;
//                    }
//                }
//                else {
//                    // If the current letter doesn't match, the sequence is terminated and totalled
//                    int length = index / strLength;
//                    if (length > max) {
//                        max = length;
//                    }
//                    sequences.remove(j); // I might want to move away from arraylists, this is linear time
//                    j--;
//                    if (sequences.isEmpty()) {
//                        break;
//                    }
//                }
//            }
//            // Adds a new sequence if the current letter is the start letter and it wouldn't repeat another sequence.
//            if (!continueSequence && currentChar == startChar) {
//                sequences.add(1);
//            }
//        }
//
//        // Iterates through any sequences left at the end and totals them.
//        if (!sequences.isEmpty()) {
//            for (Integer length : sequences) {
//                if (length / strLength > max) {
//                    max = length / strLength;
//                }
//            }
//        }
//        return max;

        int sequenceLength = sequence.length();
        long strHash = hashFunction(STR);
        long sequenceHash = hashFunction(sequence.substring(0, STR.length()));
        int radix = 116;
        long p = 2147483647;
        int[] map = createArray();
        int max = 0;
        long radixPower = (long) Math.pow(radix, STR.length() - 1);
        int strLength = STR.length();

        for (int i = 0; i < sequenceLength - strLength; i++) {
            if (strHash == sequenceHash) {
                int j = i;
                int localMax = 0;
                while (j < sequenceLength) {
                    sequenceHash = hashFunction(sequence.substring(j, j + strLength));
                    if (sequenceHash == strHash) {
                        j += strLength;
                        localMax++;
                        continue;
                    }
                    break;
                }
                if (localMax > max) {
                    max = localMax;
                }
                i = j;
            }
            if (i < sequenceLength) {
                sequenceHash = ((sequenceHash + p) - ((sequence.charAt(i) * radixPower) % p)) % p;
                sequenceHash = ((sequenceHash * radix) + sequence.charAt(i + strLength)) % p;
            }
        }
        return max;
    }


    public static long hashFunction(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * 116 + str.charAt(i)) % 2147483647;
        }
        return hash;
    }

    public static int[] createArray() {
        int[] array = new int[117];
        array['t'] = 4;
        array['g'] = 3;
        array['c'] = 2;
        array['a'] = 1;
        return array;
    }
}
