package com.javacodegeeks.android.androidsocketclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Client extends Activity {

	private Socket socket;

	private static final int SERVERPORT = 6014;
	//private static final String SERVER_IP = "192.168.253.101";
	//private static final String SERVER_IP = "172.30.112.202";
	//private static final String SERVER_IP = "172.30.133.100";
	private static final String SERVER_IP = "172.20.41.229";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		
		new Thread(new ClientThread()).start();
	}
	
	public void onClick(View view) {
		try {
			EditText et = (EditText) findViewById(R.id.EditText01);
			String str = et.getText().toString();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(str);
			Log.d("k sent: ", str);
			Toast.makeText(getBaseContext(), "socket send client : " +
							str,
					Toast.LENGTH_SHORT).show();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ClientThread implements Runnable {

		@Override
		public void run() {
			
			try {
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}
}