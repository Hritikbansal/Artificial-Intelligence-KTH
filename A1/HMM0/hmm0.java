import java.io.*;
import java.util.*;

public class hmm0{
	
public static void main(String[] args){
	
	try{
		FileInputStream fstr=new FileInputStream("filename.txt");
		Scanner sc=new Scanner(fstr);
		// for transition matrix
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
		// for emission matrix

		int e_row=sc.nextInt();
		int e_col=sc.nextInt();
		ArrayList<ArrayList<Double>> emi_vec=new ArrayList<ArrayList<Double>>();
		for(int i=0;i<e_row;i++){
			ArrayList<Double> vec_tmp=new ArrayList<Double>();
			for(int j=0;j<e_col;j++){
				vec_tmp.add(sc.nextDouble());
			}
			emi_vec.add(vec_tmp);
		}

		// for initial state probability distribution

		int init_row=sc.nextInt();
		int init_col=sc.nextInt();
		ArrayList<ArrayList<Double>> init_vec=new ArrayList<ArrayList<Double>>();
		ArrayList<Double> vec_tmp=new ArrayList<Double>();
		for(int i=0;i<init_col;i++){
			vec_tmp.add(sc.nextDouble());			
		}

		init_vec.add(vec_tmp);

		ArrayList<ArrayList<Double>> next_state=mult(mult(init_vec,trans_vec),emi_vec);
		int si=next_state.get(0).size();
		String[] arr=new String[si];
		for(int k=0;k<si;k++){
			arr[k]=Double.toString(next_state.get(0).get(k));
		}
		System.out.println(String.join(" ",arr));
	}
	catch(FileNotFoundException e){
		System.out.println("file not found!");
	}
}
}