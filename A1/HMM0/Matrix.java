
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
    public ArrayList<ArrayList<Double>> Mult(ArrayList<ArrayList<Double>> M1, ArrayList<ArrayList<Double>> M2){
        Integer RowCount1 = M1.size() ;
        Integer ColCount1 = M1.get(0).size() ;
        Integer RowCount2 = M2.size() ;
        Integer ColCount2 = M2.get(0).size() ;
        Double Sum = 0.0 ;
        ArrayList<Double> Row = null;
        ArrayList<ArrayList<Double>> MOut = null;
        
        if(ColCount1.equals(RowCount2)){
            throw new IllegalArgumentException("ColCount1 not equal to RowCount2") ;
        }
        for(int i=0 ; i<RowCount1 ; i++){
            Row = null ;
            for(int j=0; i<ColCount2 ; j++){
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
}
