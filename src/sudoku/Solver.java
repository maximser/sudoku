package sudoku;

public class Solver {
	private Sudoku in,out;
	private int gues [][][] = new int [9][9][];
	private int outTable [][];

	public Solver(Sudoku input) {
		in = input;
		outTable = in.getAll();
	}

	public Sudoku solve() throws Exception{

		do{
			makeGues();
		}while(step());

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if(outTable[i][j]==0){
						for (int k = 0; k < 9; k++) {
							if(gues[i][j][k]==0){
								outTable[i][j]=k+1;
								Sudoku go = new Sudoku(outTable);
								Solver s = new Solver(go);
								try {
									out = s.solve();
									return out;
								} catch (Exception e) {

								}

							}
						}
					}
				}
			}
		check();
		out = new Sudoku(outTable);
		return out;
	}

	private void check() throws Exception{
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(outTable[i][j]==0) throw new Exception("not solved");
			}
		}
	}
	private void makeGues(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(outTable[i][j]==0){
					gues[i][j] = new int[9];
					for (int k = 0; k < 9; k++) {
						if(k!=j && outTable[i][k]!=0){
							gues[i][j][outTable[i][k]-1]++;//hor
						}
					}
					for (int k = 0; k < 9; k++) {
						if(k!=i && outTable[k][j]!=0){
							gues[i][j][outTable[k][j]-1]++;//vert
						}
					}
					sqrCheck(i, j);

				}
			}
		}
	}

	private void sqrCheck(int iin, int jin){
		int ii = iin/3;
		int jj = jin/3;
		for (int i = 3*ii; i < 3*(ii+1); i++) {
			for (int j = 3*jj; j < 3*(jj+1); j++) {
				if(outTable[i][j] != 0 && (jin!=j || iin!=i) )
					gues[iin][jin][outTable[i][j]-1]++;
			}
		}
	}

	private boolean step(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(outTable[i][j]==0){
					int lastpos = 0, temp =0;

					for (int j2 = 0; j2 < 9; j2++) {
						if(gues[i][j][j2]==0){
							lastpos = j2;
							temp++;
						}
					}
					if(temp==1){
						outTable[i][j] = lastpos+1;
						return true;
					}
				}
			}
		}


		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int h = 0,v=0,sq=0;
				if(outTable[i][j]==0){
					int curGuesh[] = new int[9];
					int curGuesv[] = new int[9];
					int curGuessq[] = new int[9];

					for (int z = 0; z < 9; z++) {
						if (z!=j && outTable[i][z]==0) {//есть gues
							h--;
							for (int k = 0; k < 9; k++) {//hor
								if(gues[i][j][k]==0 && gues[i][z][k]!=0){
									curGuesh[k]--;

								}
							}
						}
					}
					for (int z = 0; z < 9; z++) {
						if (z!=i && outTable[z][j]==0) {//есть gues
							v--;
							for (int k = 0; k < 9; k++) {//ver
								if(gues[i][j][k]==0 && gues[z][j][k]!=0){
									curGuesv[k]--;
								}
							}
						}
					}
			//////////////////////////////////////////////////////
					int ii = i/3;
					int jj = j/3;
					for (int i2 = 3*ii; i2 < 3*(ii+1); i2++) {
						for (int j2 = 3*jj; j2 < 3*(jj+1); j2++) {
							if(!(j!=j2 && i!=i2) && outTable[i2][j2] == 0){
								sq--;
								for (int k = 0; k < 9; k++) {//sqr
									if(gues[i][j][k]==0 && gues[i2][j2][k]!=0){
										curGuessq[k]--;
									}
								}
							}
						}
					}
			//////////////////////////////////////////////////////
					int temp = 0,pos=0;
					for (int k = 0; k < 9; k++) {
						if(gues[i][j][k]==0){
							if(	curGuesv[k]==v){
								temp++;
								pos=k+1;
							}
						}
					}
					if(temp==1){
						outTable[i][j] = pos;
//						System.out.println(this.toString());
						return true;
					}
					temp = 0;pos=0;
					for (int k = 0; k < 9; k++) {
						if(gues[i][j][k]==0){
							if(	curGuesh[k]==h){
								temp++;
								pos=k+1;
							}
						}
					}
					if(temp==1){
						outTable[i][j] = pos;
//						System.out.println(this.toString());
						return true;
					}
					temp = 0;pos=0;
					for (int k = 0; k < 9; k++) {
						if(gues[i][j][k]==0){
							if(	curGuessq[k]==sq){
								temp++;
								pos=k+1;
							}
						}
					}
					if(temp==1){
						outTable[i][j] = pos;
//						System.out.println(this.toString());
						return true;
					}

				}
			}
		}



		return false;
	}
	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < outTable.length; i++) {

			for (int j = 0; j < outTable[i].length; j++) {

				if(outTable[i][j] == 0){
					temp+=" ";
				}else{
					temp+=outTable[i][j];
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

}
