package com.dsr.tuling;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements HttpGetDataListenr {
    private HttpData httpData;
    private List<ListData> lists;
    private ListView lv;
    private EditText editText_content;
    private Button button_send;
    private String content_str;
    private Textadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        button_send = (Button) findViewById(R.id.send_btn);
        editText_content = (EditText) findViewById(R.id.sendText);
        editText_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) {
                    button_send.setEnabled(false);
                    button_send.setBackgroundResource(R.mipmap.send_button_enable);
                } else {
                    button_send.setEnabled(true);
                    button_send.setBackgroundResource(R.mipmap.send_button);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lists = new ArrayList<>();
        adapter = new Textadapter(lists, this);
        lv.setAdapter(adapter);
    }

    @Override
    public void getDataUrl(String data) {
        parseText(data);
    }

    public void parseText(String str) {
        try {
            JSONObject jb = new JSONObject(str);
            ListData listData = new ListData(jb.getString("text"), ListData.RECEIVER);
            lists.add(listData);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        content_str = editText_content.getText().toString();
        ListData listData = new ListData(content_str, ListData.SEND);
        lists.add(listData);
        adapter.notifyDataSetChanged();
        httpData = (HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=c8664a9aa985189f1ea551b0bd007fee&info=" + content_str, this).execute();
        editText_content.setText("");
    }

    public void menuSelect(String str) {

        httpData = (HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=c8664a9aa985189f1ea551b0bd007fee&info=" +str, this).execute();
    }

    //菜单

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.haha:
                menuSelect("笑话");
                break;
            case R.id.weather:
                menuSelect("济宁天气");
                break;
        }
        return  true;
        //return super.onOptionsItemSelected(item);
    }

}
