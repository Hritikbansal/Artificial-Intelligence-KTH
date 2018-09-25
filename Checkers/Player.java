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
        System.err.println("Player: "+PLAYER);
        OPPONENT = (PLAYER == Constants.CELL_RED) ? Constants.CELL_WHITE : Constants.CELL_RED;

        int value ;
        int bestValue = Integer.MIN_VALUE ;
        int bestNextStateInd = -1  ;
        int alpha = Integer.MIN_VALUE ;
        int depth =7 ;

        if (lNextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(pState, new Move());
        }
        lNextStates=SortStates(lNextStates);

        System.err.println("Play has begun");
        System.err.println(lNextStates.size());

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
    //order is Normal Player,King Player, Normal Opponent , King Opponent
    public int getScore_1(int NorPla,int KingPla,int NorOpp,int KingOpp){
        int score=-1;
        String tot_score=new String();
        //System.err.println(S)
        
        score=3*(NorPla-NorOpp)+5*(KingPla-KingOpp);
        //2 Highest order digits of the final score

        return score;
    }
    /*
    *Cell_empty=0, Cell_red=1,cell_white=2, cell_red&Cell_king=5,cell_white&cell_king=6,cell_invalid=8
    *
    */
    public Vector<Integer> Count(GameState gameState){

        Vector<Integer> vec_count=new Vector<>();
        int NPlayer=0;
        int NOpponent=0;
        int KPlayer=0;
        int KOpponent=0;

        for(int i=0;i<32;i++){

            int tmp=gameState.get(i);

            if(tmp==5){ //Red King
                if(PLAYER==1){
                    KPlayer+=1;
                }
                else{
                    KOpponent+=1;
                }
            }

            else if(tmp==6){ //White King
                if(PLAYER==2){
                    KPlayer+=1;
                }
                else{
                    KOpponent+=1;
                }
            }

            else if(tmp==1){ //red
                if(PLAYER==1){
                    NPlayer+=1;
                }
                else{
                    NOpponent+=1;
                }
            }

            else if(tmp==2){
                if(PLAYER==2){
                    NPlayer+=1;
                }
                else{
                    NOpponent+=1;
                }
            }

            else{
                continue;
            }
        }

        vec_count.add(NPlayer);
        vec_count.add(KPlayer);
        vec_count.add(NOpponent);
        vec_count.add(KOpponent);

        return vec_count;
    }

    /* This function calculates the no of moves required for a normal piece to become a king
    */
    public int Distance(int piece,int pos){

        if(piece==1){
            return (7-pos);
        }
        return (pos);

    }
    /*
    This function returns the player whose normal pieces are closer to being king on average
    */
    public int getScore_2(GameState gameState, int NPlayer, int NOpponent){        

        float av_player;
        float av_opp;
        int dist_player=0;
        int dist_opp=0;

        for(int i=0;i<32;i++){
            if(gameState.get(i)==5 || gameState.get(i)==6 || gameState.get(i)==0){
                continue;
            }
            else if(gameState.get(i)==PLAYER){
                dist_player+=Distance(PLAYER,(i/4)); // i/4 gives the row number 
            }
            else{
                dist_opp+=Distance(OPPONENT,(i/4));
            }
        }

        av_player=(dist_player+(float)0)/(NPlayer+1);
        System.err.println("av_player: "+av_player);
        av_opp=(dist_opp+(float)0)/(NOpponent+1);
        System.err.println("av_opp: "+av_opp);

        if(av_player>av_opp){
            return PLAYER;
        }
        else if(av_player<av_opp){
            return OPPONENT;
        }
        return 0;
    }

    public int getScore_3(int num_player,int num_opp){
        if(num_opp>num_player){
            return OPPONENT;
        }
        else if(num_player>num_opp){
            return PLAYER;
        }
        return 0;
    }

    public int Evaluation (GameState gameState){

        int tmp_score=0;
        int closer=0;
        int ahead=0;
        Vector<Integer> count_pieces=new Vector<>();
        String tot_score=new String();
        int score;
        
        String gameString = GameStateToString(gameState) ;
        if(repeatedStates.get(gameString)!= null) return repeatedStates.get(gameString) ;

        count_pieces=Count(gameState);
        //System.err.println("Count : "+count_pieces);
        tmp_score=getScore_1(count_pieces.get(0),count_pieces.get(1),count_pieces.get(2),count_pieces.get(3));
        System.err.println("tmp_score: "+tmp_score);

        tot_score=tot_score+Integer.toString(tmp_score);  // first 2 highest digits of the final score
        
        //Next two highest digits are decided by this
        closer=getScore_2(gameState,count_pieces.get(0),count_pieces.get(2));
        System.err.println("Closer: "+closer);
        if(closer==PLAYER){
            tot_score+=Integer.toString(99);
        }
        else if(closer==OPPONENT){
            tot_score+=Integer.toString(01);
        }
        else{
            tot_score+=Integer.toString(25);
        }

        //Next two score depending upon who is ahead where "ahead" is defined on the basis of Num of pieces left
        ahead=getScore_3((count_pieces.get(0)+count_pieces.get(1)),(count_pieces.get(2)+count_pieces.get(3)));
        if(ahead==PLAYER){
            tot_score+=Integer.toString(99);
        }
        else if(ahead==OPPONENT){
            tot_score+=Integer.toString(01);
        }
        else{
            tot_score+=Integer.toString(25);
        }

        score=Integer.parseInt(tot_score);
        System.err.println("Score: "+score);
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
