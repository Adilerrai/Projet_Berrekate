package spring.berrekate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.berrekate.entities.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findAllByTeam1OrTeam2(int team1, int team2);
}