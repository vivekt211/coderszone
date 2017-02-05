package com.coderszone.blog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.coderszone.blog.dao.BlogDao;
import com.coderszone.blog.mapper.BlogResultSetExtractor;
import com.coderszone.blog.mapper.BlogResultSetMapper;
import com.coderszone.blog.mapper.TagIdResultSetExtractor;
import com.coderszone.blog.model.Blog;
import com.coderszone.blog.model.Comment;
import com.coderszone.blog.model.CommentParam;
import com.coderszone.common.DateUtil;
import com.coderszone.common.pageutil.Page;
import com.coderszone.common.pageutil.PaginationHelper;

public class BlogDaoImpl implements BlogDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	private JdbcTemplate jdbcTemplate;
	
	private String getTagIdMap;
	private String insertTagBlogMap;
	private String deleteTagBlogMap;
	private String insertNewBlog;
	private String updateBlog;
	private String getAllTags;
	private String getBlogTags;
	private String getBlog;
	private String getAllBlogSizeByKeyword;
	private String getAllBlogByKeyword;
	private String getAllBlogSize;
	private String getAllBlog;
	private String getAllBlogSizeByUserID;
	private String getAllBlogByUSerID;

	private String getAllCommentsByBlogId;

	protected String insertComment;
	private String getCommentById;
	
		public String getInsertComment() {
		return insertComment;
	}


	public void setInsertComment(String insertComment) {
		this.insertComment = insertComment;
	}


		public String getGetAllCommentsByBlogId() {
		return getAllCommentsByBlogId;
	}


	public void setGetAllCommentsByBlogId(String getAllCommentsByBlogId) {
		this.getAllCommentsByBlogId = getAllCommentsByBlogId;
	}


		public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	@Override
	public Blog getBlog(int id) {
		return jdbcTemplate.queryForObject(getBlog,new Object[]{id}, new BlogResultSetMapper());
	}
	@Override
	public String getBlogTags(int id) {
		return jdbcTemplate.queryForObject(getBlogTags,new Object[]{id},String.class);
	}

	public String getGetAllTags() {
		return getAllTags;
	}


	public void setGetAllTags(String getAllTags) {
		this.getAllTags = getAllTags;
	}


	public String getGetBlog() {
		return getBlog;
	}


	public void setGetBlog(String getBlog) {
		this.getBlog = getBlog;
	}


	@Override
	public List<String> getAlltags() {
		return jdbcTemplate.queryForList(getAllTags, String.class);
	}



	@Override
	public Map<String, Integer> getTagIdMap() {
		return jdbcTemplate.query(getTagIdMap, new TagIdResultSetExtractor());
	}

	@Override
	public void insertTagMap(final int id, final String tags, final Map<String, Integer> tagMap) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertTagBlogMap, 1);
				ps.setInt(1, id);
				ps.setInt(2, tagMap.get(tags));
				return ps;
			}
			});
	}
	
	@Override
	public void deleteTagMap(int id) {
		jdbcTemplate.update(deleteTagBlogMap, new Object[]{id});
	}


	@Override
	public int insertNewBlog(final Blog blog) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertNewBlog, 1);
				ps.setString(1, blog.getKeywords());
				ps.setString(2, blog.getTitle());
				ps.setString(3, blog.getDesc());
				ps.setString(4, blog.getContent());
				ps.setString(5, blog.getCreatedBy());
				ps.setTimestamp(6, DateUtil.utilToSql(new Date()));
				ps.setString(7, blog.getModBy());
				ps.setTimestamp(8, DateUtil.utilToSql(new Date()));
				return ps;
			}
		}, keyHolder);
		int key = keyHolder.getKey().intValue();
		return key;
	}


	@Override
	public void updateBlog(final Blog blog) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(updateBlog, 1);
				ps.setString(1, blog.getKeywords());
				ps.setString(2, blog.getTitle());
				ps.setString(3, blog.getDesc());
				ps.setString(4, blog.getContent());
				ps.setString(5, blog.getModBy());
				ps.setTimestamp(6, DateUtil.utilToSql(new Date()));
				ps.setInt(7, blog.getId());
				return ps;
			}
		});
	}


	@Override
	public Page<Blog> getAllBlogsBykeyWord(String keyword, int pageNo, int pageSize) {

		PaginationHelper<Blog> ph = new PaginationHelper<Blog>();
		if (keyword!=null && !keyword.isEmpty()) {
			return ph.fetchPage(jdbcTemplate, getAllBlogSizeByKeyword, getAllBlogByKeyword, new Object[] { (new StringBuilder("%")).append(keyword).append("%").toString(),(new StringBuilder("%")).append(keyword).append("%").toString(),(new StringBuilder("%")).append(keyword).append("%").toString() }, pageNo, pageSize, new BlogResultSetExtractor());
		}else{
			return ph.fetchPage(jdbcTemplate, getAllBlogSize, getAllBlog, new Object[0], pageNo, pageSize, new BlogResultSetExtractor());
		}
	}


	@Override
	public Page<Blog> getAllBlogsByUserId(String username, int pageNo, int pageSize) {

		PaginationHelper<Blog> ph = new PaginationHelper<Blog>();
		return ph.fetchPage(jdbcTemplate, getAllBlogSizeByUserID, getAllBlogByUSerID, new Object[]{username}, pageNo, pageSize, new BlogResultSetExtractor());

	}
	
	@Override
	public List<Comment> getAllCommentsByBlogId(int blogId) {
		return jdbcTemplate.query(getAllCommentsByBlogId,new Object[]{blogId},new RowMapper<Comment>(){
			
			@Override
			public Comment mapRow(ResultSet rs, int No) throws SQLException {
				Comment commnt=new Comment();
				commnt.setId(rs.getInt("id"));
				commnt.setDateTime(DateUtil.timeStampToString(rs.getTimestamp("date_time")));
				commnt.setName(rs.getString("name"));
				commnt.setEmail(rs.getString("email"));
				commnt.setContent(rs.getString("content"));
				return commnt;
			}});
		
	}
	@Override
	public int postComment(final CommentParam commentParam) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertComment, 1);
				ps.setTimestamp(1, DateUtil.utilToSql(new Date()));
				ps.setString(2, commentParam.getName());
				ps.setString(3, commentParam.getEmail());
				ps.setString(4, commentParam.getContent());
				ps.setInt(5, commentParam.getBlogId());
				return ps;
			}
		}, keyHolder);
		int key = keyHolder.getKey().intValue();
		return key;
	}


	@Override
	public Comment getCommentById(int id) {
		
      return jdbcTemplate.queryForObject(getCommentById,new Object[]{id},new RowMapper<Comment>(){
			
			@Override
			public Comment mapRow(ResultSet rs, int No) throws SQLException {
				Comment commnt=new Comment();
				commnt.setId(rs.getInt("id"));
				commnt.setDateTime(DateUtil.timeStampToString(rs.getTimestamp("date_time")));
				commnt.setName(rs.getString("name"));
				commnt.setEmail(rs.getString("email"));
				commnt.setContent(rs.getString("content"));
				return commnt;
			}});
		
	}

	
	public String getGetTagIdMap() {
		return getTagIdMap;
	}


	public void setGetTagIdMap(String getTagIdMap) {
		this.getTagIdMap = getTagIdMap;
	}


	public String getInsertTagBlogMap() {
		return insertTagBlogMap;
	}


	public void setInsertTagBlogMap(String insertTagBlogMap) {
		this.insertTagBlogMap = insertTagBlogMap;
	}


	public String getDeleteTagBlogMap() {
		return deleteTagBlogMap;
	}


	public void setDeleteTagBlogMap(String deleteTagBlogMap) {
		this.deleteTagBlogMap = deleteTagBlogMap;
	}


	public String getInsertNewBlog() {
		return insertNewBlog;
	}


	public void setInsertNewBlog(String insertNewBlog) {
		this.insertNewBlog = insertNewBlog;
	}


	public String getUpdateBlog() {
		return updateBlog;
	}


	public void setUpdateBlog(String updateBlog) {
		this.updateBlog = updateBlog;
	}


	public String getGetBlogTags() {
		return getBlogTags;
	}


	public void setGetBlogTags(String getBlogTags) {
		this.getBlogTags = getBlogTags;
	}


	public String getGetAllBlogSizeByUserID() {
		return getAllBlogSizeByUserID;
	}


	public void setGetAllBlogSizeByUserID(String getAllBlogSizeByUserID) {
		this.getAllBlogSizeByUserID = getAllBlogSizeByUserID;
	}


	public String getGetAllBlogByUSerID() {
		return getAllBlogByUSerID;
	}


	public void setGetAllBlogByUSerID(String getAllBlogByUSerID) {
		this.getAllBlogByUSerID = getAllBlogByUSerID;
	}

	

	public String getGetAllBlogSizeByKeyword() {
		return getAllBlogSizeByKeyword;
	}


	public void setGetAllBlogSizeByKeyword(String getAllBlogSizeByKeyword) {
		this.getAllBlogSizeByKeyword = getAllBlogSizeByKeyword;
	}


	public String getGetAllBlogByKeyword() {
		return getAllBlogByKeyword;
	}


	public void setGetAllBlogByKeyword(String getAllBlogByKeyword) {
		this.getAllBlogByKeyword = getAllBlogByKeyword;
	}


	public String getGetAllBlogSize() {
		return getAllBlogSize;
	}


	public void setGetAllBlogSize(String getAllBlogSize) {
		this.getAllBlogSize = getAllBlogSize;
	}


	public String getGetAllBlog() {
		return getAllBlog;
	}


	public void setGetAllBlog(String getAllBlog) {
		this.getAllBlog = getAllBlog;
	}


	public String getGetCommentById() {
		return getCommentById;
	}


	public void setGetCommentById(String getCommentById) {
		this.getCommentById = getCommentById;
	}


	

	



	
	
	
	
	
/*	@Override
	public Bus createNewBus(final Bus bus) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(createNewBus, 1);
				ps.setInt(1, bus.getBusNo());
				ps.setString(2, bus.getNoPlate());
				ps.setInt(3, bus.getSchoolId());
				ps.setInt(4, bus.getDriverId());
				ps.setString(5, bus.getCreatedBy());
				ps.setTimestamp(6, DateUtil.utilToSql(bus.getCreatedDate()));
				ps.setString(7, bus.getModBy());
				ps.setTimestamp(8, DateUtil.utilToSql(bus.getModDate()));
				return ps;
			}
		}, keyHolder);
		int key = keyHolder.getKey().intValue();
		bus.setId(key);
		return bus;
	}

	@Override
	public Page<Bus> getAllBusesBykeyWord(String keyword, String type, int pageNo,int pageSize) {
		
		PaginationHelper<Bus> ph = new PaginationHelper<Bus>();
		if (!keyword.isEmpty()) {
			if (type.equalsIgnoreCase("busno"))
				return ph.fetchPage(jdbcTemplate, getAllBusSizeByBusNo, getAllBusByBusNo, new Object[] { (new StringBuilder("%")).append(keyword).append("%").toString() }, pageNo, pageSize, new BusMapper());
			if (type.equalsIgnoreCase("schoolname"))
				return ph.fetchPage(jdbcTemplate, getAllBusSizeBySchool, getAllBusBySchool, new Object[] { (new StringBuilder("%")).append(keyword).append("%").toString() }, pageNo, pageSize, new BusMapper());
			else
				return ph.fetchPage(jdbcTemplate, getAllBusSize, getAllBus, new Object[0], pageNo, pageSize, new BusMapper());
		} else {
			return ph.fetchPage(jdbcTemplate, getAllBusSize, getAllBus, new Object[0], pageNo, pageSize, new BusMapper());
		}
	}

	@Override
	public Bus getBusById(int id) {
		return jdbcTemplate.queryForObject(getBusById, new Object[] { id }, new BusMapper());

	}

	@Override
	public void updateBus(final Bus bus) {
		int i = jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(updateBus);

				ps.setInt(1, bus.getBusNo());
				ps.setString(2, bus.getNoPlate());
				ps.setInt(3, bus.getSchoolId());
				ps.setInt(4, bus.getDriverId());
				ps.setString(5, bus.getModBy());
				ps.setTimestamp(6, DateUtil.utilToSql(bus.getModDate()));
				ps.setInt(7, bus.getId());
				return ps;
			}
		});
		if (i == 1) {
			System.out.println("Bus with id "+bus.getId()+" updated Successfully");
		} else {
			throw new RuntimeException("Can not update school ");
		}

	}
	@Override
	public Page<Bus> getAllBuses() {
		return getAllBusesBykeyWord("", "",1,7);
	}
	
	@Override
	public List<Bus> getBusBySchoolId(int id) {
		return jdbcTemplate.query(getBusBySchoolId, new Object[] { id }, new BusMapper());
	}
	@Override
	public List<Device> getUnallocatedGpsDevice(int id) {
		return jdbcTemplate.query(getUnallocatedGpsDevice, new Object[] { id }, new RowMapper<Device>(){

			@Override
			public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
				Device device = new Device();
				device.setId(rs.getString("id"));
				device.setPhoneNo(rs.getString("phone_no"));
				device.setDeviceUserId(rs.getString("user_id"));
				device.setModel(rs.getString("model"));
				device.setCreatedBy(rs.getString("created_by"));
				device.setCreatedDate(rs.getTimestamp("created_date"));
				device.setModBy(rs.getString("mod_by"));
				device.setModDate(rs.getTimestamp("mod_date"));
				return device;
			}
		});
	}
	@Override
	public void assignGps(final int busId, final String gpsId,final String username,final Date date) {
		int i = jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(assignGps);

				ps.setString(1, gpsId);
				ps.setInt(2,busId);
				ps.setString(3, username);
				ps.setTimestamp(4, DateUtil.utilToSql(date));
				ps.setString(5, username);
				ps.setTimestamp(6, DateUtil.utilToSql(date));
				return ps;
			}
		});
		if (i == 1) {
			System.out.println("Bus with id "+busId+" updated Successfully");
		} else {
			throw new RuntimeException("Can not update bus ");
		}

	}
	@Override
	public void removeAssignGps(final int busId, final String username, final Date date) {
		int i = jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(removeAssignGps);

				ps.setInt(1,Constants.DELETED);
				ps.setString(2, username);
				ps.setTimestamp(3, DateUtil.utilToSql(date));
				ps.setInt(4, busId);
				return ps;
			}
		});
		if (i == 1) {
			System.out.println("Bus with id "+busId+" updated Successfully");
		} else {
			throw new RuntimeException("Can not update bus ");
		}
	}
*/

	
	
}
