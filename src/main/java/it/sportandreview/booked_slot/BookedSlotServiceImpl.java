package it.sportandreview.booked_slot;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.slot.Slot;
import it.sportandreview.slot.SlotMapper;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BookedSlotServiceImpl implements BookedSlotService {

    private final BookedSlotRepository bookedSlotRepository;
    private final BookedSlotMapper bookedSlotMapper;
    private final SlotMapper slotMapper;

    @Autowired
    public BookedSlotServiceImpl(BookedSlotRepository bookedSlotRepository, BookedSlotMapper bookedSlotMapper, SlotMapper slotMapper) {
        this.bookedSlotRepository = bookedSlotRepository;
        this.bookedSlotMapper = bookedSlotMapper;
        this.slotMapper = slotMapper;
    }

    @Override
    public Long create(BookedSlotDTO bookedSlotDTO) {
        if (Objects.nonNull(bookedSlotDTO.getId()) && bookedSlotRepository.existsById(bookedSlotDTO.getId())){
            throw new CreateEntityException("BookedSlot", "BookedSlot" + " exists into database");
        }
        // Setting BookedSlot UUID
        String uuid = StringUtils.isBlank(bookedSlotDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : bookedSlotDTO.getUuid();
        bookedSlotDTO.setUuid(uuid);
        BookedSlot bookedSlot = bookedSlotRepository.save(bookedSlotMapper.toEntity(bookedSlotDTO));
        log.debug("BookedSlot created");
        return bookedSlot.getId();
    }

    @Override
    public ReducedBookedSlotDTO findByDateAndField(LocalDate date, Long fieldId) {
        List<BookedSlot> bookedSlots = bookedSlotRepository.findBookedSlotsForDateAndFieldId(date, fieldId);
        Boolean allDay = bookedSlots.stream()
                .anyMatch(BookedSlot::getAllDay);
        if (Boolean.TRUE.equals(allDay)) {
            ReducedBookedSlotDTO reducedBookedSlotDTO = ReducedBookedSlotDTO.builder().allDay(Boolean.TRUE).build();
            return reducedBookedSlotDTO;
        }
        List<Slot> slots = bookedSlots.stream().map(bookedSlot -> bookedSlot.getSlot()).collect(Collectors.toList());
        ReducedBookedSlotDTO reducedBookedSlotDTO = ReducedBookedSlotDTO.builder().allDay(Boolean.FALSE).slots(slotMapper.toDto(slots)).build();
        return reducedBookedSlotDTO;
    }

    @Override
    public List<BookedSlotDTO> saveAll(List<BookedSlotDTO> bookedSlots) {
        List<BookedSlot> bookedSlotList = bookedSlotRepository.saveAll(bookedSlotMapper.toEntity(bookedSlots));
        return bookedSlotMapper.toDto(bookedSlotList);
    }

    @Override
    public List<BookedSlot> getBookedSlotsByGameId(Long gameId) {
        return bookedSlotRepository.findFirstByGameMatchIdOrderBySlotAsc(gameId);
    }
}
