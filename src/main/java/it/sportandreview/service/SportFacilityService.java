package it.sportandreview.service;

import it.sportandreview.dto.request.SportFacilityRequestDTO;
import it.sportandreview.dto.response.SportFacilityResponseDTO;

import java.util.List;

public interface SportFacilityService {

    SportFacilityResponseDTO createSportFacility(SportFacilityRequestDTO request);
    SportFacilityResponseDTO updateSportFacility(Long id, SportFacilityRequestDTO request);
    void deleteSportFacility(Long id);
    SportFacilityResponseDTO getSportFacilityById(Long id);
    List<SportFacilityResponseDTO> getAllSportFacilities();
}
