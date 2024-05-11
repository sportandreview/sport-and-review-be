package it.sportandreview.match_state;

public enum MatchStateEnum {
	PENDING(1L),
    BOOKED(2L),
    DENIED(3L),
    CREATED(4L),
    FINISHED(5L);

	private final Long id;

   MatchStateEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
