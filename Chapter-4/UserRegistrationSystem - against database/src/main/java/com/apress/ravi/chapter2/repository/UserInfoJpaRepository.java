package com.apress.ravi.chapter2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.ravi.chapter2.dto.UserInfo;

@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
	public UserInfo findByUsername(String username);
}
