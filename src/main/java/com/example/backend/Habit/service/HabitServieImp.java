package com.example.backend.Habit.service;

import com.example.backend.Habit.mapper.HabitCheckMapper;
import com.example.backend.Habit.mapper.MyHabitMapper;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HabitServieImp implements HabitService {
        @Autowired
    private MyHabitMapper myHabitMapper;

        @Autowired
    private HabitCheckMapper habitCheckMapper;

    @Override
    public List<MyHabitVO> getMyHabit(long userId) {
        return myHabitMapper.getMyHabit(userId);
    }

    @Override
    public void addHabitChecked(HabitCheckVO habitCheckVO) {
//        habitCheckMapper.addHabitChecked(habitCheckVO);
    }

    @Override
    public List<MyHabitVO> getCheckedHabit(long userId, String checkDate) {
        //        return habitCheckMapper.getCheckedHabit(userId, checkDate);
//        log.info("getCheckHabit mapper: {}", habitCheckMapper);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("checkDate", checkDate);
        log.info("getCheckedHabit map: {}", map);
//        return null;
        return habitCheckMapper.getCheckedHabit(map);
    }

    @Override
    public void addMyHabit(MyHabitVO myHabitVO) {

    }

    @Override
    public void addMyHabitFromOther(MyHabitVO myHabitVO) {

    }

    @Override
    public void modifyMyHabit(MyHabitVO myHabitVO) {

    }

    @Override
    public void deleteMyHabit(long myHabitId) {

    }

    @Override
    public void modifyMyHabitStateS(long myHabitId) {

    }

    @Override
    public void modifyMyHabitStateW(long myHabitId) {

    }

    @Override
    public int saveTotalAmount() {
        return 0;
    }

    @Override
    public int saveRealAmount() {
        return 0;
    }

    @Override
    public void addHabitCommunity() {

    }

    @Override
    public void addPostCommunity() {

    }
}
