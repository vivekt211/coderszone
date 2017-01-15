package com.coderszone.common.pageutil;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class PaginationHelper<E> {
    public Page<E> fetchPage(final JdbcTemplate jt, final String sqlCountRows,final String sqlFetchRows,
            final Object args[],final int pageNo,final int pageSize, final ResultSetExtractor<List<E>> resultSetExtracter) {
        // determine how many rows are available
        final int rowCount = jt.queryForObject(sqlCountRows,args, Integer.class);
        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }
        // create the page object
        final Page<E> page = new Page<E>();
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);
        Object[] argsModified=new Object[args.length+2];
        for(int i=0;i<args.length;i++){
        	argsModified[i]=args[i];
        }
        argsModified[argsModified.length-2]=(pageNo-1)*pageSize;
        argsModified[argsModified.length-1]=pageSize;
        // fetch a single page of results
       // final int startRow = (pageNo - 1) * pageSize;
       List<E> listVal= jt.query(sqlFetchRows,argsModified, resultSetExtracter);
       page.setPageItems(listVal);
        return page;
    }
    public Page<E> fetchPage(final JdbcTemplate jt, final String sqlCountRows,final String sqlFetchRows,
            final Object args[],final int pageNo,final int pageSize, final RowMapper<E> rowMapper) {
        // determine how many rows are available
        final int rowCount = jt.queryForObject(sqlCountRows,args, Integer.class);
        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }
        // create the page object
        final Page<E> page = new Page<E>();
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);
        Object[] argsModified=new Object[args.length+2];
        for(int i=0;i<args.length;i++){
        	argsModified[i]=args[i];
        }
        argsModified[argsModified.length-2]=(pageNo-1)*pageSize;
        argsModified[argsModified.length-1]=pageSize;
        // fetch a single page of results
       // final int startRow = (pageNo - 1) * pageSize;
	       List<E> listVal= jt.query(sqlFetchRows,argsModified, rowMapper);
	       page.setPageItems(listVal);
        return page;
    }
}
