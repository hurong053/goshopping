package com.shopping.oauth.po;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class UserInfoRespPo extends ResponsePo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8374704890275570735L;

	/** 帐号 */
	private String accountId;

	/**
	 * 应用标识
	 */
	private String appId;

	/**
	 * 出生日期
	 */
	private String birthdate;

	/**
	 * 证件号码
	 */
	private String certificateNo;

	/**
	 * 证件类型
	 */
	private String certificateType;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 姓名
	 */
	private String cn;

	/**
	 * 企业简称
	 */
	private String epAbbr;

	/**
	 * 企业名称
	 */
	private String epName;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 固话号码
	 */
	private String phone;

	/**
	 * EMAIL
	 */
	private String mail;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 修改时间
	 */
	private String modifyDate;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 通讯地址
	 */
	private String postalAddress;

	/**
	 * 邮政编码
	 */
	private String postalCode;

	/**
	 * QQ
	 */
	private String qq;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 注册时间
	 */
	private String registerDate;

	// /**
	// * 密保答案
	// */
	// private String secretAnswer;

	/**
	 * 密保问题
	 */
	private String secretQuestion;

	/**
	 * 名称缩写
	 */
	private String sn;

	/**
	 * 用户状态
	 */
	private String state;

	/**
	 * 用户类型
	 */
	private String type;

	/**
	 * 系统生成唯一ID
	 */
	private String uid;

	// /**
	// * 密码
	// */
	// private String userPassword;

	/**
	 * 微博
	 */
	private String weiBo;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 自我介绍
	 */
	private String introduction;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 教育背景
	 */
	private Education[] educationList;

	/**
	 * 工作经历
	 */
	private WorkExperience[] workExperienceList;

	/**
	 * 爱好
	 */
	private String[] favTags;

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public String getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(String birthdate)
	{
		this.birthdate = birthdate;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo)
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateType()
	{
		return certificateType;
	}

	public void setCertificateType(String certificateType)
	{
		this.certificateType = certificateType;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCn()
	{
		return cn;
	}

	public void setCn(String cn)
	{
		this.cn = cn;
	}

	public String getEpAbbr()
	{
		return epAbbr;
	}

	public void setEpAbbr(String epAbbr)
	{
		this.epAbbr = epAbbr;
	}

	public String getEpName()
	{
		return epName;
	}

	public void setEpName(String epName)
	{
		this.epName = epName;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(String modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getPostalAddress()
	{
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress)
	{
		this.postalAddress = postalAddress;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}

	public String getSecretQuestion()
	{
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion)
	{
		this.secretQuestion = secretQuestion;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getWeiBo()
	{
		return weiBo;
	}

	public void setWeiBo(String weiBo)
	{
		this.weiBo = weiBo;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public Education[] getEducationList()
	{
		return educationList;
	}

	public void setEducationList(Education[] educationList)
	{
		this.educationList = educationList;
	}

	public WorkExperience[] getWorkExperienceList()
	{
		return workExperienceList;
	}

	public void setWorkExperienceList(WorkExperience[] workExperienceList)
	{
		this.workExperienceList = workExperienceList;
	}

	public String[] getFavTags()
	{
		return favTags;
	}

	public void setFavTags(String[] favTags)
	{
		this.favTags = favTags;
	}

	@Override
	public String toString()
	{
		JSONObject obj = JSONObject.fromObject(this);
		return obj.toString();
	}

	/**
	 * 教育背景
	 * 
	 * @author wangxm
	 */
	public static class Education
	{
		private String school_type;

		private String school_name;

		private String start_time;

		private String end_time;

		public String getSchool_type()
		{
			return school_type;
		}

		public void setSchool_type(String school_type)
		{
			this.school_type = school_type;
		}

		public String getSchool_name()
		{
			return school_name;
		}

		public void setSchool_name(String school_name)
		{
			this.school_name = school_name;
		}

		public String getStart_time()
		{
			return start_time;
		}

		public void setStart_time(String start_time)
		{
			this.start_time = start_time;
		}

		public String getEnd_time()
		{
			return end_time;
		}

		public void setEnd_time(String end_time)
		{
			this.end_time = end_time;
		}

		@Override
		public String toString()
		{
			JSONObject obj = JSONObject.fromObject(this);
			return obj.toString();
		}
	}

	/**
	 * 工作经验
	 * 
	 * @author wangxm
	 */
	public static class WorkExperience
	{
		private String company;

		private String start_time;

		private String end_time;

		public String getCompany()
		{
			return company;
		}

		public void setCompany(String company)
		{
			this.company = company;
		}

		public String getStart_time()
		{
			return start_time;
		}

		public void setStart_time(String start_time)
		{
			this.start_time = start_time;
		}

		public String getEnd_time()
		{
			return end_time;
		}

		public void setEnd_time(String end_time)
		{
			this.end_time = end_time;
		}

		@Override
		public String toString()
		{
			JSONObject obj = JSONObject.fromObject(this);
			return obj.toString();
		}
	}

}
