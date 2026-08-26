public class SparseMatrixTranspose {

    static int[][] sparse = {
        {4, 5, 4},
        {0, 1, 10},
        {1, 3, 20},
        {2, 2, 30},
        {3, 4, 40}
    };

    static int[][] transpose = new int[3][5];

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                transpose[j][i] = sparse[i][j];
            }
        }

        System.out.println("Original Triplet Matrix (5x3):");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%4d", sparse[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nTranspose of Triplet Matrix (3x5):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%4d", transpose[i][j]);
            }
            System.out.println();
        }
    }
}