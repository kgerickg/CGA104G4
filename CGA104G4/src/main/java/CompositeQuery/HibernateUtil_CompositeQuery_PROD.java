/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package CompositeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 開始 取代原 org.hibernate.Criteria 介面
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.prodtype.model.ProdTypeVO;
import com.prod.model.ProdVO;
import com.store.model.StoreVO;
import com.utils.HibernateUtil;

public class HibernateUtil_CompositeQuery_PROD {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<ProdVO> root, String columnName, String value) {

		Predicate predicate = null;

		if ("PROD_ID".equals(columnName) ||"PROD_STAT".equals(columnName) || "PROD_PRC".equals(columnName)) // 用於Integer
			predicate = builder.equal(root.get(columnName), new Integer(value));
		else if ("PROD_NAME".equals(columnName) || "PROD_CONT".equals(columnName)) // 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		else if ("PROD_TIME".equals(columnName)) // 用於date
			predicate = builder.equal(root.get(columnName), Date.valueOf(value));
		else if ("PROD_TYPE_ID".equals(columnName)) {
			ProdTypeVO prodTypeVO = new ProdTypeVO();
			prodTypeVO.setProdTypeId(new Integer(value));
			predicate = builder.equal(root.get("prodTypeVO"), prodTypeVO);
		}
		else if ("STORE_ID".equals(columnName)) {
			StoreVO storeVO = new StoreVO();
			storeVO.setStoreId(new Integer(value));
			predicate = builder.equal(root.get("storeVO"), storeVO);
		}

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<ProdVO> getAllC(Map<String, String[]> map) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<ProdVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<ProdVO> criteriaQuery = builder.createQuery(ProdVO.class);
			// 【●創建 Root】
			Root<ProdVO> root = criteriaQuery.from(ProdVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value));
					System.out.println("���e�X�d�߸�ƪ�����count = " + count);
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("empno")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 �}�l ���N�� org.hibernate.Query ����
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}
		return list;
	}
}
