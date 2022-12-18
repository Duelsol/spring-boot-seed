package me.duelsol.springbootseed.service.demo.impl;

import me.duelsol.springbootseed.dao.demo.DemoRepository;
import me.duelsol.springbootseed.dto.demo.DemoDto;
import me.duelsol.springbootseed.entity.demo.Demo;
import me.duelsol.springbootseed.service.demo.IDemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 冯奕骅
 */
@Service
public class IDemoServiceImpl implements IDemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public List<DemoDto> findAllDemos() {
        List<Demo> demoList = demoRepository.findAll();
        return demoList.stream().map(demo -> {
            DemoDto demoDto = new DemoDto();
            BeanUtils.copyProperties(demo, demoDto);
            return demoDto;
        }).collect(Collectors.toList());
    }

    @Override
    public DemoDto createDemo(int amount, String detail) {
        Demo demo = new Demo();
        demo.setAmount(amount);
        demo.setDetail(detail);

        DemoDto demoDto = new DemoDto();
        BeanUtils.copyProperties(demo, demoDto);

        return demoDto;
    }

}
