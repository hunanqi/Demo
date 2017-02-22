package com.example.utsoft.demo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 胡楠启 on 2017/2/21.
 * Function：测试greendao的数据库实体类
 * Desc：
 */
@Entity
public class DaoTestEntity {
    /**
     * 必须要的，类似于主键，默认自增长
     */
    @Id
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    @Generated(hash = 1415310003)
    public DaoTestEntity(Long id, String name, String sex, String age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    @Generated(hash = 1931881091)
    public DaoTestEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
