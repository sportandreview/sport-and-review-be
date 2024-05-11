package it.sportandreview.team;


public interface TeamService {

    Long create(TeamDTO teamDto);

    TeamDTO update(TeamDTO teamDto);
}
