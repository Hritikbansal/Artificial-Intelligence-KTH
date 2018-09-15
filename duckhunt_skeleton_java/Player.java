
import java.util.ArrayList;


class Player {
    public static final int SEQ_THRESHOLD     = 70;
    public static final int NO_TYPES     = 5;
    public static final int NO_EPOCHS     = 75;
    int round ;
    public static final float PROB_THRESHOLD     = .65f;
    public ArrayList<HMM> specieModelList ;

    public Player() {
        round = -1 ;
        specieModelList = new ArrayList<>() ;
        for(int i=0 ; i<Constants.COUNT_SPECIES ; i++){
            //Specie or not that specie
            specieModelList.add(new HMM(NO_TYPES, Constants.COUNT_MOVE)) ;
        }
    }

    /**
     * Shoot!
     *
     * This is the function where you start your work.
     *
     * You will receive a variable pState, which contains information about all
     * birds, both dead and alive. Each bird contains all past moves.
     *
     * The state also contains the scores for all players and the number of
     * time steps elapsed since the last time this function was called.
     *
     * @param pState the GameState object with observations etc
     * @param pDue time before which we must have returned
     * @return the prediction of a bird we want to shoot at, or cDontShoot to pass
     */
    
    public Boolean isBlackStork(){
        return false ;
    }
    
    public Action shoot(GameState pState, Deadline pDue) {
        /*
         * Here you should write your clever algorithms to get the best action.
         * This skeleton never shoots.
         */
        
//        ArrayList<ArrayList<ArrayList<Float>>> TempLearn ;
//        ArrayList<HMM> birdModelList = new ArrayList<HMM>();
//        Integer numBirds = pState.getNumBirds() ;
//        ArrayList<Integer> obsSeq ;
//        ArrayList<Float> TempMaxProb = new ArrayList<>() ;
//        Float max = PROB_THRESHOLD ;
//        Float maxInd = -1f ;
//        Integer birdToShoot = -1 ;
//        
//        if(pState.getRound() != round) {
//            birdModelList = new ArrayList<HMM>();
//            round = pState.getRound() ;
//  
//        }
//        
//        if(pState.getBird(0).getSeqLength()< 100-1.5*pState.getNumBirds()){
//            return cDontShoot ;
//        }
////        System.err.println("Trying to shoot with seq length: "+ pState.getBird(0).getSeqLength()) ;
//        for(int i=0 ; i<numBirds ; i++){
//            
//            Bird currBird = pState.getBird(i) ;
//            if(currBird.isDead()){
//                continue ;
//            }
//            obsSeq = Misc.getSequence(currBird) ;
//            HMM birdModel = new HMM(NO_TYPES, Constants.COUNT_MOVE) ;
//            birdModel.Learn(obsSeq, NO_EPOCHS) ;
//            birdModelList.add(birdModel) ;
//            TempMaxProb = birdModel.PredictNextObservation(obsSeq) ;
//            if(TempMaxProb.get(0)>max) {
//                max = TempMaxProb.get(0) ;
//                maxInd = TempMaxProb.get(1) ;
//                birdToShoot = i ;
//                
//            }
//            
//        }
//        // This line chooses not to shoot.
//        if(birdToShoot == -1){
//            return cDontShoot;
//        }
//        else{
////            System.err.println(birdModelList.get(birdToShoot).A);
////            System.err.println(birdModelList.get(birdToShoot).B);
////            System.err.println(birdModelList.get(birdToShoot).Pi);
//
//            System.err.println("Round: "+pState.getRound()+"Sequence Length"+pState.getBird(0).getSeqLength()+"Bird No : "+birdToShoot+" Action : "+ maxInd + " Probability"+max);
//            return new Action(birdToShoot,maxInd.intValue()) ;
//        }
        return cDontShoot ;
        // This line would predict that bird 0 will move right and shoot at it.
        // return Action(0, MOVE_RIGHT);
    }

    /**
     * Guess the species!
     * This function will be called at the end of each round, to give you
     * a chance to identify the species of the birds for extra points.
     *
     * Fill the vector with guesses for the all birds.
     * Use SPECIES_UNKNOWN to avoid guessing.
     *
     * @param pState the GameState object with observations etc
     * @param pDue time before which we must have returned
     * @return a vector with guesses for all the birds
     */
    public int[] guess(GameState pState, Deadline pDue) {
        /*
         * Here you should write your clever algorithms to guess the species of
         * each bird. This skeleton makes no guesses, better safe than sorry!
         */
        int[] lGuess = new int[pState.getNumBirds()];
//        for (int i = 0; i < pState.getNumBirds(); ++i){
//                lGuess[i] = (Constants.SPECIES_UNKNOWN) ;
//            }
        if(pState.getRound()==0){
            for (int i = 0; i < pState.getNumBirds(); ++i){
                lGuess[i] = (Constants.SPECIES_PIGEON) ;
            }
        }

        else{
        
            for (int i = 0; i < pState.getNumBirds(); ++i){
                Bird currBird =  pState.getBird(i) ;
                lGuess[i] = Misc.guessSpecie(Misc.getSequence(currBird),specieModelList) ;
                System.err.print(lGuess[i]);
            }
            System.err.println("");

        }
        
        return lGuess;
    }

    /**
     * If you hit the bird you were trying to shoot, you will be notified
     * through this function.
     *
     * @param pState the GameState object with observations etc
     * @param pBird the bird you hit
     * @param pDue time before which we must have returned
     */
    public void hit(GameState pState, int pBird, Deadline pDue) {
        System.err.println("HIT BIRD!!!");
    }

    /**
     * If you made any guesses, you will find out the true species of those
     * birds through this function.
     *
     * @param pState the GameState object with observations etc
     * @param pSpecies the vector with species
     * @param pDue time before which we must have returned
     */
    public void reveal(GameState pState, int[] pSpecies, Deadline pDue) {
        int currSpecie ;
        Bird currBird ;
        HMM model ;
        for(int i =0 ; i<pSpecies.length ; i++){
            currBird = pState.getBird(i) ;
            currSpecie = pSpecies[i] ;
            if(currSpecie==-1){
                continue ;
            }
            else{     
                System.err.println("Learning for Specie"+currSpecie);
                model = specieModelList.get(currSpecie) ;
                model.Learn(Misc.getSequence(currBird), NO_EPOCHS) ;
                specieModelList.set(currSpecie, model) ;
            }
        }
    }

    public static final Action cDontShoot = new Action(-1, -1);
}
