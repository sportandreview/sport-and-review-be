package it.sportandreview.sport;

public enum SportEnum {
	PADEL(7L),
    TENNIS(8L),
    CALCIO(9L),
    BASKET(10L),
    CALCIOA5(11L),
    CALCETTO(12L),
    CALCIOTTO(13L);

	private final Long id;

   SportEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
