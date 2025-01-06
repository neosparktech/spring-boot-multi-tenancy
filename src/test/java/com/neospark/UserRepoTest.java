package com.neospark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.neospark.config.TenantContext;
import com.neospark.config.TenantIdentifierResolver;
import com.neospark.data.UserRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest()
@ActiveProfiles("test")
@Import(TenantIdentifierResolver.class)
@Slf4j
class UserRepoTest {

	@Autowired
	UserRepo userRepo;
	@Autowired
	TenantIdentifierResolver tenantIdentifierResolver;

	@Autowired
	TestEntityManager testEntityManager;

	@BeforeEach
	void setTenant() {
		TenantContext.set(TenantContext.builder().tenantId("tenantA").build());
		testEntityManager.getEntityManager().getEntityManagerFactory().createEntityManager();
	}

	@Test
	// @Sql("classpath:data.sql")
	@Transactional(value = TxType.REQUIRES_NEW)
	void test() {

//
//		TenantContext.set(TenantContext.builder().tenantId("tenantA").build());
//		EntityManager testEm =  testEntityManager.getEntityManager().getEntityManagerFactory().createEntityManager();
//		testEm.
		log.info("Count {}", userRepo.findAll());
	}

}
