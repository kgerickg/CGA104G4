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
@Table(name="LKCOMPLMEMBER")	// 設定映射資料表，name="資料表名稱"
public class LkComplMemberVO extends LkComplVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id					// 設定識別屬性，Hibernate底層會參考識別屬性，所以⼀定要設定此Annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 
	@Column(name="MEM_ID")
	private Integer memId;		// 會員編號


}
