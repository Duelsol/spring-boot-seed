package me.duelsol.springbootseed.entity.demo;

import lombok.Getter;
import lombok.Setter;
import me.duelsol.springbootseed.entity.BaseEntity;

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
