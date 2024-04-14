package spring.berrekate.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tourist_attractions")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristAttraction implements java.io.Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String details;
	private Double ratingavr=0.0;
	private Double lat=0.0;
	private Double lon=0.0;

	@Transient
	private Set<Comment>touristAttractionComments;
	@Transient
	private Set<ImageModel> cityImages;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

//	public TouristAttraction() {
//
//	};
//
//	public TouristAttraction(Integer id, String name, String address, String details, Integer city_id, Double ratingavr, Double lat, Double lon){
//		super();
//		this.id=id;
//		this.name=name;
//		this.address=address;
//		this.details=details;
//		this.city_id=city_id;
//		this.ratingavr=ratingavr;
//		this.lat=lat;
//		this.lon=lon;
//	}
//
//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name="idTourist_attractions", unique = true, nullable = false)
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	@Column(name="name", nullable = false)
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	@Column(name="address", nullable = true)
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address =address;
//	}
//	@Column(name="details", nullable = true)
//	public String getDetails() {
//		return details;
//	}
//	public void setDetails(String details) {
//		this.details =details;
//	}
//	@Column(name = "city_id", nullable = false, length = 200)
//	public int getCityId() {
//		return city_id;
//	}
//
//	public void setCityId(int city_id) {
//		this.city_id = city_id;
//	}
//
//	@Column(name="ratingavr", nullable = true)
//	public Double getRatingavr() {
//		return ratingavr;
//	}
//
//	public void setRatingavr(Double ratingavr) {
//		this.ratingavr = ratingavr;
//	}
//
//	@Column(name="lat", nullable = true)
//	public Double getLat() {
//		return lat;
//	}
//
//	public void setLat(Double lat) {
//		this.lat = lat;
//	}
//
//	@Column(name = "lon")
//	public Double getLon() {
//		return lon;
//	}
//
//	public void setLon(Double lon) {
//		this.lon = lon;
//	}
//
//

}