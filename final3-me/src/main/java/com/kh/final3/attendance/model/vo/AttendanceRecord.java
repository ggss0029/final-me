package com.kh.final3.attendance.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRecord {
	private int attRecordNo;
	private int userNo;
	private String onTime;
	private String offTime;
}
