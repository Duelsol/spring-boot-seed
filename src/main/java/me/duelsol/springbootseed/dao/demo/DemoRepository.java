package me.duelsol.springbootseed.dao.demo;

import me.duelsol.springbootseed.entity.demo.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 冯奕骅
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {
}
