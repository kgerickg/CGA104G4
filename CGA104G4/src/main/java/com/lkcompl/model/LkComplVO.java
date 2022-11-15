package com.lkcompl.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter					
@AllArgsConstructor		// 使用後為類別生成一個"全參數"構造函數
@NoArgsConstructor		// 使用後為類別生成一個"無參數"構造函數
@Entity					// 設定為實體類別
@Table(name="LK_COMPL")	// 設定映射資料表，name="資料表名稱"
public class LkComplVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id					// 設定識別屬性，Hibernate底層會參考識別屬性，所以⼀定要設定此Annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 
	@Column(name="LK_CC_ID")
	private Integer lkCcId;		// 客訴編號PK
	@Column(name="LK_ORD_ID")
	private Integer lkOrdId;	// 福袋訂單編號
	@Column(name="LK_CC_STAT")
	private Integer lkCcStat;	// 處理狀態
	@Column(name="LK_CC_CONT")
	private String lkCcCont;	// 客訴內容
	@Column(name="LK_RFD_STAT")
	private Integer lkRfdStat;	// 退款狀態

}
