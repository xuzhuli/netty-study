package com.xuzhu.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yunfengli
 * @date 2021/6/21
 * @desc
 */
public class NIOFileChannel {

    public static void main(String[] args) throws IOException {
//        writeFile();
//        readFile();
//        copyFile();
        transferFile();
    }

    private static void writeFile() throws IOException {
        // 创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        // 获得FileChannel
        FileChannel channel = fileOutputStream.getChannel();

        String str = "Hello World";

        // 创建一个缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        // 把数据写入缓冲区
        allocate.put(str.getBytes());

        // 对缓冲区极星flip
        allocate.flip();

        // 把数据写入channel
        channel.write(allocate);

        fileOutputStream.close();
    }

    private static void readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(512);
        channel.read(allocate);
        System.out.println(new String(allocate.array()));
        fileInputStream.close();
    }

    private static void copyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("test2.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        while (true) {
            allocate.clear();
            int read = inputStreamChannel.read(allocate);
            if (read == -1) {
                break;
            }
            allocate.flip();
            outputStreamChannel.write(allocate);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static void transferFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("0.jpg");
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("1.jpg");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        inputStreamChannel.close();
        fileInputStream.close();
        outputStreamChannel.close();
        fileOutputStream.close();
    }
}
