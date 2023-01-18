package models.lombok;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectResponseBodyJsonCreator {

    public String id;
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
    private String type;
    @JsonCreator
    public ProjectResponseBodyJsonCreator(){
        this.id = id;
        this.parent_id = parent_id;
        this.order = order;
        this.color = color;
        this.name = name;
        this.comment_count = comment_count;
        this.is_shared = is_shared;
        this.is_favorite = is_favorite;
        this.is_inbox_project = is_inbox_project;
        this.is_team_inbox = is_team_inbox;
        this.url = url;
        this.view_style = view_style;
    }
    public void setType(String type) {
        this.type = type;
    }
}