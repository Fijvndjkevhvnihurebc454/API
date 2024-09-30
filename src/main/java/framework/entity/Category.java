package framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Category {
	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public static Category setDefaultData(Category category) {
		return Category.builder()
				.name(category.getName())
				.id(category.getId())
				.build();
	}

}