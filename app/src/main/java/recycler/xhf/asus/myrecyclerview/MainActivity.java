package recycler.xhf.asus.myrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.xhf.asus.myrecyclerview.adapter.MAdapter;
import recycler.xhf.asus.myrecyclerview.bean.Data;

public class MainActivity extends AppCompatActivity {

    private String path = "http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915";
    @BindView(R.id.recycler)
    RecyclerView recycler;

    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        ButterKnife.bind(this);

        new Thread(){
            @Override
            public void run() {
                super.run();
                getData();
            }
        }.start();

        mLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能

    }

    private void getData() {

        RequestQueue rQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET,path,

           new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //处理数据jk
                processData(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rQueue.add(request);
        rQueue.start();
    }

    private void processData(String response) {
//        System.out.println("response=="+response);
        Data data = new Gson().fromJson(response, Data.class);
        //设置适配器
        recycler.setAdapter(new MAdapter(this,data));
    }


}
