import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class OnlyMemberVO {
    @JsonProperty
    private Optional<Boolean> option = Optional.empty();
}

public class OnlyMember {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(new OnlyMemberVO());
        System.out.println(json);

        OnlyMemberVO result = mapper.readValue(json, OnlyMemberVO.class);
        System.out.println(result);

    }
}
