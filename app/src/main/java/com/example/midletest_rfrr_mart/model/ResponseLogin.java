package com.example.midletest_rfrr_mart.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("code")
	private int code;

	@SerializedName("informasi_user")
	private List<InformasiUserItem> informasiUser;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public List<InformasiUserItem> getInformasiUser(){
		return informasiUser;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}