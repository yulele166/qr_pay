package com.exam.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) throws Exception{
        String[] values = new String[]{new String("3个月vip".getBytes(),"utf-8")};
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                    : valueStr + values[i] + ",";
        }
        //乱码解决，这段代码在出现乱码时使用
        valueStr = new String(valueStr.getBytes(), "utf-8");
        System.out.println(valueStr);
    }
}
