package com.deepanshu.ClientServerChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable {

	private Socket socket;
	public static List<ClientHandler> clientHandler = new ArrayList<>(); // to add the multiple client in this
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;
	public static final String OPERATION = "What operation you want to perform:" + "1) Chat With AI Bot:"
			+ "2) File Transfer and Access from the System:" + "3) File Download From the Server:"
			+ "4) Read and Write data From the File: ";
	public static final String USERNAME = "Enter the user name To continue : ";
	Scanner sc;
	String messageReceive = null;

	/* constructor */
	public ClientHandler(Socket socket) {

		try {

			this.socket = socket;
			this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			clientHandler.add(this);
			sc = new Scanner(System.in);
			System.out.println("client added to the arrayist ");

		} catch (IOException e) {
			System.err.println("unable to recieve or sent the message.. ");
			e.printStackTrace();
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void run() {

		try {
			sendCall(OPERATION);
			readCall();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("not able to read the data ");
		} finally {
			try {
				bufferReader.close();
				bufferWriter.close();
				socket.close();
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}

	}

	/* this */
	public void sendCall(String message) {
		try {
			bufferWriter.write(message);
			bufferWriter.newLine();
			bufferWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void readCall() {
		String messageReceive;
		try {
			while ((messageReceive = bufferReader.readLine()) != null) {
				System.out.println("the message from the client is : " + messageReceive);
				messageBasedOperation(messageReceive);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	public void messageBasedOperation(String messageReceive2) {
		
		switch (messageReceive2) {
		case "1":
			sendCall(USERNAME);
			readCall();
			
			
		}
		
	}

}
