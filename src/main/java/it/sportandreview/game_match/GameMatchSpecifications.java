package it.sportandreview.game_match;

import it.sportandreview.booked_slot.BookedSlot;
import it.sportandreview.booked_slot.BookedSlot_;
import it.sportandreview.club.Club;
import it.sportandreview.club.Club_;
import it.sportandreview.field.Field;
import it.sportandreview.field.Field_;
import it.sportandreview.game_level.GameLevel;
import it.sportandreview.game_level.GameLevel_;
import it.sportandreview.slot.Slot;
import it.sportandreview.slot.Slot_;
import it.sportandreview.sport.Sport;
import it.sportandreview.sport.Sport_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;


public class GameMatchSpecifications {

    public static final Specification<GameMatch> NULL_SPECIFICATION = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

    public static Specification<GameMatch> filterByCity(String clubCity) {
        return (root, query, criteriaBuilder) -> {
            Join<GameMatch, Field> fieldJoin = root.join(GameMatch_.FIELD);
            Join<Field, Club> clubJoin = fieldJoin.join(Field_.CLUB);

            return criteriaBuilder.equal(clubJoin.get(Club_.CITY), clubCity);
        };
    }


    public static Specification<GameMatch> filterByClubName(String clubName) {
        return (root, query, criteriaBuilder) -> {
            Join<GameMatch, Field> fieldJoin = root.join(GameMatch_.FIELD);
            Join<Field, Club> clubJoin = fieldJoin.join(Field_.CLUB);

            return criteriaBuilder.equal(clubJoin.get(Club_.NAME), clubName);
        };
    }
    public static Specification<GameMatch> filterByDate(@NotNull LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            Join<GameMatch, BookedSlot> bookedSlotJoin = root.join(GameMatch_.BOOKED_SLOTS, JoinType.LEFT);
            Predicate datePredicate = criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(bookedSlotJoin.get(BookedSlot_.START_DATE), date.atStartOfDay()),
                    criteriaBuilder.lessThan(bookedSlotJoin.get(BookedSlot_.END_DATE), date.atStartOfDay().plusDays(1).minusSeconds(1))
            );
            query.distinct(true);

            return datePredicate;
        };
    }
    public static Specification<GameMatch> filterByTime(LocalTime time) {
        return (root, query, criteriaBuilder) -> {
            Join<GameMatch, BookedSlot> bookedSlotJoin = root.join(GameMatch_.BOOKED_SLOTS, JoinType.LEFT);
            Join<BookedSlot, Slot> slotJoin = bookedSlotJoin.join(BookedSlot_.SLOT, JoinType.LEFT);
            Predicate timePredicate = criteriaBuilder.equal(slotJoin.get(Slot_.TIME), time);
            query.distinct(true);

            return timePredicate;
        };
    }
    public static Specification<GameMatch> filterBySportId(Long sportId) {
        return (root, query, criteriaBuilder) -> {
            Join<GameMatch, Field> fieldJoin = root.join(GameMatch_.FIELD, JoinType.LEFT);
            Join<Field, Sport> sportJoin = fieldJoin.join(Field_.SPORT, JoinType.LEFT);
            Predicate sportPredicate = criteriaBuilder.equal(sportJoin.get(Sport_.ID), sportId);
            query.distinct(true);

            return sportPredicate;
        };
    }
    public static Specification<GameMatch> filterByGameLevelId(Long gameLevelId) {
        return (root, query, criteriaBuilder) -> {
            Join<GameMatch, GameLevel> gameLevelJoin = root.join(GameMatch_.GAME_LEVEL, JoinType.LEFT);
            Predicate genderTeamPredicate = criteriaBuilder.equal(gameLevelJoin.get(GameLevel_.ID), gameLevelId);
            query.distinct(true);

            return genderTeamPredicate;
        };
    }
    public static Specification<GameMatch> withMissingPlayers() {
        return (root, query, criteriaBuilder) -> {
            var subquery = query.subquery(Integer.class);
            var teamJoin = subquery.from(GameMatch.class).join("teams");
            subquery.select(criteriaBuilder.sum(criteriaBuilder.size(teamJoin.get("players"))));
            subquery.groupBy(root.get("id"));

            return criteriaBuilder.lessThan(
                    subquery.getSelection(),
                    root.get("totalPlayers")
            );
        };
    }
    public static Specification<GameMatch> withStateId(Long stateId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("state").get("id"), stateId);
    }
}

