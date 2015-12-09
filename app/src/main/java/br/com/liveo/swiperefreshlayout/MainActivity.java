package br.com.liveo.swiperefreshlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtColor;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        mTxtColor = (TextView) findViewById(R.id.txtColor);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        //– setColorSchemeResources(): Ele recebe quatro cores diferentes que vão ser utilizados para colorir a animação.
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.orange, R.color.purple);
    }

    private void coloringTriadworks(){

        //– isRefreshing(): Verificar se swipeRefreshLayout está ativo.
        if (mSwipeRefreshLayout.isRefreshing()){
            Toast.makeText(this, R.string.swipe_is_running, Toast.LENGTH_SHORT).show();
        }

        //– setRefreshing(boolean): Ativa a visibilidade do swipeRefreshLayout.
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {

                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                mTxtColor.setTextColor(color);

                //– setRefreshing(boolean): Desativa a visibilidade do swipeRefreshLayout.
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    //– setOnRefreshListener(OnRefreshListener): Adicionando um listener para iniciar um processo de refresh
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            coloringTriadworks();
        }
    };
}
