package com.xush.demo.db;

/**
 * 数据库实现接口
 * 
 * @author xush
 * @since 2019年10月10日
 */
public interface DbHelper {
	/**
	 * 检查库表存在与否
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean checkTableExist(String tableName) throws Exception;
	
	/**
	 * 根据sql创建数据库表
	 * @param sql
	 * @throws Exception
	 */
	public void crateTabel(String sql) throws Exception;
	
	/**
	 * 获取分页查询sql语句（单表）
	 * @param tableName 库表名      "table1"
	 * @param condition 条件参数   "id='1'"
	 * @param sortParam 排序参数   "id asc"
	 * @param pageIndex 页码             1
	 * @param pageSize  每页条数     10
	 * @return
	 * @throws Exception
	 */
	public String processPageSql(String tableName, String condition, String sortParam, int pageIndex, int pageSize);
}
