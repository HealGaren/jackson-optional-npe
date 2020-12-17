import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class OnlyMemberVO {
//    Include 옵션 넣든 안 넣든 null value deserialize에서는 에러 발생
//    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    @JsonProperty
    private Optional<Boolean> option;
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

        OnlyMember resultRaw = mapper.readValue("{\"option\":null}", OnlyMember.class);
        System.out.println(resultRaw);
    }
}
