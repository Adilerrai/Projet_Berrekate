package spring.berrekate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.berrekate.entities.ImageModel;


import javax.persistence.Entity;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchDTO {
    private Integer match_id;
    private String team1;
    private String team2;
    private String date;
    private String time;
    private String stadium;
    private Set<ImageModel> matchImages;
}
