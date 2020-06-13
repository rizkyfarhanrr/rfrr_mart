package com.example.midletest_rfrr_mart.model;

import com.google.gson.annotations.SerializedName;

public class InformasiUserItem{

	@SerializedName("kode_user")
	private String kodeUser;

	@SerializedName("jeniskelamin_user")
	private String jeniskelaminUser;

	@SerializedName("password_user")
	private String passwordUser;

	@SerializedName("email_user")
	private String emailUser;

	@SerializedName("namalengkap_user")
	private String namalengkapUser;

	@SerializedName("user_craeted")
	private Object userCraeted;

	@SerializedName("username_user")
	private String usernameUser;

	@SerializedName("picture")
	private String picture;

	public String getKodeUser(){ return kodeUser;}

	public String getJeniskelaminUser(){
		return jeniskelaminUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

	public String getEmailUser(){
		return emailUser;
	}

	public String getNamalengkapUser(){
		return namalengkapUser;
	}

	public Object getUserCraeted(){
		return userCraeted;
	}

	public String getUsernameUser(){
		return usernameUser;
	}

	public String getPicture(){
		return picture;
	}
}