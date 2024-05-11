package it.sportandreview.club_review;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatchService;
import it.sportandreview.player_review.PlayerReviewDTO;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class ClubReviewServiceImpl implements ClubReviewService {

    private final ClubReviewRepository clubReviewRepository;
    private final ClubReviewMapper clubReviewMapper;
    private final GameMatchService gameMatchService;

    @Autowired
    public ClubReviewServiceImpl(ClubReviewRepository clubReviewRepository, ClubReviewMapper clubReviewMapper, GameMatchService gameMatchService) {
        this.clubReviewRepository = clubReviewRepository;
        this.clubReviewMapper = clubReviewMapper;
        this.gameMatchService = gameMatchService;
    }

    @Override
    public Long create(ClubReviewDTO clubReviewDTO) {
        if (Objects.nonNull(clubReviewDTO.getId()) && clubReviewRepository.existsById(clubReviewDTO.getId())){
            throw new CreateEntityException("ClubReview", "ClubReview" + " exists into database");
        }
        // Setting ClubReview UUID
        String uuid = StringUtils.isBlank(clubReviewDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : clubReviewDTO.getUuid();
        clubReviewDTO.setUuid(uuid);
        canSubmitReview(clubReviewDTO);
        ClubReview clubReview = clubReviewRepository.save(clubReviewMapper.toEntity(clubReviewDTO));
        log.debug("ClubReview created");
        return clubReview.getId();
    }

    @Override
    public ClubReviewDTO update(ClubReviewDTO clubReviewDTO) {
        if (Objects.isNull(clubReviewDTO.getId()) || !clubReviewRepository.existsById(clubReviewDTO.getId())) {
            throw new NotFoundException("ClubReview", "ClubReview" + " not found");
        }
        ClubReview saved = clubReviewRepository.save(clubReviewMapper.toEntity(clubReviewDTO));
        log.debug("ClubReview updated");
        ClubReviewDTO savedDTO = clubReviewMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public List<ClubReviewDTO> findAll() {
        return clubReviewRepository.findAll().stream().map(clubReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ClubReviewDTO findById(Long clubReviewId) {
        return clubReviewRepository.findById(clubReviewId).map(clubReviewMapper::toDto).
                orElseThrow(() -> new NotFoundException("ClubReview", "ClubReview" + "not exists into database"));
    }

    private void canSubmitReview(ClubReviewDTO clubReviewDTO) {
        boolean reviewExists = clubReviewRepository.existsByPlayerUser_IdAndClub_Id(clubReviewDTO.getPlayerUserId(), clubReviewDTO.getClubId());
        if (reviewExists) {
            throw new CreateEntityException("Errore", "Non è possibile inviare più di una recensione per la stessa partita e lo stesso utente.");
        }
        gameMatchService.checkTimeForGameMatch(clubReviewDTO.getGameMatchId());
    }
}
