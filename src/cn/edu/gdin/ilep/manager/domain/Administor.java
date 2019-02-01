package cn.edu.gdin.ilep.manager.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.gdin.ilep.util.Constant;

public class Administor {
	private String aid;
	@Size(min=3,max=18,message="{admin.name.length}")
	@NotBlank(message="{admin.name.empty}")
	@Pattern(regexp="^([\\u4E00-\\u9FA5]|\\w)*$",message="{regexp}")
	private String name;
	
	
	@NotBlank(message="{admin.password.empty}")
	private String password;
	
	@NotBlank(message="{admin.password.empty}")
	private String repassword;
	
	@Min(value=0,message="{admin.power.empty}")
	@Max(value=1,message="{admin.power.empty}")
	private int power=Constant.ADMIN_POWER_DEFAULT_NOTSUPER;
	
	@NotBlank(message="{admin.phone.empty}")
	@Size(min=11,max=11,message="{admin.phone.length}")
	@Pattern(regexp="^[0-9]{11}",message="{admin.phone.regexp}")
	private String phone;
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Administor [aid=" + aid + ", name=" + name + ", password="
				+ password + ", power=" + power + ", phone=" + phone
				+ "]";
	}
}
