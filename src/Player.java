import java.util.*;

public class Player {
    /**
     * Performs a move
     *
     * @param gameState
     *            the current state of the board
     * @param deadline
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
    int SIZE = 4 ;
    int PLAYER ;
    int OPPONENT ;
    
    
    public GameState play(final GameState gameState, final Deadline deadline) {
//        System.err.print(gameState.toString(Constants.CELL_O));
        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);
        PLAYER = gameState.getNextPlayer() ;
        OPPONENT = (PLAYER == Constants.CELL_X) ? Constants.CELL_O : Constants.CELL_X;
        System.err.println("Size of next State" + nextStates.size());
        int value ;
        int bestValue = Integer.MIN_VALUE ;
        int bestNextStateInd = -1  ;
        System.err.println("123");
        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }
        System.err.println("nextState Size" + nextStates.size());
        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        for(int i=0 ; i<nextStates.size() ; i++){
            System.err.println(i);
//            value =1;
            value = MiniMaxPruning(nextStates.elementAt(i),2,Integer.MIN_VALUE,Integer.MAX_VALUE,gameState.getNextPlayer()) ;
            if(value > bestValue){
                bestValue = value ;
                bestNextStateInd = i ;
            }
        }
        if(bestNextStateInd == -1){
            System.err.println("No best state found");
            return null ;
        }
        return nextStates.elementAt(bestNextStateInd) ;
    }    
    
    public int MiniMax(GameState gameState, int player){
        Vector<GameState> nextStates = new Vector<>();
        gameState.findPossibleMoves(nextStates);
        int bestPossible ;
        int value ;
      
        if(gameState.isEOG())
            return Evaluation(gameState) ;
        
        else if(player == PLAYER){
            bestPossible = Integer.MIN_VALUE ;
            for(int i=0 ; i<nextStates.size() ; i++){
                value = MiniMax(nextStates.elementAt(i), OPPONENT) ;
                bestPossible = Math.max(bestPossible, value) ;
            }
            return bestPossible ;
        }
        
        else if(player == OPPONENT){
            bestPossible = Integer.MAX_VALUE ;
            for(int i=0 ; i<nextStates.size() ; i++){
                value = MiniMax(nextStates.elementAt(i), PLAYER) ;
                bestPossible = Math.min(bestPossible, value) ;
            }
            return bestPossible ;
        
        }
        else{
            return Integer.MIN_VALUE ;
        }
                
    }
    
    public int MiniMaxPruning(GameState gameState,int depth, int alpha, int beta, int player){
        
        Vector<GameState> nextStates = new Vector<>();
        gameState.findPossibleMoves(nextStates);
        int bestPossible ;
        int value ;
        System.err.println("Inside MinMax Depth=   "+depth) ;
        if(depth ==0 ||gameState.isEOG())
            return Evaluation(gameState) ;
        
        else if(player == PLAYER){
            bestPossible = Integer.MIN_VALUE ;
            for(int i=0 ; i<nextStates.size() ; i++){
                value = MiniMaxPruning(nextStates.elementAt(i),depth-1, alpha, beta, OPPONENT) ;
                bestPossible = Math.max(bestPossible, value) ;
                alpha = Math.max(bestPossible,alpha) ;
                if(beta<=alpha) break ;
            }
            return bestPossible ;
        }
        
        else if(player == OPPONENT){
            bestPossible = Integer.MAX_VALUE ;
            for(int i=0 ; i<nextStates.size() ; i++){
                value = MiniMaxPruning(nextStates.elementAt(i),depth-1, alpha, beta,PLAYER) ;
                bestPossible = Math.min(bestPossible, value) ;
                beta = Math.min(bestPossible, beta) ;
                if(beta<=alpha) break ;
            }
            return bestPossible ;
        
        }
        else
            return Integer.MIN_VALUE ;
                
    }
    
    public int Evaluation (GameState gameState){
//        System.err.println("Player.Evaluation()");
        
        
        return (getScore(gameState, PLAYER)-getScore(gameState, OPPONENT)) ;
        
    }
    public int getScore(GameState gameState , int player) {
        
        int score = 0 ;
        boolean full;
        
        //check rows
        for(int i=0 ; i<SIZE ;i++){
            full = true;
            for(int j=0 ; j<SIZE ; j++) {
                if(gameState.at(i,j)!= player){
                    full = false ;
                    break ;
                }
            }
            if(full) score++ ;
        }
        
        //check columns
        for(int i=0 ; i<SIZE ;i++){
            full = true ;
            for(int j=0 ; j<SIZE ; j++) {
                if(gameState.at(j,i)!= player){
                    full = false ;
                    break ;
                }
            }
            if(full) score++ ;
        }
        //check diagonal1
        full = true ;
        for(int i=0 ; i<SIZE ;i++){
            if(gameState.at(i,i)!= player){
                full = false ;
                break ;
            }
        }
        if(full) score++ ;
        
        //check diagonal2
        full = true ;
        for(int i=0 ; i<SIZE ;i++){
            if(gameState.at(SIZE-i-1,i)!= player){
                full = false ;
                break ;
            }
        }
        if(full) score++ ;
        
        return score ;
    }
}
