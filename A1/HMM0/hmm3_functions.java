import java.util.*;
import java.io.*;

public class hmm3{



	public static ArrayList<ArrayList<Float>> GetBetaList(ArrayList<ArrayList<Float>> A1,ArrayList<ArrayList<Float>> B1, ArrayList<Integer> O1){

		ArrayList<ArrayList<Float>> entire_beta=new ArrayList<ArrayList<Float>>();

		ArrayList<Float> initial_beta=new ArrayList<Float>();
		int num_states=A1.size();
		ArrayList<ArrayList<Float>> next_beta=new ArrayList<ArrayList<Float>>(); // twoD arraylist

		for(int k=0;k<num_states;k++){
			initial.beta.add(1);
		}
		entire.beta.add(initial_beta);
		ArrayList<ArrayList<Float>> prev_beta=new ArrayList<ArrayList<Float>>();
		next_beta.add(initial_beta);
		for(int j=1;j<O1.size();j++){
			prev_beta=GetPrevBeta(next_beta,A1,B1,O1.get(O1.size()-j));
			entire_beta.add(prev_beta.get(0));
			next_beta=prev_beta;
		}
		Collections.reverse(entire_beta);
		return entire_beta;
	}

	public static ArrayList<ArrayList<ArrayList<Double>>> GetScaledAlphaBeta(ArrayList<ArrayList<Double>> Alpha, ArrayList<ArrayList<Double>> Beta){
		ArrayList<ArrayList<ArrayList<Double>>> MOut=new ArrayList<>();
		ArrayList<ArrayList<Double>> Sum=new ArrayList<>();
		ArrayList<Double> HoldSum=new ArrayList<>();
		Double tmp=0.0;
		//finding the sum
		for(int i=0;i<Alpha.size();i++){
			tmp=0.0;
			for(int j=0;j<Alpha.get(0).size();j++){
				tmp+=Alpha.get(i).get(j);
			}
			HoldSum.add(tmp);
		}
		Sum.add(HoldSum);
		//Scaling 
		for(int i=0;i<Alpha.size();i++){
			for(int j=0;j<Alpha.get(0).size();j++){
				Alpha.get(i).get(j)=Alpha.get(i).get(j)/(HoldSum.get(i));
				Beta.get(i).get(j)=Beta.get(i).get(j)/(HoldSum.get(i));
			}
		}

		MOut.add(Alpha);
		MOut.add(Beta);
		MOut.add(Sum);

		return MOut;

	}

	public static ArrayList<ArrayList<Double>> GetAlphaList(ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B,ArrayList<ArrayList<Double>> Pi,ArrayList<Integer> ObsSeq){

		ArrayList<ArrayList<Double>> AlphaList=new ArrayList<>();

		for(int i=0;i<ObsSeq.size();i++){
			if(i == 0)
                Alpha = GetObsProbInit(Pi, B,ObsSeq.get(i)).get(0) ;
            	AlphaList.add(Alpha);
            else
                Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)).get(0) ;
            	AlphaList.add(Alpha);
		}
		return AlphaList;
	}

	public static ArrayList<ArrayList<Float>> Zeros(int row,int col){
		ArrayList<ArrayList<Float>> zero_mat=new ArrayList<ArrayList<Float>>();

		for(int i=0;i<row;i++){
			ArrayList<Float> tmp=new ArrayList<Float>();
			for(int j=0;j<col;j++){
				tmp.add(0);
			}
			zero_mat.add(tmp);
		}
		return zero_mat;
	}

	public static void main(String[] args){

		Scanner sc=new Scanner(System.in);
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
		// initial estimate of emission matrix

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

		// initial probability distribution

		int init_row=sc.nextInt();
		int init_col=sc.nextInt();
		ArrayList<ArrayList<Float>> init_vec=new ArrayList<ArrayList<Float>>();
		ArrayList<Float> vec_tmp=new ArrayList<Float>();
		for(int i=0;i<init_col;i++){
			vec_tmp.add(sc.nextFloat());			
		}

		init_vec.add(vec_tmp);

		//  observation sequence
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
		// probability distribution 



	}
}