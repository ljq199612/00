package com.ljq.springboot.mapper;

import com.ljq.springboot.model.Admin;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface AdminMapper {

    @Insert("insert into admin(id,userName,password,registDate) values(#{id}, #{userName}, #{password}, #{registDate})")
    Boolean insert(Admin admin);   // 若返回 int 型， 执行成功为 1

    @Delete("delete from admin where id = #{id}")
    Boolean delete(String id);   // 若返回 int 型， 执行成功为 1

    @Select("select * from admin")
    List<Admin> selectAll();

    @Update("update admin set userName=#{userName},password=#{password}, registDate=#{registDate} where id =#{id}")
    Boolean update(Admin admin);


    // 动态SQL
    @SelectProvider(type=AdminSql.class, method="selectAdminLikeSql")
    List<Admin> selectAdminLike(@Param("admin") Admin admin);

    // NOTICE : 注意是内部类，或者单独写一个 java 文件，修饰符为 public
    class AdminSql {

        public String selectAdminLikeSql(Map<String, Admin> adminMap) {
            Admin admin = adminMap.get("admin");
            return new SQL() {{
                SELECT("A.*");
                FROM("admin A");

                if ( StringUtils.isNotBlank(admin.getId()) ) {  // null、 空格、 制表符等
                    WHERE("A.id like "+ admin.getId());
                }
                if ( StringUtils.isNotBlank(admin.getUserName()) ) {
                    WHERE("A.userName like "+ admin.getUserName());
                }
                if ( StringUtils.isNotBlank(admin.getPassword()) ) {
                    WHERE("A.password like "+ admin.getPassword());
                }

                if ( admin.getRegistDate() != null) {
                    // FIXME
                    //  WHERE("A.registDate = " + admin.getRegistDate().toString());
                }
                //ORDER_BY("A.userName");
            }}.toString();
    }

    }

}