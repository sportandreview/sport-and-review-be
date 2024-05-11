package it.sportandreview.user_code_type;

public enum UserCodeTypeEnum {
	PHONE(1L),
    EMAIL(2L),
    PASSWORD(3L);

	private final Long id;

   UserCodeTypeEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
