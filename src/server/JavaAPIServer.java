package server;

import hw.unit4.StackHW1;
import server.reflection.MethodExteractor;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;

public class JavaAPIServer {

    private static MethodExteractor exteractor;

    public static void main(String[] args) throws Exception{
        exteractor = new MethodExteractor();
        exteractor.collectMethods(StackHW1.class);
        ServerStart();
    }

    private static byte[] processCommand(String command, ByteReader reader){
        if(!exteractor.isLegalMethod(command))
            return ByteReader.asArray("Illegal Input");
        Method method = exteractor.getMethod(command);

        return ByteReader.asArray("Called method " + method.getName());
    }

    private static void ServerStart() throws Exception{
        try(ServerSocket server = new ServerSocket(8821)){
            while (true) {

                ClientConnection socket = new ClientConnection(server.accept());

                while (true) {
                    ByteReader msg = new ByteReader(socket.receiveBytes());

                    String command = msg.readUntilString(' ');

                    if(command.equals("EXIT"))
                    {
                        socket.send("Disconnect");
                        break;
                    }

                    msg.readAll();
                    msg.dispose();
                    socket.send(command);
                }


            }
        }catch (IOException e){
            System.out.println("IO Exception: " + e.getMessage());
        }
        /*catch (Exception e){
            System.out.println("Exception: " + e);
        }*/
    }

}
