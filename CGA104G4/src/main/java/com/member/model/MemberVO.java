package com.member.model;

import com.refill.model.RefillVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MEMBER")
public class MemberVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MEM_ID")
	private Integer memId;
	@Column(name="MEM_EMAIL")
	private String memEmail;
	@Column(name = "MEM_PWD")
	private String memPwd;
	@Column(name = "ACC_STAT",insertable = false)
	private Integer accStat;
	@Column(name = "MEM_NAME")
	private String memName;
	@Column(name="MEM_MOBILE")
	private String memMobile;
	@Column(name="MEM_CITY")
	private String memCity;
	@Column(name="MEM_DIST")
	private String memDist;
	@Column(name="MEM_ADR")
	private String memAdr;
	@Column(name="MEM_REG_TIME",insertable = false)
	private Date memRegTime;
	@Column(name="MEM_PIC")
	private byte[] memPic;
	@Column(name="MEM_TOKEN",insertable = false)
	private Integer memToken;

	@OneToMany
	@JoinColumn(name="MEM_ID",referencedColumnName = "MEM_ID")
	private List<RefillVO> refillVO;


}
