package sudoku;

public class Sudoku {
	private int [][] table = new int [9][9];
	public Sudoku(int [][] input) throws Exception {
		if (input.length == 9) {
			for (int i = 0; i < input.length; i++) {
				if (input[i].length == 9) {
					for (int j = 0; j < input[i].length; j++) {
						if (input[i][j]>=0 && input[i][j]<=9) {
							table[i][j] = input[i][j];
						}else{
							throw new Exception("number "+ input[i][j]);
						}
					}
				}else{
					throw new Exception("lenth "+ i +" is "+input[i].length);
				}
			}
		}else{
			throw new Exception("lenth is "+input.length);
		}
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				vertCheck(i, j);
				horCheck(i, j);
				sqrCheck(i, j);
			}
		}
	}

	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < table.length; i++) {

			for (int j = 0; j < table[i].length; j++) {

				if(table[i][j] == 0){
					temp+=" ";
				}else{
					temp+=table[i][j];
				}
				if ((j+1)%3==0 && j<8) {
					temp+=" | ";
				}
			}
			temp+="\n";
			if((i+1)%3==0 && i<8){
				temp+= "–––––––––––\n";
			}
		}
		return temp;
	}

	public int get(int i, int j){
		return table[i][j];
	}

	public int[][] getAll(){
		return table;
	}
	private void vertCheck(int i, int j) throws Exception{
		int temp = table[i][j];

		for (int k = 0; k < table.length; k++) {
			if(table[k][j] == 0) continue;
			if(i!=k && table[k][j] == temp)
				throw new Exception("vertCheck not pass "+ (i+1)+(j+1)+ "=="+ (k+1)+(j+1)+"\n" + this.toString());
		}
	}
	private void horCheck(int i, int j) throws Exception{
		int temp = table[i][j];
		for (int k = 0; k < table[i].length; k++) {
			if(table[i][k] == 0) continue;
			if(j!=k && table[i][k] == temp)
				throw new Exception("horCheck not pass "+ (i+1)+(j+1)+ "=="+ (i+1)+(k+1)+"\n" + this.toString());
		}
	}
	private void sqrCheck(int iin, int jin) throws Exception{
		int temp = table[iin][jin];
		int ii = iin/3;
		int jj = jin/3;
		for (int i = 3*ii; i < 3*(ii+1); i++) {
			for (int j = 3*jj; j < 3*(jj+1); j++) {
				if(table[i][j] == 0) continue;
				if(jin!=j && iin!=i && table[i][j] == temp)
					throw new Exception("sqrCheck not pass "+ (iin+1)+(jin+1) +"=="+ (i+1)+(j+1)+"\n" + this.toString());
			}
		}
	}


}
