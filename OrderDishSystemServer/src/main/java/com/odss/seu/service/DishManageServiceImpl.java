package com.odss.seu.service;

import com.odss.seu.mapper.DishMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.DishExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishManageServiceImpl implements DishManageService {

    public static Logger lo= Logger.getLogger(DishManageServiceImpl.class);
    private DishMapper dishMapper;

    @Autowired
    public DishManageServiceImpl(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    @Override
    public List<Dish> queryAllDishes() {
        lo.info("查询所有菜品成功");
        return dishMapper.selectByExample(new DishExample());
    }

    @Override
    public Dish queryDishDetail(Integer dishId) {
        lo.info("查询ID为"+dishId+"的菜品成功");
        return dishMapper.selectByPrimaryKey(dishId);
    }

    @Override
    public Dish addDish(Dish dish) {
        lo.info("添加ID为"+dish.getId()+"的菜品成功");
        dishMapper.insert(dish);
        return dish;
    }

    @Override
    public void updateDish(Dish dish) {
        lo.info("更新ID为"+dish.getId()+"的菜品成功");
        dishMapper.updateByPrimaryKey(dish);
    }

    @Override
    public void deleteDish(Integer dishId) {
        lo.info("删除ID为"+dishId+"的菜品成功");
        dishMapper.deleteByPrimaryKey(dishId);
    }
}
