package com.deepanshu.ClientServerChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private String message;
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;
	

	Scanner sc;

	public Client(Socket socket) {

		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sc = new Scanner(System.in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Client client = null;
		try {
			Socket socket = new Socket("localhost", 1234);
			client = new Client(socket);
			client.readCall();

		} catch (IOException e) {
			System.err.println("not able to connect with the server");
			e.printStackTrace();
			System.out.println(e.getMessage());

		}

	}

	public void readCall() {
		String messageReceive;
		try {
			while ((messageReceive = bufferedReader.readLine()) != null) {
				System.out.println("the message from the server is : " + messageReceive);
				messageBasedOperations(messageReceive);

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	public void sendCall(String message) {
		try {
			bufferedWriter.write(message);
			bufferedWriter.newLine();
			bufferedWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void messageBasedOperations(String messageReceive) {
		if (messageReceive.equals(ClientHandler.OPERATION)) {
			int sendMessage = sc.nextInt();
			sendCall(Integer.toString(sendMessage));
		}
		if (messageReceive.equals(ClientHandler.USERNAME)) {
			String sendUserName = sc.next();
			sendCall(sendUserName);
		}

	}
}
