/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// package hmm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.math.*;
/**
 *
 * @author shrey
 */
public class HMM {

    public static ArrayList<ArrayList<Double>> Mult(ArrayList<ArrayList<Double>> M1, ArrayList<ArrayList<Double>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        Double Sum ;
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();
        
        if(!ColCount1.equals(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<>();
            for(int j=0; j<ColCount2 ; j++){
                Sum=0.0 ;
                for(int k=0 ; k<ColCount1 ; k++){
                    Sum += M1.get(i).get(k)*M2.get(k).get(j) ;
                }
                Row.add(Sum) ;
            }
            MOut.add(Row) ;
        }
        return MOut ;
    }
    
    public static ArrayList<ArrayList<Double>> MaxMult(ArrayList<ArrayList<Double>> M1, ArrayList<ArrayList<Double>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        Double Max, Term ;
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();

        if(!ColCount1.equals(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<>();
            for(int j=0; j<ColCount2 ; j++){
                Max=0.0 ;
                for(int k=0 ; k<ColCount1 ; k++){
                    Term =  M1.get(i).get(k)*M2.get(k).get(j) ;
                    if(Max< Term)
                        Max = Term ;
                }
                Row.add(Max) ;
            }
            MOut.add(Row) ;
        }
        return MOut ;
    }
    
    public static ArrayList<ArrayList<Double>> Transpose(ArrayList<ArrayList<Double>> M){
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();
        Integer RowCount = M.size() ;
        Integer ColCount = M.get(0).size() ;

        for(int j=0 ; j<ColCount ; j++){
            Row = new ArrayList<>();
            for(int i=0 ; i<RowCount ; i++){
                Row.add(M.get(i).get(j));
            }
            MOut.add(Row) ;
        }
        return MOut ;
    }

    public static ArrayList<ArrayList<Double>> ElementWiseMult(ArrayList<ArrayList<Double>> V, ArrayList<ArrayList<Double>> M, Integer RowWise){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        Integer ColCount2 = M.get(0).size() ;
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();
        
        if(RowWise.equals(0)){
            if(!RowCount1.equals(1)){
                throw new IllegalArgumentException("First aregument is not a vector") ;
            }

            if(!ColCount1.equals(RowCount2)){
                throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
            }

            for(int i=0 ; i<ColCount2 ; i++){
                Row = new ArrayList<>();
                for(int j=0; j<ColCount1 ; j++){
                    Row.add(V.get(0).get(j)*M.get(j).get(i));
                }
                MOut.add(Row) ;
            }
        }
        else {
            if(!RowCount1.equals(1)){
                throw new IllegalArgumentException("First aregument is not a vector") ;
            }

            if(!ColCount1.equals(ColCount2)){
                throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
            }

            for(int i=0 ; i<RowCount2 ; i++){
                Row = new ArrayList<>();
                for(int j=0; j<ColCount2 ; j++){
                    Row.add(V.get(0).get(j)*M.get(i).get(j));
                }
                MOut.add(Row) ;
            }
        }
        return MOut ;

    }
    public static ArrayList<ArrayList<Double>> ElementWiseMatMult(ArrayList<ArrayList<Double>> M1, ArrayList<ArrayList<Double>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        ArrayList<Double> Row;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<ArrayList<Double>>();

            if(!RowCount1.equals(RowCount2)){
                throw new IllegalArgumentException("rowcount1 is not equal to rowcount2") ;
            }

            if(!ColCount1.equals(ColCount2)){
                throw new IllegalArgumentException("ColCount1 not equal to ColCount2") ;
            }
            
            for(int i=0;i<RowCount1;i++){
                Row = new ArrayList<>() ;
                for(int j=0;j<ColCount1;j++){
                    Row.add(M1.get(i).get(j)*M2.get(i).get(j));
                }
                MOut.add(Row);
            }
        return MOut ;

    }
    
    public static ArrayList<ArrayList<Double>> ElementWiseMatAdd(ArrayList<ArrayList<Double>> M1, ArrayList<ArrayList<Double>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        ArrayList<Double> Row;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<ArrayList<Double>>();

            if(!RowCount1.equals(RowCount2)){
                throw new IllegalArgumentException("rowcount1 is not equal to rowcount2") ;
            }

            if(!ColCount1.equals(ColCount2)){
                throw new IllegalArgumentException("ColCount1 not equal to ColCount2") ;
            }
            
            for(int i=0;i<RowCount1;i++){
                Row = new ArrayList<>() ;
                for(int j=0;j<ColCount1;j++){
                    Row.add(M1.get(i).get(j)+M2.get(i).get(j));
                }
                MOut.add(Row);
            }
        return MOut ;
    }
    
    public static ArrayList<ArrayList<Double>> ElementWiseVecDivide(ArrayList<ArrayList<Double>> V, ArrayList<ArrayList<Double>> M, Integer RowWise){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        Integer ColCount2 = M.get(0).size() ;
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();
        
        if(RowWise.equals(0)){
            if(!RowCount1.equals(1)){
                throw new IllegalArgumentException("First aregument is not a vector") ;
            }

            if(!ColCount1.equals(RowCount2)){
                throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
            }

            for(int i=0 ; i<ColCount2 ; i++){
                Row = new ArrayList<>();
                for(int j=0; j<ColCount1 ; j++){
                    Row.add(M.get(j).get(i)/V.get(0).get(j));
                }
                MOut.add(Row) ;
            }
        }
        else {
            if(!RowCount1.equals(1)){
                throw new IllegalArgumentException("First aregument is not a vector") ;
            }

            if(!ColCount1.equals(ColCount2)){
                throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
            }

            for(int i=0 ; i<RowCount2 ; i++){
                Row = new ArrayList<>();
                for(int j=0; j<ColCount2 ; j++){
                    Row.add(M.get(i).get(j)/V.get(0).get(j));
                }
                MOut.add(Row) ;
            }
        }
        //////System.out.println("debugg2");
        //////System.out.println(MOut);
        return MOut ;

    }
    
    public static ArrayList<ArrayList<Double>> ColAdd(ArrayList<ArrayList<Double>> V, ArrayList<ArrayList<Double>> M, Integer ColNo){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        ArrayList<ArrayList<Double>> MOut = M;
        
        if(!RowCount1.equals(1)){
            throw new IllegalArgumentException("1st Argument is not  vector");
        }
        if(!ColCount1.equals(RowCount2)){
            throw new IllegalArgumentException("1st Argument Colcount != 2nd Argument RowCount");
        }
        
        for(int i=0 ; i<RowCount2 ; i++) {
//            ////System.out.println("MOut ColAdd Init");
//            ////System.out.println(MOut);
            MOut.get(i).set(ColNo,M.get(i).get(ColNo)+V.get(0).get(i)) ;
//            ////System.out.println("MOut ColAdd After");
//            ////System.out.println(MOut);
        }
        return MOut ;
    }

    public static ArrayList<ArrayList<Double>> Zeros(Integer RowCount,Integer ColCount){
            ArrayList<ArrayList<Double>> Out=new ArrayList<>();

            for(int i=0;i<RowCount;i++){
                    ArrayList<Double> tmp=new ArrayList<Double>();
                    for(int j=0;j<ColCount;j++){
                            tmp.add(0.0);
                    }
                    Out.add(tmp);
            }
            return Out;
    }

    public static ArrayList<ArrayList<Double>> ElementWiseScalerDivide(Double Factor,ArrayList<ArrayList<Double>> Mat){
        Integer RowCount = Mat.size() ;
        Integer ColCount = Mat.get(0).size() ;
        
        for(int i=0 ; i<RowCount ; i++){
            for(int j =0 ; j<ColCount ; j++) {
                Mat.get(i).set(j, Mat.get(i).get(j)/Factor) ;
            }
        }
        
        return Mat ;
    }
    
    public static Double GetVecSum(ArrayList<Double> li){
        Double tmp_sum=0.0;
        for(int i=0;i<li.size();i++){
                tmp_sum+=li.get(i);
        }
        return tmp_sum;
    }
    public static Double GetLogVecSum(ArrayList<Double> AlphaSum){
        Double sum=0.0;
        //System.out.println("size: "+AlphaSum.size());
        for(int i=0;i<AlphaSum.size();i++){
            sum+=(Double)Math.log(AlphaSum.get(i));
        }
        //System.out.println("sum: "+sum);
        return sum;
    }
    
    public static ArrayList<ArrayList<ArrayList<Double>>> TakeInputLambda(Scanner sc){
        ArrayList<ArrayList<ArrayList<Double>>> Out = new ArrayList<>();
        // initial estimate of transition matrix
        int t_row=sc.nextInt();
        int t_col=sc.nextInt();
        ArrayList<ArrayList<Double>> trans_vec=new ArrayList<ArrayList<Double>>();
        for(int i=0;i<t_row;i++){
                ArrayList<Double> vec_tmp=new ArrayList<Double>();
                for(int j=0;j<t_col;j++){
                        vec_tmp.add(sc.nextDouble());
                }
                trans_vec.add(vec_tmp);
        }
        Out.add(trans_vec);
        // initial estimate of emission matrix

        int e_row=sc.nextInt();
        int e_col=sc.nextInt();  //this is same as M(number of discrete observation states)

        ArrayList<ArrayList<Double>> emi_vec=new ArrayList<>();
        for(int i=0;i<e_row;i++){
                ArrayList<Double> vec_tmp=new ArrayList<>();
                for(int j=0;j<e_col;j++){
                        vec_tmp.add(sc.nextDouble());
                }
                emi_vec.add(vec_tmp);
        }
        Out.add(emi_vec);
        // initial probability distribution

        int init_row=sc.nextInt();
        int init_col=sc.nextInt();
        ArrayList<ArrayList<Double>> init_vec=new ArrayList<>();
        ArrayList<Double> vec_tmp=new ArrayList<>();
        for(int i=0;i<init_col;i++){
                vec_tmp.add(sc.nextDouble());            
        }

        init_vec.add(vec_tmp);
        Out.add(init_vec);
        //  observation sequence
        return Out ;
    }
    public static ArrayList<Integer> TakeInputObsSeq(Scanner sc){
        
        int obs_num=sc.nextInt();
        ArrayList<Integer> obs_vec=new ArrayList<>();
        
        for(int i=0;i<obs_num;i++){
                int f=sc.nextInt();
                obs_vec.add(f);     
        }
        return obs_vec ;
    }
    
    public static void RetVal(ArrayList<ArrayList<ArrayList<Double>>> Out){
        
        ArrayList<ArrayList<Double>> A=Out.get(0);
        ////System.out.println("A: "+A);
        int Arow=A.size();
        int Acol=A.get(0).size();
        ArrayList<ArrayList<Double>> B=Out.get(1);
        ////System.out.println("B: "+B);
        int Brow=B.size();
        int Bcol=B.get(0).size();
        
        ArrayList<ArrayList<Double>> Pi=Out.get(2);
        ////System.out.println("A: "+A);
        int Pirow=Pi.size();
        int Picol=Pi.get(0).size();
        
        String[] str_A=new String[Arow*Acol+2];
        String[] str_B=new String[Brow*Bcol+2];
        String[] str_Pi=new String[Pirow*Picol+2];
        
        str_A[0]=Integer.toString(Arow);
        str_A[1]=Integer.toString(Acol);
        str_B[0]=Integer.toString(Brow);
        str_B[1]=Integer.toString(Bcol);
        str_Pi[0]=Integer.toString(Pirow);
        str_Pi[1]=Integer.toString(Picol);
        
        int k=2;
        for(int i=0;i<Arow;i++){
            for(int j=0;j<Acol;j++){
                str_A[k]=Double.toString(A.get(i).get(j));
                k+=1;
            }
        }
        
        int l=2;
        for(int i=0;i<Brow;i++){
            for(int j=0;j<Bcol;j++){
                str_B[l]=Double.toString(B.get(i).get(j));
                l+=1;
            }
        }
        l=2 ;
        for(int i=0;i<Pirow;i++){
            for(int j=0;j<Picol;j++){
                str_Pi[l]=Double.toString(Pi.get(i).get(j));
                l+=1;
            }
        }
        
        System.out.println(String.join(" ",str_A));
        System.out.println(String.join(" ", str_B));
//        System.out.println(String.join(" ", str_Pi));
        
    }
    public static Double GetDist(ArrayList<ArrayList<Double>> Mat1, ArrayList<ArrayList<Double>> Mat2){
        Integer RowCount = Mat1.size() ;
        Integer ColCount = Mat1.get(0).size() ;
        Double Sum =0.0 ;
        for(int i=0 ; i<RowCount ; i++){
            for (int j=0 ; j<ColCount ; j++){
                Sum+= (Mat1.get(i).get(j)-Mat2.get(i).get(j))*(Mat1.get(i).get(j)-Mat2.get(i).get(j)) ;
//                System.out.println("j"+j+"Sum: "+Sum);
            }
        }
        return Sum ;
    }
    public static void PrintDist(ArrayList<ArrayList<ArrayList<Double>>> Lambda, ArrayList<ArrayList<ArrayList<Double>>> LambdaUsed) {
        for(int i=0 ; i<3 ; i++){
            System.out.println("Distance between "+i+"  "+GetDist(Lambda.get(i),LambdaUsed.get(i)));
        }
    }
    

    /**
     * @param args the command line arguments
     */
    Integer numStates ;
    Integer numObservations ;
    ArrayList<ArrayList<Double>> A = new ArrayList<>();
    ArrayList<ArrayList<Double>> B = new ArrayList<>();
    ArrayList<ArrayList<Double>> Pi = new ArrayList<>();
    ArrayList<ArrayList<Double>> AlphaSum = new ArrayList<>();
    ArrayList<Integer> D=new ArrayList<>();   //D gives the indices of the most likely states (Viterbi)

    ArrayList<Double> Row = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> delta_indices=new ArrayList<ArrayList<Integer>>();

    public Double ArrSum(ArrayList<Double> Arr){
        Double add=0.0;
        
        for(int j=0;j<Arr.size();j++){
            add+=Arr.get(j);
        }
        return add;
    }

    public ArrayList<Double> RStochastic(ArrayList<Double> li){
        Double sum=ArrSum(li);
        for(int i=0;i<li.size();i++){
            li.set(i,li.get(i)/sum);
        }
        return li;
    }
    public HMM(Integer noStates, Integer noObservations){
       
         ArrayList<Double> Row;
        for(int i = 0; i<noStates ; i++) {
            Row=new ArrayList<>();
            Double sum=0.0;
            for(int j=0 ; j<noStates ; j++){
                if(j!=noStates-1){
                    Double tt=((Math.random()*0.1)+0.9)*(1.0/noStates);
                    sum+=(tt);
                    Row.add(tt);
                }
                else{
                    Row.add(1-sum);
                }
            }
            this.A.add(Row) ;
        }
        
        for(int i = 0; i<noStates ; i++) {
            Row=new ArrayList<>();
            Double sum=0.0;
            for(int j=0 ; j<noObservations ; j++){
                
                if(j!=noObservations-1){
                    Double tmp=((Math.random()*0.1)+0.9)*(1.0/noObservations);
                    sum+=tmp;
                    Row.add(tmp);
                }
                else{
                    Row.add(1-sum);
                }

            }
            this.B.add(Row) ;
        }
        
        Row=new ArrayList<>();
        Double sum=0.0;
        for(int i = 0; i<noStates ; i++) {
                if(i!=noStates-1){
                    Double tmp=((Math.random()*0.1)+0.9)*(1.0/noStates);
                    sum+=tmp;
                    Row.add(tmp);
                }
                else{
                    Row.add(1-sum);
                }      
            }
        this.Pi.add(Row) ;

        
    }
    
    public HMM(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B, ArrayList<ArrayList<Double>> Pi){
            this.numStates = A.size() ;
            this.numObservations = B.get(0).size() ;
            this.A = A ;
            this.B = B ;
            this.Pi = Pi ;

    }
    
        // Alpha Calculation
    public ArrayList<ArrayList<Double>> GetAlphaInit(Integer Obs){
        ArrayList<ArrayList<Double>> Out = ElementWiseMult(this.Pi,this.B,0) ;
        ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
        Alpha.add(Out.get(Obs)) ;
        return Alpha ;
    }
    public ArrayList<ArrayList<Double>> GetAlpha(ArrayList<ArrayList<Double>> PrevAlpha,Integer Obs){
        // System.err.println("B");
        // System.err.println(B);
        // System.err.println("A");
        // System.err.println(A);
        // System.err.println("Pi");
        // System.err.println(Pi);
       ArrayList<ArrayList<Double>> Out = ElementWiseMult(Mult(PrevAlpha,this.A),this.B,0) ;
       ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
       Alpha.add(Out.get(Obs)) ;
       return Alpha ;
    }
    //Alpha calculation ended
    
    //Probability of given observation sequence
    public ArrayList<ArrayList<Double>> GetObsSeqProb(ArrayList<Integer> ObsSeq){
       
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Double>> Alpha = GetAlphaInit(ObsSeq.get(0)) ;
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Alpha = GetAlpha(Alpha,ObsSeq.get(i)) ;
       }       
       return Alpha ;
    }
    
    //Beta Calculation
    public ArrayList<ArrayList<Double>> GetPrevBeta(ArrayList<ArrayList<Double>> FutureBeta, Integer Obs){
       ArrayList<ArrayList<Double>> Out = new ArrayList<>() ;
       Out.add(ElementWiseMult(FutureBeta,B,0).get(Obs));
       ////System.out.println("debugg3");
       ////System.out.println(A);
       ArrayList<ArrayList<Double>> Beta = Mult(Out,Transpose(A)) ;
       return Beta ;
    }
    //Di-gamma Calculation
    public ArrayList<ArrayList<Double>> GetDiGamma(ArrayList<ArrayList<Double>> Alpha, ArrayList<ArrayList<Double>> Beta, Integer Obs){
        ArrayList<ArrayList<Double>> Temp = new ArrayList<>() ;
        //////System.out.println("debugg1");
        //////System.out.println(B);
        Temp.add(Transpose(B).get(Obs)) ;
        //////System.out.println(Transpose(B));
        ArrayList<ArrayList<Double>> Gamma =  ElementWiseMult(Temp,ElementWiseMatMult(Mult(Transpose(Alpha),Beta),A),1) ;
        return Gamma ;   
    }
    //Gaama Calculation
    public ArrayList<ArrayList<Double>> GetGamma(ArrayList<ArrayList<Double>> DiGamma){
        ArrayList<ArrayList<Double>> Gamma = new ArrayList<>() ;
        ArrayList<Double> Row = new ArrayList<>() ;
        Double Sum ;
        for(int i=0 ; i<DiGamma.size() ; i++) {
            Sum = 0.0 ;
            for(int j=0 ; j<DiGamma.size() ; j++){
                Sum+=DiGamma.get(i).get(j) ;
            }
            Row.add(Sum) ;
        }
        Gamma.add(Row) ;
        return Gamma ;
    }
     
    public static ArrayList<ArrayList<Double>> GetAlphaScaledList(Double Factor,ArrayList<ArrayList<Double>> Mat){

        for(int i=0;i<Mat.get(0).size();i++){
            Mat.get(0).set(i,Mat.get(0).get(i)/Factor);
        }

        return Mat;
    }

    public ArrayList<ArrayList<ArrayList<Double>>> GetAlphaList(ArrayList<Integer> ObsSeq){

        ArrayList<ArrayList<ArrayList<Double>>> MOut=new ArrayList<>();
        ArrayList<ArrayList<Double>> AlphaList=new ArrayList<>();
        ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
        ArrayList<ArrayList<Double>> SumList=new ArrayList<>();
        ArrayList<Double> TempSum = new ArrayList<>() ;
        Double Sum=0.0;

        for(int i=0;i<ObsSeq.size();i++){
            if(i == 0){
                Alpha = GetAlphaInit(ObsSeq.get(i)) ;
                Sum=GetVecSum(Alpha.get(0));
                TempSum.add(Sum);
                Alpha=GetAlphaScaledList(Sum,Alpha);
                AlphaList.add(Alpha.get(0));
            }
            else{
                Alpha = GetAlpha(Alpha,ObsSeq.get(i)) ;
                Sum=GetVecSum(Alpha.get(0));
                TempSum.add(Sum);
                Alpha=GetAlphaScaledList(Sum,Alpha);
                AlphaList.add(Alpha.get(0));
            }
        }
        SumList.add(TempSum);
        
        MOut.add(AlphaList);
        //System.err.println("Sumlist");
        //System.err.println(SumList);
        MOut.add(SumList);
        
        //this.alpha=tmp;
        ////System.out.println(MOut);
        return MOut;
    }
    
        //Beta List Formation
    public ArrayList<ArrayList<Double>> GetBetaList(ArrayList<Integer> ObsSeq,ArrayList<Double> AlphaSum){

        ArrayList<ArrayList<Double>> BetaList=new ArrayList<>();
        ArrayList<Double> BetaInit=new ArrayList<>();
        int NumStates=A.size();
        ArrayList<ArrayList<Double>> FutureBeta=new ArrayList<>(); // twoD arraylist

        for(int k=0;k<NumStates;k++){
                BetaInit.add(1.0);
        }

        ArrayList<ArrayList<Double>> temp=new ArrayList<>();
        temp.add(BetaInit) ;
        BetaInit=ElementWiseScalerDivide(AlphaSum.get(AlphaSum.size()-1),temp).get(0);
        BetaList.add(BetaInit);

        ArrayList<ArrayList<Double>> prev_beta=new ArrayList<>();
        FutureBeta.add(BetaInit);
        for(int j=1;j<ObsSeq.size();j++){
                prev_beta=GetPrevBeta(FutureBeta,ObsSeq.get(ObsSeq.size()-j));
                //System.out.println("prev_beta");
                //System.out.println(prev_beta);
                prev_beta=ElementWiseScalerDivide(AlphaSum.get(AlphaSum.size()-1-j),prev_beta);
                BetaList.add(prev_beta.get(0));
                FutureBeta=prev_beta;
        }
        Collections.reverse(BetaList);
        ////System.out.println("BetaList");
        ////System.out.println(BetaList);
        return BetaList;
    }
    public Boolean Convergence(ArrayList<ArrayList<Double>> PrevAlphaSum, ArrayList<ArrayList<Double>> NewAlphaSum){
        Double Sum=0.0;

        for(int i=0;i<PrevAlphaSum.get(0).size();i++){
            Sum+=(Double)Math.log(PrevAlphaSum.get(0).get(i)) - (Double)Math.log(NewAlphaSum.get(0).get(i));
        }
        if(Sum>=0.0)
            return true ;
        else
            return false ;    
    }
    
    public ArrayList<ArrayList<ArrayList<Double>>> LearnEpoch(ArrayList<Integer> ObsSeq){
        
        ArrayList<ArrayList<ArrayList<Double>>> ScaledAlphaAndAlphaSum = GetAlphaList(ObsSeq) ;
        ArrayList<ArrayList<Double>> ScaledAlphaList = ScaledAlphaAndAlphaSum.get(0) ;
        ArrayList<ArrayList<Double>> AlphaSum = ScaledAlphaAndAlphaSum.get(1) ;
        ArrayList<ArrayList<Double>> ScaledBetaList =  GetBetaList(ObsSeq,AlphaSum.get(0)) ;
        ArrayList<ArrayList<Double>> ScaledAlpha = new ArrayList<>();
        ArrayList<ArrayList<Double>> ScaledBeta = new ArrayList<>();
        ArrayList<ArrayList<Double>> DiGamma = new ArrayList<>();
        ArrayList<ArrayList<Double>> Gamma = new ArrayList<>();
        ArrayList<ArrayList<Double>> DiGammaSum = new ArrayList<>();
        ArrayList<ArrayList<Double>> GammaSum = new ArrayList<>();
        ArrayList<ArrayList<Double>> GammaSumObsSel = Zeros(B.size(),B.get(0).size()) ;
        ArrayList<ArrayList<Double>> NewA = new ArrayList<>();
        ArrayList<ArrayList<Double>> NewB = new ArrayList<>();
        ArrayList<ArrayList<Double>> NewPi = new ArrayList<>();

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
                DiGammaSum = ElementWiseMatAdd(DiGammaSum,DiGamma) ;
                GammaSum = ElementWiseMatAdd(GammaSum,Gamma) ;
            } 
            // //System.out.println("Gamma");
            // //System.out.println(Gamma);
            GammaSumObsSel = ColAdd(Gamma,GammaSumObsSel, ObsSeq.get(i)) ;
            // //System.out.println("GammaSumObsSel");
            // //System.out.println(GammaSumObsSel);
//            ////System.out.println("DiGammaSum");
//            ////System.out.println(DiGamma);
//            ////System.out.println("GammaSum");
//            ////System.out.println(Gamma);
//            ////System.out.println("GammaSumObsSel");
//            ////System.out.println(GammaSumObsSel);
        }
        NewA = Transpose(ElementWiseVecDivide(GammaSum,DiGammaSum,0)); 
        NewB = Transpose(ElementWiseVecDivide(GammaSum,GammaSumObsSel,0));
        this.A=NewA;
        this.B=NewB;
        this.Pi=NewPi;
        ArrayList<ArrayList<ArrayList<Double>>> Out = new ArrayList<>() ;
        Out.add(NewA) ;
        Out.add(NewB) ;
        Out.add(NewPi);
        Out.add(AlphaSum) ;
        //////System.out.println("Out");
        //////System.out.println(Out);
        return Out ;
    }  
        //Probability of given observation sequence
        public ArrayList<ArrayList<Double>> GetEmissionSequenceProb(ArrayList<Integer> ObsSeq){
       
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Double>> Alpha = GetAlphaInit(ObsSeq.get(0)) ;
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Alpha = GetAlpha(Alpha,ObsSeq.get(i)) ;
       }       
       return Alpha ;
        }
     
     public ArrayList<Double> PredictNextObservation (ArrayList<Integer> ObsSeq){
        ArrayList<ArrayList<Double>> LastAlpha = GetEmissionSequenceProb(ObsSeq) ;
        //Alpha is prob of observation and state, we should get probability of state given observation sequence probabilty and hence divide by observation sequence prob
        //LastAlpha = ElementWiseScalerDivide(GetVecSum(LastAlpha.get(0)), LastAlpha) ;
        ArrayList<ArrayList<Double>> Temp = Mult(Mult(LastAlpha,A),B) ;
//        System.err.println(Temp);
        ArrayList<Double> Out = new ArrayList<>();
        Double Max = 0.0 ;
        Double MaxInd = 0.0 ;
        for(int i=0 ; i<Temp.get(0).size() ; i++ ){
            if(Temp.get(0).get(i) >Max ){
                Max = Temp.get(0).get(i) ;
                MaxInd = (double) i ;
            }
                
        }
        Out.add(Max) ;
        Out.add(MaxInd) ;
        
        return Out ;
        
    }
    
    public ArrayList<ArrayList<ArrayList<Double>>> Learn(ArrayList<Integer> ObsSeq, Integer NoEpochs){
        ArrayList<ArrayList<ArrayList<Double>>> Temp = new ArrayList<>() ;
        ArrayList<ArrayList<ArrayList<Double>>> PrevTemp = new ArrayList<>() ;
        
        Temp = LearnEpoch(ObsSeq) ;
        
        Double oldlogprob=-Double.POSITIVE_INFINITY;
        Double newlogprob;

        for (int i=1 ; i<NoEpochs ; i++){
            //System.out.println("Iteration : "+i);
            PrevTemp = Temp ;
            
            oldlogprob= GetLogVecSum(PrevTemp.get(3).get(0));
            //System.out.println("oldlogprob");
            //System.out.println(oldlogprob);

            Temp = LearnEpoch(ObsSeq) ;
            newlogprob=GetLogVecSum(Temp.get(3).get(0));
            
            //System.out.println("newlogprob");
            //System.out.println(newlogprob);
            if(newlogprob<oldlogprob){
                //System.err.println("Converged:"+i);
                //this.AlphaSum=PrevTemp.get(3);
                this.A = PrevTemp.get(0) ;
                this.B = PrevTemp.get(1) ;
                this.Pi = PrevTemp.get(2) ;
                // System.err.println("A");
                // System.err.println(this.A);
                // System.err.println("B");
                // System.err.println(this.B);
                // System.err.println("Pi");
                // System.err.println(this.Pi);
                return PrevTemp ;
            }
            
        }

        this.A = Temp.get(0) ;
        this.B = Temp.get(1) ;
        this.Pi = Temp.get(2) ;
        // System.err.println("A");
        // System.err.println(this.A);
        // System.err.println("B");
        // System.err.println(this.B);
        // System.err.println("Pi");
        // System.err.println(this.Pi);
        //this.AlphaSum=Temp.get(3);
        return Temp;
        
    }
    
    public ArrayList<Double> GetEmiProb(ArrayList<Integer> Observe,int ind){
        
        ArrayList<Double> b1=new ArrayList<Double>();
        for(int k=0;k<this.B.size();k++){
            b1.add(this.B.get(k).get(Observe.get(ind)));
        }
        return b1;
    }


    public static int maximum(ArrayList<Double> arrli){

        Double ma=-1.0;
        int ind=-1;
        ArrayList<Double> ret= new ArrayList<Double>();

        for(int j=0;j<arrli.size();j++){
            if(arrli.get(j)>ma){
                ma=arrli.get(j);
                ind=j;
            }
        }
        return ind; 
    }

        
    public ArrayList<Integer> Backtrack(ArrayList<ArrayList<Double>> finaldelta,int num_obs){
        
        int[] states=new int[num_obs];
        //System.err.println(finaldelta.get(0));
        states[num_obs-1]=maximum(finaldelta.get(0));
        //System.err.println("delta indices");
        //System.err.println(delta_indices);
        for(int j=1;j<num_obs;j++){
            //System.err.println("delta_indices.size()-j");
            //System.err.println(delta_indices.size()-j);
            //System.err.println("numobs-j");
            //System.err.println(states[num_obs-j]);
            states[num_obs-1-j]=delta_indices.get(delta_indices.size()-j).get(states[num_obs-j]);
        }
       ArrayList<Integer> State=new ArrayList<>();
       for(int k=0;k<states.length;k++){
        State.add(states[k]);
       }
       return State;
    }    
    
    public ArrayList<ArrayList<Double>> ElementWiseMult_V(ArrayList<ArrayList<Double>> V, ArrayList<Double> M){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer ColCount2 = M.size() ;
       
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();
        
        if(!RowCount1.equals(1)){
            throw new IllegalArgumentException("First argument is not a vector") ;
        }
        Row = new ArrayList<>();
        for(int j=0; j<ColCount1 ; j++){
            Row.add(V.get(0).get(j)*M.get(j));
        }
        MOut.add(Row) ;

        return MOut ;    
    }

    public static ArrayList<ArrayList<Double>> Max_Mult_V(ArrayList<ArrayList<Double>> M1, ArrayList<ArrayList<Double>> M2){
        
        int RowCount1 = M1.size() ;
        int ColCount1 = M1.get(0).size() ;
        int RowCount2 = M2.size() ;
        int ColCount2 = M2.get(0).size() ;
        Double Max_val = 0.0;
        int max_ind=-1;
        ArrayList<Double> Row=new ArrayList<Double>(); 
        ArrayList<ArrayList<Double>> MOut = new ArrayList<ArrayList<Double>>();
        ArrayList<Integer> index=new ArrayList<Integer>();

        if(ColCount1!=(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<Double>();
            for(int j=0; j<ColCount2 ; j++){
                Max_val=0.0 ;
                max_ind=-1;
                for(int k=0 ; k<ColCount1 ; k++){
                    if(M1.get(i).get(k)*M2.get(k).get(j) >=Max_val){
                     Max_val=M1.get(i).get(k)*M2.get(k).get(j);
                     max_ind=k;
                    }
                }
                Row.add(Max_val);
                index.add(max_ind);
            }
            MOut.add(Row) ;
            delta_indices.add(index);
        }
        return MOut ;
    }


    public ArrayList<Integer> Viterbi(ArrayList<Integer> ObsSeq){

        ArrayList<ArrayList<Double>> delta1=new ArrayList<ArrayList<Double>>();

        ArrayList<Double> b_tmp=GetEmiProb(ObsSeq,0);
        
        //initial probability
        delta1=ElementWiseMult_V(this.Pi,b_tmp);

        ArrayList<Integer> temp_ind=new ArrayList<Integer>();
        for(int k=0;k<delta1.get(0).size();k++){
            temp_ind.add(null);
        }
        delta_indices.add(temp_ind); // this is for the first iteration

        ArrayList<ArrayList<Double>> delta2=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> temp=new ArrayList<ArrayList<Double>>();
        
        for(int i=1;i<ObsSeq.size();i++){
            
            b_tmp=GetEmiProb(ObsSeq,i);
            temp=Max_Mult_V(delta1,this.A);
            delta2=ElementWiseMult_V(temp,b_tmp);
            delta1=delta2;
        }
        // i will have my delta_indices and delta1 ready by now.Backtrack to find the result
        this.D=Backtrack(delta1,ObsSeq.size());
        return this.D;
    }

    public static void main(String[] args){

    Scanner sc=new Scanner(System.in);
    ArrayList<Integer> Obs = TakeInputObsSeq(sc) ;
    HMM Model = new HMM(3,4) ;
    // System.out.println(Model.A);
    // System.out.println(Model.B);
    // System.out.println(Model.Pi);
//    System.out.println(Model);
    ArrayList<ArrayList<ArrayList<Double>>> AB = Model.Learn(Obs,50) ;
    ArrayList<Integer> StateSeq=Model.Viterbi(Obs);
//    PrintDist(AB,LambdaUsed) ;

    RetVal(AB);
}  

    
}