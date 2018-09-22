import java.util.*;

public class Player {
    /**
     * Performs a move
     *
     * @param pState
     *            the current state of the board
     * @param pDue
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */

    Hashtable<String, Integer> repeatedStates = new Hashtable<>() ;
    int PLAYER ;
    int OPPONENT ;
  

    public GameState play(final GameState pState, final Deadline pDue) {

        Vector<GameState> lNextStates = new Vector<GameState>();
        pState.findPossibleMoves(lNextStates);

        PLAYER = pState.getNextPlayer() ;
        OPPONENT = (PLAYER == Constants.CELL_RED) ? Constants.CELL_WHITE : Constants.CELL_RED;

        int value ;
        int bestValue = Integer.MIN_VALUE ;
        int bestNextStateInd = -1  ;
        int alpha = Integer.MIN_VALUE ;
        int depth =1 ;

        if (lNextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(pState, new Move());
        }

        System.err.println("Play has begun");

        for(int i=0 ; i<lNextStates.size(); i++){
           // System.err.println("Time Remaining"+pDue.timeUntil());
//            if(deadline.timeUntil()<100000000) break ;
            value = MiniMaxPruning(lNextStates.elementAt(i),depth,alpha,Integer.MAX_VALUE,OPPONENT,pDue) ;
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
        return lNextStates.elementAt(bestNextStateInd) ;

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

    public int getScore(int NorPla,int KingPla,int NorOpp,int KingOpp){
        int score=-1;
        score=(NorPla-NorOpp)+10*(KingPla-KingOpp);

        return score;
    }

    //Trying a simple Evaluation function Assuming that a king is 10 times more valuable than Normal
    public int Evaluation (GameState gameState){

        int score=0;
        int NPlayer=0;
        int NOpponent=0;
        int KPlayer=0;
        int KOpponent=0;
        
        String gameString = GameStateToString(gameState) ;
        if(repeatedStates.get(gameString)!= null) return repeatedStates.get(gameString) ;

        for(int i=0;i<32;i++){
            int tmp=gameState.get(i);

            if(tmp==PLAYER){
                if(tmp==Constants.CELL_KING){
                    KPlayer+=1;
                }
                NPlayer+=1;
            }

            else{
                if(tmp==Constants.CELL_KING){
                    KOpponent+=1;
                }
                NPlayer+=1;
            }
        }

        score=getScore(NPlayer,KPlayer,NOpponent,KOpponent);
        repeatedStates.put(gameString, score) ;
        return score;

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
        for(int i=0 ; i<32 ; i++){
            out+= Integer.toString(gameState.get(i)) ;
        }
        return out ;
    }

    

}
