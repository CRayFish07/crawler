package com.maoxiaobing.scan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoxiaobing.scan.repository.MockRepository;
import com.maoxiaobing.scan.service.MockService;

@Service("mockService")
@Transactional
public class MockServiceImpl implements MockService {
	@Autowired
	private MockRepository mockRepository;

	@Override
	public void createOneMock() {
		this.mockRepository.insert();
	}

}
