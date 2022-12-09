package com.ygh.admin;

import com.ygh.admin.commons.utils.JwtUtils;
import com.ygh.admin.commons.utils.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YghAdminApplicationTests {

    @Test
    void contextLoads() {
        String token = JwtUtils.getJwtToken("1", "admin");
        System.out.println(token);
        String encrypt = MD5.encrypt("123456");
        System.out.println(encrypt);
    }

}
