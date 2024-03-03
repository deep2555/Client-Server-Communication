package com.deepanshu.ClientServerChat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

	private Socket socket;
	public static List<ClientHandler> clientHandler = new ArrayList<>(); // to add the multiple client in this
	private BufferedReader bufferReader;

	/* constructor */
	public ClientHandler(Socket socket) {

		try {

			this.socket = socket;
			this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			clientHandler.add(this);
			System.out.println("client added to the arrayist ");

		} catch (IOException e) {
			System.err.println("unable to recieve or sent the message.. ");
			// closeAllConnection(socket, dataInputStream, dataOutputStream);
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
		}finally {
			try {
				bufferReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void readDataFromClient() {
		String messageFromClient;
		try {
			while ((messageFromClient = bufferReader.readLine()) != null) {
				System.out.println("inside while loop");
				System.out.println("message is:" + messageFromClient);
			}

		} catch (IOException e) {
			System.out.println("message not Recieve from the client");
			e.printStackTrace();
		}

	}

	/* this method will close all the open connection */
	public void closeAllConnection(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
		try {
			if (dataOutputStream != null) {
				dataOutputStream.close();
			}
			if (dataInputStream != null) {
				dataInputStream.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
