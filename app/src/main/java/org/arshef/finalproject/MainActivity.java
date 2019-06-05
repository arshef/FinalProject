package org.arshef.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orm.SugarContext;
import com.orm.SugarDb;

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
        double[][] vals = {{1, 1, 1, 0, 0}, {2, 2, 2, 0, 0}, {1, 1, 1, 0, 0}, {5, 5, 5, 0, 0}, {0, 0, 0, 2, 2}, {0, 0, 0, 3, 3}, {0, 0, 0, 1, 1}};
        Matrix A = new Matrix(vals);
        System.out.print("A = ");
        A.print(9, 6);

        // compute the singular value decomposition
        System.out.println("A = U S V^T");
        System.out.println();
        SingularValueDecomposition s = A.svd();
        System.out.print("U = ");
        Matrix U = s.getU();
        U.print(9, 6);
        System.out.print("Sigma = ");
        Matrix S = s.getS();
        S.print(9, 6);
        System.out.print("V = ");
        Matrix V = s.getV();
        V.print(9, 6);
        System.out.println("rank = " + s.rank());
        System.out.println("condition number = " + s.cond());
        System.out.println("2-norm = " + s.norm2());

        // print out singular values
        System.out.print("singular values = ");
        Matrix svalues = new Matrix(s.getSingularValues(), 1);
        svalues.print(9, 6);

        // S.set(1, 1, 0);
        //S.set(3, 3, 0);
        // S.set(4, 4, 0);
        System.out.print("Sigma = ");
        S.print(9, 6);
        Matrix B = U.times(S.times(V.transpose()));
        System.out.print("B = ");
        B.print(9, 6);
    }
}
