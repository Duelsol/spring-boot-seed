package me.duelsol.springbootseed.service;

import me.duelsol.springbootseed.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 冯奕骅
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {
}
