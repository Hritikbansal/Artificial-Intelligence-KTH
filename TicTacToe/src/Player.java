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
        
        int value ;
        int alpha = Integer.MIN_VALUE  ;
        int bestValue = Integer.MIN_VALUE ;
        int bestNextStateInd = -1  ;
 
        
        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }
        
        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        for(int i=0 ; i<nextStates.size() ; i++){
            
        }
        
        for(int i=0 ; i<nextStates.size() ; i++){
            System.err.println("NextState No: "+i);
            
            value = MiniMaxPruning(nextStates.elementAt(i),6,alpha,Integer.MAX_VALUE,OPPONENT) ;
            System.err.println("Best Value Acquired:"+value);
            
            if(value > bestValue){
                bestValue = value ;
                bestNextStateInd = i ;
                alpha = Math.max(alpha, bestValue) ;
            }

        }
        if(bestNextStateInd == -1){
            System.err.println("No best state found");
            return null ;
        }
        return nextStates.elementAt(bestNextStateInd) ;
    }    
    
//    public int MiniMax(GameState gameState, int player){
//        Vector<GameState> nextStates = new Vector<>();
//        gameState.findPossibleMoves(nextStates);
//        int bestPossible ;
//        int value ;
//      
//        if(gameState.isEOG())
//            return Evaluation(gameState) ;
//        
//        else if(player == PLAYER){
//            bestPossible = Integer.MIN_VALUE ;
//            for(int i=0 ; i<nextStates.size() ; i++){
//                value = MiniMax(nextStates.elementAt(i), OPPONENT) ;
//                bestPossible = Math.max(bestPossible, value) ;
//                if(bestPossible > 1){
//                break;
//                }
//            }
//            return bestPossible ;
//        }
//        
//        else if(player == OPPONENT){
//            bestPossible = Integer.MAX_VALUE ;
//            for(int i=0 ; i<nextStates.size() ; i++){
//                value = MiniMax(nextStates.elementAt(i), PLAYER) ;
//                bestPossible = Math.min(bestPossible, value) ;
//                if(bestPossible < -1){
//                break;
//                }
//            }
//            return bestPossible ;
//            
//        }
//        else{
//            return Integer.MIN_VALUE ;
//        }
//                
//    }
        public int Evaluation (GameState gameState){
//        System.err.println("Player.Evaluation()");
//        if(!(gameState.isOWin()||gameState.isXWin())) return 0 ;
//        else if((gameState.isXWin() && PLAYER == Constants.CELL_X) || (gameState.isOWin() && PLAYER == Constants.CELL_O)) return 1 ;
//        else return -1 ;

            return getScore(gameState);

    }
    
    public int MiniMaxPruning(GameState gameState,int depth, int alpha, int beta, int player){
        
        Vector<GameState> nextStates = new Vector<>();
        gameState.findPossibleMoves(nextStates);
        int bestPossible ;
        int value ;
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
    

    public int getScore(GameState gameState) {
        
        int score = 0 ;
        int inLinePlayer ;
        int inLineOpponent ;
        int inLineEmpty ;
        
        //check rows
        for(int i=0 ; i<SIZE ;i++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int j=0 ; j<SIZE ; j++) {
                if(gameState.at(i,j)== PLAYER){
                    inLinePlayer++ ;  
                }
                else if(gameState.at(i,j)== OPPONENT){
                    inLineOpponent++ ;  
                }
                else{
                    inLineEmpty++ ;
                }
            }
            score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
        }
        
        //check columns
        for(int i=0 ; i<SIZE ;i++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int j=0 ; j<SIZE ; j++) {
                if(gameState.at(j,i)== PLAYER){
                    inLinePlayer++ ;  
                }
                else if(gameState.at(j,i)== OPPONENT){
                    inLineOpponent++ ;  
                }
                else{
                    inLineEmpty++ ;
                }
            }
            score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
        }
        //check diagonal1
        inLinePlayer = 0 ;
        inLineOpponent = 0 ;
        inLineEmpty = 0 ;
        for(int j=0 ; j<SIZE ; j++) {
            if(gameState.at(j,j)== PLAYER){
                inLinePlayer++ ;  
            }
            else if(gameState.at(j,j)== OPPONENT){
                inLineOpponent++ ;  
            }
            else{
                inLineEmpty++ ;
            }
        }
        score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
        //check diagonal2
        inLinePlayer = 0 ;
        inLineOpponent = 0 ;
        inLineEmpty = 0 ;
        for(int j=0 ; j<SIZE ; j++) {
            if(gameState.at(j,SIZE-1-j)== PLAYER){
                inLinePlayer++ ;  
            }
            else if(gameState.at(j,SIZE-1-j)== OPPONENT){
                inLineOpponent++ ;  
            }
            else{
                inLineEmpty++ ;
            }
        }
        score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);

        return score ;
    }
    
    public int getInlineScore(int player, int empty, int opponent){
        if(player == 4) return 1000 ;
        else if(player == 3 && empty == 1) return 100 ;
        else if(player == 2 && empty == 2) return 10 ;
        else if(player == 1 && empty == 0) return 1 ;
        else if(opponent == 4) return -1000 ;
        else if(opponent == 3 && empty == 1) return -100 ;
        else if(opponent == 2 && empty == 2) return -10 ;
        else if(player == 1 && empty == 0) return -1 ;
        else return 0 ;
        
    }
    public static void main(String[] args) {
        
    }
}
