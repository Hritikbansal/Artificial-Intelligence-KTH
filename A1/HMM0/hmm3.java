
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
    public static ArrayList<ArrayList<Float>> Mult(ArrayList<ArrayList<Float>> M1, ArrayList<ArrayList<Float>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        Float Sum ;
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();
        
        if(!ColCount1.equals(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<>();
            for(int j=0; j<ColCount2 ; j++){
                Sum=(float)0 ;
                for(int k=0 ; k<ColCount1 ; k++){
                    Sum += M1.get(i).get(k)*M2.get(k).get(j) ;
                }
                Row.add(Sum) ;
            }
            MOut.add(Row) ;
        }
        return MOut ;
    }
    
    public static ArrayList<ArrayList<Float>> MaxMult(ArrayList<ArrayList<Float>> M1, ArrayList<ArrayList<Float>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        Float Max, Term ;
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();

        if(!ColCount1.equals(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<>();
            for(int j=0; j<ColCount2 ; j++){
                Max=(float)0 ;
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
    
    public static ArrayList<ArrayList<Float>> Transpose(ArrayList<ArrayList<Float>> M){
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();
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

    public static ArrayList<ArrayList<Float>> ElementWiseMult(ArrayList<ArrayList<Float>> V, ArrayList<ArrayList<Float>> M, Integer RowWise){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        Integer ColCount2 = M.get(0).size() ;
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();
        
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
    public static ArrayList<ArrayList<Float>> ElementWiseMatMult(ArrayList<ArrayList<Float>> M1, ArrayList<ArrayList<Float>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        ArrayList<Float> Row;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<ArrayList<Float>>();

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
    
    public static ArrayList<ArrayList<Float>> ElementWiseMatAdd(ArrayList<ArrayList<Float>> M1, ArrayList<ArrayList<Float>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        ArrayList<Float> Row;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<ArrayList<Float>>();

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
    
    public static ArrayList<ArrayList<Float>> ElementWiseVecDivide(ArrayList<ArrayList<Float>> V, ArrayList<ArrayList<Float>> M, Integer RowWise){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        Integer ColCount2 = M.get(0).size() ;
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();
        
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
    
    public static ArrayList<ArrayList<Float>> ColAdd(ArrayList<ArrayList<Float>> V, ArrayList<ArrayList<Float>> M, Integer ColNo){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        ArrayList<ArrayList<Float>> MOut = M;
        
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

    public static ArrayList<ArrayList<Float>> Zeros(Integer RowCount,Integer ColCount){
            ArrayList<ArrayList<Float>> Out=new ArrayList<>();

            for(int i=0;i<RowCount;i++){
                    ArrayList<Float> tmp=new ArrayList<Float>();
                    for(int j=0;j<ColCount;j++){
                            tmp.add((float)0);
                    }
                    Out.add(tmp);
            }
            return Out;
    }


//    public static ArrayList<Float> GetColumn(ArrayList<ArrayList<Float>> M, Integer ColNo){
//        Integer RowCount = M.size() ;
//        ArrayList<Float> Output = new ArrayList<>() ;
//        
//        for(int i=0 ; i<RowCount ; i++){
//            Output.add(M.get(i).get(ColNo)) ;
//        }
//        return Output ;
//    }
    
    // Alpha Calculation
    public static ArrayList<ArrayList<Float>> GetObsProbInit(ArrayList<ArrayList<Float>> Pi, ArrayList<ArrayList<Float>> B, Integer Obs){
        ArrayList<ArrayList<Float>> Out = ElementWiseMult(Pi,B,0) ;
        ArrayList<ArrayList<Float>> Alpha = new ArrayList<>() ;
        Alpha.add(Out.get(Obs)) ;
        return Alpha ;
    }
    public static ArrayList<ArrayList<Float>> GetObsProb(ArrayList<ArrayList<Float>> PrevAlpha, ArrayList<ArrayList<Float>> A,ArrayList<ArrayList<Float>> B, Integer Obs){
       ArrayList<ArrayList<Float>> Out = ElementWiseMult(Mult(PrevAlpha,A),B,0) ;
       ArrayList<ArrayList<Float>> Alpha = new ArrayList<>() ;
       Alpha.add(Out.get(Obs)) ;
       return Alpha ;
    }
    //Alpha calculation ended
    public static ArrayList<ArrayList<Float>> GetObsSeqProb(ArrayList<ArrayList<Float>> Pi, ArrayList<ArrayList<Float>> A,ArrayList<ArrayList<Float>> B, ArrayList<Integer> ObsSeq){
       
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Float>> Alpha = GetObsProbInit(Pi, B, ObsSeq.get(0)) ;
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)) ;
           ////System.out.println("Alpha at iter i = "+i);
           ////System.out.println(Alpha);
       }       
       return Alpha ;
    }
    //Beta calculation
    public static ArrayList<ArrayList<Float>> GetPrevBeta(ArrayList<ArrayList<Float>> NextBeta, ArrayList<ArrayList<Float>> A,ArrayList<ArrayList<Float>> B, Integer Obs){
       ArrayList<ArrayList<Float>> Out = new ArrayList<>() ;
       Out.add(ElementWiseMult(NextBeta,B,0).get(Obs));
       ////System.out.println("debugg3");
       ////System.out.println(A);
       ArrayList<ArrayList<Float>> Beta = Mult(Out,Transpose(A)) ;
       return Beta ;
    }
    //Di-gamma Calculation
    public static ArrayList<ArrayList<Float>> GetDiGamma(ArrayList<ArrayList<Float>> Alpha, ArrayList<ArrayList<Float>> Beta,ArrayList<ArrayList<Float>> A,ArrayList<ArrayList<Float>> B, Integer Obs){
        ArrayList<ArrayList<Float>> Temp = new ArrayList<>() ;
        //////System.out.println("debugg1");
        //////System.out.println(B);
        Temp.add(Transpose(B).get(Obs)) ;
        //////System.out.println(Transpose(B));
        ArrayList<ArrayList<Float>> Gamma =  ElementWiseMult(Temp,ElementWiseMatMult(Mult(Transpose(Alpha),Beta),A),1) ;
        return Gamma ;   
    }
    //Gaama Calculation
    public static ArrayList<ArrayList<Float>> GetGamma(ArrayList<ArrayList<Float>> DiGamma){
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
    //Beta List Formation
    public static ArrayList<ArrayList<Float>> GetBetaList(ArrayList<ArrayList<Float>> A,ArrayList<ArrayList<Float>> B, ArrayList<Integer> ObsSeq,ArrayList<Float> AlphaSum){

        ArrayList<ArrayList<Float>> BetaList=new ArrayList<>();
        ArrayList<Float> BetaInit=new ArrayList<>();
        int NumStates=A.size();
        ArrayList<ArrayList<Float>> NextBeta=new ArrayList<>(); // twoD arraylist

        for(int k=0;k<NumStates;k++){
                BetaInit.add((float)1);
        }

        ArrayList<ArrayList<Float>> temp=new ArrayList<>();
        temp.add(BetaInit) ;
        BetaInit=GetBetaScaledList(AlphaSum.get(AlphaSum.size()-1),temp).get(0);
        BetaList.add(BetaInit);

        ArrayList<ArrayList<Float>> prev_beta=new ArrayList<>();
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

	public static ArrayList<ArrayList<Float>> GetBetaScaledList(Float Factor,ArrayList<ArrayList<Float>> Mat){

		for(int i=0;i<Mat.get(0).size();i++){
			Mat.get(0).set(i,Mat.get(0).get(i)/Factor);
		}

		return Mat;
	}

    public static ArrayList<ArrayList<Float>> GetAlphaScaledList(Float Factor,ArrayList<ArrayList<Float>> Mat){

        for(int i=0;i<Mat.get(0).size();i++){
            Mat.get(0).set(i,Mat.get(0).get(i)/Factor);
        }

        return Mat;
    }
	
	public static Float GetSum(ArrayList<Float> li){
		Float tmp_sum=(float)0;
		for(int i=0;i<li.size();i++){
			tmp_sum+=li.get(i);
		}
		return tmp_sum;
	}

    public static ArrayList<ArrayList<ArrayList<Float>>> GetAlphaList(ArrayList<ArrayList<Float>> A,ArrayList<ArrayList<Float>> B,ArrayList<ArrayList<Float>> Pi,ArrayList<Integer> ObsSeq){

        ArrayList<ArrayList<ArrayList<Float>>> MOut=new ArrayList<>();
        ArrayList<ArrayList<Float>> AlphaList=new ArrayList<>();
        ArrayList<ArrayList<Float>> Alpha = new ArrayList<>() ;
        ArrayList<ArrayList<Float>> SumList=new ArrayList<>();
        ArrayList<Float> TempSum = new ArrayList<>() ;
        Float Sum=(float)0;

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
        public static Boolean Convergence(ArrayList<ArrayList<Float>> PrevAlphaSum, ArrayList<ArrayList<Float>> NewAlphaSum){
            Float Sum=(float)0;
            
            for(int i=0;i<PrevAlphaSum.get(0).size();i++){
                Sum+=(float)Math.log(PrevAlphaSum.get(0).get(i)) - (float)Math.log(NewAlphaSum.get(0).get(i));
            }
            if(Sum>=(float)0)
                return true ;
            else
                return false ;    
        }
    //Learning
    public static ArrayList<ArrayList<ArrayList<Float>>> LearnEpoch(ArrayList<ArrayList<Float>> A, ArrayList<ArrayList<Float>> B,ArrayList<ArrayList<Float>> Pi,ArrayList<Integer> ObsSeq){
        
        ArrayList<ArrayList<ArrayList<Float>>> ScaledAlphaAndAlphaSum = GetAlphaList(A,B,Pi,ObsSeq) ;
        ArrayList<ArrayList<Float>> ScaledAlphaList = ScaledAlphaAndAlphaSum.get(0) ;
        //System.out.println("ScaledAlpha");
        //System.out.println(ScaledAlphaList);
        ArrayList<ArrayList<Float>> AlphaSum = ScaledAlphaAndAlphaSum.get(1) ;
        //System.out.println("AlphaSum");
        //System.out.println(AlphaSum);
        ArrayList<ArrayList<Float>> ScaledBetaList =  GetBetaList(A,B,ObsSeq,AlphaSum.get(0)) ;
        //System.out.println("ScaledBeta");
        //System.out.println(ScaledBetaList);
        
//        
//Print Beta List
//        ////System.out.println("BetaList");
//        ////System.out.println(BetaList);
        ArrayList<ArrayList<Float>> ScaledAlpha = new ArrayList<>();
        ArrayList<ArrayList<Float>> ScaledBeta = new ArrayList<>();
        ArrayList<ArrayList<Float>> DiGamma = new ArrayList<>();
        ArrayList<ArrayList<Float>> Gamma = new ArrayList<>();
        ArrayList<ArrayList<Float>> DiGammaSum = new ArrayList<>();
        ArrayList<ArrayList<Float>> GammaSum = new ArrayList<>();
        ArrayList<ArrayList<Float>> GammaSumObsSel = Zeros(B.size(),B.get(0).size()) ;
//        ////System.out.println("Intial GammaSumObsSel");
//        ////System.out.println(GammaSumObsSel);

        ArrayList<ArrayList<Float>> NewA = new ArrayList<>();
        ArrayList<ArrayList<Float>> NewB = new ArrayList<>();
        ArrayList<ArrayList<Float>> NewPi = new ArrayList<>();

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
        
        ArrayList<ArrayList<ArrayList<Float>>> Out = new ArrayList<>() ;
        Out.add(NewA) ;
        Out.add(NewB) ;
        Out.add(NewPi);
        Out.add(AlphaSum) ;
        //////System.out.println("Out");
        //////System.out.println(Out);
        return Out ;
    }   
    
    public static Float GetLogProb(ArrayList<Float> AlphaSum){
        Float sum=(float)0;
        //System.out.println("size: "+AlphaSum.size());
        for(int i=0;i<AlphaSum.size();i++){
            sum+=(float)Math.log(AlphaSum.get(i));
        }
        //System.out.println("sum: "+sum);
        return sum;
    }
    
    public static ArrayList<ArrayList<ArrayList<Float>>> Learn(ArrayList<ArrayList<Float>> A, ArrayList<ArrayList<Float>> B,ArrayList<ArrayList<Float>> Pi,ArrayList<Integer> ObsSeq, Integer NoEpochs){
        ArrayList<ArrayList<ArrayList<Float>>> Temp = new ArrayList<>() ;
        ArrayList<ArrayList<ArrayList<Float>>> PrevTemp = new ArrayList<>() ;
        
        Temp = LearnEpoch(A, B, Pi, ObsSeq) ;
        
        ////System.out.println("New A");
        ////System.out.println(Temp.get(0));
        
        ////System.out.println("New B");
        ////System.out.println(Temp.get(1));
        Float oldlogprob=-Float.POSITIVE_INFINITY;
        Float newlogprob;

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
            if(newlogprob<oldlogprob){
                 return PrevTemp ;
             }            
        }
        return Temp;
        
    }

    

    public static ArrayList<ArrayList<ArrayList<Float>>> TakeInputLambda(Scanner sc){
        ArrayList<ArrayList<ArrayList<Float>>> Out = new ArrayList<>();
        // initial estimate of transition matrix
        int t_row=sc.nextInt();
        int t_col=sc.nextInt();
        ArrayList<ArrayList<Float>> trans_vec=new ArrayList<ArrayList<Float>>();
        for(int i=0;i<t_row;i++){
                ArrayList<Float> vec_tmp=new ArrayList<Float>();
                for(int j=0;j<t_col;j++){
                        vec_tmp.add(sc.nextFloat());
                }
                trans_vec.add(vec_tmp);
        }
        Out.add(trans_vec);
        // initial estimate of emission matrix

        int e_row=sc.nextInt();
        int e_col=sc.nextInt();  //this is same as M(number of discrete observation states)

        ArrayList<ArrayList<Float>> emi_vec=new ArrayList<>();
        for(int i=0;i<e_row;i++){
                ArrayList<Float> vec_tmp=new ArrayList<>();
                for(int j=0;j<e_col;j++){
                        vec_tmp.add(sc.nextFloat());
                }
                emi_vec.add(vec_tmp);
        }
        Out.add(emi_vec);
        // initial probability distribution

        int init_row=sc.nextInt();
        int init_col=sc.nextInt();
        ArrayList<ArrayList<Float>> init_vec=new ArrayList<>();
        ArrayList<Float> vec_tmp=new ArrayList<>();
        for(int i=0;i<init_col;i++){
                vec_tmp.add(sc.nextFloat());			
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
    
    public static void RetVal(ArrayList<ArrayList<ArrayList<Float>>> Out){
        
        ArrayList<ArrayList<Float>> A=Out.get(0);
        ////System.out.println("A: "+A);
        int Arow=A.size();
        int Acol=A.get(0).size();
        ArrayList<ArrayList<Float>> B=Out.get(1);
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
                str_A[k]=Float.toString(A.get(i).get(j));
                k+=1;
            }
        }
        
        int l=2;
        for(int i=0;i<Brow;i++){
            for(int j=0;j<Bcol;j++){
                str_B[l]=Float.toString(B.get(i).get(j));
                l+=1;
            }
        }
        
        System.out.println(String.join(" ",str_A));
        System.out.println(String.join(" ", str_B));
        
    }
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        ArrayList<ArrayList<ArrayList<Float>>> Lambda = TakeInputLambda(sc) ;
        ArrayList<Integer> ObsSeq = TakeInputObsSeq(sc) ;
        ArrayList<ArrayList<ArrayList<Float>>> AB = Learn(Lambda.get(0),Lambda.get(1),Lambda.get(2), ObsSeq,25) ;
        
        RetVal(AB);
    }  
}