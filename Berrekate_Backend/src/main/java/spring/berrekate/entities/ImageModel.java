package spring.berrekate.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="image_model")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@ToString
public class ImageModel {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    @Column(name= "hotel_id")
    private Integer hotel_id;

    @Column(name= "restaurant_id")
    private int restaurant_id;

    @Column(name= "touristAttraction_id")
    private int touristAttraction_id;

    @Column(name= "event_id")
    private int event_id;

    @Column(name= "city_id")
    private int city_id;

    @Column(name= "user_id")
    private int user_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "touristAttraction")
    private TouristAttraction touristAttraction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match")
    private Match match;

    public ImageModel(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

    public ImageModel(String name, String type, byte[] pic, int hotel_id, int restaurant_id, int touristAttraction_id, int event_id, int city_id, int user_id) {
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.hotel_id=hotel_id;
        this.restaurant_id=restaurant_id;
        this.event_id=event_id;
        this.city_id=city_id;
        this.user_id=user_id;
        this.touristAttraction_id= touristAttraction_id;
    }
    public ImageModel(Long id, String name, String type, byte[] pic, int hotel_id, int restaurant_id, int touristAttraction_id,int event_id, int city_id, int user_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.hotel_id=hotel_id;
        this.restaurant_id=restaurant_id;
        this.event_id=event_id;
        this.city_id=city_id;
        this.user_id=user_id;
        this.touristAttraction_id= touristAttraction_id;
    }

    public ImageModel(int id, String originalFilename, String contentType, byte[] bytes) {
        this.user_id=id;
        this.name=originalFilename;
        this.type=contentType;
        this.pic=bytes;

    }
}