package com.odss.seu.mapper;

import com.odss.seu.vo.UserType;
import com.odss.seu.vo.UserTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    long countByExample(UserTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int deleteByExample(UserTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int insert(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int insertSelective(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    List<UserType> selectByExample(UserTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    UserType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int updateByExampleSelective(@Param("record") UserType record, @Param("example") UserTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int updateByExample(@Param("record") UserType record, @Param("example") UserTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int updateByPrimaryKeySelective(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ods_usertype
     *
     * @mbg.generated Fri Sep 08 14:07:18 CST 2017
     */
    int updateByPrimaryKey(UserType record);
}