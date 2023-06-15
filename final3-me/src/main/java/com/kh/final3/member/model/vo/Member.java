package com.kh.final3.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private int userNo;//	USER_NO	NUMBER
	private String userId;//	USER_ID	VARCHAR2(30 BYTE)
	private String userName;//	USER_NAME	VARCHAR2(15 BYTE)
	private String userPwd;//	USER_PWD	VARCHAR2(30 BYTE)
	private String phone;//	PHONE	VARCHAR2(15 BYTE)
	private String email;//	EMAIL	VARCHAR2(50 BYTE)
	private String address;//	ADDRESS	VARCHAR2(300 BYTE)
	private Date birth;//	BIRTH	DATE
	private String deptCode;//	DEPT_CODE	VARCHAR2(4 BYTE)
	private String jobCode;//	JOB_CODE	VARCHAR2(4 BYTE)
	private Date empolymentDate;//	EMPOLYMENT_DATE	DATE
	private Date depatureDate;//	DEPATURE_DATE	DATE
	private String auth;//	AUTH	VARCHAR2(50 BYTE)
	private int enabled;//	ENABLED	NUMBER(1,0)
	private String status;//	STATUS	VARCHAR2(1 BYTE)
	
	private String jobName;
	private String deptName;
}
