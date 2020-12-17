import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class JsonPropertyInConstructorVO {
    private Optional<Boolean> option;

    public JsonPropertyInConstructorVO() {}


    public JsonPropertyInConstructorVO(@JsonProperty("option") Optional<Boolean> option) {
        this.option = option;
    }
}

public class JsonPropertyInConstructor {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(new JsonPropertyInConstructorVO(Optional.empty()));
        System.out.println(json);

        JsonPropertyInConstructorVO result = mapper.readValue(json, JsonPropertyInConstructorVO.class);
        System.out.println(result);
    }
}
