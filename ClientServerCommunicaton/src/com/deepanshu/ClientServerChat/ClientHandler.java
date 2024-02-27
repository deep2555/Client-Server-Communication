package com.deepanshu.ClientServerChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

	private Socket socket;
	public static List<ClientHandler> clientHandler = new ArrayList<>(); // to add the multiple client in this
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
