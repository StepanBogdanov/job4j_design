package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        if (str.contains("Hello")) {
                            out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                            out.write("Hello\r\n".getBytes());
                        } else if (str.contains("Exit")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            server.close();
                        } else if (str.contains("msg=")){
                            out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                            out.write("What\r\n".getBytes());
                            out.write("\r\n".getBytes());
                       }
                        str = in.readLine();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
}
