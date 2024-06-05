package it.sportandreview.slot;

import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Log4j2
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final SlotMapper slotMapper;

    @Autowired
    public SlotServiceImpl(SlotRepository slotRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
    }

    @Override
    public List<SlotDTO> createAll(Set<SlotDTO> slotDTOs) {
      Set<SlotDTO> slotsSet = slotDTOs.stream().map(slotDTO -> {
            String uuid = StringUtils.isBlank(slotDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : slotDTO.getUuid();
            slotDTO.setUuid(uuid);
            return slotDTO;
        }).sorted(Comparator.comparing(SlotDTO::getTime)).collect(Collectors.toCollection(LinkedHashSet::new));
        List<Slot> slots = slotRepository.saveAll(slotMapper.toEntity(slotsSet));
        return slotMapper.toDto(slots);
    }
}
