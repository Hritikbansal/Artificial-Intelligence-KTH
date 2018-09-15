
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shrey
 */
public class Misc {
    public static ArrayList<Integer> getSequence(Bird bird) {
        ArrayList<Integer> sequence = new ArrayList<>() ;
        int obs ;
        for(int i=0 ; i<bird.getSeqLength() ; i++){

            obs = bird.getObservation(i) ;
            if(obs<0 || obs>9){
                //System.err.println("observation out of range"+ obs);
                break ;
            }
            else{
            sequence.add(bird.getObservation(i)) ;}
        }
        return sequence ;
    }
    
    public static Integer guessSpecie(ArrayList<Integer> obsSeq, ArrayList<HMM> specieModelList) {
        ArrayList<ArrayList<Float>> Alpha ;
        Float totProb ;
        Float totProbMax = 0f;
        Integer birdSpecieGuess = -1 ;
        for(int i=0 ; i<specieModelList.size() ;i++){
//            if(specieModelList.get(i).learnNum == 0)
//                continue ;
//            else{
                Alpha = specieModelList.get(i).GetLastAlpha(obsSeq) ;
                System.err.println(Alpha);

                totProb = Matrix.GetVecSum(Alpha.get(0)) ;
                System.err.println(totProb);
                if(totProb > totProbMax){
                    totProbMax = totProb ;
                    birdSpecieGuess = i ;
                }
//            }
        }
        return birdSpecieGuess ;
    }
}
