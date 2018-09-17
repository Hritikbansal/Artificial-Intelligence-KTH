import java.util.*;
import java.io.*;

public class Viterbi{
	//global variable
	public static ArrayList<ArrayList<Integer>> delta_indices=new ArrayList<ArrayList<Integer>>();

    public static ArrayList<ArrayList<Float>> ElementWiseMult(ArrayList<ArrayList<Float>> V, ArrayList<Float> M){
        Integer RowCount1 = V.size() ;
        Integer ColCount1 = V.get(0).size() ;
        Integer ColCount2 = M.size() ;
       
        ArrayList<Float> Row ;
        ArrayList<ArrayList<Float>> MOut = new ArrayList<>();
        
        if(!RowCount1.equals(1)){
            throw new IllegalArgumentException("First aregument is not a vector") ;
        }
        Row = new ArrayList<>();
        for(int j=0; j<ColCount1 ; j++){
            Row.add(V.get(0).get(j)*M.get(j));
        }
        MOut.add(Row) ;
        return MOut ;    
    }


public static ArrayList<ArrayList<Float>> Max_Mult(ArrayList<ArrayList<Float>> M1, ArrayList<ArrayList<Float>> M2){
        
        int RowCount1 = M1.size() ;
        int ColCount1 = M1.get(0).size() ;
        int RowCount2 = M2.size() ;
        int ColCount2 = M2.get(0).size() ;
        float Max_val = 0;
        int max_ind=-1;
        ArrayList<Float> Row=new ArrayList<Float>(); 
        ArrayList<ArrayList<Float>> MOut = new ArrayList<ArrayList<Float>>();
        ArrayList<Integer> index=new ArrayList<Integer>();

        if(ColCount1!=(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = new ArrayList<Float>();
            for(int j=0; j<ColCount2 ; j++){
                Max_val=0 ;
                max_ind=-1;
                for(int k=0 ; k<ColCount1 ; k++){
                	if(M1.get(i).get(k)*M2.get(k).get(j) >Max_val){
                     Max_val=M1.get(i).get(k)*M2.get(k).get(j);
                     max_ind=k;
                    //System.out.println(M1.get(i).get(k)+"   1");
                    //System.out.println(M2.get(k).get(j)+"   2");
                    //System.out.println(M1.get(i).get(k)*M2.get(k).get(j)+"   3");
                    //System.out.println(Sum);
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

public static int maximum(ArrayList<Float> arrli){

	float ma=0;
	int ind=-1;
	ArrayList<Float> ret=new ArrayList<Float>();
	for(int j=0;j<arrli.size();j++){
		if(arrli.get(j)>ma){
			ma=arrli.get(j);
			ind=j;
		}
	}
	return ind; 
}

public static ArrayList<Float> getemiprob(ArrayList<ArrayList<Float>> emission_vec,ArrayList<Integer> observation_vec,int ind){
		
		ArrayList<Float> b1=new ArrayList<Float>();
		for(int k=0;k<emission_vec.size();k++){
			b1.add(emission_vec.get(k).get(observation_vec.get(ind)));
		}
		return b1;
	}

public static String Backtrack(ArrayList<ArrayList<Float>> finaldelta,int num_obs){
	
	int[] states=new int[num_obs];
	states[num_obs-1]=maximum(finaldelta.get(0));
	for(int j=1;j<num_obs;j++){
		states[num_obs-1-j]=delta_indices.get(delta_indices.size()-j).get(states[num_obs-j]);
	}
	String[] str_states=new String[num_obs];
	for(int z=0;z<num_obs;z++){
		str_states[z]=Integer.toString(states[z]);
	}
	return String.join(" ",str_states);
}

public static void main(String[] args){

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

		/*xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx*/

		ArrayList<ArrayList<Float>> delta1=new ArrayList<ArrayList<Float>>();
		ArrayList<Float> b_tmp=getemiprob(emi_vec,obs_vec,0);
		//initial probability
		delta1=ElementWiseMult(init_vec,b_tmp);

		ArrayList<Integer> temp_ind=new ArrayList<Integer>();
		for(int k=0;k<delta1.get(0).size();k++){
			temp_ind.add(null);
		}
		delta_indices.add(temp_ind); // this is for the first iteration

		ArrayList<ArrayList<Float>> delta2=new ArrayList<ArrayList<Float>>();
		ArrayList<ArrayList<Float>> temp=new ArrayList<ArrayList<Float>>();
		for(int i=1;i<obs_num;i++){
			b_tmp=getemiprob(emi_vec,obs_vec,i);
			temp=Max_Mult(delta1,trans_vec);
			delta2=ElementWiseMult(temp,b_tmp);
			delta1=delta2;
		}
		// i will have my delta_indices and delta1 ready by now.Backtrack to find the result
		System.out.println(Backtrack(delta1,obs_num));
		
}

}