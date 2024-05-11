package it.sportandreview.player_review;

import it.sportandreview.booked_slot.BookedSlot;
import it.sportandreview.booked_slot.BookedSlotDTO;
import it.sportandreview.booked_slot.BookedSlotService;
import it.sportandreview.club.Club;
import it.sportandreview.club.ClubDTO;
import it.sportandreview.club_review.ClubReviewDTO;
import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.*;
import it.sportandreview.payment.PaymentDTO;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
public class PlayerReviewServiceImpl implements PlayerReviewService{

    private final PlayerReviewRepository playerReviewRepository;
    private final PlayerReviewMapper playerReviewMapper;
    private final GameMatchRepository gameMatchRepository;
    private final GameMatchMapper gameMatchMapper;
    private final GameMatchService gameMatchService;
    private final BookedSlotService bookedSlotService;

    @Autowired
    public PlayerReviewServiceImpl(PlayerReviewRepository playerReviewRepository, PlayerReviewMapper playerReviewMapper,
                                   GameMatchRepository gameMatchRepository, GameMatchMapper gameMatchMapper,
                                   GameMatchService gameMatchService, BookedSlotService bookedSlotService) {
        this.playerReviewRepository = playerReviewRepository;
        this.playerReviewMapper = playerReviewMapper;
        this.gameMatchRepository = gameMatchRepository;
        this.gameMatchMapper = gameMatchMapper;
        this.gameMatchService = gameMatchService;
        this.bookedSlotService = bookedSlotService;
    }
    @Override
    public Long create(PlayerReviewDTO playerReviewDTO) {
        if (Objects.nonNull(playerReviewDTO.getId()) && playerReviewRepository.existsById(playerReviewDTO.getId())){
            throw new CreateEntityException("PlayerReview", "PlayerReview" + " exists into database");
        }
        // Setting PlayerReview UUID
        String uuid = StringUtils.isBlank(playerReviewDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : playerReviewDTO.getUuid();
        playerReviewDTO.setUuid(uuid);
        canSubmitReview(playerReviewDTO);
        PlayerReview playerReview = playerReviewRepository.save(playerReviewMapper.toEntity(playerReviewDTO));
        log.debug("PlayerReview created");
        return playerReview.getId();
    }

    @Override
    public List<PlayerReviewDTO> findAll() {
        return playerReviewRepository.findAll().stream().map(playerReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PlayerReviewDTO findById(Long playerReviewId) {
        return playerReviewRepository.findById(playerReviewId).map(playerReviewMapper::toDto).
                orElseThrow(() -> new NotFoundException("PlayerReview", "PlayerReview" + "not exists into database"));
    }
   private void canSubmitReview(PlayerReviewDTO playerReviewDTO) {
        boolean reviewExists = playerReviewRepository.existsByPlayerUserIdAndMadeById(playerReviewDTO.getPlayerUserId(), playerReviewDTO.getMadeById());
        if (reviewExists) {
            throw new CreateEntityException("Errore", "Non è possibile inviare più di una recensione per la stessa partita e lo stesso utente.");
        }
        gameMatchService.checkTimeForGameMatch(playerReviewDTO.getGameMatchId());
    }

}
