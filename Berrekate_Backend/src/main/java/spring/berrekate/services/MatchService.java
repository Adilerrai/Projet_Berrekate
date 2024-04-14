package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.berrekate.dto.MatchDTO;
import spring.berrekate.entities.Match;
import spring.berrekate.repositories.MatchRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;

    public MatchService(MatchRepository matchRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
    }

    public List<Object> findAll() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Object convertToDto(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }

   public MatchDTO findMatchById(int matchId) {
            Optional<Match> match = matchRepository.findById(matchId);
            return (MatchDTO) convertToDto(match.get());
        }

        public Match create(MatchDTO matchDTO) {
            Match match = convertToEntity(matchDTO);
            Match savedMatch = matchRepository.save(match);
            return savedMatch;
        }

    private Match convertToEntity(MatchDTO matchDTO) {
        return modelMapper.map(matchDTO, Match.class);
    }

    public void updateMatch(MatchDTO matchDTO) {
        Match match = convertToEntity(matchDTO);
        matchRepository.save(match);
    }

    public void deleteMatchById(int id) {
        matchRepository.deleteById(id);
    }

    public List<Object> findAllMatchesByTeamId(int team1, int team2) {
        List<Match> matches = matchRepository.findAllByTeam1OrTeam2(team1, team2);
        return matches.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

