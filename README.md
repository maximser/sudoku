[![Build Status](https://travis-ci.org/maximser/sudoku.svg?branch=master)](https://travis-ci.org/maximser/sudoku)
# sudoku

'sudoku solver' - solve basic 9x9 sudoku, using 3 differnt technics:

>1. Singles - the method is finding the table of individuals, i.e., cells in which only one figure and no other. Record this number in the cell and excluded from other cells in that row, column, and block

>2. Hidden singles - if the cell is a few candidates, but one of them is not found in any other cell in this row (column or block), then this candidate is called "hidden single"

>3. Backtracing - algorithm for finding all (or some) solutions to some computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons each partial candidate c ("backtracks") as soon as it determines that c cannot possibly be completed to a valid solution.

##Usage

```java
    int [][] table = {
				{2,0,0,0,7,0,0,3,8},
				{0,0,0,0,0,6,0,7,0},
				{3,0,0,0,4,0,6,0,0},
				{0,0,8,0,2,0,7,0,0},
				{1,0,0,0,0,0,0,0,6},
				{0,0,7,0,3,0,4,0,0},
				{0,0,4,0,8,0,0,0,9},
				{0,6,0,4,0,0,0,0,0},
				{9,1,0,0,6,0,0,0,2}
							};
    Sudoku q = new Sudoku(table);
		Solver s = new Solver(q);
		Sudoku res = s.solve();
		System.out.println(res.toString());
```
###output
```cmd
246 | 975 | 138
589 | 316 | 274
371 | 248 | 695
–––––––––––
498 | 621 | 753
132 | 754 | 986
657 | 839 | 421
–––––––––––
724 | 183 | 569
865 | 492 | 317
913 | 567 | 842
```
