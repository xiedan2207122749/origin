package com.thinkifu.origin.app;

import org.junit.jupiter.api.Test;

// @SpringBootTest
class AppApplicationTests {

    @Test
    void contextLoads() {
        String str = "http://15973488008.gnway.cc:8000/download/origin/2023/3/29/computerGame/introduction/1090736981794619392.jpeg?originalFileName=3bb111a008e8dbde5037c2c40eaee619.jpeg";
        System.out.println(str.subSequence(str.lastIndexOf("."), str.length()));


        System.out.println(str.indexOf(".", 3));
        System.out.println();
    }

}
