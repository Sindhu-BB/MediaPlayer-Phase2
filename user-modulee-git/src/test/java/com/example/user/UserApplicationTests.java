/*package com.example.user;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserApplicationTests {

	
	@Test
    void testEnableFeignClientsAnnotation() {
        Class<UserApplication> applicationClass = UserApplication.class;
        Annotation[] annotations = applicationClass.getAnnotations();
        boolean isEnableFeignClientsPresent = false;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getSimpleName().equals("EnableFeignClients")) {
                isEnableFeignClientsPresent = true;
                break;
            }
        }
        assertTrue(isEnableFeignClientsPresent);
    }

}
*/
