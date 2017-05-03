package com.chattingus.dao;
import com.alibaba.cobarclient.MysdalCobarSqlMapClientDaoSupport;

/**
 * 类描述：
 *
 * @version: 1.0
 * @author: Administrator
 * @version: Nov 26, 2010 1:53:06 PM
 */
public class CobarClientBaseDao extends MysdalCobarSqlMapClientDaoSupport {

	/**
	 * ibatis的namespace名称
	 */
	protected String domain;
	
	protected String buildStatementName(String id){
		return new StringBuilder(domain).append(".").append(id).toString();
	}
}
