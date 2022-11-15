package com.refill.model;

import com.member.model.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="REFILL")
public class RefillVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REFILL_ID")
	private Integer refillId;
	@Column(name = "MEM_ID")
	private Integer memId;
	@Column(name= "REFILL_TIME",insertable = false)
	private Timestamp refillTime;
	@Column(name="REFILL_AMT")
	private Integer refillAmt;
	@Column(name = "REFILL_TOKEN")
	private Integer refillToken;

	@ManyToOne
	@JoinColumn(name = "MEM_ID",insertable = false,updatable = false)
	private MemberVO memberVO;

}
