package com.dsr.tuling;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取数据，调用时需要传递一个网址进来
 * Created by Administrator on 2015/12/12.
 */
public class HttpData extends AsyncTask<String, Void, String> {


    private String httpurl = "";
    private HttpURLConnection httpURLConnection;


    private HttpGetDataListenr listener;


    public HttpData(String httpurl, HttpGetDataListenr listener) {
        this.httpurl = httpurl;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(httpurl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);//设置连接超时时间
            httpURLConnection.setRequestMethod("GET");//使用GET方式
            httpURLConnection.setDoOutput(true);//允许输出输入
            httpURLConnection.setDoInput(true);//允许输入
            httpURLConnection.setUseCaches(false);//
            httpURLConnection.setRequestProperty("Charset", "UFT-8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String resultData = "";//存放从服务器返回的信息
            String line = null;
            while ((line = reader.readLine()) != null) {//使用循环来获得数据
                resultData += line;
            }
            reader.close();
            System.out.print(resultData);
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return resultData;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        listener.getDataUrl(s);
    }
}
