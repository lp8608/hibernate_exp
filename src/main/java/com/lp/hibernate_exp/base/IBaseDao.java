package com.lp.hibernate_exp.base;

import org.hibernate.stat.Statistics;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author LIPENGAK
 * @Description: base dao
 * @date 2018-10-24 20:59
 */
public interface IBaseDao<T> {



    public HibernateTemplate hibernateTemplate();

    /**
     * 使对象处理受管状态
     *
     * @param vo 要受管的实体
     */
    public void manage(T vo);

    /**
     * 新增对象
     *
     * @param vo 要保存的实体
     */
    public void add(T vo);

    /**
     * 新增对象，并返回对象
     *
     * @param vo 要保存的实体
     * @return 返回持久化后的实体
     */
    public T addCallBack(T vo);

    /**
     * 新增或修改对象
     * <p>当实体已存在时修改实体，不存在时添加实体</p>
     *
     * @param vo
     */
    public void saveOrUpdate(T vo);

    /**
     * 修改对象
     *
     * @param vo 要修改的实体
     */
    public void update(T vo);

    /**
     * 删除对象
     *
     * @param vo 要删除的实体
     */
    public void delete(T vo);

    /**
     * 批量删除，删除指定字段条件的所有值的记录
     * <pre>delete ENTITY where wkey in (list...)</pre>
     *
     * @param entity 实体类名
     * @param wkey   判断条件字段
     * @param list   条件值集合
     * @return 返回被删除的记录数
     */
    public int delete(final String entity, final String wkey, final Collection<?> list);

    /**
     * 获取总数
     *
     * @param entity   实体类名
     * @param whereMap 条件映射(键为字段名，值为字段值)
     * @return 符合条件的总数
     */
    public long count(String entity, Map whereMap);

    /**
     * 按key值删除对象
     *
     * @param entity  实体类名
     * @param keyName 判断条件字段
     * @param value   条件值
     * @return 返回被刪除的记录数
     */
    public int del(String entity, String keyName, Object value);

    /**
     * 根据Map条件删除
     *
     * @param entity   实体类名
     * @param valueMap 条件映射(键为字段名，值为字段值)
     * @return 返回被刪除的记录数
     */
    public int delWhere(String entity, Map valueMap);

    /**
     * 多条件查找
     *
     * @param entity 实体类名
     * @param map    条件映射(键为字段名，值为字段值)
     * @return 返回符合条件的实体，如果有多个则返回第一个，如果没有则返回null
     */
    public T fetch(String entity, Map map);

    /**
     * 按指定字段条件查找
     *
     * @param entity 实体类名
     * @param key    判断条件字段
     * @param obj    条件值
     * @return 返回符合条件的实体，如果有多个则返回第一个，如果没有则返回null
     */
    public T fetch(String entity, String key, Object obj);

    /**
     * 执行hql语句
     *
     * @param hql      hql语句
     * @param paraList 条件
     * @return 返回生效的记录数
     */
    public int hqlExcute(String hql, List paraList);

    /**
     * hql查询
     *
     * @param hql   hql查询语句
     * @param paras 数组(Object[])或集合(Collection)，如条件时可为null
     * @return 返回符合条件的集合，如果没有任何记录则返回一个空的集合而不是null
     */
    public List<T> hqlQuery(String hql, Object paras);

    /**
     * hql总数查询
     *
     * @param hql      hql查询语句
     * @param paraList 查询条件，如条件时可为null
     * @return 符合条件的总数
     */
    public long hqlCount(String hql, List paraList);

    /**
     * 多条件分页查询
     *
     * @param hql      hql查询语句
     * @param start    起始数（从第几条记录开始）
     * @param limit    每页显示记录数（每页显示多少条记录）
     * @param paraList 查询条件，如条件时可为null
     * @return 返回符合条件的集合，如果没有任何记录则返回一个空的集合而不是null
     */
    public List<T> hqlQuery(String hql, int start, int limit, List paraList);

    /**
     * 多条件查询（不分页）
     *
     * @param entity 实体类名
     * @param map    条件映射(键为字段名，值为字段值)
     * @return 返回符合条件的集合，如果没有任何记录则返回一个空的集合而不是null
     */
    public List<T> queryAll(String entity, Map map);

    /**
     * 多条件分页查找
     *
     * @param entity 实体类名
     * @param map    条件映射(键为字段名，值为字段值)
     * @param start  起始数（从第几条记录开始）
     * @param limit  每页显示记录数（每页显示多少条记录）
     * @return 返回符合条件的集合，如果没有任何记录则返回一个空的集合而不是null
     */
    public List<T> queryPage(String entity, Map map, int start, int limit);

    /**
     * 批量更新实体
     * <p>将符合wMap的条件的实体的对应字段（对应到vMap的键）更新为vMap的值</p>
     *
     * @param entity 实体类名
     * @param vMap   设值映射(键为字段名，值为字段值)
     * @param wMap   条件映射(键为字段名，值为字段值)
     * @return 更新成功的记录数
     */
    public int update(String entity, Map vMap, Map wMap);

    /**
     * 批量更新实体
     * <p>将符合某一字段(wkey)条件的实体的某一字段(vkey)更新为提供的值(vObject)</p>
     *
     * @param entity  实体类名
     * @param vkey    要更新的字段名
     * @param vObject 更新的字段值
     * @param wkey    判断条件字段
     * @param wOper   条件判断操作符（如大于>）
     * @param wObject 条件值
     * @return 更新成功的记录数
     */
    public int update(String entity, String vkey, Object vObject, String wkey, String wOper, Object wObject);

    /**
     * 批量更新实体
     * <p>将符合某一字段(wkey)条件(条件值支持多个,即in(...))的实体的某一字段(vkey)更新为提供的值(vObject)</p>
     *
     * @param entity  实体类名
     * @param vkey    要更新的字段名
     * @param vObject 更新的字段值
     * @param wkey    判断条件字段
     * @param list    条件值(集合)
     * @return 更新成功的记录数
     */
    public int update(final String entity, final String vkey, final Object vObject, final String wkey, final Set<Object> list);

    /**
     * 获取Hibernate Statistics信息
     *
     * @return
     */
    public Statistics getSessionStatistics();

    /**
     * 清除所有缓存
     */
    public void clear();

    /**
     * 从Session中清除单个实体
     *
     * @param vo
     */
    public void evict(T vo);

    /**
     * 刷新Session
     */
    public void flush();

    /**
     * hql分页查询
     * <p>支持条件参数名为":参数名"而不是"?"的hql,而且支持参数对应的参数值为数组或集合的情况</p>
     * <pre>
     *    String hql = "form Entity where id in(:ids) and name = :name";
     *    long[] ids = new long[]{1l,2l,3l};
     *    List<Entity> entities = hqlQuery(hql,0,50,new String[]{},new Object[]{ids,"Paul"});
     * </pre>
     *
     * @param hql        hql查询语句
     * @param start      起始数（从第几条记录开始）
     * @param limit      每页显示记录数（每页显示多少条记录）
     * @param paraNames  查询参数名(占位符,如:name)(数组(Object[])或集合(Collection))
     * @param paraValues 查询参数值(数组(Object[])或集合(Collection))
     * @return 返回符合条件的集合，如果没有任何记录则返回一个空的集合而不是null
     */
    public List<T> hqlQuery(final String hql, final int start, final int limit, final Object paraNames, final Object paraValues);

    /**
     * hql查询
     *
     * @param hql        hql查询语句
     * @param paraNames  查询参数名(占位符,如:name)(数组(Object[])或集合(Collection))
     * @param paraValues 查询参数值(数组(Object[])或集合(Collection))
     * @return 返回符合条件的集合，如果没有任何记录则返回一个空的集合而不是null
     */
    public List<T> hqlQuery(final String hql, final Object paraNames, final Object paraValues);

    public T queryByHql(String hql, List<Object> paramList);

    /**
     * hql查询总数
     *
     * @param hql        hql查询语句
     * @param paraNames  查询参数名集合(占位符,如:name)
     * @param paraValues 查询参数值集合
     * @return count 总数
     */
    public long hqlCount(final String hql, final List<String> paraNames, final List paraValues);

    /**
     * sql 查询List
     *
     * @param clazz
     * @param sqlString
     * @param objects
     * @return
     */
    public List<T> findListBySql(Boolean isCache, Class<T> clazz, String sqlString, Object... objects);

    /**
     * sql 查询List
     *
     * @param sqlString 查询语句
     * @param clazz     Class
     * @return 返回符合条件的class对象集合
     */
    public List<T> findListBySql(String sqlString, Class clazz,Boolean isCache);

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
    public List<T> findListBySql(Boolean isCache, Class<T> clazz, String sqlString, List<Object> paramList);

    /**
     * SQL查询List,SQL预处理
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public List<T> findListBySql(Boolean isCache, Class<T> clazz, String sqlString, Map<String, Object> paramMap);

    /**
     * SQL查詢List
     *
     * @param sqlString sql查询语句
     * @return 符合条件的Map对象集合 <br/>
     * eg:返回值 List<Map<Object,Object>>
     */
    public List<T> findListMapBySql(String sqlString, Object... objects);

    /**
     * SQL查詢List
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public List<T> findListMapBySql(String sqlString, Map<String, Object> paramMap);

    /**
     * SQL查询List
     *
     * @param sqlString
     * @param paramList
     * @return
     */
    public List<T> findListMapBySql(String sqlString, List<Object> paramList);


    /**
     * SQL查询List
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public Map<String, Object> findMapBySql(String sqlString, Map<String, Object> paramMap);


    /**
     * SQL查询List
     *
     * @param sqlString
     * @param paramList
     * @return
     */
    public Map<String, Object> findMapBySql(String sqlString, List<Object> paramList);


    /**
     * SQL查詢
     *
     * @param sqlString
     * @param objects   数组
     * @return
     */
    public List<T> findListArrBySql(String sqlString, Object... objects); //Object[]

    /**
     * sql查询对象
     *
     * @param sqlString 查询语句
     * @return 返回符合条件的class对象
     */
    public T findUniqueBySql(Class<T> clazz, String sqlString, Object... objects);

    /**
     * sql查询对象
     *
     * @param sqlString 查询语句
     * @param clazz     Class
     * @return 返回符合条件的class对象
     */
    public T findUniqueBySql(String sqlString, Class clazz,Boolean isCache);

    /**
     * SQL查询对象,SQL预处理
     *
     * @param sqlString 查询语句
     * @param paramList 参数组成的Map集合
     * @return 返回符合条件的class对象<br/>
     * eg:<br/>
     * select * from tableName where userName = ? and password = ? <br/>
     * List<Object> paramList = new ArrayList<Object> ();<br/>
     * paramList.add(admin);<br/>
     * paramList.add(123456);<br/>
     * 参数的添加顺序要跟Sql中顺序对应<br/>
     */
    public T findUniqueBySql(Class<T> clazz, String sqlString, List<Object> paramList);

    /**
     * SQL查询对象,SQL预处理
     *
     * @param sqlString 查询语句
     * @param clazz     实体类
     * @param paramList 参数组成的Map集合
     * @return 返回符合条件的class对象<br/>
     * eg:<br/>
     * select * from tableName where userName = ? and password = ? <br/>
     * List<Object> paramList = new ArrayList<Object> ();<br/>
     * paramList.add(admin);<br/>
     * paramList.add(123456);<br/>
     * 参数的添加顺序要跟Sql中顺序对应<br/>
     */
    public T findUniqueBySql(String sqlString, Class clazz, List<Object> paramList,Boolean isCache);

    /**
     * SQL查询对象，SQL预处理，参数用MAP封装
     *
     * @param sqlString
     * @param paramMap
     * @return
     */
    public T findUniqueBySql(Class<T> clazz, String sqlString, Map<String, Object> paramMap);

/**
 * IBaseJdbcDAO迁移
 */

    /**
     * 带参数执行sql语句
     *
     * @param sql
     * @return
     */
    public int sqlExecute(String sql, Object[] objects);

    /**
     * 返回list实体对象
     *
     * @param sql
     * @param objects
     * @param clazz
     * @return
     */
    public List<T> sqlQuery(String sql, Object[] objects, Class<T> clazz);

    /**
     * 带参数返回list Map对象
     *
     * @param sql
     * @param objects
     * @return
     */
    public List sqlQuery(String sql, Object[] objects);


    /**
     * 带参数返回总数
     *
     * @param sql
     * @param objects
     * @return
     */
    public long sqlCount(String sql, Object[] objects);


    /**
     * 采用批量提交的方式保存数据
     * @param list
     * @return 保存数量
     */
    public int batchSaveOrUpdate(List<T> list);

    /**
     * 批量保存后回调
     * @param list
     * @return
     */
    public List batchSaveOrUpdateCallBack(List<T> list);
}
