package org.arshef.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.HashMap;
import java.util.List;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(getApplicationContext());
        SugarDb db = new SugarDb(this);
        db.onCreate(db.getDB());
        double[][] table = getTable();
//        Do();

    }

    private double[][] getTable() {

        return new double[0][];
    }

    private void Do(double[][] vals, double[][] rate) {
//        int M = 8, N = 5;
//        double[][] vals = {{2, 0, 2, 4, 5, 0}, {5, 0, 4, 0, 0, 1}, {0, 0, 5, 0, 2, 0}, {0, 1, 0, 5, 0, 4}, {0, 0, 4, 0, 0, 2}, {4, 5, 0, 1, 0, 0}};
        Matrix A = new Matrix(vals);
        System.out.print("A = ");
        A.print(9, 6);
        Log.e("", "A = U S V^T");
        SingularValueDecomposition s = A.svd();
        System.out.print("U = ");
        Matrix U = s.getU().getMatrix(0, 4, 0, 4);
        U.print(9, 6);
        Matrix ut = U.transpose();
        Log.e("", "*******************************");
        ut.print(9, 6);
        Matrix r = ut.times(U);
        Log.e("", "*******************************");
        r.print(9, 6);
        Log.e("", "*******************************");
//        double[][] doubles = {{2.5, 3, 1, 4, 2.5}};
        Matrix n = new Matrix(rate);
        Matrix matrix = r.times(n.transpose()).transpose();
        matrix.print(9, 6);
        double[][] result = matrix.getArray();
        double[] row = result[0];
        HashMap<Integer, Double> hashMap = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            hashMap.put(i, row[i]);
        }
        Log.e("", "rank = " + s.rank());
        Log.e("", "condition number = " + s.cond());
        Log.e("", "2-norm = " + s.norm2());
        System.out.print("singular values = ");
        Matrix svalues = new Matrix(s.getSingularValues(), 1);
        svalues.print(9, 6);
    }
}
