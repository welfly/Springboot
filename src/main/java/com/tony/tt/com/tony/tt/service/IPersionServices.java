package com.tony.tt.service;

import java.util.Map;

import com.tony.tt.vo.User;

public interface IPersionServices {
	Map<String, Object> findByIdi (int id);
	Map<String, Object> addOne(User u);
	Map<String, Object> delet(int id);
	Map<String, Object> update1(User u);
	
	
}
