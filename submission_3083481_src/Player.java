import java.util.*;
import java.math.*;
class Player {

    public static final int DEFAULT_GUESS     = Constants.SPECIES_RAVEN;
    public static final int SEQ_THRESHOLD     = 65;  // this means taking 2 shots per bird on average
    public static final int NO_TYPES     = 5;
    public static final int NO_EPOCHS     = 100;
    int round ;
    public static final double PROB_THRESHOLD     = .68f;
    ArrayList<HMM> birdModelList ;
    
    public ArrayList<ArrayList<HMM>> specieModelList ; //Arraylist of HMM Model Objects
    public ArrayList<ArrayList<Integer>> BirdObsSeq=new ArrayList<>();
//    public ArrayList<Boolean> specieLearnt ;

    public Player() {
        round=-1;
        specieModelList = new ArrayList<>() ;
        for(int i=0 ; i<Constants.COUNT_SPECIES ; i++){
            specieModelList.add(new ArrayList<>()) ;
            BirdObsSeq.add(new ArrayList<>());
//            specieLearnt.add(Boolean.FALSE) ;
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

    public ArrayList<Integer> MakeObsSeq(Bird bb,int t){

        ArrayList<Integer> TempObs=new ArrayList<>();
        for(int j=0;j<t;j++){
            TempObs.add(bb.getObservation(j));
        }
        return TempObs;
    }

    public Double SUM(ArrayList<Double> Arrli){
        Double su=0.0;

        for(int i=0;i<Arrli.size();i++){
            su+=Arrli.get(i);
        }
        return su;
    }

    public ArrayList<Integer> MakeMoveSeq(Bird bb,int t){

        ArrayList<Integer> TempObs=new ArrayList<>();
        for(int j=0;j<t;j++){
            if(bb.getObservation(j)!=-1){
                TempObs.add(bb.getObservation(j));
            }
            else{
                break;
            }
        }
        return TempObs;

    }

     public Integer guessSpecie(ArrayList<Integer> ObsSeq) {

        ArrayList<Double> Alpha ;
        Double totProb = 1e-80 ;
        Double totProbMax = -Double.POSITIVE_INFINITY;
        Integer birdSpecieGuess = -1 ;
        ArrayList<HMM> specieModel ;
        for(int i=0 ; i<specieModelList.size() ;i++){
            specieModel = specieModelList.get(i) ;
            for(int j=0 ; j<specieModel.size(); j++){

                Alpha=specieModel.get(j).GetEmissionSequenceProb(ObsSeq).get(0);
                // AlphaSum = specieModelList.get(i).GetAlphaList(obsSeq).get(1).get(0) ;
                //System.err.println(AlphaSum);
                //System.err.println(AlphaSum);
                totProb= specieModel.get(j).GetVecSum(Alpha);

                //System.err.println("Guess with probabitlity "+totProb);
                if(totProb > totProbMax){
                    totProbMax = totProb ;
                    birdSpecieGuess = i ;
//                    break ;
                }

            }
        }
        double logprob=Math.log10(totProbMax);
        //System.err.println("Guess Probability Log "+Math.log10(totProbMax));
        //System.err.println("Species of Bird "+birdSpecieGuess);
        if(logprob>-80.0){
            return birdSpecieGuess;
        }
        else{
            return Constants.SPECIES_UNKNOWN;
        }
        
    }

    public Action shoot(GameState pState, Deadline pDue) {
        /*
         * Here you should write your clever algorithms to get the best action.
         * This skeleton never shoots.
         */

        // HS-Pattern of flying(5)   OS-Movements(9)
        // return cDontShoot;
        // if(pState.getRound()==0 || pState.getRound()==1){  //don't shoot in the first round 
        //     return cDontShoot;
        // }

        // int num_birds=pState.getNumBirds();
        // int time_step=pState.getBird(0).getSeqLength();
        // System.err.println(time_step);  

        // int waitingtime=30;  //we have to change this asap

        // if(time_step<waitingtime){
        //     return cDontShoot;
        // }

        // else{

        //     int i=(time_step-waitingtime)%(num_birds);
            
        //     Bird B=pState.getBird(i);
            
        //     while(B.isDead()){  // we don't want to calculate for an already dead bird
        //         B=pState.getBird((i+1)%num_birds);
        //         i=i+1;
        //     }

        //     //guess the bird
        //     Integer birdSpecie ;

        //     ArrayList<Integer> Obs=MakeMoveSeq(B,time_step);

        //     birdSpecie=guessSpecie(Obs);

        //     if(birdSpecie==5 || birdSpecie==-1){
        //         return cDontShoot;
        //     }


        //     // HMM model=new HMM(8,9);  // 5- flying pattern states and 9-movements
                

        //     // ArrayList<ArrayList<ArrayList<Double>>> Parameters = new ArrayList<>();
        //     // Parameters=model.Learn(Obs,50); //let the number of epochs for hmm training be 100
        //     // //Most likely flying patterns of the choosen bird
        //     //System.err.println("Most Likely Flying Pattern");
        //     //System.err.println(model.Viterbi(Obs));
        //     //Possible movements ArrayList
        //     ArrayList<Integer> PosMoves=new ArrayList<>();
        //     PosMoves.add(0); PosMoves.add(1);PosMoves.add(2); PosMoves.add(3);
        //     PosMoves.add(4); PosMoves.add(5);PosMoves.add(6); PosMoves.add(7);PosMoves.add(8);

        //     Double maxProb=-Double.POSITIVE_INFINITY;
        //     Integer move=-2;

        //     ArrayList<Double> TempAlpha;
        //     ArrayList<Integer> NewObs;

        //     for(int j=0;j<PosMoves.size();j++){
        //         //NewObs gives the obs
        //         NewObs=new ArrayList<>();
        //         for(int k=0;k<Obs.size();k++){
        //             NewObs.add(Obs.get(k));
        //         }
        //         NewObs.add(PosMoves.get(j));
        //         //TempAlpha=new ArrayList<>();
        //         //System.err.println(model.A);
        //         //System.err.println(model.alpha);
        //         TempAlpha=specieModelList.get(birdSpecie).GetAlphaList(NewObs).get(1).get(0);
        //         Double prob=specieModelList.get(birdSpecie).GetLogVecSum(TempAlpha);

        //         if(prob>=maxProb){
        //             maxProb=prob;
        //             move=j;
        //         }
        //     }
        //     System.err.println(maxProb);
        //     //return new Action(i,move);
        //     return new Action(i,move);
        // }
        // assuming that i will be in a good position
        if(pState.getRound()==0 || pState.getRound()==1 ){//|| pState.getRound()==2 || pState.getRound()==3 || pState.getRound()==4){
            return cDontShoot;
        }

        if(pState.getRound() != round) {

            // don't shoot without guessing ..plisss
            for(int i=0;i<BirdObsSeq.size();i++){
                if(BirdObsSeq.get(i).size()==0 && pState.getRound()<3){
                    return cDontShoot;
                }
            }

            birdModelList = new ArrayList<>();
            round = pState.getRound() ;
            for(int i=0 ; i<pState.getNumBirds() ; i++){
                birdModelList.add(null) ;
            }
  
        }
        return getNextAction(pState) ;   // i am going on next state only if I can guess each bird specie otherwise not
       // return cDontShoot;

    }
    
    public ArrayList<Integer> getSequence(Bird bird) {
        
        ArrayList<Integer> sequence = new ArrayList<>() ;
        int obs ;
        for(int i=0 ; i<bird.getSeqLength() ; i++){

            obs = bird.getObservation(i) ;
            if(obs<0 || obs>8){
                //System.err.println("observation out of range"+ obs);
                break ;
            }
            else{
            sequence.add(bird.getObservation(i)) ;}
        }
        return sequence ;
    }
     public Action getNextAction(GameState pState) {

        Action nextAction ;
        ArrayList<Integer> obsSeq ;
        Integer numBirds = pState.getNumBirds() ;
        Integer birdSpecie ;
        ArrayList<Double> TempMaxProb = new ArrayList<>() ;
        //Double max = PROB_THRESHOLD ;
        Double max=-25.0;
        Double maxInd = -1.0 ;
        Integer birdToShoot = -1 ;
        HMM model ;
        if(pState.getBird(0).getSeqLength()< SEQ_THRESHOLD){
            return cDontShoot ;
        }
            
            for(int i=0 ; i<numBirds ; i++){
            
                Bird currBird = pState.getBird(i) ;
                if(currBird.isDead()){
                    continue ;
                }

                obsSeq = getSequence(currBird) ;
//                birdSpecie = guessSpecie(obsSeq) ;
//
//                if(birdSpecie == Constants.SPECIES_BLACK_STORK || birdSpecie == Constants.SPECIES_UNKNOWN){  //don't shoot if the species is unknown
//                    continue ;
//                }

                 if(pState.getBird(0).getSeqLength()%5 == 0 && pState.getBird(0).getSeqLength()>=SEQ_THRESHOLD){
//                    System.err.println("Learning at seq Leangth : "+pState.getBird(0).getSeqLength()) ;
                   // HMM birdModel = new HMM(specieModelList.get(birdSpecie).A,specieModelList.get(birdSpecie).B,specieModelList.get(birdSpecie).Pi) ;
//                    model = birdModelList.get(i);
                    model = new HMM(5,9) ;
                    model.Learn(obsSeq, 50) ;
                    //if(birdModel == null){System.err.println("Something is WRONG : null ");}
                    birdModelList.set(i,model) ;
                }
                model = birdModelList.get(i) ;
                if(model == null){
                    model = new HMM(5,9) ;
                    model.Learn(obsSeq, 50) ;
                    birdModelList.set(i,model) ;
                }
                
//                if(model == null){continue ;}
                
                TempMaxProb = model.PredictNextObservation(obsSeq) ;
                //System.err.println("Probablity of Next Move : "+Math.log10(TempMaxProb.get(0)));
                if(TempMaxProb.get(0)>max) {
                    max = TempMaxProb.get(0) ;
                    maxInd = TempMaxProb.get(1) ;
                    birdToShoot = i ;

                }
            
            }
            if(birdToShoot == -1){
                nextAction = cDontShoot ;
            }
            else{
                nextAction = new Action(birdToShoot,maxInd.intValue()) ;
                System.err.println("ROUND: "+round+"\tSEQ LENGTH: "+pState.getBird(0).getSeqLength()+"\tBIRD: "+birdToShoot+"\tPROB: "+max);
            }
            
            return nextAction ;
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
        if(pState.getRound()==0){
            for (int i = 0; i < pState.getNumBirds(); ++i){
                 //lGuess[i] = Math.round(((Double)Math.random()*(Double)0.7)) ;
                    lGuess[i]= DEFAULT_GUESS;
            }

        }

         else{

            //System.err.println("Round: "+pState.getRound());
           // System.err.print("Guessing  :  ");

            for (int i = 0; i < pState.getNumBirds(); ++i){
                Bird currBird =  pState.getBird(i) ;
                lGuess[i] = guessSpecie(MakeMoveSeq(currBird,currBird.getSeqLength())) ;
                //System.err.print(lGuess[i]);
            }
            System.err.println("");
        }
//        System.err.println("Guess for round "+pState.getRound());
//        System.err.println(Arrays.toString(lGuess));
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

    public ArrayList<Integer> Append(int spe,Bird bb){
        ArrayList<Integer> Temp=BirdObsSeq.get(spe);
        ArrayList<Integer> Observations=MakeMoveSeq(bb,bb.getSeqLength());
        for(int j=0;j<Observations.size();j++){
            Temp.add(Observations.get(j));
        }
        return Temp;
    }
    /**
     * If you made any guesses, you will find out the true species of those
     * birds through this function.
     *
     * @param pState the GameState object with observations etc
     * @param pSpecies the vector with species
     * @param pDue time before which we must have returned
     */
//    
//    public Boolean checkBlackStork(){
//        ArrayList<HMM> specieModel = specieModelList.get(Constants.SPECIES_BLACK_STORK) ;
//        Boolean isBlackStork = True ;
//        for(int i=0 ; i<specieModel.size() ; i++){
//            if
//        }
//    }
    public void reveal(GameState pState, int[] pSpecies, Deadline pDue) {

        int currSpecie ;
        Bird currBird ;
        HMM model ;
//        System.err.println("Truth for round "+pState.getRound());
//        System.err.println(Arrays.toString(pSpecies));
        

        for(int i =0 ; i<pSpecies.length ; i++){
            
            currBird = pState.getBird(i) ;
            currSpecie = pSpecies[i] ;
            
            if(currSpecie==-1){
                continue ;
            }

            else{ 

//                if(pState.getRound()<1){
//
//                    if(BirdObsSeq.get(currSpecie).size()>500){
//                        continue;
//                    }
//                    else if(BirdObsSeq.get(currSpecie).size()<500){
//                        ArrayList<Integer> Concatenated=Append(currSpecie,currBird);
//                        BirdObsSeq.set(currSpecie,Concatenated);
//                    }
//
//                }

//                if(pState.getRound()>0){
                    if(BirdObsSeq.get(currSpecie).size()<500){
                        ArrayList<Integer> Concatenated=Append(currSpecie,currBird);
                        BirdObsSeq.set(currSpecie,Concatenated);

                    }
                    else{
                        BirdObsSeq.set(currSpecie, MakeMoveSeq(currBird,currBird.getSeqLength())) ;
                    }
//                    else{
//                        specieLearnt.set(i, Boolean.TRUE) ;
//                    }
//                }
            }       
        }

//        if(pState.getRound()>=0){  //for rounds greater than 3

            for(int k=0;k<Constants.COUNT_SPECIES;k++){
                    model=new HMM(5,9);
                    
                    if(BirdObsSeq.get(k).size()>0){
//                        if(specieLearnt.get(k)) continue ;
                        //System.err.println(BirdObsSeq.get(k));
                        model.Learn(BirdObsSeq.get(k),10);
                        specieModelList.get(k).add(model);
                    }
                        
            }
//        }

    }

    public static final Action cDontShoot = new Action(-1, -1);
}

