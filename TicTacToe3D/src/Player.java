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
    

    public int getScore(GameState gameState) {
        
        int score = 0 ;
        int inLinePlayer ;
        int inLineOpponent ;
        int inLineEmpty ;
        
        //check rows
        for(int l=0 ; l<SIZE ;l++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int r=0 ; r<SIZE ;r++){
                for(int c=0 ; c<SIZE ; c++) {
                    if(gameState.at(r,c,l)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(r,c,l)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                }
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        }
        
        //check columns
        for(int l=0 ; l<SIZE ;l++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int c=0 ; c<SIZE ;c++){
                for(int r=0 ; r<SIZE ; r++) {
                    if(gameState.at(r,c,l)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(r,c,l)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                }
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        }
        //check multi-layer straight lines
        for(int r=0 ; r<SIZE ;r++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int c=0 ; c<SIZE ;c++){
                for(int l=0 ; l<SIZE ; l++) {
                    if(gameState.at(r,c,l)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(r,c,l)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                }
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        }
        //check diagonals1
        for(int l=0 ; l<SIZE ;l++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(i,i,l)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(i,i,l)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(SIZE-1-i,i,l)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(SIZE-1-i,i,l)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        }
        //check diagonal 2
        for(int r=0 ; r<SIZE ;r++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(r,i,i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(r,i,i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(r,i,SIZE-1-i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(r,i,SIZE-1-i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        }
        //check diagonal 3
        for(int c=0 ; c<SIZE ;c++){
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(i,c,i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(i,c,i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
            inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(i,c,SIZE-1-i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(i,c,SIZE-1-i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        }
        
        //check main diagonal 1
        inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(i,i,i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(i,i,i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
            
        //check main diagonal 2
        inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(SIZE-1-i,i,i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(SIZE-1-i,i,i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
        
        //check main diagonal 3
        inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(i,SIZE-1-i,i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(i,SIZE-1-i,i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }
            
        //check main diagonal 4
        inLinePlayer = 0 ;
            inLineOpponent = 0 ;
            inLineEmpty = 0 ;
            for(int i=0 ; i<SIZE ;i++){
                
                    if(gameState.at(i,i,SIZE-1-i)== PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(gameState.at(i,i,SIZE-1-i)== OPPONENT){
                        inLineOpponent++ ;  
                    }
                    else{
                        inLineEmpty++ ;
                    }
                
                score+= getInlineScore(inLinePlayer,inLineEmpty, inLineOpponent);
            }

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
