package by.farshyniou.jdbc.utils;

public final class Queries {

    private Queries() {}

    public static final String CREATE_TABLE_BREED = """
                    Create table breed (
            id serial primary key,
            breed_id varchar(255),
            breed_name varchar(255),
            breed_temperament varchar(255),
            breed_origin varchar(255),
            breed_country_code varchar(255),
            breed_description varchar(255),
            breed_life_span varchar(255),
            breed_wikipedia_url varchar(255))""";

    public static final String CREATE_TABLE_CAT = """
                    CREATE Table cat (
            id serial primary key,
            cat_id varchar(255),
            cat_url varchar(255),
            breed_id integer,
            constraint fk_breed_id FOREIGN KEY (breed_id)
            references breed(id))""";

    public static final String INSERT_INTO_BREED = """
                        INSERT INTO BREED (breed_id, breed_name, breed_temperament, breed_origin, breed_country_code, breed_description, breed_life_span, breed_wikipedia_url)
            VALUES ('XFG','Pers','Angry','Belarus','22','Some','10','url')
                        """;
    public static final String INSERT_INTO_CAT = """
                            INSERT INTO cat (cat_id,cat_url, breed_id)
                VALUES ('xhfj4','url','1'),
            ('bggfeq11','url3455','1')
                            """;

    public static final String DELETE_FROM_CAT = """
            delete from cat where id = %s
            """;

    public static final String SELECT_FROM_CAT = """
                        select * from cat 
            left join breed on cat.breed_id = breed.id;
                        """;

    public static final String DROP_TABLES = """
                        DROP TABLE IF EXISTS cat;
            drop table if exists breed;
                        """;
}
