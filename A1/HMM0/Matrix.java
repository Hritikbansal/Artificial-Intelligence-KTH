
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shrey
 */
public class Matrix  {
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
//            System.out.println("MOut ColAdd Init");
//            System.out.println(MOut);
            MOut.get(i).set(ColNo,M.get(i).get(ColNo)+V.get(0).get(i)) ;
//            System.out.println("MOut ColAdd After");
//            System.out.println(MOut);
        }
        return MOut ;
    }

    public static ArrayList<ArrayList<Double>> Zeros(Integer RowCount,Integer ColCount){
            ArrayList<ArrayList<Double>> Out=new ArrayList<ArrayList<Double>>();

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
           System.out.println("Alpha at iter i = "+i);
           System.out.println(Alpha);
       }
        
       
       return Alpha ;
    }
    //Beta calculation
    public static ArrayList<ArrayList<Double>> GetPrevBeta(ArrayList<ArrayList<Double>> NextBeta, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
       ArrayList<ArrayList<Double>> Out = new ArrayList<>() ;
       Out.add(ElementWiseMult(NextBeta,B,0).get(Obs));
       ArrayList<ArrayList<Double>> Beta = Mult(Out,A) ;
       return Beta ;
    }
    //Di-gamma Calculation
    public static ArrayList<ArrayList<Double>> GetDiGamma(ArrayList<ArrayList<Double>> Alpha, ArrayList<ArrayList<Double>> Beta,ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
        ArrayList<ArrayList<Double>> Temp = new ArrayList<>() ;
        Temp.add(Transpose(B).get(Obs)) ;
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
    	public static ArrayList<ArrayList<Double>> GetBetaList(ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq){

		ArrayList<ArrayList<Double>> BetaList=new ArrayList<ArrayList<Double>>();

		ArrayList<Double> BetaInit=new ArrayList<Double>();
		int NumStates=A.size();
		ArrayList<ArrayList<Double>> NextBeta=new ArrayList<ArrayList<Double>>(); // twoD arraylist

		for(int k=0;k<NumStates;k++){
			BetaInit.add(1.0);
		}
		BetaList.add(BetaInit);
		ArrayList<ArrayList<Double>> prev_beta=new ArrayList<ArrayList<Double>>();
		NextBeta.add(BetaInit);
		for(int j=1;j<ObsSeq.size();j++){
			prev_beta=GetPrevBeta(NextBeta,A,B,ObsSeq.get(ObsSeq.size()-j));
			BetaList.add(prev_beta.get(0));
			NextBeta=prev_beta;
		}
		Collections.reverse(BetaList);
		return BetaList;
	}
    //Alpha List
        public static ArrayList<ArrayList<ArrayList<Double>>> GetScaledAlphaBeta(ArrayList<ArrayList<Double>> AlphaList, ArrayList<ArrayList<Double>> BetaList){
		ArrayList<ArrayList<ArrayList<Double>>> MOut=new ArrayList<>();
		ArrayList<ArrayList<Double>> Sum=new ArrayList<>();
		ArrayList<Double> AlphaSum=new ArrayList<>();
		Double tmp=0.0;
		//finding the sum
		for(int i=0;i<AlphaList.size();i++){
			tmp=0.0;
			for(int j=0;j<AlphaList.get(0).size();j++){
				tmp+=AlphaList.get(i).get(j);
			}
			AlphaSum.add(tmp);
		}
		Sum.add(AlphaSum);
		//Scaling 
		for(int i=0;i<AlphaList.size();i++){
			for(int j=0;j<AlphaList.get(0).size();j++){
				AlphaList.get(i).set(j,AlphaList.get(i).get(j)/(AlphaSum.get(i)));
				BetaList.get(i).set(j,BetaList.get(i).get(j)/(AlphaSum.get(i)));
			}
		}

		MOut.add(AlphaList);
		MOut.add(BetaList);
		MOut.add(Sum);

		return MOut;

	}

	public static ArrayList<ArrayList<Double>> GetAlphaList(ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B,ArrayList<ArrayList<Double>> Pi,ArrayList<Integer> ObsSeq){

		ArrayList<ArrayList<Double>> AlphaList=new ArrayList<>();
                ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;

		for(int i=0;i<ObsSeq.size();i++){
			if(i == 0){
                            Alpha = GetObsProbInit(Pi, B,ObsSeq.get(i)) ;
                            AlphaList.add(Alpha.get(0));
                        }
                        else{
                            Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)) ;
                            AlphaList.add(Alpha.get(0));
                        }
		}
		return AlphaList;
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
        
        ArrayList<ArrayList<ArrayList<Double>>> ScaledAlphaBeta = GetScaledAlphaBeta(GetAlphaList(A,B,Pi,ObsSeq),GetBetaList(A,B,ObsSeq)) ;
        ArrayList<ArrayList<Double>> ScaledAlphaList = ScaledAlphaBeta.get(0) ;
        ArrayList<ArrayList<Double>> ScaledBetaList =  ScaledAlphaBeta.get(1) ;
        ArrayList<ArrayList<Double>> AlphaSum = ScaledAlphaBeta.get(2) ;
//        
//Print Beta List
//        System.out.println("BetaList");
//        System.out.println(BetaList);
        ArrayList<ArrayList<Double>> ScaledAlpha = new ArrayList<>();
        ArrayList<ArrayList<Double>> ScaledBeta = new ArrayList<>();
        ArrayList<ArrayList<Double>> DiGamma = new ArrayList<>();
        ArrayList<ArrayList<Double>> Gamma = new ArrayList<>();
        ArrayList<ArrayList<Double>> DiGammaSum = new ArrayList<>();
        ArrayList<ArrayList<Double>> GammaSum = new ArrayList<>();
        ArrayList<ArrayList<Double>> GammaSumObsSel = Zeros(B.size(),B.get(0).size()) ;
//        System.out.println("Intial GammaSumObsSel");
//        System.out.println(GammaSumObsSel);

        ArrayList<ArrayList<Double>> NewA = new ArrayList<>();
        ArrayList<ArrayList<Double>> NewB = new ArrayList<>();
        for(int i=0 ; i<ObsSeq.size()-1 ; i++){
//            System.out.println("Iteration:  "+ i);

            
            //check the list that you are taking
            ScaledAlpha.add(ScaledAlphaList.get(i)) ;
            ScaledBeta.add(ScaledBetaList.get(i+1)) ;
//            System.out.println("ScaledAlpha");
//            System.out.println(ScaledAlpha);
//            System.out.println("ScaledBeta");
//            System.out.println(ScaledBeta);
            DiGamma = GetDiGamma(ScaledAlpha, ScaledBeta, A, B, ObsSeq.get(i+1)) ;
//            System.out.println("DiGamma");
//            System.out.println(DiGamma);
            Gamma = GetGamma(DiGamma) ;
//            System.out.println("Gamma");
//            System.out.println(Gamma);
            ScaledAlpha = new ArrayList<>() ;
            ScaledBeta = new ArrayList<>() ;
            if(i==0){
                DiGammaSum = DiGamma ;
                GammaSum = Gamma ;
            }
            else{
                DiGammaSum = ElementWiseMatAdd(DiGammaSum,DiGamma) ;
                GammaSum = ElementWiseMatAdd(GammaSum,Gamma) ;
            } 
            GammaSumObsSel = ColAdd(Gamma,GammaSumObsSel, ObsSeq.get(i)) ;
//            System.out.println("DiGammaSum");
//            System.out.println(DiGamma);
//            System.out.println("GammaSum");
//            System.out.println(Gamma);
//            System.out.println("GammaSumObsSel");
//            System.out.println(GammaSumObsSel);
        }
        NewA = Transpose(ElementWiseVecDivide(GammaSum,DiGammaSum,0)); 
        NewB = Transpose(ElementWiseVecDivide(GammaSum,GammaSumObsSel,0));
        
        ArrayList<ArrayList<ArrayList<Double>>> Out = new ArrayList<>() ;
        Out.add(NewA) ;
        Out.add(NewB) ;
        Out.add(AlphaSum) ;
        
        return Out ;
    }   
    
    
    
    public static ArrayList<ArrayList<ArrayList<Double>>> Learn(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B,ArrayList<ArrayList<Double>> Pi,ArrayList<Integer> ObsSeq, Integer NoEpochs){
        ArrayList<ArrayList<ArrayList<Double>>> Temp = new ArrayList<>() ;
        ArrayList<ArrayList<ArrayList<Double>>> PrevTemp = new ArrayList<>() ;
        
        Temp = LearnEpoch(A, B, Pi, ObsSeq) ;
        
        System.out.println("New A");
        System.out.println(Temp.get(0));
        
        System.out.println("New B");
        System.out.println(Temp.get(1));
        
//        ArrayList<ArrayList<Double>> NewAlpha = GetObsSeqProb(Pi, TempA, TempB, ObsSeq) ;
//        System.out.println("PrevAlpha");
//        System.out.println(PrevAlpha);
//        
//        System.out.println("NewAlpha");
//        System.out.println(NewAlpha);
        
//        System.out.println("Convergence");
//        System.out.println(Convergence(PrevAlpha,NewAlpha)) ;
            
        for (int i=0 ; i<NoEpochs ; i++){
            
            PrevTemp = Temp ;
            Temp = LearnEpoch(PrevTemp.get(0), PrevTemp.get(1), Pi, ObsSeq) ;
            if(!Convergence(PrevTemp.get(2),Temp.get(2))){
                break ;
            }
            
        }
        return PrevTemp ;
    }

    
//    public static ArrayList<ArrayList<Double>> GetMostProbObsInit(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> B, Integer Obs){
//        ArrayList<ArrayList<Double>> Out = ElementWiseMult(Pi,B) ;
//        ArrayList<ArrayList<Double>> Delta = new ArrayList<>() ;
//        Delta.add(Out.get(Obs)) ;
//        return Delta ;
//    }
//    public static ArrayList<ArrayList<Double>> GetMostProbObs(ArrayList<ArrayList<Double>> PrevAlpha, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
//       ArrayList<ArrayList<Double>> Out = ElementWiseMult(MaxMult(PrevAlpha,A),B) ;
//       ArrayList<ArrayList<Double>> Delta = new ArrayList<>() ;
//       Delta.add(Out.get(Obs)) ;
//       return Delta ;
//    }
//    
//    public static Integer GetArgMax(ArrayList<Double> Arr){
//        Double Max = 0.0 ;
//        Integer MaxInd = 0 ;
//        for (int i=0 ; i<Arr.size() ; i++){
//            if(Max < Arr.get(i)){
//                Max = Arr.get(i) ;
//                MaxInd = i ;
//            }
//        }
//        return MaxInd ;
//    }
//    
//    public static ArrayList<Integer> GetMostProbObsSeq(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq){
//       ArrayList<Integer> StateSeq = new ArrayList<>() ;
//       if(ObsSeq.isEmpty()){
//           throw new IllegalArgumentException("No observations found") ;
//       }
//       ArrayList<ArrayList<Double>> Delta = GetObsProbInit(Pi, B, ObsSeq.get(0)) ;
//       StateSeq.add(GetArgMax(Delta.get(0))) ;
//       
//       for(int i=1 ; i<ObsSeq.size() ; i++){
//           Delta = GetObsProb(Delta, A, B, ObsSeq.get(i)) ;
//       }
//       
//       return StateSeq ;
//    }
//    
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

        ArrayList<ArrayList<Double>> emi_vec=new ArrayList<ArrayList<Double>>();
        for(int i=0;i<e_row;i++){
                ArrayList<Double> vec_tmp=new ArrayList<Double>();
                for(int j=0;j<e_col;j++){
                        vec_tmp.add(sc.nextDouble());
                }
                emi_vec.add(vec_tmp);
        }
        Out.add(emi_vec);
        // initial probability distribution

        int init_row=sc.nextInt();
        int init_col=sc.nextInt();
        ArrayList<ArrayList<Double>> init_vec=new ArrayList<ArrayList<Double>>();
        ArrayList<Double> vec_tmp=new ArrayList<Double>();
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
        ArrayList<Integer> obs_vec=new ArrayList<Integer>();
        
        for(int i=0;i<obs_num;i++){
                int f=sc.nextInt();
                obs_vec.add(f);		
        }
        return obs_vec ;
    }
    
    public static void RetVal(ArrayList<ArrayList<ArrayList<Double>>> Out){
        
        ArrayList<ArrayList<Double>> A=Out.get(0);
        int Arow=A.size();
        int Acol=A.get(0).size();
        ArrayList<ArrayList<Double>> B=Out.get(1);
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
//        ArrayList<ArrayList<Double>> A = new ArrayList<>();
//        ArrayList<Double> Row = new ArrayList<>() ;
//        Row.add(.5) ;
//        Row.add(.5) ;
//        A.add(Row) ;
//        Row = new ArrayList<>() ;
//        Row.add(.6) ;
//        Row.add(.4) ;
//        A.add(Row) ;
//        System.out.println("A") ;
//        System.out.println(A);
//        
//        ArrayList<ArrayList<Double>> B = new ArrayList<>();
//        Row = new ArrayList<>() ;
//        Row.add(.7) ;
//        Row.add(.3) ;
//        B.add(Row) ;
//        Row = new ArrayList<>() ;
//        Row.add(.2) ;
//        Row.add(.8) ;
//        B.add(Row) ;
//        System.out.println("B") ;
//        System.out.println(B);
//        
//        ArrayList<ArrayList<Double>> Pi = new ArrayList<>();
//        Row = new ArrayList<>() ;
//        Row.add(.3) ;
//        Row.add(.7) ;
//        Pi.add(Row) ;
//        
//        ArrayList<ArrayList<Double>> Beta = new ArrayList<>();
//        Row = new ArrayList<>() ;
//        Row.add(.2) ;
//        Row.add(.4) ;
//        Beta.add(Row) ;
//        System.out.println("Beta") ;
//        System.out.println(Beta);
//        
//        ArrayList<ArrayList<Double>> Alpha = new ArrayList<>();
//        Row = new ArrayList<>() ;
//        Row.add(.3) ;
//        Row.add(.5) ;
//        Alpha.add(Row) ;
//        System.out.println("Alpha") ;
//        System.out.println(Alpha);
//        
//        ArrayList<Integer> ObsSeq = new ArrayList<>() ;
//        ObsSeq.add(1) ;
//        ObsSeq.add(0) ;
//        ObsSeq.add(1) ;
//        System.out.println("Observation Sequence") ;
//        System.out.println(ObsSeq);
//        
//        System.out.println("A*B") ;
//        System.out.println(Mult(A,B));
//        System.out.println("Transpose of A") ;
//        System.out.println(Transpose(A));
//        System.out.println("Element wise mult of beta and A") ;
//        System.out.println(ElementWiseMult(Beta,A,0));
//        
//        System.out.println("Next beta given A & B") ;
//        System.out.println(GetPrevBeta(Beta, A, B, 1));
//        
//        System.out.println("DiGaama") ;
//        ArrayList<ArrayList<Double>> DiGamma=GetDiGamma(Alpha, Beta, A, B, 1) ;
//        System.out.println(DiGamma);
//        
//        System.out.println("Gaama") ;
//        System.out.println(GetGamma(DiGamma));
//        
//        ArrayList<ArrayList<ArrayList<Double>>> AB = Learn(A, B, Pi, ObsSeq,3) ;
//        System.out.println("New A");
//        System.out.println(AB.get(0));
//        
//        System.out.println("New B");
//        System.out.println(AB.get(1));
        Scanner sc=new Scanner(System.in);
        ArrayList<ArrayList<ArrayList<Double>>> Lambda = TakeInputLambda(sc) ;
        System.out.println("Input A");
        System.out.println(Lambda.get(0));
        
        System.out.println("Input B");
        System.out.println(Lambda.get(1));
        
        System.out.println("Input Pi");
        System.out.println(Lambda.get(2));
        
        ArrayList<Integer> ObsSeq = TakeInputObsSeq(sc) ;
        System.out.println("Observation Sequence");
        System.out.println(ObsSeq);
        
        ArrayList<ArrayList<ArrayList<Double>>> AB = Learn(Lambda.get(0),Lambda.get(1),Lambda.get(2), ObsSeq,3) ;
        System.out.println("New A");
        System.out.println(AB.get(0));
        
        System.out.println("New B");
        System.out.println(AB.get(1));
        
        RetVal(AB);
    }  
}
