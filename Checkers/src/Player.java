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

    
//    public static final int BOARD_POINTS[] = {500, 500, 1000, 2000, 3000, 2000, 1500, 2500, 3500, 2500, 3000, 4000, 5000, 4000, 3500, 4500, 5500, 4500, 5000, 6000, 7000, 6000, 5500, 6500, 7500, 6500, 7000, 8000, 9000, 8000, 7500, 8500} ;
//    public static final int BOARD_POINTS[] = {1500, 500, 1000, 2000, 3000, 2000, 1500, 2500, 3500, 2500, 3000, 4000, 5000, 4000, 3500, 4500, 4500, 3500, 4000, 5000, 4000, 3000, 2500, 3500, 2500, 1500, 2000, 3000, 2000, 1000, 500, 1500}
     public static final int BOARD_POINTS[] = {420, 340, 380, 460, 360, 280, 240, 320, 220, 140, 180, 260, 160, 80, 40, 120,120, 40, 80, 160, 260, 180, 140, 220, 320, 240, 280, 360, 460, 380, 340, 420} ;

    
    public static final int PAWN_POINTS = 30000 ;
    public static final int KING_POINTS = 50000 ;
    public static final int WIN_POINTS = 5000000 ;
    Hashtable<Long, Integer> repeatedStates = new Hashtable<>() ;
    public static ArrayList<ArrayList<Long>> ZOBRIST = new ArrayList<>() ;
    public static final int START_DEPTH = 8 ;
    public static final int MAX_DEPTH = 12 ;
    public static final int CUTOFF_TIME = 1000 ;
    public static boolean searchCutoff = false ;

    

//    Long num = 651766647 ;
    int PLAYER, OPPONENT ;
    int SIZE = 32 ;
    
    public Player(){
        ArrayList<Long> row = new ArrayList<>() ;
        for(int i=0 ; i<32 ; i++){
            row = new ArrayList<>() ;

            for(int j=0  ; j<4 ; j++){
                row.add((long)(Math.random()*Long.MAX_VALUE)) ;
            }
            ZOBRIST.add(row) ;
        }
//      System.out.println(Array);
    }
    

    public GameState play(final GameState pState, final Deadline pDue) {

        Vector<GameState> nextStates = new Vector<GameState>();
        pState.findPossibleMoves(nextStates);
        nextStates = SortStates(nextStates) ;
        
        PLAYER = pState.getNextPlayer() ;
        if(PLAYER == Constants.CELL_RED) OPPONENT = Constants.CELL_WHITE ;
        else OPPONENT = Constants.CELL_RED ;
        

        int bestNextStateInd = -1  ;
        int finalBestNextStateInd = -1 ;

        
        
        
        int nextStateSize = nextStates.size() ;
        if (nextStateSize == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(pState, new Move());
        }
        if(nextStateSize==1){
            return nextStates.get(0) ;
        }

        System.err.println("Play has begun NEXT STATE SIZE   " + nextStateSize);
        
        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        searchCutoff = false ;
        for(int i=START_DEPTH ; i<MAX_DEPTH ; i++){
            bestNextStateInd = BestNextStateIndex(nextStates,i, pDue) ;
            if(!searchCutoff)
                finalBestNextStateInd = bestNextStateInd ;
            else
                break ;
            
        }
        
        if(bestNextStateInd == -1){
            System.err.println("No best state found");
            return null ;
        }
        System.err.println("returning the next state with last move"+nextStates.get(bestNextStateInd).getMove().toMessage());
        return nextStates.elementAt(bestNextStateInd) ;
    }
    public int BestNextStateIndex(Vector<GameState> nextStates, int depth, Deadline pDue){
        int value ;
        int bestValue = Integer.MIN_VALUE ;
        int bestNextStateInd = -1  ;
        int alpha = Integer.MIN_VALUE ;
        
        
        for(int i=0 ; i<nextStates.size(); i++){
            System.err.println("Time Remaining"+pDue.timeUntil());
//            if(pDue.timeUntil()<100000000) break ;
            value = MiniMaxPruning(nextStates.elementAt(i),depth,alpha,Integer.MAX_VALUE,OPPONENT,pDue) ;
//            System.err.println("Best Value Acquired:"+value);

            if(value > bestValue){
                
                bestValue = value ;
                bestNextStateInd = i ;
                alpha = Math.max(alpha, bestValue) ;
            }
//            else if(value == bestValue&& Math.random()>.5){
//                bestValue = value ;
//                bestNextStateInd = i ;
//                alpha = Math.max(alpha, bestValue) ;
//            }
        }
        return bestNextStateInd ;
    }
    
    public int MiniMaxPruning(GameState pState,int depth, int alpha, int beta, int player, Deadline pDue){
        
        if(pDue.timeUntil()<CUTOFF_TIME){
            searchCutoff= true ;
        }
        
        if(searchCutoff||depth ==0 ||pState.isEOG()){
            return Evaluation(pState) ;
        }
        Vector<GameState> nextStates = new Vector<>();
        pState.findPossibleMoves(nextStates);

        int bestPossible ;
        int value ;
        
        if(player == PLAYER){
            bestPossible = Integer.MIN_VALUE ;
            for(int i=0 ; i<nextStates.size() ; i++){
                value = MiniMaxPruning(nextStates.elementAt(i),depth-1, alpha, beta, OPPONENT,pDue) ;
                bestPossible = Math.max(bestPossible, value) ;
                alpha = Math.max(bestPossible,alpha) ;
                if(beta<=alpha) {
                    break ;
                }

            }
            return bestPossible ;
        }
        
        else if(player == OPPONENT){
            bestPossible = Integer.MAX_VALUE ;
            for(int i=0 ; i<nextStates.size(); i++){
                value = MiniMaxPruning(nextStates.elementAt(i),depth-1, alpha, beta,PLAYER,pDue) ;
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
    
        public int Evaluation (GameState pState){
            int score = 0 ;
            int totalNum = 0 ;
            int cell = 0 ;
            
            if(pState.isEOG() ){
                if(PLAYER == Constants.CELL_RED && pState.isRedWin()){
                    return WIN_POINTS ;
                }
                else if(PLAYER == Constants.CELL_WHITE && pState.isWhiteWin()){
                    return WIN_POINTS ;
                }
                else if(PLAYER == Constants.CELL_WHITE && pState.isRedWin()){
                    return -WIN_POINTS ;
                }
                else if(PLAYER == Constants.CELL_RED && pState.isWhiteWin()){
                    return -WIN_POINTS ;
                }
                else
                    return -WIN_POINTS+10 ;
                
            
            }
            else{
//            String pStateAsString = GameStateToString(pState) ;
            Long pStateHash = GameStateHash(pState) ;
            if(repeatedStates.containsKey(pStateHash)) return repeatedStates.get(pStateHash) ;
            
            for(int i=0 ; i<SIZE ; i++){
                cell = pState.get(i) ;
                if((cell & PLAYER)!=0) {
                    totalNum++ ;
                    if((cell & Constants.CELL_KING)!=0){
                        score+=KING_POINTS ;
                    }
                    else{
                        score+=PAWN_POINTS ;
                        score+=BOARD_POINTS[i] ;
                    }
                }
                else if((pState.get(i) & OPPONENT)!=0) {
                    totalNum-- ;
                    if((cell & Constants.CELL_KING)!=0){
                        score-=KING_POINTS ;
                    }
                    else{
                        score-=PAWN_POINTS ;
                        score-=BOARD_POINTS[SIZE-1-i] ;
                    }
                }
            }
            score+=totalNum*100 ;
            
//            score+=(((double)(Math.random()-0.5))*20) ;
            repeatedStates.put(pStateHash,score) ;
            return score ;
            }

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
    
    public String GameStateToString (GameState pState) {
        StringBuilder temp = new StringBuilder() ;
//        String out = new String();
    
        for(int i=0 ; i<SIZE ; i++){
             temp.append(Integer.toString(pState.get(i))) ;
        }
        
        return temp.toString() ;
    }
    public Long GameStateHash(GameState pState){
        long hash = 0 ;
        int cell =0 ;
        for(int i=0 ; i<SIZE ; i++){
            cell = pState.get(i) ;
            if((cell & PLAYER)!=0) {
                if((cell & Constants.CELL_KING)!=0){
                    hash^=ZOBRIST.get(i).get(1) ;
                }
                else{
                    hash^=ZOBRIST.get(i).get(0) ;
                }
            }
            else if((pState.get(i) & OPPONENT)!=0) {
                if((cell & Constants.CELL_KING)!=0){
                    hash^=ZOBRIST.get(i).get(3) ;
                }
                else{
                    hash^=ZOBRIST.get(i).get(2) ;
                }
            }
        }
        return hash ;
    }
}
