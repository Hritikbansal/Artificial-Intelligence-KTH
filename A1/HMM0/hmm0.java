import java.io.*;
import java.util.*;

public class hmm0{

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
                    System.out.println(M1.get(i).get(k)+"   1");
                    System.out.println(M2.get(k).get(j)+"   2");
                    System.out.println(M1.get(i).get(k)*M2.get(k).get(j)+"   3");
                    System.out.println(Sum);
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
		int e_col=sc.nextInt();
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
		//System.out.println(trans_vec);
		//System.out.println(emi_vec);
		//System.out.println(init_vec);
		ArrayList<ArrayList<Float>> tmp=Mult(init_vec,trans_vec);
		ArrayList<ArrayList<Float>> next_state=Mult(tmp,emi_vec);
		int si=next_state.get(0).size();
		String[] arr=new String[si+2];
		arr[0]="1";
		arr[1]=Integer.toString(si);
		for(int k=0;k<si;k++){
		//	float f=(float)next_state.get(0).get(k);
			arr[k+2]=Float.toString(next_state.get(0).get(k));
		}

		System.out.println(String.join(" ",arr));
	//}
	//catch(FileNotFoundException e){
	//	System.out.println("file not found!");
	//}
}
}