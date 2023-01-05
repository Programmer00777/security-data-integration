package com.codelib.securitydataintegration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfiguration.class)
public class SecurityMessageRepositoryTests {

    @Autowired
    SecurityMessageRepository repository;

    @Test
    @WithMockCustomUser
    void findAllOnlyToCurrentUser() {
        Method method = ReflectionUtils.findMethod(SecurityMessageRepositoryTests.class, "findAllOnlyToCurrentUser");
        WithMockCustomUser withMockCustomUser = AnnotationUtils.findAnnotation(method, WithMockCustomUser.class);
        List<Message> messages = this.repository.findAll();
        assertThat(messages).hasSize(3);
        for (Message m : messages) {
            assertThat(m.getTo().getId()).isEqualTo(withMockCustomUser.id());
        }
    }
}
