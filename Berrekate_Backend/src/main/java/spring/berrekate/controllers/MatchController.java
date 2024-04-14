package spring.berrekate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.berrekate.dto.MatchDTO;
import spring.berrekate.entities.Match;
import spring.berrekate.services.MatchService;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllMatches() {
        return ResponseEntity.ok(matchService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable int id) {
        return ResponseEntity.ok(matchService.findMatchById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Match> createMatch(@RequestBody MatchDTO matchDTO) {
        Match createdMatch = matchService.create(matchDTO);
        return ResponseEntity.ok(createdMatch);
    }

    @PutMapping
    public ResponseEntity<Void> updateMatch(@RequestBody MatchDTO matchDTO) {
        matchService.updateMatch(matchDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")

    public ResponseEntity<Void> deleteMatch(@PathVariable int id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/{team1}/{team2}")
    public ResponseEntity<List<Object>> getMatchesByTeamId(@PathVariable int team1, @PathVariable int team2) {
        return ResponseEntity.ok(matchService.findAllMatchesByTeamId(team1, team2));
    }
}