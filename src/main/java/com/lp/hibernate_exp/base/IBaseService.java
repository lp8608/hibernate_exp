package com.lp.hibernate_exp.base;

import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author LIPENGAK
 * @Description: base service
 * @date 2018-10-24 20:59
 */
public interface IBaseService<T> {


    /**
     * 新增对象
     *
     * @param vo 保存数据对象
     */
    public int add(T vo) throws Exception;

    /**
     * 新增对象，并返回对象
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public T addCallBack(T vo) throws Exception;

    /**
     * 新增或修改对象
     *
     * @param vo
     * @throws Exception
     */
    public int saveOrUpdate(T vo) throws Exception;

    /**
     * 修改对象，不存在修改失败
     *
     * @param vo
     */
    public void update(T vo);

    /**
     * 删除对象
     *
     * @param vo
     * @throws Exception
     */
    public int delete(T vo) throws Exception;

    /**
     * 多对象新增, 1成功，-1失败
     *
     * @param list
     * @throws Exception
     */
    public int add(List<T> list) throws Exception;

    /**
     * 新增修改多对象 , 1成功，-1失败
     *
     * @param list
     * @return
     * @throws Exception
     */
    public int addOrUpdate(List<T> list) throws Exception;

    /**
     * 批量执行
     *
     * @param list
     * @throws Exception
     */
    public int batchExecute(List list) throws Exception;

    /**
     * 获取总数
     *
     * @param entity
     * @param whereMap
     * @return
     */
    public long count(String entity, Map whereMap) throws Exception;

    /**
     * 批量删除数据，list 保存id值
     *
     * @param entity
     * @param list
     * @return
     */
    public int del(String entity, List list) throws Exception;

    /**
     * 按key值删除对象
     *
     * @param entity
     * @param keyName
     * @param value
     * @return
     */
    public int del(String entity, String keyName, Object value) throws Exception;

    /**
     * 根据Map条件删除
     *
     * @param entity
     * @param valueMap
     * @return
     */
    public int delWhere(String entity, Map valueMap) throws Exception;

    /**
     * 批量删除
     *
     * @param entity
     * @param wkey
     * @param list
     * @return
     */

    public int delete(final String entity, final String wkey, final Set<Object> list);

    /**
     * 多条件查找
     *
     * @param entity
     * @param map
     * @return
     */
    public T fetch(String entity, Map map) throws Exception;

    /**
     * 但条件查找
     *
     * @param entity
     * @param key
     * @param obj
     * @return
     */
    public T fetch(String entity, String key, Object obj) throws Exception;

    /**
     * hql多条件执行,-1失败，1成功
     *
     * @param hql
     * @param paraList
     * @return
     */
    public int hqlExcute(String hql, List paraList) throws Exception;

    /**
     * 多条件 hql查询
     *
     * @param hql
     * @param paraList
     * @return
     */
    public List<T> hqlQuery(String hql, List paraList) throws Exception;

    /**
     * 通过hql查询单个实体
     *
     * @param hql
     * @param paramList
     * @return
     */
    public T queryByHql(String hql, List<Object> paramList);

    /**
     * hql总数查询
     *
     * @param hql
     * @param paraList
     * @return
     */
    public long hqlCount(String hql, List paraList) throws Exception;

    /**
     * 多条件分页查询
     *
     * @param hql
     * @param start
     * @param limit
     * @param paraList
     * @return
     */
    public List<T> hqlQuery(String hql, int start, int limit, List paraList) throws Exception;

    /**
     * 多条件查询
     *
     * @param entity
     * @param map
     * @return
     */
    public List<T> queryAll(String entity, Map map) throws Exception;

    /**
     * 多条件分页查找
     *
     * @param entity
     * @param map
     * @param start
     * @param limit
     * @return
     */
    public List<T> queryPage(String entity, Map map, int start, int limit) throws Exception;

    /**
     * 多对象更新 -1失败 ，1成功
     *
     * @param list
     * @return
     */
    public int update(List list) throws Exception;

    /**
     * 根据实体名称更新  -1失败 1成功
     *
     * @param entity
     * @param vMap
     * @param wMap
     * @return
     */
    public int update(String entity, Map vMap, Map wMap) throws Exception;

    /**
     * 根据实体名称更新单字段值 -1失败，1成功
     *
     * @param entity
     * @param vkey
     * @param vObject
     * @param wkey
     * @param wOper
     * @param wObjec
     * @return
     */
    public int update(String entity, String vkey, Object vObject, String wkey, String wOper, Object wObjec) throws Exception;

    /**
     * 根据实体名称更新单字段值 -1失败，1成功
     *
     * @param entity
     * @param vkey
     * @param vObject
     * @param wkey
     * @param list
     * @return
     * @throws Exception
     */
    public int update(final String entity, final String vkey,
                      final Object vObject, final String wkey, final Set<Object> list) throws Exception;

    /**
     * 清除所有缓存
     */
    public void clear();

    /**
     * 清除单个实体
     *
     * @param vo
     */
    public void evict(T vo);

    /**
     * 刷新
     */
    public void flush();


    /**
     * IBaseJdbcService迁移
     */

    /**
     * 执行sql，paraList=null
     *
     * @param sql
     * @param paraList
     * @return
     * @throws Exception
     */
    public int sqlExecute(String sql, Object[] objects) throws Exception;

    /**
     * 带参数返回总数
     *
     * @param sql
     * @param objects
     * @return
     */
    public long sqlCount(String sql, Object[] objects) throws Exception;

    /**
     * 带参数返回list Map对象
     *
     * @param sql
     * @param objects
     * @return
     */
    public List sqlQuery(String sql, Object[] objects) throws Exception;


    /**
     * SQL查詢List
     *
     * @param sqlString sql查询语句
     * @return 符合条件的Map对象集合 <br/>
     * eg:返回值 List<Map<Object,Object>>
     */
    public List<T> findListMapBySql(String sqlString, Object... objects) throws Exception;

    /**
     * SQL查詢List
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public List<T> findListMapBySql(String sqlString, Map<String, Object> paramMap) throws Exception;

    /**
     * SQL查询List
     *
     * @param sqlString
     * @param paramList
     * @return
     */
    public List<T> findListMapBySql(String sqlString, List<Object> paramList) throws Exception;

    /**
     * SQL查询List
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public Map<String, Object> findMapBySql(String sqlString, Map<String, Object> paramMap) throws Exception;


    /**
     * SQL查询List
     *
     * @param sqlString
     * @param paramList
     * @return
     */
    public Map<String, Object> findMapBySql(String sqlString, List<Object> paramList) throws Exception;


    /**
     * sql 查询List
     *
     * @param sqlString 查询语句
     * @return 返回符合条件的class对象集合
     */
    public List<T> findListBySql(Boolean isCache, Class<T> clazz, String sqlString, Object... objects) throws Exception;

    /**
     * SQL查询List,SQL预处理
     *
     * @param sqlString sql查询语句
     * @param paramList 参数对象集合
     * @return 返回符合条件的class对象集合<br/>
     * eg:<br/>
     * select * from tableName where userName = ? and password = ? <br/>
     * List<Object> paramList = new ArrayList<Object> ();<br/>
     * paramList.add(admin);<br/>
     * paramList.add(123456);<br/>
     * 参数的添加顺序要跟Sql中顺序对应<br/>
     */
    public List<T> findListBySql(Boolean isCache, Class<T> clazz, String sqlString, List<Object> paramList) throws Exception;

    /**
     * SQL查询List,SQL预处理
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public List<T> findListBySql(Boolean isCache, Class<T> clazz, String sqlString, Map<String, Object> paramMap) throws Exception;


    /**
     * 批量保存数据
     * @param  list
     * @return 成功新增的数据
     */
    public int batchSaveOrUpdate(List<T> list);

}
