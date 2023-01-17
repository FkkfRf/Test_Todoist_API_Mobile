package models.lombok;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProjectResponseBody {

    public Long id;
    private String parent_id;
    private Integer order;
    private String color;
    private String name;
    private Integer comment_count;
    private Boolean is_shared;
    private Boolean is_favorite;
    private Boolean is_inbox_project;
    private Boolean is_team_inbox;
    private String url;
    private String view_style;

}
