package com.maoxiaobing.entity;

import java.io.Serializable;

public class MockBean implements Serializable {
	private static final long serialVersionUID = 6306652452697868621L;
	private String f1;
	private String f2;

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

}
