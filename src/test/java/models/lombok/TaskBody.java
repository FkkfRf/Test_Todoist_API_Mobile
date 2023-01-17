package models.lombok;

import lombok.Data;

@Data
public class TaskBody {
    private String id;
    private String section;
    private String description;
    private String project_id;

}
