package it.sportandreview.review_from_admin;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatchService;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class ReviewFromAdminServiceImpl implements ReviewFromAdminService{

    private final ReviewFromAdminRepository reviewFromAdminRepository;
    private final ReviewFromAdminMapper reviewFromAdminMapper;
    private final GameMatchService gameMatchService;

    @Autowired
    public ReviewFromAdminServiceImpl(ReviewFromAdminRepository reviewFromAdminRepository, ReviewFromAdminMapper reviewFromAdminMapper,
                                     GameMatchService gameMatchService) {
        this.reviewFromAdminRepository = reviewFromAdminRepository;
        this.reviewFromAdminMapper = reviewFromAdminMapper;
        this.gameMatchService = gameMatchService;
    }

    @Override
    public Long create(ReviewFromAdminDTO reviewFromAdminDTO) {
        if (Objects.nonNull(reviewFromAdminDTO.getId()) && reviewFromAdminRepository.existsById(reviewFromAdminDTO.getId())){
            throw new CreateEntityException("ReviewFromAdmin", "ReviewFromAdmin" + " exists into database");
        }
        // Setting ReviewFromAdmin UUID
        String uuid = StringUtils.isBlank(reviewFromAdminDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : reviewFromAdminDTO.getUuid();
        reviewFromAdminDTO.setUuid(uuid);
        canSubmitReview(reviewFromAdminDTO);
        ReviewFromAdmin reviewFromAdmin = reviewFromAdminRepository.save(reviewFromAdminMapper.toEntity(reviewFromAdminDTO));
        log.debug("reviewFromAdmin created");
        return reviewFromAdmin.getId();
    }

    @Override
    public List<ReviewFromAdminDTO> findAll() {
        return reviewFromAdminRepository.findAll().stream().map(reviewFromAdminMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReviewFromAdminDTO findById(Long playerReviewId) {
        return reviewFromAdminRepository.findById(playerReviewId).map(reviewFromAdminMapper::toDto).
                orElseThrow(() -> new NotFoundException("ReviewFromAdmin", "ReviewFromAdmin" + "not exists into database"));
    }
    private void canSubmitReview(ReviewFromAdminDTO reviewFromAdminDTO) {
        boolean reviewExists = reviewFromAdminRepository.existsByPlayerUserIdAndAdminUserId(reviewFromAdminDTO.getPlayerUserId(), reviewFromAdminDTO.getAdminUserId());
        if (reviewExists) {
            throw new CreateEntityException("Errore", "Non è possibile inviare più di una recensione per lo stesso utente.");
        }
        gameMatchService.checkTimeForGameMatch(reviewFromAdminDTO.getGameMatchId());
    }
}
