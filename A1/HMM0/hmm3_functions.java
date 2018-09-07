import java.util.*;
import java.io.*;

public class hmm3{



	public static ArrayList<ArrayList<Double>> GetBetaList(ArrayList<ArrayList<Double>> A,ArrayList<ArrayList<Double>> B, ArrayList<Integer> ObsSeq,ArrayList<Double> AlphaSum){

		ArrayList<ArrayList<Double>> BetaList=new ArrayList<ArrayList<Double>>();

		ArrayList<Double> BetaInit=new ArrayList<Double>();
		int NumStates=A.size();
		ArrayList<ArrayList<Double>> NextBeta=new ArrayList<ArrayList<Double>>(); // twoD arraylist

		for(int k=0;k<NumStates;k++){
			BetaInit.add(1.0);
		}
		ArrayList<ArrayList<Double>> temp=new ArrayList<ArrayList<Double>>();
		BetaInit=GetScaledList(AlphaSum.get(AlphaSum.size()-1),temp.add(BetaInit)).get(0);
		BetaList.add(BetaInit);

		ArrayList<ArrayList<Double>> prev_beta=new ArrayList<ArrayList<Double>>();
		NextBeta.add(BetaInit);
		for(int j=1;j<ObsSeq.size();j++){
			prev_beta=GetPrevBeta(NextBeta,A,B,ObsSeq.get(ObsSeq.size()-j));
			prev_beta=GetScaledList(AlphaSum.get(AlphaSum.size()-1-j),prev_beta);
			BetaList.add(prev_beta.get(0));
			NextBeta=prev_beta;
		}
		Collections.reverse(BetaList);
		return BetaList;
	}

	public static ArrayList<ArrayList<Double>> GetScaledList(Double AplhaSum,ArrayList<ArrayList<Double>> Mat){

		for(int i=0;i<Mat.get(0).size();i++){
			Mat.get(0).set(i,Mat.get(0).get(i)/AlphaSum);
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
        ArrayList<ArrayList<Double>> TempSum = new ArrayList<>() ;
        Double Sum=0.0;

		for(int i=0;i<ObsSeq.size();i++){
			if(i == 0){
                            Alpha = GetObsProbInit(Pi, B,ObsSeq.get(i)) ;
                            Sum=GetSum(Alpha.get(0));
                            TempSum.add(Sum);
                            Alpha=GetScaledList(Sum,Alpha);
                            AlphaList.add(Alpha.get(0));
                        }
            else{
                            Alpha = GetObsProb(Alpha, A, B, ObsSeq.get(i)) ;
                            Sum=GetSum(Alpha.get(0));
                            TempSum.add(Sum);
                            Alpha=GetScaledList(Sum,Alpha);
                            AlphaList.add(Alpha.get(0));
                }
		}
		Mout.add(AlphaList);
		SumList.add(TempSum);
		MOut.add(SumList);
		
		return MOut;
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