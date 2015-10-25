/*
 * HumanPlayer.java
 *
 * Created on 
 */

/**
 *
 * @author  
 */
import java.io.*;



public class ComputerPlayer implements Player {
	
	int bestMove; // The coulmn number that represents the best move for the computer player
	int current_Move;
	int possible_Move;
	//int i;
	Heuristic heuristic;
	final int MAX_DEPTH = 5;		// The maximum number of levels searched before a cutoff
	final int INFIN = 999999999;	// Value representing the max/min value of alpha/beta
	int made = 0, reversed = 0;

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void go(SimpleBoard b) {
		// TODO Auto-generated method stub
		
		/** This method determines the play that this computer player makes on its turn */
		/** You need to write this part */
		
		heuristic = new Heuristic();
		
		// Perform min-max with alpha-beta on the board.  Alpha and beta initialised with -INF and +INF.
		alphaBeta(b, 0, 2, -INFIN, INFIN);
		
	}
	
	public int alphaBeta(SimpleBoard b, int searchDepth, int turn, int alpha, int beta) {
		System.out.println("Hi from alpha-beta");
		// If it is not the root node, swap the current players turn
		if(searchDepth > 0){
			turn = 3 - turn;
		}
		System.out.println("search Depth: " + searchDepth);
		if( searchDepth >= MAX_DEPTH || b.numTokens() == 0){
			System.out.println("Consulting heuristic");
			return heuristic.evaluteMaxPlayerScore(b, turn);
		}
		
		
		// Try each column on the board
		for(int i = 0; i < b.cols.length; i++){
			
			System.out.println("b.cols[i] for i = " + i + " is " + b.cols[i]);
			 if ((b.cols[i]==6)) 
				 continue;
				 
			System.out.println("i = " + i);
			System.out.println("Making a Move");
			made++;
			System.out.println("Made Count: " + made);
			b.Move(i);
			
			
			// A single case by successively negating the value returned by the node below
			// and always finding the maximum.
			System.out.println("Recursive Call to alphaBeta");
			System.out.println("depth parameter: " + (searchDepth + 1));
			System.out.println("turn parameter: " + turn);
			int newAlpha = -alphaBeta(b, searchDepth+1, turn, -beta, -alpha);
			if(newAlpha > alpha){
				System.out.println("newAlpha > alpha");
				alpha = newAlpha;
				bestMove = i;
			}
			
			// Reverse the move
			System.out.println("Reversing Move");
			reversed++;
			System.out.println("Reverse Count: " + reversed);
			b.undoLastMove(i);
			
			// Cut-off condition - another node in the tree is guaranteed to be better
			if(beta <= alpha){
				break;
			}
		}
		
		// If we are at the root node, we can perform the best move
		
		if(searchDepth == 0){
			b.Move(bestMove);
		}

		return alpha;
		
	}

	@Override
	public void setMove(int col) {
		// TODO Auto-generated method stub
		
		/** You do not need to implement this method for a computer player. This is used for the HumanPlayer. */
		
		
	}
	
	

}
