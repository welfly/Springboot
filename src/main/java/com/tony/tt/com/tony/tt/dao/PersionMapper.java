package com.tony.tt.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tony.tt.vo.User;

@Mapper
public interface PersionMapper {
	User findById(int id) throws Exception;
	int addOne(User u) throws Exception;
	int d1 (int id) throws Exception;
	int updateByName (User u) throws Exception;
	int findMaxId() throws Exception;
}
