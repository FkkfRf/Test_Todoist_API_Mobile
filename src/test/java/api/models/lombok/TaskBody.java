package api.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskBody {
    private String id;
    private String content;
    private String description;
    private String project_id;

}
