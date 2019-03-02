package me.duelsol.springbootseed.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 冯奕骅
 */
@Entity
@Table(name = "springmvc_demo")
@Getter
@Setter
public class Demo extends BaseEntity {

    private Integer amount;

    private String detail;

    private Integer deleteFlag;

}
