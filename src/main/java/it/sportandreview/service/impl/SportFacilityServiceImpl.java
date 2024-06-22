package it.sportandreview.service.impl;

import it.sportandreview.dto.request.SportFacilityRequestDTO;
import it.sportandreview.dto.response.GooglePlaceDetailsResponse;
import it.sportandreview.dto.response.SportFacilityResponseDTO;
import it.sportandreview.entity.Address;
import it.sportandreview.entity.SportFacility;
import it.sportandreview.entity.User;
import it.sportandreview.exception.SportFacilityNotFoundException;
import it.sportandreview.mapper.SportFacilityMapper;
import it.sportandreview.repository.SportFacilityRepository;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.GoogleMapsService;
import it.sportandreview.service.SportFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportFacilityServiceImpl implements SportFacilityService {

    private final SportFacilityRepository sportFacilityRepository;
    private final SportFacilityMapper sportFacilityMapper;
    private final GoogleMapsService googleMapsService;
    private final UserRepository userRepository;

    @Override
    public SportFacilityResponseDTO createSportFacility(SportFacilityRequestDTO request) {
        SportFacility sportFacility = sportFacilityMapper.toEntity(request);
        Address address = getAddressFromGoogleMaps(request.getAddress().getPlaceId());
        sportFacility.setAddress(address);

        User owner = getLoggedUser();
        sportFacility.setOwner(owner);

        SportFacility savedSportFacility = sportFacilityRepository.save(sportFacility);
        return sportFacilityMapper.toDto(savedSportFacility);
    }

    @Override
    public SportFacilityResponseDTO updateSportFacility(Long id, SportFacilityRequestDTO request) {
        SportFacility sportFacility = sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException(id));
        sportFacilityMapper.updateEntityFromDto(request, sportFacility);

        Address address = getAddressFromGoogleMaps(request.getAddress().getPlaceId());
        sportFacility.setAddress(address);

        SportFacility updatedSportFacility = sportFacilityRepository.save(sportFacility);
        return sportFacilityMapper.toDto(updatedSportFacility);
    }

    @Override
    public void deleteSportFacility(Long id) {
        if (!sportFacilityRepository.existsById(id)) {
            throw new SportFacilityNotFoundException(id);
        }
        sportFacilityRepository.deleteById(id);
    }

    @Override
    public SportFacilityResponseDTO getSportFacilityById(Long id) {
        SportFacility sportFacility = sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException(id));
        return sportFacilityMapper.toDto(sportFacility);
    }

    @Override
    public List<SportFacilityResponseDTO> getAllSportFacilities() {
        return sportFacilityRepository.findAll().stream()
                .map(sportFacilityMapper::toDto)
                .collect(Collectors.toList());
    }

    private Address getAddressFromGoogleMaps(String placeId) {
        GooglePlaceDetailsResponse.Result placeDetails = googleMapsService.getPlaceDetails(placeId).getResult();
        return Address.builder()
                .street(getAddressComponent(placeDetails, "route"))
                .streetNumber(getAddressComponent(placeDetails, "street_number"))
                .city(getAddressComponent(placeDetails, "locality"))
                .state(getAddressComponent(placeDetails, "administrative_area_level_1"))
                .zipCode(getAddressComponent(placeDetails, "postal_code"))
                .country(getAddressComponent(placeDetails, "country"))
                .build();
    }

    private String getAddressComponent(GooglePlaceDetailsResponse.Result result, String type) {
        return result.getAddress_components().stream()
                .filter(component -> component.getTypes().contains(type))
                .findFirst()
                .map(GooglePlaceDetailsResponse.Result.AddressComponent::getLong_name)
                .orElse(null);
    }

    private User getLoggedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
