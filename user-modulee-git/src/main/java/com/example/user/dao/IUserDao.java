package com.example.user.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
	public User findByUserName(String userName);

	public void deleteByUserName(String userName);
}
