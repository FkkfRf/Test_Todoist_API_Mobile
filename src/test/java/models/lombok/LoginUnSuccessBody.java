package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUnSuccessBody {
    public List<LoginUnSuccess> data;

    @Data
    public static class LoginUnSuccess {
        private String error;
        private String error_code;
        private ErrorExtra errorextra;
        private String error_tag;
        private Integer http_code;

        @Data
        public static class ErrorExtra {
            public String event_id;
            public Integer retry_after;
        }
    }
}
