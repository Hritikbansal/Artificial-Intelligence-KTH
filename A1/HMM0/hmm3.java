
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.math.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class hmm3  {
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


//    public static ArrayList<Double> GetColumn(ArrayList<ArrayList<Double>> M, Integer ColNo){
//        Integer RowCount = M.size() ;
//        ArrayList<Double> Output = new ArrayList<>() ;
//        
//        for(int i=0 ; i<RowCount ; i++){
//            Output.add(M.get(i).get(ColNo)) ;
//        }
//        return Output ;
//    }
    
    // Alpha Calculation
    public static ArrayList<ArrayList<Double>> GetObsProbInit(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> B, Integer Obs){
        ArrayList<ArrayList<Double>> Out = ElementWiseMult(Pi,B,0) ;
        ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
        Alpha.add(Out.get(Obs)) ;
        return Alpha ;
    }
    public static ArrayList<ArrayList<Double>> GetObsProb(ArrayList<ArrayList<Double>> PrevAlpha, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
       ArrayList<ArrayList<Double>> Out = ElementWiseMult(Mult(PrevAlpha,A),B,0) ;
       ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
       Alpha.add(Out.get(Obs)) ;
       return Alpha ;
    }
    //Alpha calculation ended
    public static ArrayList<ArrayList<Double>> GetObsSeqProb(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq){
       
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Double>> Alpha = GetObsProbInit(Pi, B, ObsSeq.get(0)) ;
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)) ;
           ////System.out.println("Alpha at iter i = "+i);
           ////System.out.println(Alpha);
       }       
       return Alpha ;
    }
    //Beta calculation
    public static ArrayList<ArrayList<Double>> GetPrevBeta(ArrayList<ArrayList<Double>> NextBeta, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
       ArrayList<ArrayList<Double>> Out = new ArrayList<>() ;
       Out.add(ElementWiseMult(NextBeta,B,0).get(Obs));
       ////System.out.println("debugg3");
       ////System.out.println(A);
       ArrayList<ArrayList<Double>> Beta = Mult(Out,Transpose(A)) ;
       return Beta ;
    }
    //Di-gamma Calculation
    public static ArrayList<ArrayList<Double>> GetDiGamma(ArrayList<ArrayList<Double>> Alpha, ArrayList<ArrayList<Double>> Beta,ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
        ArrayList<ArrayList<Double>> Temp = new ArrayList<>() ;
        //////System.out.println("debugg1");
        //////System.out.println(B);
        Temp.add(Transpose(B).get(Obs)) ;
        //////System.out.println(Transpose(B));
        ArrayList<ArrayList<Double>> Gamma =  ElementWiseMult(Temp,ElementWiseMatMult(Mult(Transpose(Alpha),Beta),A),1) ;
        return Gamma ;   
    }
    //Gaama Calculation
    public static ArrayList<ArrayList<Double>> GetGamma(ArrayList<ArrayList<Double>> DiGamma){
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
    //Beta List Formation
    public static ArrayList<ArrayList<Double>> GetBetaList(ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq,ArrayList<Double> AlphaSum){

        ArrayList<ArrayList<Double>> BetaList=new ArrayList<>();
        ArrayList<Double> BetaInit=new ArrayList<>();
        int NumStates=A.size();
        ArrayList<ArrayList<Double>> NextBeta=new ArrayList<>(); // twoD arraylist

        for(int k=0;k<NumStates;k++){
                BetaInit.add(1.0);
        }

        ArrayList<ArrayList<Double>> temp=new ArrayList<>();
        temp.add(BetaInit) ;
        BetaInit=GetBetaScaledList(AlphaSum.get(AlphaSum.size()-1),temp).get(0);
        BetaList.add(BetaInit);

        ArrayList<ArrayList<Double>> prev_beta=new ArrayList<>();
        NextBeta.add(BetaInit);
        for(int j=1;j<ObsSeq.size();j++){
                prev_beta=GetPrevBeta(NextBeta,A,B,ObsSeq.get(ObsSeq.size()-j));
                //System.out.println("prev_beta");
                //System.out.println(prev_beta);
                prev_beta=GetBetaScaledList(AlphaSum.get(AlphaSum.size()-1-j),prev_beta);
                BetaList.add(prev_beta.get(0));
                NextBeta=prev_beta;
        }
        Collections.reverse(BetaList);
        ////System.out.println("BetaList");
        ////System.out.println(BetaList);
        return BetaList;
    }

	public static ArrayList<ArrayList<Double>> GetBetaScaledList(Double Factor,ArrayList<ArrayList<Double>> Mat){

		for(int i=0;i<Mat.get(0).size();i++){
			Mat.get(0).set(i,Mat.get(0).get(i)/Factor);
		}

		return Mat;
	}

    public static ArrayList<ArrayList<Double>> GetAlphaScaledList(Double Factor,ArrayList<ArrayList<Double>> Mat){

        for(int i=0;i<Mat.get(0).size();i++){
            Mat.get(0).set(i,Mat.get(0).get(i)/Factor);
        }

        return Mat;
    }
	
	public static Double GetSum(ArrayList<Double> li){
		Double tmp_sum=0.0;
		for(int i=0;i<li.size();i++){
			tmp_sum+=li.get(i);
		}
		return tmp_sum;
	}

    public static ArrayList<ArrayList<ArrayList<Double>>> GetAlphaList(ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B,ArrayList<ArrayList<Double>> Pi,ArrayList<Integer> ObsSeq){

        ArrayList<ArrayList<ArrayList<Double>>> MOut=new ArrayList<>();
        ArrayList<ArrayList<Double>> AlphaList=new ArrayList<>();
        ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
        ArrayList<ArrayList<Double>> SumList=new ArrayList<>();
        ArrayList<Double> TempSum = new ArrayList<>() ;
        Double Sum=0.0;

        for(int i=0;i<ObsSeq.size();i++){
            if(i == 0){
                Alpha = GetObsProbInit(Pi, B,ObsSeq.get(i)) ;
                Sum=GetSum(Alpha.get(0));
                TempSum.add(Sum);
                Alpha=GetAlphaScaledList(Sum,Alpha);
                AlphaList.add(Alpha.get(0));
            }
            else{
                Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)) ;
                Sum=GetSum(Alpha.get(0));
                TempSum.add(Sum);
                Alpha=GetAlphaScaledList(Sum,Alpha);
                AlphaList.add(Alpha.get(0));
            }
        }
        SumList.add(TempSum);
        
        MOut.add(AlphaList);
        MOut.add(SumList);
        ////System.out.println(MOut);
        return MOut;
    }

        
    //Convergence Condition
        public static Boolean Convergence(ArrayList<ArrayList<Double>> PrevAlphaSum, ArrayList<ArrayList<Double>> NewAlphaSum){
            Double Sum=0.0;
            
            for(int i=0;i<PrevAlphaSum.get(0).size();i++){
                Sum+=Math.log(PrevAlphaSum.get(0).get(i)) - Math.log(NewAlphaSum.get(0).get(i));
            }
            if(Sum>=0.0)
                return true ;
            else
                return false ;    
        }
    //Learning
    public static ArrayList<ArrayList<ArrayList<Double>>> LearnEpoch(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B,ArrayList<ArrayList<Double>> Pi,ArrayList<Integer> ObsSeq){
        
        ArrayList<ArrayList<ArrayList<Double>>> ScaledAlphaAndAlphaSum = GetAlphaList(A,B,Pi,ObsSeq) ;
        ArrayList<ArrayList<Double>> ScaledAlphaList = ScaledAlphaAndAlphaSum.get(0) ;
        //System.out.println("ScaledAlpha");
        //System.out.println(ScaledAlphaList);
        ArrayList<ArrayList<Double>> AlphaSum = ScaledAlphaAndAlphaSum.get(1) ;
        //System.out.println("AlphaSum");
        //System.out.println(AlphaSum);
        ArrayList<ArrayList<Double>> ScaledBetaList =  GetBetaList(A,B,ObsSeq,AlphaSum.get(0)) ;
        //System.out.println("ScaledBeta");
        //System.out.println(ScaledBetaList);
        
//        
//Print Beta List
//        ////System.out.println("BetaList");
//        ////System.out.println(BetaList);
        ArrayList<ArrayList<Double>> ScaledAlpha = new ArrayList<>();
        ArrayList<ArrayList<Double>> ScaledBeta = new ArrayList<>();
        ArrayList<ArrayList<Double>> DiGamma = new ArrayList<>();
        ArrayList<ArrayList<Double>> Gamma = new ArrayList<>();
        ArrayList<ArrayList<Double>> DiGammaSum = new ArrayList<>();
        ArrayList<ArrayList<Double>> GammaSum = new ArrayList<>();
        ArrayList<ArrayList<Double>> GammaSumObsSel = Zeros(B.size(),B.get(0).size()) ;
//        ////System.out.println("Intial GammaSumObsSel");
//        ////System.out.println(GammaSumObsSel);

        ArrayList<ArrayList<Double>> NewA = new ArrayList<>();
        ArrayList<ArrayList<Double>> NewB = new ArrayList<>();
        ArrayList<ArrayList<Double>> NewPi = new ArrayList<>();

        for(int i=0 ; i<ObsSeq.size()-1 ; i++){
//            ////System.out.println("Iteration:  "+ i);
            //check the list that you are taking
            ScaledAlpha.add(ScaledAlphaList.get(i)) ;
            ScaledBeta.add(ScaledBetaList.get(i+1)) ;
            ////System.out.println("ScaledAlpha");
            ////System.out.println(ScaledAlpha);
            ////System.out.println("ScaledBeta");
            ////System.out.println(ScaledBeta);

            DiGamma = GetDiGamma(ScaledAlpha, ScaledBeta, A, B, ObsSeq.get(i+1)) ;
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
        
        ArrayList<ArrayList<ArrayList<Double>>> Out = new ArrayList<>() ;
        Out.add(NewA) ;
        Out.add(NewB) ;
        Out.add(NewPi);
        Out.add(AlphaSum) ;
        //////System.out.println("Out");
        //////System.out.println(Out);
        return Out ;
    }   
    
    public static Double GetLogProb(ArrayList<Double> AlphaSum){
        Double sum=0.0;
        //System.out.println("size: "+AlphaSum.size());
        for(int i=0;i<AlphaSum.size();i++){
            sum+=Math.log(AlphaSum.get(i));
        }
        //System.out.println("sum: "+sum);
        return sum;
    }
    
    public static ArrayList<ArrayList<ArrayList<Double>>> Learn(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B,ArrayList<ArrayList<Double>> Pi,ArrayList<Integer> ObsSeq, Integer NoEpochs){
        ArrayList<ArrayList<ArrayList<Double>>> Temp = new ArrayList<>() ;
        ArrayList<ArrayList<ArrayList<Double>>> PrevTemp = new ArrayList<>() ;
        
        Temp = LearnEpoch(A, B, Pi, ObsSeq) ;
        
        ////System.out.println("New A");
        ////System.out.println(Temp.get(0));
        
        ////System.out.println("New B");
        ////System.out.println(Temp.get(1));
        Double oldlogprob=-Double.POSITIVE_INFINITY;
        Double newlogprob;

        for (int i=1 ; i<NoEpochs ; i++){
            //System.out.println("Iteration : "+i);
            PrevTemp = Temp ;
            
            oldlogprob=GetLogProb(PrevTemp.get(3).get(0));
            //System.out.println("oldlogprob");
            //System.out.println(oldlogprob);

            Temp = LearnEpoch(PrevTemp.get(0), PrevTemp.get(1), PrevTemp.get(2), ObsSeq) ;
            newlogprob=GetLogProb(Temp.get(3).get(0));
            
            //System.out.println("newlogprob");
            //System.out.println(newlogprob);
            if(newlogprob>oldlogprob){
                 return PrevTemp ;
             }            
        }
        return Temp;
        
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
        
        String[] str_A=new String[Arow*Acol+2];
        String[] str_B=new String[Brow*Bcol+2];
        
        str_A[0]=Integer.toString(Arow);
        str_A[1]=Integer.toString(Acol);
        str_B[0]=Integer.toString(Brow);
        str_B[1]=Integer.toString(Bcol);
        
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
        
        System.out.println(String.join(" ",str_A));
        System.out.println(String.join(" ", str_B));
        
    }
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        ArrayList<ArrayList<ArrayList<Double>>> Lambda = TakeInputLambda(sc) ;
        ArrayList<Integer> ObsSeq = TakeInputObsSeq(sc) ;
        ArrayList<ArrayList<ArrayList<Double>>> AB = Learn(Lambda.get(0),Lambda.get(1),Lambda.get(2), ObsSeq,25) ;
        
        RetVal(AB);
    }  
}