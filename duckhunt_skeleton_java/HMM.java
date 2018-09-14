/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author shrey
 */
public class HMM {

    /**
     * @param args the command line arguments
     */
    Integer numStates ;
    Integer numObservations ;
    ArrayList<ArrayList<Float>> A = new ArrayList<>();
    ArrayList<ArrayList<Float>> B = new ArrayList<>();
    ArrayList<ArrayList<Float>> Pi = new ArrayList<>();
    ArrayList<Float> Row = new ArrayList<>();
    
     public HMM(Integer noStates, Integer noObservations){
       
        for(int i = 0; i<noStates ; i++) {
            Row=new ArrayList<>();
            float sum=0;
            for(int j=0 ; j<noStates ; j++){
                if(j!=noStates-1){
                float temp=(float)Math.random()*((float)1/noStates);
                sum+=temp;
                Row.add(temp) ;

                }
                else{
                    Row.add(1-sum);
                }
            }
            this.A.add(Row) ;
        }
        
        for(int i = 0; i<noStates ; i++) {
            Row=new ArrayList<>();
            float sum=0;
            for(int j=0 ; j<noObservations ; j++){
                if(j!=noObservations-1){
                float temp=(float)Math.random()*((float)1/noObservations);
                sum+=temp;
                Row.add(temp) ;
                }
                else{
                    Row.add(1-sum);
                }
            }
            this.B.add(Row) ;
        }
        
        Row=new ArrayList<>();
        float sum=0;
        for(int i = 0; i<noStates ; i++) {
            if(i!=noStates-1){
                float temp=(float)Math.random()*((float)1/noStates);
                sum+=temp;
                Row.add(temp) ;
                }
                else{
                    Row.add(1-sum);
                }                     
        }
        this.Pi.add(Row) ;     
    }
    
    
    public HMM(ArrayList<ArrayList<Float>> A, ArrayList<ArrayList<Float>> B, ArrayList<ArrayList<Float>> Pi){
            this.numStates = A.size() ;
            this.numObservations = B.get(0).size() ;
            this.A = A ;
            this.B = B ;
            this.Pi = Pi ;
    }
    
        // Alpha Calculation
    public ArrayList<ArrayList<Float>> GetAlphaInit(Integer Obs){
        ArrayList<ArrayList<Float>> Out = Matrix.ElementWiseMult(Pi,B,0) ;
        ArrayList<ArrayList<Float>> Alpha = new ArrayList<>() ;
        Alpha.add(Out.get(Obs)) ;
        return Alpha ;
    }
    public ArrayList<ArrayList<Float>> GetAlpha(ArrayList<ArrayList<Float>> PrevAlpha,Integer Obs){
       ArrayList<ArrayList<Float>> Out = Matrix.ElementWiseMult(Matrix.Mult(PrevAlpha,A),B,0) ;
       ArrayList<ArrayList<Float>> Alpha = new ArrayList<>() ;
       Alpha.add(Out.get(Obs)) ;
       return Alpha ;
    }
    //Alpha calculation ended
    
    //Probability of given observation sequence
    public ArrayList<ArrayList<Float>> GetObsSeqProb(ArrayList<Integer> ObsSeq){
       
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Float>> Alpha = GetAlphaInit(ObsSeq.get(0)) ;
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Alpha = GetAlpha(Alpha,ObsSeq.get(i)) ;
       }       
       return Alpha ;
    }
    
    //Beta Calculation
    public ArrayList<ArrayList<Float>> GetPrevBeta(ArrayList<ArrayList<Float>> FutureBeta, Integer Obs){
       ArrayList<ArrayList<Float>> Out = new ArrayList<>() ;
       Out.add(Matrix.ElementWiseMult(FutureBeta,B,0).get(Obs));
       ////System.out.println("debugg3");
       ////System.out.println(A);
       ArrayList<ArrayList<Float>> Beta = Matrix.Mult(Out,Matrix.Transpose(A)) ;
       return Beta ;
    }
    //Di-gamma Calculation
    public ArrayList<ArrayList<Float>> GetDiGamma(ArrayList<ArrayList<Float>> Alpha, ArrayList<ArrayList<Float>> Beta, Integer Obs){
        ArrayList<ArrayList<Float>> Temp = new ArrayList<>() ;
        //////System.out.println("debugg1");
        //////System.out.println(B);
        Temp.add(Matrix.Transpose(B).get(Obs)) ;
        //////System.out.println(Transpose(B));
        ArrayList<ArrayList<Float>> Gamma =  Matrix.ElementWiseMult(Temp,Matrix.ElementWiseMatMult(Matrix.Mult(Matrix.Transpose(Alpha),Beta),A),1) ;
        return Gamma ;   
    }
    //Gaama Calculation
    public ArrayList<ArrayList<Float>> GetGamma(ArrayList<ArrayList<Float>> DiGamma){
        ArrayList<ArrayList<Float>> Gamma = new ArrayList<>() ;
        ArrayList<Float> Row = new ArrayList<>() ;
        Float Sum ;
        for(int i=0 ; i<DiGamma.size() ; i++) {
            Sum = (float)0 ;
            for(int j=0 ; j<DiGamma.size() ; j++){
                Sum+=DiGamma.get(i).get(j) ;
            }
            Row.add(Sum) ;
        }
        Gamma.add(Row) ;
        return Gamma ;
    }
    
    public ArrayList<ArrayList<ArrayList<Float>>> GetAlphaList(ArrayList<Integer> ObsSeq){

        ArrayList<ArrayList<ArrayList<Float>>> MOut=new ArrayList<>();
        ArrayList<ArrayList<Float>> AlphaList=new ArrayList<>();
        ArrayList<ArrayList<Float>> Alpha = new ArrayList<>() ;
        ArrayList<ArrayList<Float>> SumList=new ArrayList<>();
        ArrayList<Float> TempSum = new ArrayList<>() ;
        Float Sum=(float)0;

        for(int i=0;i<ObsSeq.size();i++){
            if(i == 0){
                Alpha = GetAlphaInit(ObsSeq.get(i)) ;
                Sum=Matrix.GetVecSum(Alpha.get(0));
                TempSum.add(Sum);
                Alpha=Matrix.ElementWiseScalerDivide(Sum,Alpha);
                AlphaList.add(Alpha.get(0));
            }
            else{
                Alpha = GetAlpha(Alpha,ObsSeq.get(i)) ;
                Sum=Matrix.GetVecSum(Alpha.get(0));
                TempSum.add(Sum);
                Alpha=Matrix.ElementWiseScalerDivide(Sum,Alpha);
                AlphaList.add(Alpha.get(0));
            }
        }
        SumList.add(TempSum);
        
        MOut.add(AlphaList);
        MOut.add(SumList);
        ////System.out.println(MOut);
        return MOut;
    }
    
        //Beta List Formation
    public ArrayList<ArrayList<Float>> GetBetaList(ArrayList<Integer> ObsSeq,ArrayList<Float> AlphaSum){

        ArrayList<ArrayList<Float>> BetaList=new ArrayList<>();
        ArrayList<Float> BetaInit=new ArrayList<>();
        int NumStates=A.size();
        ArrayList<ArrayList<Float>> FutureBeta=new ArrayList<>(); // twoD arraylist

        for(int k=0;k<NumStates;k++){
                BetaInit.add((float)1);
        }

        ArrayList<ArrayList<Float>> temp=new ArrayList<>();
        temp.add(BetaInit) ;
        BetaInit=Matrix.ElementWiseScalerDivide(AlphaSum.get(AlphaSum.size()-1),temp).get(0);
        BetaList.add(BetaInit);

        ArrayList<ArrayList<Float>> prev_beta=new ArrayList<>();
        FutureBeta.add(BetaInit);
        for(int j=1;j<ObsSeq.size();j++){
                prev_beta=GetPrevBeta(FutureBeta,ObsSeq.get(ObsSeq.size()-j));
                //System.out.println("prev_beta");
                //System.out.println(prev_beta);
                prev_beta=Matrix.ElementWiseScalerDivide(AlphaSum.get(AlphaSum.size()-1-j),prev_beta);
                BetaList.add(prev_beta.get(0));
                FutureBeta=prev_beta;
        }
        Collections.reverse(BetaList);
        ////System.out.println("BetaList");
        ////System.out.println(BetaList);
        return BetaList;
    }
    public Boolean Convergence(ArrayList<ArrayList<Float>> PrevAlphaSum, ArrayList<ArrayList<Float>> NewAlphaSum){
        Float Sum=(float)0;

        for(int i=0;i<PrevAlphaSum.get(0).size();i++){
            Sum+=(float)Math.log(PrevAlphaSum.get(0).get(i)) - (float)Math.log(NewAlphaSum.get(0).get(i));
        }
        if(Sum>=(float)0)
            return true ;
        else
            return false ;    
    }
    
    public ArrayList<ArrayList<ArrayList<Float>>> LearnEpoch(ArrayList<Integer> ObsSeq){
        
        ArrayList<ArrayList<ArrayList<Float>>> ScaledAlphaAndAlphaSum = GetAlphaList(ObsSeq) ;
        ArrayList<ArrayList<Float>> ScaledAlphaList = ScaledAlphaAndAlphaSum.get(0) ;
        ArrayList<ArrayList<Float>> AlphaSum = ScaledAlphaAndAlphaSum.get(1) ;
        ArrayList<ArrayList<Float>> ScaledBetaList =  GetBetaList(ObsSeq,AlphaSum.get(0)) ;
        ArrayList<ArrayList<Float>> ScaledAlpha = new ArrayList<>();
        ArrayList<ArrayList<Float>> ScaledBeta = new ArrayList<>();
        ArrayList<ArrayList<Float>> DiGamma = new ArrayList<>();
        ArrayList<ArrayList<Float>> Gamma = new ArrayList<>();
        ArrayList<ArrayList<Float>> DiGammaSum = new ArrayList<>();
        ArrayList<ArrayList<Float>> GammaSum = new ArrayList<>();
        ArrayList<ArrayList<Float>> GammaSumObsSel = Matrix.Zeros(B.size(),B.get(0).size()) ;
        ArrayList<ArrayList<Float>> NewA = new ArrayList<>();
        ArrayList<ArrayList<Float>> NewB = new ArrayList<>();
        ArrayList<ArrayList<Float>> NewPi = new ArrayList<>();

        for(int i=0 ; i<ObsSeq.size()-1 ; i++){

            ScaledAlpha.add(ScaledAlphaList.get(i)) ;
            ScaledBeta.add(ScaledBetaList.get(i+1)) ;

            DiGamma = GetDiGamma(ScaledAlpha, ScaledBeta, ObsSeq.get(i+1)) ;
//            ////System.out.println("DiGamma");
//            ////System.out.println(DiGamma);
            Gamma = GetGamma(DiGamma) ;
 //           ////System.out.println("Gamma");
//            ////System.out.println(Gamma);
            ScaledAlpha = new ArrayList<>() ;
            ScaledBeta = new ArrayList<>() ;
            if(i==0){
                DiGammaSum = DiGamma ;
                GammaSum = Gamma ;
                NewPi=Gamma;
            }
            else{
                DiGammaSum = Matrix.ElementWiseMatAdd(DiGammaSum,DiGamma) ;
                GammaSum = Matrix.ElementWiseMatAdd(GammaSum,Gamma) ;
            } 
            // //System.out.println("Gamma");
            // //System.out.println(Gamma);
            GammaSumObsSel = Matrix.ColAdd(Gamma,GammaSumObsSel, ObsSeq.get(i)) ;
            // //System.out.println("GammaSumObsSel");
            // //System.out.println(GammaSumObsSel);
//            ////System.out.println("DiGammaSum");
//            ////System.out.println(DiGamma);
//            ////System.out.println("GammaSum");
//            ////System.out.println(Gamma);
//            ////System.out.println("GammaSumObsSel");
//            ////System.out.println(GammaSumObsSel);
        }
        NewA = Matrix.Transpose(Matrix.ElementWiseVecDivide(GammaSum,DiGammaSum,0)); 
        NewB = Matrix.Transpose(Matrix.ElementWiseVecDivide(GammaSum,GammaSumObsSel,0));
        
        ArrayList<ArrayList<ArrayList<Float>>> Out = new ArrayList<>() ;
        Out.add(NewA) ;
        Out.add(NewB) ;
        Out.add(NewPi);
        Out.add(AlphaSum) ;
        //////System.out.println("Out");
        //////System.out.println(Out);
        return Out ;
    }  
    
    public ArrayList<ArrayList<ArrayList<Float>>> Learn(ArrayList<Integer> ObsSeq, Integer NoEpochs){
        ArrayList<ArrayList<ArrayList<Float>>> Temp = new ArrayList<>() ;
        ArrayList<ArrayList<ArrayList<Float>>> PrevTemp = new ArrayList<>() ;
        
        Temp = LearnEpoch(ObsSeq) ;
        
        Float oldlogprob=-Float.POSITIVE_INFINITY;
        Float newlogprob;

        for (int i=1 ; i<NoEpochs ; i++){
            //System.out.println("Iteration : "+i);
            PrevTemp = Temp ;
            
            oldlogprob= Matrix.GetLogVecSum(PrevTemp.get(3).get(0));
            //System.out.println("oldlogprob");
            //System.out.println(oldlogprob);

            Temp = LearnEpoch(ObsSeq) ;
            newlogprob=Matrix.GetLogVecSum(Temp.get(3).get(0));
            
            //System.out.println("newlogprob");
            //System.out.println(newlogprob);
            if(newlogprob<oldlogprob){
//                System.out.println("Converged:"+i);
                 return PrevTemp ;
            }
            else {
                A = Temp.get(0) ;
                B = Temp.get(1) ;
                Pi = Temp.get(2) ;
            }
        }
        A = Temp.get(0) ;
        B = Temp.get(1) ;
        Pi = Temp.get(2) ;
        return Temp;
        
    }
    
    public static void main(String[] args){

    Scanner sc=new Scanner(System.in);
    ArrayList<ArrayList<ArrayList<Float>>> Lambda = Matrix.TakeInputLambda(sc) ;
//    ArrayList<ArrayList<ArrayList<Float>>> LambdaUsed = TakeInputLambda(sc) ;
//    System.out.println(LambdaUsed);

    ArrayList<Integer> ObsSeq = Matrix.TakeInputObsSeq(sc) ;
    HMM Model = new HMM(Lambda.get(0),Lambda.get(1),Lambda.get(2)) ;
//    System.out.println(Model);
    ArrayList<ArrayList<ArrayList<Float>>> AB = Model.Learn(ObsSeq,50) ;
//    PrintDist(AB,LambdaUsed) ;

    Matrix.RetVal(AB);
}  

    
}
