package com.example.midletest_rfrr_mart.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("code")
	private int code;

	@SerializedName("data_user")
	private List<DataUserItem> dataUser;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public List<DataUserItem> getDataUser(){
		return dataUser;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}