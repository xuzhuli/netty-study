package com.xuzhu.nio;

import java.nio.IntBuffer;

/**
 * @author yunfengli
 * @date 2021/6/18
 * @desc
 */
public class BufferTest {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i=0; i<5; i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
