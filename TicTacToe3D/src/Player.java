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
    Hashtable<String, Integer> repeatedStates = new Hashtable<>() ;
    int SIZE = 4 ;
    int PLAYER ;
    int OPPONENT ;
    int CHECK_STATES_NO ;
    int startTime = 0 ;
    
    int[][] lines = {{0, 16, 32, 48}, {1, 17, 33, 49}, {2, 18, 34, 50}, {3, 19, 35, 51}, {4, 20, 36, 52}, {5, 21, 37, 53}, {6, 22, 38, 54}, {7, 23, 39, 55}, {8, 24, 40, 56}, {9, 25, 41, 57}, {10, 26, 42, 58}, {11, 27, 43, 59}, {12, 28, 44, 60}, {13, 29, 45, 61}, {14, 30, 46, 62}, {15, 31, 47, 63}, {0, 1, 2, 3}, {16, 17, 18, 19}, {32, 33, 34, 35}, {48, 49, 50, 51}, {4, 5, 6, 7}, {20, 21, 22, 23}, {36, 37, 38, 39}, {52, 53, 54, 55}, {8, 9, 10, 11}, {24, 25, 26, 27}, {40, 41, 42, 43}, {56, 57, 58, 59}, {12, 13, 14, 15}, {28, 29, 30, 31}, {44, 45, 46, 47}, {60, 61, 62, 63}, {0, 4, 8, 12}, {16, 20, 24, 28}, {32, 36, 40, 44}, {48, 52, 56, 60}, {1, 5, 9, 13}, {17, 21, 25, 29}, {33, 37, 41, 45}, {49, 53, 57, 61}, {2, 6, 10, 14}, {18, 22, 26, 30}, {34, 38, 42, 46}, {50, 54, 58, 62}, {3, 7, 11, 15}, {19, 23, 27, 31}, {35, 39, 43, 47}, {51, 55, 59, 63}, {0, 17, 34, 51}, {3, 18, 33, 48}, {4, 21, 38, 55}, {7, 22, 37, 52}, {8, 25, 42, 59}, {11, 26, 41, 56}, {12, 29, 46, 63}, {15, 30, 45, 60}, {0, 20, 40, 60}, {12, 24, 36, 48}, {1, 21, 41, 61}, {13, 25, 37, 49}, {2, 22, 42, 62}, {14, 26, 38, 50}, {3, 23, 43, 63}, {15, 27, 39, 51}, {0, 5, 10, 15}, {12, 9, 6, 3}, {16, 21, 26, 31}, {28, 25, 22, 19}, {32, 37, 42, 47}, {44, 41, 38, 35}, {48, 53, 58, 63}, {60, 57, 54, 51}, {0, 21, 42, 63}, {12, 25, 38, 51}, {3, 22, 41, 60}, {15, 26, 37, 48}} ;
    
    int Heuristic[][] = {
        {     0,   -10,  -100, -1000 , -10000},
        {    10,     0,     0,     0 ,      0},
        {   100,     0,     0,     0 ,      0},
        {  1000,     0,     0,     0 ,      0},
        { 10000,     0,     0,     0 ,      0}
        }; 
    
    public GameState play(final GameState gameState, final Deadline deadline) {
//        System.err.print(gameState.toString(Constants.CELL_O));

        
        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);
        nextStates = SortStates(nextStates) ;
        PLAYER = gameState.getNextPlayer() ;
        OPPONENT = (PLAYER == Constants.CELL_X) ? Constants.CELL_O : Constants.CELL_X;
        
        int value ;
        int bestValue = Integer.MIN_VALUE ;
        int bestNextStateInd = -1  ;
        int alpha = Integer.MIN_VALUE ;
        int depth =1 ;
        
        int nextStateSize = nextStates.size() ;
        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }
//        CHECK_STATES_NO = nextStateSize ;
        if(nextStateSize>45) depth =1 ;
        else if(nextStateSize>35) depth =2 ;
        else if(nextStateSize>20) depth =3 ;
        else if(nextStateSize>10) depth =4 ;
        else if(nextStateSize>0) depth =5 ;
//        depth = 2 ;
//        CHECK_STATES_NO =  ;
        System.err.println("Play has begun");
        
        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        for(int i=0 ; i<nextStateSize; i++){
            System.err.println("Time Remaining"+deadline.timeUntil());
//            if(deadline.timeUntil()<100000000) break ;
            value = MiniMaxPruning(nextStates.elementAt(i),depth,alpha,Integer.MAX_VALUE,OPPONENT,deadline) ;
//            System.err.println("Best Value Acquired:"+value);
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
        System.err.println("returning the next state with index"+bestNextStateInd);
        return nextStates.elementAt(bestNextStateInd) ;
    }    
    


    
    public int MiniMaxPruning(GameState gameState,int depth, int alpha, int beta, int player, Deadline deadline){
        if(depth ==0 ||gameState.isEOG()){
            return Evaluation(gameState) ;
        }
        Vector<GameState> nextStates = new Vector<>();
        gameState.findPossibleMoves(nextStates);

        int bestPossible ;
        int value ;
        
        if(player == PLAYER){
            bestPossible = Integer.MIN_VALUE ;
            for(int i=0 ; i<nextStates.size() ; i++){
                value = MiniMaxPruning(nextStates.elementAt(i),depth-1, alpha, beta, OPPONENT,deadline) ;
                bestPossible = Math.max(bestPossible, value) ;
                alpha = Math.max(bestPossible,alpha) ;
                if(beta<=alpha) {
//                    System.err.println("pruned at depth:"+depth);
                    break ;
                }

            }
            return bestPossible ;
        }
        
        else if(player == OPPONENT){
            bestPossible = Integer.MAX_VALUE ;
            for(int i=0 ; i<nextStates.size(); i++){
                value = MiniMaxPruning(nextStates.elementAt(i),depth-1, alpha, beta,PLAYER,deadline) ;
                bestPossible = Math.min(bestPossible, value) ;
                beta = Math.min(bestPossible, beta) ;
                if(beta<=alpha) {
                    break ;
                }
            }
            return bestPossible ;
        
        }
        else
            return Integer.MIN_VALUE;
                
    }
    
        public int Evaluation (GameState gameState){
            int score = 0 ;
            int inLinePlayer ;
            int inLineOpponent ;
            int mark ;
            String gameString = GameStateToString(gameState) ;
            if(repeatedStates.get(gameString)!= null) return repeatedStates.get(gameString) ;
            for(int i=0 ; i<lines.length ;i++){
                inLinePlayer = 0 ;
                inLineOpponent = 0 ;
                for(int j=0 ; j<SIZE ; j++){
                    mark = gameState.at(lines[i][j]) ;
                    if(mark == PLAYER){
                        inLinePlayer++ ;  
                    }
                    else if(mark== OPPONENT){
                        inLineOpponent++ ;  
                    }
                }
                score+= Heuristic[inLinePlayer][inLineOpponent] ;
                
            }
            repeatedStates.put(gameString, score) ;
            return score ;

    }

        public class stateComparator implements Comparator<GameState>{

    //@Override
        public int compare(GameState sA, GameState sB){
            return Evaluation(sB)- Evaluation(sA);
        }

    //    @Override
        public boolean equals(GameState sA, GameState sB){
            return Evaluation(sB)==Evaluation(sA);
        }
    }

    Vector<GameState> SortStates(Vector<GameState> stateVector){
        Collections.sort(stateVector, new stateComparator());
        return stateVector;
    }
    
    public String GameStateToString (GameState gameState) {
        String out = new String();
        for(int i=0 ; i<64 ; i++){
            out+= Integer.toString(gameState.at(i)) ;
        }
        return out ;
    }
    
}
