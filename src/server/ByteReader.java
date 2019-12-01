package server;

import java.util.LinkedList;
import java.util.List;

public class ByteReader {

    private byte[] array;
    private int pointer;

    public ByteReader(byte[] array){
        this.array = array;
        this.pointer = 0;
    }

    public boolean finished(){
        return pointer >= array.length;
    }

    public String readAll(){
        StringBuilder sb = new StringBuilder();
        for(byte b : array) {
            sb.append((char) b);
            pointer++;
        }
        return sb.toString();
    }

    public String readUntilString(char c){
        StringBuilder sb = new StringBuilder();
        while (!finished() && (char)array[pointer] != c){
            sb.append((char)array[pointer]);
            pointer++;
        }
        return sb.toString();
    }
    public byte[] readUntilBytes(byte b){
        int temp = pointer;
        while (temp < array.length && array[temp] != b){
            temp++;
        }
        byte[] bytes = new byte[temp - pointer];
        temp = 0;
        while (!finished() && array[pointer] != b){
            bytes[temp] = array[pointer];
            pointer++;
            temp++;
        }
        return bytes;
    }
    public int readNext(){
        if(finished()) return -1;
        int value = array[pointer];
        pointer++;
        return value;
    }

    public void dispose(){
        array = null;
        pointer = -1;
    }

    public static byte[] asArray(String c){
        byte[] bytes = new byte[c.length()];

        int i = 0;
        for (char s :
                c.toCharArray()) {
            bytes[i] = (byte)s;
            i++;
        }

        return bytes;
    }
}
