package info.exac.envers_demo;

import info.exac.envers_demo.model.AuthorEntity;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AuthorEntity> authorJacksonTester;


    @Test
    @SneakyThrows
    void getAuthor() {
        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    @SneakyThrows
    void createAuthor() {
        AuthorEntity author = AuthorEntity.builder()
                .name("John Doe")
                .build();

        String responseContent = mockMvc.perform(post("/authors")
                    .contentType("application/json")
                    .content(authorJacksonTester.write(author).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AuthorEntity responseAuthor = authorJacksonTester.parseObject(responseContent);

        assertThat(responseAuthor).isNotNull();
        assertThat(responseAuthor.getId()).isPositive();
        assertThat(responseAuthor.getName()).isEqualTo("John Doe");
    }

}