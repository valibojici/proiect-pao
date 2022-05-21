package proiect.repository;

import proiect.mapper.RowMapper;
import proiect.service.DBQueryExecutorService;

import java.util.List;

public class Repository {
    protected static <T> List<T> readQuery(String sql, RowMapper<T> rowMapper){
        return DBQueryExecutorService.executeReadQuery(sql, rowMapper).stream().map(x -> (T)x).toList();
    }

    protected static Integer executeQuery(String sql){
        return DBQueryExecutorService.executeUpdateQuery(sql);
    }


    protected static String addQuotMark(String s){
        return "'" + s + "'";
    }
}
