package com.programmers.ticketparis.domain.performance;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

public abstract class CategoryEnumTypeHander<E extends Enum<E> & CategoryEnum> implements TypeHandler<CategoryEnum> {

    private Class<E> categoryType;

    public CategoryEnumTypeHander(Class<E> type) {
        this.categoryType = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, CategoryEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getName());
    }

    @Override
    public CategoryEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return getCategoryEnum(rs.getString(columnName));
    }

    @Override
    public CategoryEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getCategoryEnum(rs.getString(columnIndex));
    }

    @Override
    public CategoryEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getCategoryEnum(cs.getString(columnIndex));
    }

    private CategoryEnum getCategoryEnum(String category) {
        return EnumSet.allOf(categoryType)
                .stream()
                .filter(value -> value.getName().equals(category))
                .findFirst()
                .orElseGet(null);
    }
}
