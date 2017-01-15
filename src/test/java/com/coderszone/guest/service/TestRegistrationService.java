package com.coderszone.guest.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coderszone.common.exception.DataBaseAccessException;
import com.coderszone.common.exception.UserIdAlreadyExistException;
import com.coderszone.common.key.KeyGenService;
import com.coderszone.common.mail.MailService;
import com.coderszone.guest.dao.RegistrationDao;
import com.coderszone.guest.model.RegistrationModel;
import com.coderszone.guest.model.User;
import com.coderszone.guest.service.impl.RegistrationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestRegistrationService {
	
	@Configuration
	static class RegistrationServiceTestContextConfiguration {
		@Bean
		public RegistrationService registrationService() {
			return new RegistrationServiceImpl();
		}
		@Bean
		public RegistrationDao registrationDao() {
			return Mockito.mock(RegistrationDao.class);
		}
		@Bean
		public KeyGenService keyGenService() {
			return Mockito.mock(KeyGenService.class);
		}
		@Bean
		public MailService MailService() {
			return Mockito.mock(MailService.class);
		}
	}
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private KeyGenService keyGenService;
	@Autowired
	private MailService MailService;
	
	@Before
    public void setup() throws Exception {
        RegistrationModel register = new RegistrationModel();
        register.setId(0);
        register.setEmail("test@test.com");
        register.setName("test");
        register.setPassword("pass");
        
        User user=new User();
        user.setId(1);
        user.setEmail("test@test.com");
        user.setName("test");
        user.setPassword("pass");
        user.setVerificationCode("asd123");
        Mockito.when(registrationDao.registerUser(register, "asd123")).thenReturn(1);
        Mockito.when(registrationDao.getUserByUserId("test@test.com")).thenThrow(EmptyResultDataAccessException.class);
        Mockito.doNothing().when(registrationDao).insertUserRole("test@test.com");
        
        Mockito.when(keyGenService.generateNewKeys()).thenReturn("asd123");
        Mockito.doNothing().when(MailService).sendVerificationCode("test@test.com", "asd123");
    }
	@Test
	public void testRegisterUser() throws DataBaseAccessException, UserIdAlreadyExistException{
		RegistrationModel register = new RegistrationModel();
		register.setId(0);
        register.setEmail("test@test.com");
        register.setName("test");
        register.setPassword("pass");
        RegistrationModel register1=registrationService.registerUser(register);
        assertEquals(0,register1.getId());
        
	}

}
