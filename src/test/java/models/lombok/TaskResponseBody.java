package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponseBody {
    @Data
    public static class ListTaskBody {
        private String creator_id;
        private String created_at;
        private String assignee_id;
        private String assigner_id;
        private Integer comment_count;
        private Boolean is_completed;
        private String content;
        private String description;
        private ListAdd due;
        private String id;
        private List labels;
        private Integer order;
        private Integer priority;
        private String project_id;
        private String section_id;
        private String parent_id;
        private String url;

        @Data
        public static class ListAdd {
            public String date;
            public Boolean is_recurring;
            public String datetime;
            public String string;
            public String timezone;
        }
    }
}
