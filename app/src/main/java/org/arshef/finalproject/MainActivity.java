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
        Do();
    }

    private void Do() {
        // create M-by-N matrix that doesn't have full rank
        int M = 8, N = 5;
        //Matrix B = Matrix.random(5, 3);
        //Matrix A = Matrix.random(M, N).times(B).times(B.transpose());
        double[][] vals = {{2, 0, 2, 4, 5, 0}, {5, 0, 4, 0, 0, 1}, {0, 0, 5, 0, 2, 0}, {0, 1, 0, 5, 0, 4}, {0, 0, 4, 0, 0, 2}, {4, 5, 0, 1, 0, 0}};
        Matrix A = new Matrix(vals);
        System.out.print("A = ");
        A.print(9, 6);
        // compute the singular value decomposition
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
        double[][] doubles = {{2.5, 3, 1, 4, 2.5}};
        Matrix n = new Matrix(doubles);
        Matrix matrix = r.times(n.transpose()).transpose();
        matrix.print(9, 6);
        double[][] result = matrix.getArray();
        double[] row = result[0];
        HashMap<Integer, Double> hashMap = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            hashMap.put(i, row[i]);
        }
//        System.out.print("Sigma = ");
//        Matrix S = s.getS();
//        S.print(9, 6);
//        System.out.print("V = ");
//        Matrix V = s.getV();
//        V.print(9, 6);
        Log.e("", "rank = " + s.rank());
        Log.e("", "condition number = " + s.cond());
        Log.e("", "2-norm = " + s.norm2());

        // print out singular values
        System.out.print("singular values = ");
        Matrix svalues = new Matrix(s.getSingularValues(), 1);
        svalues.print(9, 6);

        // S.set(1, 1, 0);
        //S.set(3, 3, 0);
        // S.set(4, 4, 0);
        System.out.print("Sigma = ");
        //S.print(9, 6);
        //Matrix B = U.times(S.times(V.transpose()));
        System.out.print("B = ");
        //B.print(9, 6);
    }
}
