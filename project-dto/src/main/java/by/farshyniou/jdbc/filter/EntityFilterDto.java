package by.farshyniou.jdbc.filter;

public abstract class EntityFilterDto {
    private final int limit;
    private final int offset;

    public EntityFilterDto(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
