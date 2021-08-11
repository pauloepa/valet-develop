package com.martins.bematechprintmanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

/**
 * Created by policante on 7/14/16.
 */
public class BematechManager {

    private Socket socket;

    private String host;
    private int port;

    private int timeout;

    public BematechManager(String host, int port) {
        this(host, port, (int) TimeUnit.SECONDS.toMillis(5));
    }

    public BematechManager(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    private void connect() {
        try {
            InetAddress serverIP = InetAddress.getByName(this.host);
            SocketAddress socketAddress = new InetSocketAddress(serverIP, this.port);

            socket = new Socket();
            socket.connect(socketAddress, this.timeout);

            if (!socket.isConnected()) {
                throw new SocketException("socket not connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(final BematechPrint print) throws Exception {

        if (socket == null || socket.isClosed()) {
            connect();
        }

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.print(print.toString());
        printWriter.close();

        if (socket != null) {
            socket.close();
            socket = null;
        }
    }

}
