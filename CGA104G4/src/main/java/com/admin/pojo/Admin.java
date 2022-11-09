package com.admin.pojo;

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
	private Integer admId;
	private Integer admAccStat;
	private String admACC;
	private String admPwd;
	private String admName;
	private Integer admStat;
	private byte[] admPic;

}