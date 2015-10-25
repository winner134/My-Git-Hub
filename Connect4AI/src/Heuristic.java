
public class Heuristic {
	
	private final int MAX_EVAL = 999999;		// The maximum value returned for the best move possible (ensures a win)
	private final int MIN_EVAL = -999999;		// The minimum value returned for the worst possible moves;
	private int points;
	//private int opposingPlayerMove;
	private int last_Player;
	private int token_Sequence = 0;
	private int space = 0;
	
	public int evaluteMaxPlayerScore (SimpleBoard b, int move) {
		
		if (b.checkWin(move) == true) {
			
			return MAX_EVAL;
		}
		
		if (b.movelist.length() + 1 == b.cols.length * b.loc.length) {
			
			return 0;
		}
		
		
		
		for(int rows = 0; rows < b.loc.length ; rows++){
			
			last_Player = 1;
			token_Sequence = 0;
			space = 0;
			
			for(int cols = 0; cols < b.cols.length; cols++){
				
				System.out.println("1 - rows: " + rows + " cols: " + cols);
				points = points + awardPointsToLocation(b, b.loc[rows][cols], last_Player);
				last_Player = b.loc[rows][cols];
			}
		}
		
		for (int cols = 0; cols < b.cols.length; cols++) {
			
			last_Player = 1;
			token_Sequence = 0;
			space = 0;
			
			for (int rows = 0; rows < b.loc.length ; rows++) {
				System.out.println("2 - rows: " + rows + " cols: " + cols);
				points = points + awardPointsToLocation(b, b.loc[rows][cols], last_Player);
				last_Player = b.loc[rows][cols];
			}
		
		}
		
		// diagonals (top-left to bottom right), first half
		int startCol = 0;
		int startRow = 0;
		for(startRow = 0; startRow < b.loc.length; startRow++){
			
			last_Player = 1;
			token_Sequence = 0;
			space = 0;

			for(int rows = startRow, cols = startCol; rows < b.loc.length && cols < b.cols.length; rows++, cols++){
				System.out.println("3 - rows: " + rows + " cols: " + cols);
				points = points + awardPointsToLocation(b, b.loc[rows][cols], last_Player);
				last_Player = b.loc[rows][cols];
			}
		}
		
		// diagonals (top-left to bottom right), second half
		startRow = 0;
		for(startCol = 1; startCol< b.cols.length; ++startCol){
			
			last_Player = 1;
			token_Sequence = 0;
			space = 0;
			
			for(int rows = startRow, cols = startCol; rows < b.loc.length && cols < b.cols.length; rows++, cols++){
				System.out.println("4 - rows: " + rows + " cols: " + cols);
				points = points + awardPointsToLocation(b, b.loc[rows][cols], last_Player);
				last_Player = b.loc[rows][cols];
			}
		}
		
		// diagonals (top-right to bottom-left), first half
		startCol = 0;
		for(startRow = b.loc.length - 1; startRow > 0; startRow--){
			
			last_Player = 1;
			token_Sequence = 0;
			space = 0;

			for(int rows = startRow, cols = startCol; rows >= 0 && cols < b.cols.length; rows--, cols++){
				System.out.println("5 - rows: " + rows + " cols: " + cols);
				points = points + awardPointsToLocation(b, b.loc[rows][cols], last_Player);
				last_Player = b.loc[rows][cols];
			}
		}
		
		// diagonals (top-right to bottom-left), second half
		startRow = b.loc.length - 1;
		for(startCol = 1; startCol < b.cols.length; startCol++){

			last_Player = 1;
			token_Sequence = 0;
			space = 0;
			
			for(int rows = startRow, cols = startCol; rows >= 0 && cols < b.cols.length; rows--, cols++){
				System.out.println("6 - rows: " + rows + " cols: " + cols);
				points = points + awardPointsToLocation(b, b.loc[rows][cols], last_Player);
				last_Player = b.loc[rows][cols];
			}
		}
		
		return points;
	}
	
	public int awardPointsToLocation(SimpleBoard b, int currentToken, int player) {
		
		int otherToken;
		int points = 0;
		
		if (currentToken == 1 || currentToken == 2) {
			
			otherToken = 3 - currentToken;
			
			if (player == otherToken) {
				
				points = points + calcPoints(otherToken);
			}
		}
		
		else {
			
			if (player == 1 || player == 2) {
				
				space++;
				points = points + calcPoints(player);
				token_Sequence = 0;
			}
			
			else {
				
				token_Sequence = 0;
				space = 1;
			}
		}
		
		return points;
		
	}
	
	public int calcPoints (int token) {
		
		int pointsAdded;
		
		if (token_Sequence <= 1)
			pointsAdded = 0;
		
		else if (token_Sequence == 2)
			pointsAdded = 10 * space;
		
		else if (token_Sequence == 3)
			pointsAdded = 100 * space;
		
		else
			pointsAdded = 100000;
		
		return pointsAdded;
	}
	
	public int getPoints() {
		
		return points;
	}
	
	
}
