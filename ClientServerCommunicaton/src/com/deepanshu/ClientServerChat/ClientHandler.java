package com.deepanshu.ClientServerChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

	private Socket socket;
	public static List<ClientHandler> clientHandler = new ArrayList<>(); // to add the multiple client in this
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
//	private String message;

	/* constructor */
	public ClientHandler(Socket socket) {

		try {

			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			clientHandler.add(this);
			System.out.println("client added to the arrayist ");

		} catch (IOException e) {
			System.err.println("unable to recieve or sent the message.. ");
			closeAllConnection(socket, bufferedReader, bufferedWriter);
			e.printStackTrace();
		}

	}

	/* this method will close all the open connection */
	public void closeAllConnection(Socket socket, BufferedReader bufferReader, BufferedWriter bufferedWriter) {
		try {
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String messageFromClient;
		while (socket.isConnected()) {
			try {
				messageFromClient = bufferedReader.readLine();
				System.out.println("message from the client is : " + messageFromClient);

			} catch (IOException e) {
				System.err.println("now able to read the message from the client :");
				e.printStackTrace();
				closeAllConnection(socket, bufferedReader, bufferedWriter);
			}
		}

	}

}
