package me.duelsol.springbootseed.service.demo.impl;

import me.duelsol.springbootseed.dao.demo.DemoRepository;
import me.duelsol.springbootseed.dto.demo.DemoBean;
import me.duelsol.springbootseed.entity.demo.Demo;
import me.duelsol.springbootseed.service.demo.DemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 冯奕骅
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public List<DemoBean> findAllDemos() {
        List<Demo> demoList = demoRepository.findAll();
        return demoList.stream().map(demo -> {
            DemoBean demoBean = new DemoBean();
            BeanUtils.copyProperties(demo, demoBean);
            return demoBean;
        }).collect(Collectors.toList());
    }

    @Override
    public DemoBean createDemo(int amount, String detail) {
        Demo demo = new Demo();
        demo.setAmount(amount);
        demo.setDetail(detail);

        DemoBean demoBean = new DemoBean();
        BeanUtils.copyProperties(demo, demoBean);

        return demoBean;
    }

}
