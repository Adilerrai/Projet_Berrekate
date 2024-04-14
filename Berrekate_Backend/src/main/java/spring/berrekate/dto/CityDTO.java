package spring.berrekate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.berrekate.entities.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDTO {

	private Integer cityId;
	private String name;
	private String country;
	private String description;
	private Double lat;
	private Double lon;
	private ImageModel cityImage;
	private List<Event> events;

	private List<Hotel> hotels;

	private List<Restaurant> restaurants;
	private List<TouristAttraction> touristAttractions;
}
