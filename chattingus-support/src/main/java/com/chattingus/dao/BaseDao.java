package com.chattingus.dao;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * 类描述：
 * @version: 1.0
 * @author: Administrator
 * @version: Nov 26, 2010 1:53:06 PM
 */
@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public class BaseDao extends SqlMapClientDaoSupport {
	
	protected static final Logger logger = Logger.getLogger(BaseDao.class);
	
	public static final int BATCH_SIZE = 1000;
	
	/**
	 * ibatis的namespace名称
	 */
	protected String domain;
	
	protected String buildStatementName(String id){
		return new StringBuilder(domain).append(".").append(id).toString();
	}
	
	/**
	  * 方法描述：批量插入数据 list批量插入的对象，statement SqlMap.xml一条语句的id
	  * @param: {@link java.util.List @link java.lang.String}
	  * @return:
	  * @version: 1.0
	  * @author: Administrator
	  * @version: Nov 26, 2010 2:05:02 PM
	 * @throws java.sql.SQLException
	 */
	public void insertBatch(final List list,final String statement) throws SQLException {
		if (!CollectionUtils.isEmpty(list)) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback(){

				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for (int i = 0; i < list.size(); i++) {
						executor.insert(statement, list.get(i));

						if(i != 0 && i % BATCH_SIZE == 0){
							executor.executeBatch();
						}
					}
					executor.executeBatch();
					return null;
				}

			});
		}
	}


	/**
	  * 方法描述：批量更新数据 list批量更新的对象，statement SqlMap.xml一条语句的id
	  * @param: {@link java.util.List @link java.lang.String}
	  * @return:
	  * @version: 1.0
	  * @author: Administrator
	  * @version: Nov 26, 2010 2:05:02 PM
	 */
	public void updateBatch(final List list,final String statement) throws Exception {
		if (!CollectionUtils.isEmpty(list)) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback(){

				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for (int i = 0; i < list.size(); i++) {
						executor.update(statement, list.get(i));

						if(i != 0 && i % BATCH_SIZE == 0){
							executor.executeBatch();
						}
					}
					executor.executeBatch();
					return null;
				}

			});
		}
	}

	/**
	  * 方法描述：批量删除数据 list批量删除对象，statement SqlMap.xml一条语句的nid
	  * @param: {@link java.util.List @link java.lang.String}
	  * @return: 
	  * @version: 1.0
	  * @author: Administrator
	  * @version: Nov 26, 2010 2:05:02 PM
	 */
	public void deleteBatch(final List list,final String statement) throws Exception {
		if (!CollectionUtils.isEmpty(list)) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback(){

				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for (int i = 0; i < list.size(); i++) {
						executor.delete(statement, list.get(i));
						
						if(i != 0 && i % BATCH_SIZE == 0){
							executor.executeBatch();
						}
					}
					executor.executeBatch();
					return null;
				}
				
			});
		}
	}
}
