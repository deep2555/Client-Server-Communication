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
	private static BufferedWriter bufferedWriter;
	private static BufferedReader bufferedReader;
	Scanner sc;

	public Client(Socket socket) {

		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 1234);
			Client client = new Client(socket);
			client.sendMessageToServer();
			client.recieveMessageFromServer();

		} catch (IOException e) {
			System.err.println("not able to connect with the server");
			e.printStackTrace();

		} finally {
			try {
				bufferedWriter.close();
				bufferedReader.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/* methods for sending the Request message to server */
	public void sendMessageToServer() {
		try {
			sc = new Scanner(System.in);
			System.out.println("Select the option to proceed \n " + "1) Chat with AI Bot:\n"
					+ "2) Access and Download File from the Internet:\n " + "3) Read and Write data from the file:\n");
			message = sc.nextLine();
			bufferedWriter.write(message);
			bufferedWriter.newLine();
			bufferedWriter.flush();

		} catch (IOException e) {
			System.err.println("not able to write the message :");
			e.printStackTrace();
			sc.close();
		}
	}

	/* method for the receiving revert message from the server */
	public void recieveMessageFromServer() {
		String messageFromserver;
		try {
			while ((messageFromserver = bufferedReader.readLine()) != null) {
				System.out.println("inside while loop message from server");
				System.out.println("message is:" + messageFromserver);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
