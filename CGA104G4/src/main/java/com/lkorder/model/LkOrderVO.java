package com.lkorder.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity					
@Getter
@Setter					
@NoArgsConstructor		
@AllArgsConstructor
@Table(name="LK_ORDER")
public class LkOrderVO implements Serializable {
	protected static final long serialVersionUID = 1L;
	@Id					// 設定識別屬性，Hibernate底層會參考識別屬性，所以⼀定要設定此Annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 
	@Column(name="LK_ORD_ID")
	protected Integer lkOrderId;
	@Column(name="LK_ID")
	protected Integer lkId;
	@Column(name="MEM_ID")
	protected Integer memId;
	@Column(name="LK_TODAY_ID")
	protected Integer lkTodayId;
	@Column(name="LK_ORD_AMT")
	protected Integer lkOrdAmt;
	@Column(name="LK_ORD_STAT")
	protected Integer lkOrdStat;
	@Column(name="LK_ORD_TIME_S")
	protected Date lkOrdTimeS;
	@Column(name="LK_ORD_TAKETIME")
	protected Date lkOrdTaketime;
	@Column(name="LK_ORD_TIME_E")
	protected Date lkOrdTimeE;

}
