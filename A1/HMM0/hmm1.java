import java.util.*;
import java.io.*;

public class hmm1{

    
    public static ArrayList<ArrayList<Float>> ElementWiseMult(ArrayList<ArrayList<Float>> V, ArrayList<Float> M){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer ColCount2 = M.size() ;
       
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();
        
        if(!RowCount1.equals(1)){
            throw new IllegalArgumentException("First aregument is not a vector") ;
        }
        
       // if(!ColCount1.equals(RowCount2)){
         //   throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        //}
        
       
        Row = new ArrayList<>();
        for(int j=0; j<ColCount1 ; j++){
            Row.add(V.get(0).get(j)*M.get(j));
        }
        MOut.add(Row) ;
        
        return MOut ;
        
    }


public static ArrayList<ArrayList<Float>> Mult(ArrayList<ArrayList<Float>> M1, ArrayList<ArrayList<Float>> M2){
        
        int RowCount1 = M1.size() ;
        int ColCount1 = M1.get(0).size() ;
        int RowCount2 = M2.size() ;
        int ColCount2 = M2.get(0).size() ;
        float Sum = (float)0.0 ;
        ArrayList<Float> Row=new ArrayList<Float>(); 
        ArrayList<ArrayList<Float>> MOut = new ArrayList<ArrayList<Float>>();

        if(ColCount1!=(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<Float>();
            for(int j=0; j<ColCount2 ; j++){
                Sum=(float)0.0 ;
                for(int k=0 ; k<ColCount1 ; k++){
                    Sum = Sum+M1.get(i).get(k)*M2.get(k).get(j) ;
                    //System.out.println(M1.get(i).get(k)+"   1");
                    //System.out.println(M2.get(k).get(j)+"   2");
                    //System.out.println(M1.get(i).get(k)*M2.get(k).get(j)+"   3");
                    //System.out.println(Sum);
                }
                Row.add(Sum);
            }
            MOut.add(Row) ;
        }
        return MOut ;
    }

public static void main(String[] args){

	//try{
	//	FileInputStream fstr=new FileInputStream("filename.txt");
		Scanner sc=new Scanner(System.in);
		// for transition matrix
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
		// for emission matrix

		int e_row=sc.nextInt();
		int e_col=sc.nextInt();  //this is same as M(number of discrete observation states)

		ArrayList<ArrayList<Float>> emi_vec=new ArrayList<ArrayList<Float>>();
		for(int i=0;i<e_row;i++){
			ArrayList<Float> vec_tmp=new ArrayList<Float>();
			for(int j=0;j<e_col;j++){
				vec_tmp.add(sc.nextFloat());
			}
			emi_vec.add(vec_tmp);
		}

		// for initial state probability distribution

		int init_row=sc.nextInt();
		int init_col=sc.nextInt();
		ArrayList<ArrayList<Float>> init_vec=new ArrayList<ArrayList<Float>>();
		ArrayList<Float> vec_tmp=new ArrayList<Float>();
		for(int i=0;i<init_col;i++){
			vec_tmp.add(sc.nextFloat());			
		}

		init_vec.add(vec_tmp);

		// for observation sequence
		int obs_num=sc.nextInt();
		ArrayList<Integer> obs_vec=new ArrayList<Integer>();
		int M=e_col;
		for(int i=0;i<obs_num;i++){
			int f=sc.nextInt();
			if(f>=0 && f<=M-1){
			obs_vec.add(f);	
			}
			else{
				//System.out.println(0.0);
				break;
			}		
		}

		ArrayList<ArrayList<Float>> alpha1=new ArrayList<ArrayList<Float>>();
		
		ArrayList<Float> b_tmp=new ArrayList<Float>();
		for(int k=0;k<e_row;k++){
			b_tmp.add(emi_vec.get(k).get(obs_vec.get(0)));
		}
		alpha1=ElementWiseMult(init_vec,b_tmp);
		ArrayList<ArrayList<Float>> alpha2=new ArrayList<ArrayList<Float>>();
		for(int i=1;i<obs_num;i++){
			
			b_tmp=new ArrayList<Float>();
			for(int k=0;k<e_row;k++){
				b_tmp.add(emi_vec.get(k).get(obs_vec.get(i)));
			}

			alpha2=ElementWiseMult(Mult(alpha1,trans_vec),b_tmp);
			alpha1=alpha2;
		}
		
		float prob=0;
		for(int j=0;j<alpha1.get(0).size();j++){
			prob=prob+alpha1.get(0).get(j);
		}

		System.out.println(prob);

		
	//}
	//catch(FileNotFoundException e){
	//	//System.out.println("file not found!");
	//}
}
}