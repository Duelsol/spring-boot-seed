package me.duelsol.springbootseed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.duelsol.springbootseed.dto.demo.DemoDto;
import me.duelsol.springbootseed.model.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 冯奕骅
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

    DemoDto selectByIdAndUserId(@Param("id") int id, @Param("userId") String userId);

}
