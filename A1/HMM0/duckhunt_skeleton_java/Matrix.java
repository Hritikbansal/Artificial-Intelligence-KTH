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
public class Matrix {
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

    public static ArrayList<ArrayList<Float>> ElementWiseScalerDivide(Float Factor,ArrayList<ArrayList<Float>> Mat){
        Integer RowCount = Mat.size() ;
        Integer ColCount = Mat.get(0).size() ;
        
        for(int i=0 ; i<RowCount ; i++){
            for(int j =0 ; j<ColCount ; j++) {
                Mat.get(i).set(j, Mat.get(i).get(j)/Factor) ;
            }
        }
        
        return Mat ;
    }
	
    public static Float GetVecSum(ArrayList<Float> li){
        Float tmp_sum=(float)0;
        for(int i=0;i<li.size();i++){
                tmp_sum+=li.get(i);
        }
        return tmp_sum;
    }
    public static Float GetLogVecSum(ArrayList<Float> AlphaSum){
        Float sum=(float)0;
        //System.out.println("size: "+AlphaSum.size());
        for(int i=0;i<AlphaSum.size();i++){
            sum+=(float)Math.log(AlphaSum.get(i));
        }
        //System.out.println("sum: "+sum);
        return sum;
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
        
        ArrayList<ArrayList<Float>> Pi=Out.get(2);
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
        l=2 ;
        for(int i=0;i<Pirow;i++){
            for(int j=0;j<Picol;j++){
                str_Pi[l]=Float.toString(Pi.get(i).get(j));
                l+=1;
            }
        }
        
        System.out.println(String.join(" ",str_A));
        System.out.println(String.join(" ", str_B));
//        System.out.println(String.join(" ", str_Pi));
        
    }
    public static Float GetDist(ArrayList<ArrayList<Float>> Mat1, ArrayList<ArrayList<Float>> Mat2){
        Integer RowCount = Mat1.size() ;
        Integer ColCount = Mat1.get(0).size() ;
        Float Sum =(float) 0 ;
        for(int i=0 ; i<RowCount ; i++){
            for (int j=0 ; j<ColCount ; j++){
                Sum+= (Mat1.get(i).get(j)-Mat2.get(i).get(j))*(Mat1.get(i).get(j)-Mat2.get(i).get(j)) ;
//                System.out.println("j"+j+"Sum: "+Sum);
            }
        }
        return Sum ;
    }
    public static void PrintDist(ArrayList<ArrayList<ArrayList<Float>>> Lambda, ArrayList<ArrayList<ArrayList<Float>>> LambdaUsed) {
        for(int i=0 ; i<3 ; i++){
            System.out.println("Distance between "+i+"  "+GetDist(Lambda.get(i),LambdaUsed.get(i)));
        }
    }
    
    

}