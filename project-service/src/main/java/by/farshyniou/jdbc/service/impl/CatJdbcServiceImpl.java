package by.farshyniou.jdbc.service.impl;

import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.converter.to_dto.ToDtoConverter;
import by.farshyniou.jdbc.converter.to_entity.ToEntityConverter;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.exception.EntityNotFoundException;
import by.farshyniou.jdbc.repository.impl.CatJdbcRepositoryImpl;
import by.farshyniou.jdbc.service.JdbcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class CatJdbcServiceImpl implements JdbcService<Long, CatDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatJdbcServiceImpl.class);
    private static final CatJdbcServiceImpl INSTANCE = new CatJdbcServiceImpl();

    private CatJdbcServiceImpl() {
    }

    public static CatJdbcServiceImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public void createTable() {
        CatJdbcRepositoryImpl.getInstance().createTable();
    }

    @Override
    public Long delete(Long id) {
        return CatJdbcRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public List<CatDto> findAll() {
        List<Cat> cats = CatJdbcRepositoryImpl.getInstance().findAll();
        List<CatDto> catDtos = new ArrayList<>();
        cats.forEach(cat -> {
            catDtos.add(ToDtoConverter.convertToCatDto(cat));
        });
        return catDtos;
    }

    @Override
    public DatabaseMetaData getMetaData() {
        return CatJdbcRepositoryImpl.getInstance().getMetaData();
    }

    @Override
    public CatDto findById(Long catId) {
        return ToDtoConverter.convertToCatDto(
                CatJdbcRepositoryImpl.getInstance().findById(catId).orElseThrow(
                        () -> new EntityNotFoundException("Cat with id " + catId + " not found")
                ));
    }

    @Override
    public boolean update(CatDto catDto) {
        return CatJdbcRepositoryImpl.getInstance().update(ToEntityConverter.toCatEntity(catDto));
    }

    @Override
    public boolean insert(CatDto dto) {
        return CatJdbcRepositoryImpl.getInstance().insert(ToEntityConverter.toCatEntity(dto));
    }
}
