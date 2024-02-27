package com.deepanshu.ClientServerChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;

	// constructor
	public Server(ServerSocket serverSocket) {
		super();
		this.serverSocket = serverSocket;
	}

	// method to start the server
	public void startServer() {

		try {
			while (!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				System.out.println("New Client is connected to the Server!! ");

				/* to handle the multiple client */
				ClientHandler clientHandler = new ClientHandler(socket);
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("Not able to connect with the client!");
			e.printStackTrace();
		} finally {
			stopServer();
		}
	}

	public void stopServer() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			System.err.println("server socket is not closed ");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234);
		Server server = new Server(serverSocket);
		server.startServer();
	}

}
