package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClientConnection {

    private static final int MAX_DIGIT_COUNT = 4;

    private Socket socket;
    private InputStream input;
    private OutputStream output;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    public void send(String msg) throws Exception{
        int length = ("" + msg.length()).length();

        StringBuilder sb = new StringBuilder();
        int digitCount = length == 0 ? 1 : 0;
        while (length > 0){
            length /= 10;
            digitCount++;
        }

        if(digitCount > MAX_DIGIT_COUNT) throw new Exception("Message to large");

        for (int i = 0; i < MAX_DIGIT_COUNT - digitCount; i++) {
            sb.append(0);
        }

        sb.append(msg.length());
        ByteBuffer lengthMsg = StandardCharsets.UTF_8.encode(sb.toString());
        ByteBuffer message = StandardCharsets.UTF_8.encode(msg);

        output.write(lengthMsg.array());
        output.write(message.array());
    }

    public String receiveString() throws IOException{
        String lenMsg = readStringFromStream(input, MAX_DIGIT_COUNT);
        int length = Integer.decode(lenMsg);
        return readStringFromStream(input, length);
    }
    public byte[] receiveBytes() throws IOException{
        String lenMsg = readStringFromStream(input, MAX_DIGIT_COUNT);
        int length = toInt(lenMsg);
        byte[] bytes = readBytesFromStream(input, length);
        return bytes;
    }

    public static String readStringFromStream(InputStream stream, int amount) throws IOException{
        return bytesToString(readBytesFromStream(stream, amount));
    }
    public static byte[] readBytesFromStream(InputStream stream, int amount) throws IOException{
        byte[] bytes = new byte[amount];
        stream.read(bytes);
        return bytes;
    }

    public static String bytesToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b :
                bytes) {
            sb.append((char)b);
        }
        return sb.toString();
    }

    public static int toInt(String str){
        int value = 0;
        for (char c :
                str.toCharArray()) {
            value = value * 10 + Character.digit(c, 10);
        }
        return value;
    }
}
