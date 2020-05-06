package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;import org.gouzhong1223.cymmtj.dto.rep.CatResponse;import org.gouzhong1223.cymmtj.dto.rep.ResultCat;import org.gouzhong1223.cymmtj.pojo.Cat;import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-06 15:01
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cat record);

    int insertSelective(Cat record);

    Cat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cat record);

    int updateByPrimaryKey(Cat record);

    /**
     * 批量插入 Cat
     *
     * @param list Cat 集合
     * @return
     */
    int insertList(@Param("list") List<Cat> list);

    /**
     * 查询所有 Cat
     *
     * @return
     */
    List<Cat> selectAllCats();

    List<CatResponse> selectIdAndName();

    /**
     * 获取前四个最受欢迎的 Cat
     *
     * @return
     */
    List<ResultCat> selectIdAndNameAndCommontOrderByPraiseDesc();

    /**
     * 根据 id 查询 id，name，commont
     *
     * @param id
     * @return
     */
    ResultCat selectIdAndNameAndCommontByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询所有的 id ，name，commont
     *
     * @return
     */
    List<ResultCat> selectIdAndNameAndCommont();

    /**
     * 给点赞数量增加 1
     *
     * @return
     */
    int thumbUp();

    /**
     * 给点赞数量减少 1
     *
     * @return
     */
    int cancelPraise();

    /**
     * 根据猫咪主键更新审核状态
     *
     * @param updatedAudit 审核状态 1-已审核 0-未审核
     * @param id           猫咪 ID
     * @return
     */
    int updateAuditById(@Param("updatedAudit") Integer updatedAudit, @Param("id") Integer id);

    /**
     * 根据猫咪主键更新是否可见
     *
     * @param updatedVisible 可见状态 1-可见 0-不可见
     * @param id             猫咪主键
     * @return
     */
    int updateVisibleById(@Param("updatedVisible") Integer updatedVisible, @Param("id") Integer id);
}