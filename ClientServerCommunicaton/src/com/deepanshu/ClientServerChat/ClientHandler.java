package com.deepanshu.ClientServerChat;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

	private Socket socket;
	public static List<ClientHandler> clientHandler = new ArrayList<>(); // to add the multiple client in this
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;


	/* constructor */
	public ClientHandler(Socket socket) {

		try {

			this.socket = socket;
			this.dataInputStream = new DataInputStream((socket.getInputStream()));
			this.dataOutputStream = new DataOutputStream((socket.getOutputStream()));
			clientHandler.add(this);
			System.out.println("client added to the arrayist ");

		} catch (IOException e) {
			System.err.println("unable to recieve or sent the message.. ");
			closeAllConnection(socket, dataInputStream, dataOutputStream);
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		int messageFromClient;
		while (socket.isConnected()) {
			try {
				messageFromClient = dataInputStream.readInt();
				System.out.println("message from the client is : " + messageFromClient);
				/* Switch case to handle the and perform the client request */

			} catch (IOException e) {
				System.err.println("now able to read the message from the client :");
				e.printStackTrace();
				closeAllConnection(socket, dataInputStream, dataOutputStream);
			}
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
