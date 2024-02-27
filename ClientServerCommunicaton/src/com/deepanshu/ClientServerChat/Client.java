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
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String message;

	public Client(Socket socket) {

		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (IOException e) {
			e.printStackTrace();
			closeAllConnection(socket, bufferedReader, bufferedWriter);
		}

	}

	public void sendMessageToServer() {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);

			while (socket.isConnected()) {
				System.out.println("enter the message to sent: ");
				message = sc.nextLine();
				bufferedWriter.write(message);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			System.err.println("not able to write the message :");
			e.printStackTrace();
			closeAllConnection(socket, bufferedReader, bufferedWriter);
			sc.close();
		}
	}

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
