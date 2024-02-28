package com.deepanshu.ClientServerChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private int message;

	public Client(Socket socket) {

		try {
			this.socket = socket;
			this.dataInputStream = new DataInputStream((socket.getInputStream()));
			this.dataOutputStream = new DataOutputStream((socket.getOutputStream()));

		} catch (IOException e) {
			e.printStackTrace();
			closeAllConnection(socket, dataInputStream, dataOutputStream);
		}

	}

	public void sendMessageToServer() {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);

			while (socket.isConnected()) {
				System.out.println("Choose the operation to perform \n" + "1) Chat with AI Bot: \n"
						+ "2) Read the File from the source: \n" + "3) write the File from the Source: \n"
						+ "4) Download th file from the Internet: \n" + "5) Share the file to the resource: \n");
				message = sc.nextInt();
				dataOutputStream.writeInt(message);
				dataOutputStream.flush();
			}
		} catch (IOException e) {
			System.err.println("not able to write the message :");
			e.printStackTrace();
			closeAllConnection(socket, dataInputStream, dataOutputStream);
			sc.close();
		}
	}

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

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 1234);
			Client client = new Client(socket);
			client.sendMessageToServer();

		} catch (IOException e) {
			System.err.println("not able to connect with the server");
			e.printStackTrace();
		}

	}

}
