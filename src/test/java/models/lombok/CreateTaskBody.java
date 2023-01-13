package models.lombok;

import lombok.Data;

@Data
public class CreateTaskBody {
    private String added_at;
    private String added_by_uid;
    private String assigned_by_uid;
    private Boolean checked;
    private Integer child_order;
    private Boolean collapsed;
    private String content;
    private String description;
}
