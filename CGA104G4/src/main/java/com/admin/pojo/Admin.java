package com.admin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADMIN")
public class Admin extends Core {
	private static final long serialVersionUID = 1062017833925367218L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADM_ID")
	private Integer admId;
	@Column(name = "ADM_ACC_STAT")
	private boolean admAccStat;
	@Column(name = "ADM_ACC")
	private String admAcc;
	@Column(name = "ADM_PWD")
	private String admPwd;
	@Column(name = "ADM_NAME")
	private String admName;
	@Column(name = "ADM_STAT")
	private Integer admStat;
	@Column(name = "ADM_PIC")
	private byte[] admPic;

}