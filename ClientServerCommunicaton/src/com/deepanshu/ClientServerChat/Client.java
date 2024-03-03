package com.deepanshu.ClientServerChat;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client {

	private Socket socket;
	private String message;
	private static BufferedWriter bufferedWriter;

	public Client(Socket socket) {

		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (IOException e) {
			e.printStackTrace();
			// closeAllConnection(socket, dataInputStream, dataOutputStream);
		}

	}

	public void sendMessageToServer() {
		try {

			message = "hello how are you";
			bufferedWriter.write(message);
			bufferedWriter.newLine();
			bufferedWriter.flush();

		} catch (IOException e) {
			System.err.println("not able to write the message :");
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
		} finally {
			try {
				bufferedWriter.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
