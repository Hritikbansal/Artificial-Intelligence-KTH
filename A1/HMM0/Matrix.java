
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

    public static ArrayList<ArrayList<Double>> ElementWiseMult(ArrayList<ArrayList<Double>> V, ArrayList<ArrayList<Double>> M){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer RowCount2 = M.size() ;
        Integer ColCount2 = M.get(0).size() ;
        ArrayList<Double> Row ;
        ArrayList<ArrayList<Double>> MOut = new ArrayList<>();

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
        return MOut ;

    }
    public static ArrayList<ArrayList<Double>> GetObsProbInit(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> B, Integer Obs){
        ArrayList<ArrayList<Double>> Out = ElementWiseMult(Pi,B) ;
        ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
        Alpha.add(Out.get(Obs)) ;
        return Alpha ;
    }
    public static ArrayList<ArrayList<Double>> GetObsProb(ArrayList<ArrayList<Double>> PrevAlpha, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
       ArrayList<ArrayList<Double>> Out = ElementWiseMult(Mult(PrevAlpha,A),B) ;
       ArrayList<ArrayList<Double>> Alpha = new ArrayList<>() ;
       Alpha.add(Out.get(Obs)) ;
       return Alpha ;
    }
    
    public static ArrayList<ArrayList<Double>> GetObsSeqProb(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq){
       
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Double>> Alpha = GetObsProbInit(Pi, B, ObsSeq.get(0)) ;
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)) ;
       }
       
       return Alpha ;
    }
    
        public static ArrayList<ArrayList<Double>> GetMostProbObsInit(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> B, Integer Obs){
        ArrayList<ArrayList<Double>> Out = ElementWiseMult(Pi,B) ;
        ArrayList<ArrayList<Double>> Delta = new ArrayList<>() ;
        Delta.add(Out.get(Obs)) ;
        return Delta ;
    }
    public static ArrayList<ArrayList<Double>> GetMostProbObs(ArrayList<ArrayList<Double>> PrevAlpha, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, Integer Obs){
       ArrayList<ArrayList<Double>> Out = ElementWiseMult(MaxMult(PrevAlpha,A),B) ;
       ArrayList<ArrayList<Double>> Delta = new ArrayList<>() ;
       Delta.add(Out.get(Obs)) ;
       return Delta ;
    }
    
    public static Integer GetArgMax(ArrayList<Double> Arr){
        Double Max = 0.0 ;
        Integer MaxInd = 0 ;
        for (int i=0 ; i<Arr.size() ; i++){
            if(Max < Arr.get(i)){
                Max = Arr.get(i) ;
                MaxInd = i ;
            }
        }
        return MaxInd ;
    }
    
    public static ArrayList<Integer> GetMostProbObsSeq(ArrayList<ArrayList<Double>> Pi, ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq){
       ArrayList<Integer> StateSeq = new ArrayList<>() ;
       if(ObsSeq.isEmpty()){
           throw new IllegalArgumentException("No observations found") ;
       }
       ArrayList<ArrayList<Double>> Delta = GetObsProbInit(Pi, B, ObsSeq.get(0)) ;
       StateSeq.add(GetArgMax(Delta.get(0))) ;
       
       for(int i=1 ; i<ObsSeq.size() ; i++){
           Delta = GetObsProb(Delta, A, B, ObsSeq.get(i)) ;
       }
       
       return StateSeq ;
    }
    
    
    public static void main(String[] args){
        ArrayList<ArrayList<Double>> Input1 = new ArrayList<>();
        ArrayList<Double> Row = new ArrayList<>() ;
        Row.add(3.0) ;
        Row.add(2.0) ;
        Input1.add(Row) ;
        Row = new ArrayList<>() ;
        Row.add(1.0) ;
        Row.add(5.0) ;
        Input1.add(Row) ;
        System.out.println(Input1);
        
        ArrayList<ArrayList<Double>> Input2 = new ArrayList<>();
        Row = new ArrayList<>() ;
        Row.add(2.0) ;
        Row.add(6.0) ;
        Input2.add(Row) ;
        Row = new ArrayList<>() ;
        Row.add(3.0) ;
        Row.add(2.0) ;
        Input2.add(Row) ;
        System.out.println(Input2);
        
        ArrayList<ArrayList<Double>> Input3 = new ArrayList<>();
        Row = new ArrayList<>() ;
        Row.add(2.0) ;
        Row.add(6.0) ;
        Input3.add(Row) ;
        System.out.println(Input3);
        
        System.out.println(Mult(Input1,Input2));
        System.out.println(Transpose(Input1));
        System.out.println(ElementWiseMult(Input3,Input1));
           
    }
    
    
}
