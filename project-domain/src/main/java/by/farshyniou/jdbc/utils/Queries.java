package by.farshyniou.jdbc.utils;

public class Queries {
    public static final String CREATE_TABLE_BREED = """
                    Create table breed (
            breed_id serial primary key,
            breed_string_id varchar(255),
            breed_name varchar(255),
            breed_temperament varchar(255),
            breed_origin varchar(255),
            breed_country_code varchar(255),
            breed_description varchar(255),
            breed_life_span varchar(255),
            breed_wikipedia_url varchar(255))""";

    public static final String CREATE_TABLE_CAT = """
                                CREATE Table cat (
                        cat_id serial primary key,
                        cat_url varchar(255),
                        breed_id integer,
                        constraint fk_breed_id FOREIGN KEY (breed_id)
                        references breed(breed_id))""";
}
