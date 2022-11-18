/*
 *  1. 萬用複合查詢-可隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,此處無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_PROD {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("PROD_ID".equals(columnName) || "STORE_ID".equals(columnName) || "PRO_DTYPE_ID".equals(columnName) || "PROD_PRC".equals(columnName) || "PROD_STAT".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("PROD_NAME".equals(columnName) || "PROD_CONT".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("PROD_TIME".equals(columnName))                          // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
}
