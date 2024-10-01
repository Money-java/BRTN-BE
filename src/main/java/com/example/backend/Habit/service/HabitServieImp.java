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

    // 1. 나의 습관 조회
    @Override
    public List<MyHabitVO> getMyHabit(long userId) {
        return myHabitMapper.getMyHabit(userId);
    }

    // 2. 습관 달성 체크
    @Override
    public void addHabitChecked(HabitCheckVO habitCheckVO) {
        habitCheckMapper.addHabitChecked(habitCheckVO);
    }

    // 3. 달성한 습관 조회
    @Override
    public List<MyHabitVO> getCheckedHabit(long userId, String checkDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("checkDate", checkDate);
        return habitCheckMapper.getCheckedHabit(map);
    }

    // 4. 새로운 습관 작성
    // step 1 : Habit 테이블에 습관 삽입
    @Override
    public void addMyHabit(MyHabitVO myHabitVO) {
        myHabitMapper.insertHabit(myHabitVO);
    }
    // step 2 : 방금 추가한 habitId 조회
    @Override
    public Long getLastInsertedHabitId() {
        return myHabitMapper.selectLastInsertedHabitId();
    }
    @Override
    public void createHabitWithMyHabit(MyHabitVO myHabitVO) {
        myHabitMapper.insertHabit(myHabitVO);
        Long habitId = myHabitMapper.selectLastInsertedHabitId();
        myHabitVO.setHabitId(habitId);
        myHabitMapper.insertMyHabit(myHabitVO);
    }

    // 5. 습관 수정
    @Override
    public void modifyMyHabit(MyHabitVO myHabitVO) {
        myHabitMapper.updateMyHabit(myHabitVO);
    }

    // 6. 습관 삭제
    @Override
    public void deleteMyHabit(long myHabitId) {
        myHabitMapper.deleteMyHabit(myHabitId);
    }

    // 7. 습관 상태 변경
    @Override
    public void modifyMyHabitState(List<MyHabitVO> habitList) {
        myHabitMapper.updateMyHabitState(habitList);
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
