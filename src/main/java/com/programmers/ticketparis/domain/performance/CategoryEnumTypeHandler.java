package com.programmers.ticketparis.domain.performance;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Category.class)
public class CategoryEnumTypeHandler implements TypeHandler<Category> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Category parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getName());
    }

    @Override
    public Category getResult(ResultSet rs, String columnName) throws SQLException {

        return Category.fromString(rs.getString(columnName));
    }

    @Override
    public Category getResult(ResultSet rs, int columnIndex) throws SQLException {

        return Category.fromString(rs.getString(columnIndex));
    }

    @Override
    public Category getResult(CallableStatement cs, int columnIndex) throws SQLException {

        return Category.fromString(cs.getString(columnIndex));
    }
}
