package framework.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import framework.entity.Category;
import framework.entity.TagsItem;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetsCreationUpdateRequest {
    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("tags")
    private List<TagsItem> tags;

    @JsonProperty("status")
    private String status;

    public static PetsCreationUpdateRequest setDefaultData(PetsCreationUpdateRequest request) {
        return PetsCreationUpdateRequest.builder()
                .photoUrls(request.photoUrls)
                .name(request.getName())
                .id(request.getId())
                .category(request.getCategory())
                .tags(request.getTags())
                .status(request.getStatus())
                .build();
    }
}
