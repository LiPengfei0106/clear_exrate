package com.cleartv.exrate;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by Lee on 2017/7/7.
 */

public class MainActivity extends Activity {

    String languageCode = "zh-CN";//en-US
    List<ExRateBean> currencyDatas;
    String url = "http://op.juhe.cn/onebox/exchange/currency";
    static final String CNY = "CNY";
    static final String APP_KEY = "4b60fe0654788e994e1ee8d9d4148e57";

    static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().hasExtra("languageCode"))
            languageCode = getIntent().getStringExtra("languageCode");
        Configuration configuration = getResources().getConfiguration();
        if(TextUtils.isEmpty(languageCode) || "zh-CN".equals(languageCode)){
            languageCode = "zh-CN";
            configuration.setLocale(Locale.CHINESE);
        }else{
            languageCode = "en-US";
            configuration.setLocale(Locale.ENGLISH);
        }
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);

        currencyDatas = new ArrayList<>();

        currencyDatas.add(new ExRateBean("美元", "USD", "USD","0.1470","6.8008", R.mipmap.usd));
        currencyDatas.add(new ExRateBean("欧元", "EUR", "EUR","0.1289","7.7585", R.mipmap.eur));
        currencyDatas.add(new ExRateBean("英镑", "GBP", "GBP","0.1140","8.7727", R.mipmap.gbp));
        currencyDatas.add(new ExRateBean("港币", "HKD", "HKD","1.1485","0.8707", R.mipmap.hkd));
        currencyDatas.add(new ExRateBean("澳门币", "MOP", "MOP","1.1816","0.8463", R.mipmap.mop));
        currencyDatas.add(new ExRateBean("澳元", "AUD", "AUD","0.1932","5.1757", R.mipmap.aud));
        currencyDatas.add(new ExRateBean("新加坡元", "SGD", "SGD","0.2032","4.9209", R.mipmap.sgd));
        currencyDatas.add(new ExRateBean("加元", "CAD", "CAD","0.1893","5.2814", R.mipmap.cad));
        currencyDatas.add(new ExRateBean("日元", "JPY", "JPY","16.7816","0.0596", R.mipmap.jpy));
        currencyDatas.add(new ExRateBean("韩元", "KRW", "KRW","169.1095","0.0059", R.mipmap.krw));
        currencyDatas.add(new ExRateBean("瑞士法郎", "CHF", "CHF","0.1417","7.0583", R.mipmap.chf));
        currencyDatas.add(new ExRateBean("挪威克朗", "NOK", "NOK","1.2269","0.8150", R.mipmap.nok));
        currencyDatas.add(new ExRateBean("丹麦克朗", "DKK", "DKK","0.9585","1.0433", R.mipmap.dkk));
        currencyDatas.add(new ExRateBean("瑞典克朗", "SEK", "SEK","1.2379","0.8078", R.mipmap.sek));
        currencyDatas.add(new ExRateBean("泰铢", "THB", "THB","5.0117","0.1995", R.mipmap.thb));
        currencyDatas.add(new ExRateBean("菲律宾比索", "PHP", "PHP","7.4374","0.1345", R.mipmap.php));

        GridView gv_content = findViewById(R.id.gv_content);
        gv_content.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return currencyDatas.size();
            }

            @Override
            public ExRateBean getItem(int i) {
                return currencyDatas.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                view = View.inflate(MainActivity.this, R.layout.item_view, null);
                final TextView fromRMB = view.findViewById(R.id.fromRMB);
                final TextView toRMB = view.findViewById(R.id.toRMB);
                TextView name = view.findViewById(R.id.name);
                Shader shader = new LinearGradient(0, 0, 0, 34, Color.argb(255, 243, 229, 171), Color.argb(255, 213, 192, 149), Shader.TileMode.CLAMP);
                name.getPaint().setShader(shader);
                ImageView icon = view.findViewById(R.id.icon);
                name.setText(getItem(i).getCountryName().get(languageCode));
                icon.setImageResource(getItem(i).getImgRes());

                RequestBody body = new FormBody.Builder()
                        .add("from", CNY)
                        .add("to", getItem(i).getCountryCode())
                        .add("key", APP_KEY)
                        .build();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                fromRMB.setText(getItem(i).getFromRMB());
                                toRMB.setText(getItem(i).getToRMB());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try{
                            Gson gson = new Gson();
                            final JuHeData data = gson.fromJson(response.body().string(),JuHeData.class);
                            if(data.getError_code() == 0 && data.getResult().size()>=2){
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        fromRMB.setText(data.getResult().get(1).getResult());
                                        toRMB.setText(data.getResult().get(0).getResult());
                                    }
                                });
                            }else{
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        fromRMB.setText(getItem(i).getFromRMB());
                                        toRMB.setText(getItem(i).getToRMB());
                                    }
                                });
                            }
                        }catch (Exception e){

                        }
                    }
                });
                return view;
            }
        });
    }
}
