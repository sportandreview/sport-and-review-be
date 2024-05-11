package it.sportandreview.services;



import java.util.List;

public interface ServicesService {

    Long create(ServicesDTO servicesDTO);

    ServicesDTO update(ServicesDTO servicesDTO);

    List<ServicesDTO> findByClubsId(List<Long> clubId);

    List<ServicesDTO> findAll();

}
