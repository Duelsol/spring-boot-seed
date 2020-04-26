package me.duelsol.springbootseed.service.demo;

import me.duelsol.springbootseed.dao.demo.DemoRepository;
import me.duelsol.springbootseed.dto.demo.DemoBean;
import me.duelsol.springbootseed.dto.demo.DemoDTO;
import me.duelsol.springbootseed.dto.demo.DemoListDTO;
import me.duelsol.springbootseed.entity.demo.Demo;
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
    public DemoListDTO findAllDemos() {
        List<Demo> demoList = demoRepository.findAll();
        List<DemoBean> demoBeanList = demoList.stream().map(demo -> {
            DemoBean demoBean = new DemoBean();
            BeanUtils.copyProperties(demo, demoBean);
            return demoBean;
        }).collect(Collectors.toList());
        return new DemoListDTO(demoBeanList);
    }

    @Override
    public DemoDTO createDemo(int amount, String detail) {
        Demo demo = new Demo();
        demo.setAmount(amount);
        demo.setDetail(detail);

        DemoBean demoBean = new DemoBean();
        BeanUtils.copyProperties(demo, demoBean);

        return new DemoDTO(demoBean);
    }

}
