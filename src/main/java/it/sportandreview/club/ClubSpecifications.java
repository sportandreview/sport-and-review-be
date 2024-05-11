package it.sportandreview.club;

import it.sportandreview.brand.Brand;
import it.sportandreview.brand.Brand_;
import it.sportandreview.field.Field;
import it.sportandreview.field.Field_;
import it.sportandreview.field_size.FieldSize;
import it.sportandreview.field_size.FieldSize_;
import it.sportandreview.services.Services;
import it.sportandreview.services.Services_;
import it.sportandreview.sport.Sport_;
import it.sportandreview.sport_equipment.SportEquipment;
import it.sportandreview.sport_equipment.SportEquipment_;
import it.sportandreview.user.User;
import it.sportandreview.user.User_;
import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

public class ClubSpecifications {

   public static Specification<Club> NULL_SPECIFICATION = null;

    public static Specification<Club> cityEquals(@NotNull String city) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Club_.CITY), city);
    }

    public static Specification<Club> sportEquals(@NotNull Long sportId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(Club_.FIELDS, JoinType.INNER).get(Field_.SPORT).get(Sport_.ID), sportId);
    }

    public static Specification<Club> filterByPreferred(@NotNull Long playerId) {
        return (root, query, criteriaBuilder) -> {
            Join<Club, User> preferringUsersJoin = root.join(Club_.PREFERRING_PLAYER_USERS, JoinType.INNER);

            Predicate playerPredicate = criteriaBuilder.equal(preferringUsersJoin.get(User_.ID), playerId);

            return playerPredicate;
        };
    }

    public static Specification<Club> filterByName(@NotNull String name) {
        return (root, query, criteriaBuilder) -> {
            String namePattern = "%" + name + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(Club_.NAME)), namePattern.toLowerCase());
        };
    }

    public static Specification<Club> filterByServices(@NotNull Set<Long> selectedServices) {
        return (root, query, criteriaBuilder) -> {
                Join<Club, Services> servicesJoin = root.join(Club_.SERVICES, JoinType.INNER);
                return servicesJoin.in(selectedServices);
        };
    }


    public static Specification<Club> filterByServicesBySportEquipment(@NotNull Long brandId, @NotNull Set<String> dimensions) {
        return (root, query, criteriaBuilder) -> {
            Join<Club, Services> servicesJoin = root.join(Club_.SERVICES, JoinType.INNER);
            SetJoin<Services, SportEquipment> sportEquipmentJoin = servicesJoin.join(Services_.sportEquipmentSet, JoinType.INNER);
            Join<SportEquipment, Brand> brandJoin = sportEquipmentJoin.join(SportEquipment_.BRAND, JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(brandJoin.get(Brand_.ID), brandId));
            Expression<String> sizeExpr = sportEquipmentJoin.get(SportEquipment_.DIMENSION);
            predicates.add(sizeExpr.in(dimensions));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Club> filterByFootballGoalSize(@NotNull Set<String> footballGoalDimensions) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(footballGoalDimensions) || footballGoalDimensions.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Club, Services> servicesJoin = root.join(Club_.SERVICES, JoinType.INNER);
            Join<Services, SportEquipment> sportEquipmentJoin = servicesJoin.join(Services_.sportEquipmentSet, JoinType.INNER);

            Expression<String> sizeExpr = sportEquipmentJoin.get(SportEquipment_.DIMENSION);
            Predicate sizePredicate = sizeExpr.in(footballGoalDimensions);

            return sizePredicate;
        };
    }

    public static Specification<Club> filterByFieldSize(@NotNull Long fieldId, @NotNull Set<String> fieldSizeDimensions) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(fieldSizeDimensions) || fieldSizeDimensions.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Club, Services> servicesJoin = root.join(Club_.SERVICES, JoinType.INNER);
            SetJoin<Services, FieldSize> fieldSizeJoin = servicesJoin.join(Services_.fieldSizeSet, JoinType.INNER);
            Join<FieldSize, Field> fieldJoin = fieldSizeJoin.join(FieldSize_.FIELD, JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(fieldJoin.get(Field_.ID), fieldId));
            Expression<String> sizeExpr = fieldSizeJoin.get(FieldSize_.DIMENSION);
            predicates.add(sizeExpr.in(fieldSizeDimensions));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
