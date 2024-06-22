package it.sportandreview.service;

import it.sportandreview.dto.request.PlaygroundRequestDTO;
import it.sportandreview.dto.response.PlaygroundResponseDTO;

public interface PlaygroundService {
    PlaygroundResponseDTO createPlayground(Long facilityId, PlaygroundRequestDTO request);
}
