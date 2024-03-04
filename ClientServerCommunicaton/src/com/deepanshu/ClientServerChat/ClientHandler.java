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
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;

	/* constructor */
	public ClientHandler(Socket socket) {

		try {

			this.socket = socket;
			this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			clientHandler.add(this);
			System.out.println("client added to the arrayist ");

		} catch (IOException e) {
			System.err.println("unable to recieve or sent the message.. ");
			e.printStackTrace();
			
		}

	}

	@Override
	public void run() {

		try {
			readDataFromClient();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("not able to read the data ");
		} finally {
			try {
				bufferReader.close();
				bufferWriter.close();
				socket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

	public void readDataFromClient() {
		int messageFromClient;
		try {
			while ((messageFromClient = Integer.parseInt(bufferReader.readLine())) != -1) {
				System.out.println("inside while loop");
				System.out.println("message is:" + messageFromClient);

				switch (messageFromClient) {
				case 1:
					String messageTOClient = "your request is recieved";
					
					bufferWriter.write(messageTOClient);
					bufferWriter.newLine();
					bufferWriter.flush();

				}
			}

		} catch (IOException e) {
			System.out.println("message not Recieve from the client");
			e.printStackTrace();
		}

	}

}
