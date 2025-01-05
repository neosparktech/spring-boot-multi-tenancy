package com.neospark.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, UUID> {

	@Modifying
	@Query("update User set firstName=:firstName , lastName=:lastName where id =:id ")
	int updateUser(UUID id, String firstName, String lastName);

}
