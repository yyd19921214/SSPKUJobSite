package core.mapper;

/**
 * 
 * @author yangyudong
 *
 * @param <T>
 */
public interface IBaseMapper<T> {

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T record);

}
