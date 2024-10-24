

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

        // Map to store integer values for A C G and T
        int[] hashArr = createArray();

        long strHash = hashFunction(STR, hashArr);
        long sequenceHash = hashFunction(sequence.substring(0, STR.length()), hashArr);
        int max = 0;

        // Precalculated to save on efficiency
        int sequenceLength = sequence.length();
        int strLength = STR.length();

        // Loops through the sequence
        for (int i = 0; i < sequenceLength - strLength; i++) {

            // If hashes match, enters another loop to find length of sequence
            if (strHash == sequenceHash) {
                int j = i;
                int localMax = 0;
                while (j < sequenceLength) {

                    // Fully recalculating hash is more efficient than shifting over one by one
                    sequenceHash = hashFunction(sequence.substring(j, j + strLength), hashArr);

                    // If another match is found, we move forward the length of STR
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

                // Go to the end of the last verified STR
                i = j;
            }

            // Update sequenceHash
            if (i < sequenceLength) {
                sequenceHash = sequenceHash - ((long)hashArr[sequence.charAt(i)] << ((strLength-1) * 2));
                sequenceHash = (sequenceHash << 2) + hashArr[sequence.charAt(i + strLength)];
            }
        }
        return max;
    }


    // Hashes a given string using the values the characters map to
    // We don't need a modulus statement as values will always be less than a long
    public static long hashFunction(String str, int[] hashArr) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 2) + hashArr[str.charAt(i)];
        }
        return hash;
    }


    // Initializes a map for the letters in the DNA sequence
    public static int[] createArray() {
        int[] array = new int[117];
        array['T'] = 3;
        array['G'] = 2;
        array['C'] = 1;
        array['A'] = 0;
        return array;
    }
}
