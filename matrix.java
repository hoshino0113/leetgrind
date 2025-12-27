//define a matrix
//A matrix is fixed sized after its definition
// Where 3 is the #of rows and 4 is the #of columns
int[][] matrix = new int[3][4];

//modify an element
matrix[0][1] = 6;

//access a specific element
//row 0, column 1
int element = matrix[0][1];

//define elements for the matrix, it will overwrite the 6 we defined earlier
// matrix should have values
/* 0 1 2 3
   4 5 6 7
   8 9 10 11 
after the modifications*/

int val = 0;
for (int row = 0; row < matrix.length; row++) {
    for (int col = 0; col < matrix[row].length; col++) {
        matrix[row][col] = val;
        val++;
    }
}

//traverse all the element and print them out
//this syntax is only good if we don't need the index,
//if we need the syntax, we need to use the above syntax
for (int[] row : matrix) {
    for (int x : row) {
        System.out.print(x + " ");
    }
}