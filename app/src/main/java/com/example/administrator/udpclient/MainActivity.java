package com.example.administrator.udpclient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private UdpHelper udphelper;
    private UDPClient client;
    private String sendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView hw = (TextView) findViewById(R.id.hw);

        // 开启服务器
        ExecutorService exec = Executors.newCachedThreadPool();
        UDPServer server = new UDPServer();
        exec.execute(server);
        hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myThread1 thread = new myThread1("22");
                new Thread(thread).start();
            }
        });

    }

    final Handler mHander = new Handler() {

        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
//            info_tv.setText(sendInfo);

            Log.d("msg", "client.send()=");
        }
    };

    class myThread1 implements Runnable {

        private String threadName;

        public myThread1(String name) {
            this.threadName = name;
        }

        public void run() {
//            Log.d("msg", "MyThread  execu"+msg_et.getText().toString());
            client = new UDPClient("123");
            sendInfo = client.send();

            Message msg = mHander.obtainMessage();
            msg.arg1 = 1;
            mHander.sendMessage(msg);
            Log.d("msg", "client.send()=");
        }
    }


}
