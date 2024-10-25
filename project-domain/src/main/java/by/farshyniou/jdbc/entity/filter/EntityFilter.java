package by.farshyniou.jdbc.entity.filter;

public abstract class EntityFilter {

    private final int limit;
    private final int offset;

    public EntityFilter(int limit, int offset) {
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
