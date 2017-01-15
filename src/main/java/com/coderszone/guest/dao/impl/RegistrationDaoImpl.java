package com.coderszone.guest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.coderszone.common.Constants;
import com.coderszone.common.DateUtil;
import com.coderszone.guest.dao.RegistrationDao;
import com.coderszone.guest.mapper.UserMapper;
import com.coderszone.guest.model.RegistrationModel;
import com.coderszone.guest.model.User;

public class RegistrationDaoImpl implements RegistrationDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	private JdbcTemplate jdbcTemplate;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	private String createNewUser;
	private String getUserByUserId;
	private String getUserById;
	protected String updateUserAsVerified;
	protected String insertUserRole;

	@Override
	public int registerUser(final RegistrationModel registrationModel,final String regKey) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(createNewUser, 1);
				ps.setString(1, registrationModel.getEmail());
				ps.setString(2, registrationModel.getPassword());
				ps.setString(3, registrationModel.getName());
				ps.setString(4, registrationModel.getEmail());
				ps.setTimestamp(5, DateUtil.utilToSql(new Date()));
				ps.setString(6, registrationModel.getEmail());
				ps.setTimestamp(7, DateUtil.utilToSql(new Date()));
				ps.setString(8, regKey);
				return ps;
			}
		}, keyHolder);
		int key = keyHolder.getKey().intValue();
		return key;
	}

	@Override
	public User getUserByUserId(String email) {
		return jdbcTemplate.queryForObject(getUserByUserId, new Object[] { email }, new UserMapper());
	}
	@Override
	public User getUserByid(int id) {
		return jdbcTemplate.queryForObject(getUserById, new Object[] { id }, new UserMapper());
	}

	@Override
	public void verifyUserByid(final int id) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(updateUserAsVerified, 1);
				ps.setInt(1, Constants.USER_VERIFIED);
				ps.setInt(2, id);
				return ps;
			}
		});
		
	}
	
	@Override
	public void insertUserRole(final String email) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertUserRole, 1);
				ps.setString(1, email);
				ps.setString(2, Constants.ROLE_BLOGGER);
				ps.setString(3, email);
				ps.setTimestamp(4,DateUtil.utilToSql(new Date()));
				
				return ps;
			}
		});
	}

	public String getCreateNewUser() {
		return createNewUser;
	}

	public void setCreateNewUser(String createNewUser) {
		this.createNewUser = createNewUser;
	}

	public String getGetUserByUserId() {
		return getUserByUserId;
	}

	public void setGetUserByUserId(String getUserByUserId) {
		this.getUserByUserId = getUserByUserId;
	}
	
	public String getGetUserById() {
		return getUserById;
	}

	public void setGetUserById(String getUserById) {
		this.getUserById = getUserById;
	}

	public String getUpdateUserAsVerified() {
		return updateUserAsVerified;
	}

	public void setUpdateUserAsVerified(String updateUserAsVerified) {
		this.updateUserAsVerified = updateUserAsVerified;
	}

	public String getInsertUserRole() {
		return insertUserRole;
	}

	public void setInsertUserRole(String insertUserRole) {
		this.insertUserRole = insertUserRole;
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
