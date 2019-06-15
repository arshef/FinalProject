package org.arshef.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.orm.SugarContext;
import com.orm.SugarDb;

import org.arshef.finalproject.Models.News;
import org.arshef.finalproject.Models.Rating;
import org.arshef.finalproject.Models.Suggestion;
import org.arshef.finalproject.Models.User;
import org.arshef.finalproject.R;
import org.arshef.finalproject.Tools.StaticTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

import static com.orm.SugarRecord.listAll;

public class MainActivity extends AppCompatActivity {

    ImageButton newsBtn, transferBtn, billBtn, simBtn, mapBtn, chatBtn;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsBtn = findViewById(R.id.news_btn);
        transferBtn = findViewById(R.id.transfer_btn);
        billBtn = findViewById(R.id.bill_btn);
        simBtn = findViewById(R.id.simcard_btn);
        mapBtn = findViewById(R.id.map_btn);
        chatBtn = findViewById(R.id.chat_btn);
        SugarContext.init(getApplicationContext());
        SugarDb db = new SugarDb(this);
        db.onCreate(db.getDB());
        test();

        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });

        billBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BillActivity.class);
                startActivity(intent);
            }
        });

        simBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimcardActivity.class);
                startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChattingActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * temp func for testing
     */
    private void test() {
        double[][] vals = {{2, 0, 2, 4, 5, 0}, {5, 0, 4, 0, 0, 1}, {0, 0, 5, 0, 2, 0}, {0, 1, 0, 5, 0, 4}, {0, 0, 4, 0, 0, 2}, {4, 5, 0, 1, 0, 0}, {1, 2, 0, 3, 2, 2}, {2, 3, 5, 3, 0, 0}};
        double[][] doubles = {{1, 2.5, 5, 2, 2.5, 2}};
        double[] test = calculate(vals, doubles, 6);

    }

    private List<News> getSvd(User user) {
        double[] doubles = calculate(getTable(), getUserRating(user), getUserRating(user)[0].length);
        List<News> news = News.listAll(News.class);
        List<Suggestion> suggestions = new ArrayList<>();
        for (int i = 0; i < doubles.length; i++) {
            Suggestion suggestion = new Suggestion(i, doubles[i], news.get(i));
            suggestions.add(suggestion);
        }
        Collections.sort(suggestions, new Comparator<Suggestion>() {
            @Override
            public int compare(Suggestion o1, Suggestion o2) {
                return Double.compare(o1.rate, o2.rate);
            }
        });
        List<News> newsList = new ArrayList<>();
        for (Suggestion s :
                suggestions) {
            newsList.add(s.news);
        }
        return newsList;
    }

    private double[][] getUserRating(User user) {
        double[][] doubles = new double[1][];
        List<Rating> ratings = Rating.listAll(Rating.class);
        List<News> news = News.listAll(News.class);
        for (int i = 0; i < news.size(); i++) {
            doubles[0][i] = findUserRate(user, news.get(i), ratings, 2.5);
        }
        return doubles;
    }

    private double[][] getTable() {
        List<User> users = User.listAll(User.class);
        List<Rating> ratings = Rating.listAll(Rating.class);
        List<News> news = News.listAll(News.class);
        double[][] doubles = new double[users.size()][];
        for (int j = 0; j < users.size(); j++) {
            for (int i = 0; i < news.size(); i++) {
                doubles[j][i] = findUserRate(users.get(j), news.get(i), ratings, 0);
            }
        }
        return doubles;
    }

    private double findUserRate(User user, News news, List<Rating> ratings, double defaultValue) {
        Rating rate = null;
        for (Rating rating :
                ratings) {
            if (rating.getUser().equals(user) && rating.getNews().equals(news)) {
                rate = rating;
            }
        }
        if (rate != null)
            return rate.getRate();
        else
            return defaultValue;
    }

    private double[] calculate(double[][] vals, double[][] rate, int cut) {
        Matrix A = new Matrix(vals);
        SingularValueDecomposition s = A.svd();
        System.out.print("U = ");
        Matrix U = s.getU().getMatrix(0, cut - 1, 0, cut - 1);
        Matrix ut = U.transpose();
        Matrix r = ut.times(U);
        Matrix n = new Matrix(rate);
        Matrix matrix = r.times(n.transpose()).transpose();
        Log.e("", "*******************************");
        matrix.print(9, 6);
        Log.e("", "*******************************");
        double[][] temp = matrix.getArray();
        return temp[0];
    }
}
