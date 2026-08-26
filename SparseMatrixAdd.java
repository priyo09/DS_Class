public class SparseMatrixAdd {

    static int[][] A = {
        {5, 0, 0, 0},
        {0, 0, 8, 0},
        {0, 0, 0, 0},
        {0, 3, 0, 0}
    };

    static int[][] B = {
        {0, 7, 0, 0},
        {0, 0, 2, 0},
        {0, 0, 0, 0},
        {9, 0, 0, 0}
    };

    public static void main(String[] args) {

        int c1 = 0, c2 = 0;

        // Count non-zero elements of A
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (A[i][j] != 0)
                    c1++;
            }
        }

        // Count non-zero elements of B
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (B[i][j] != 0)
                    c2++;
            }
        }

        int[][] sA = new int[c1 + 1][3];
        int[][] sB = new int[c2 + 1][3];
        int[][] sC = new int[c1 + c2 + 1][3];

        // Converting A into 3-tuple
        sA[0][0] = 4;
        sA[0][1] = 4;
        sA[0][2] = c1;

        int k = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (A[i][j] != 0) {
                    sA[k][0] = i;
                    sA[k][1] = j;
                    sA[k][2] = A[i][j];
                    k++;
                }
            }
        }

        // Converting B into 3-tuple
        sB[0][0] = 4;
        sB[0][1] = 4;
        sB[0][2] = c2;

        k = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (B[i][j] != 0) {
                    sB[k][0] = i;
                    sB[k][1] = j;
                    sB[k][2] = B[i][j];
                    k++;
                }
            }
        }

        System.out.println("3-Tuple of Matrix A:");
        System.out.println("Row\tCol\tValue");

        for (int i = 0; i <= c1; i++) {
            System.out.println(
                sA[i][0] + "\t" +
                sA[i][1] + "\t" +
                sA[i][2]
            );
        }

        System.out.println("\n3-Tuple of Matrix B:");
        System.out.println("Row\tCol\tValue");

        for (int i = 0; i <= c2; i++) {
            System.out.println(
                sB[i][0] + "\t" +
                sB[i][1] + "\t" +
                sB[i][2]
            );
        }

        // Addition of 3-tuples
        int i = 1, j = 1;
        k = 1;

        sC[0][0] = 4;
        sC[0][1] = 4;

        while (i <= c1 && j <= c2) {

            if (sA[i][0] == sB[j][0] &&
                sA[i][1] == sB[j][1]) {

                sC[k][0] = sA[i][0];
                sC[k][1] = sA[i][1];
                sC[k][2] = sA[i][2] + sB[j][2];

                i++;
                j++;
                k++;
            }

            else if (sA[i][0] < sB[j][0] ||
                    (sA[i][0] == sB[j][0] &&
                     sA[i][1] < sB[j][1])) {

                sC[k][0] = sA[i][0];
                sC[k][1] = sA[i][1];
                sC[k][2] = sA[i][2];

                i++;
                k++;
            }

            else {
                sC[k][0] = sB[j][0];
                sC[k][1] = sB[j][1];
                sC[k][2] = sB[j][2];

                j++;
                k++;
            }
        }

        while (i <= c1) {
            sC[k][0] = sA[i][0];
            sC[k][1] = sA[i][1];
            sC[k][2] = sA[i][2];

            i++;
            k++;
        }

        while (j <= c2) {
            sC[k][0] = sB[j][0];
            sC[k][1] = sB[j][1];
            sC[k][2] = sB[j][2];

            j++;
            k++;
        }

        sC[0][2] = k - 1;

        System.out.println("\nResultant 3-Tuple:");
        for (i = 0; i < k; i++) {
            System.out.println(
                sC[i][0] + "\t" +
                sC[i][1] + "\t" +
                sC[i][2]
            );
        }
    }
}