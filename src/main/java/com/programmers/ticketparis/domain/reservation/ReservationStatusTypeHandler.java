package com.programmers.ticketparis.domain.reservation;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ReservationStatus.class)
public class ReservationStatusTypeHandler implements TypeHandler<ReservationStatus> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, ReservationStatus parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter.toString());
    }

    @Override
    public ReservationStatus getResult(ResultSet resultSet, String columnName) throws SQLException {
        String name = resultSet.getString(columnName);
        return ReservationStatus.valueOf(name);
    }

    @Override
    public ReservationStatus getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String name = resultSet.getString(columnIndex);
        return ReservationStatus.valueOf(name);
    }

    @Override
    public ReservationStatus getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String name = callableStatement.getString(columnIndex);
        return ReservationStatus.valueOf(name);
    }
}
