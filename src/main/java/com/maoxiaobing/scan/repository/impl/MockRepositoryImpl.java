package com.maoxiaobing.scan.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maoxiaobing.mybatis.mapper.MockMapper;
import com.maoxiaobing.scan.repository.MockRepository;

@Repository("mockRepository")
public class MockRepositoryImpl implements MockRepository {
	@Autowired
	private MockMapper mockMapper;

	@Override
	public void insert() {
		//this.mockMapper.insert();
		System.out.println("insert one mock in repository layer");
	}
}
