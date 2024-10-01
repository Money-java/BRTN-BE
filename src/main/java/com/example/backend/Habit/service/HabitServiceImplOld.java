package com.example.backend.Habit.service;

import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@Service
public class HabitServiceImplOld implements HabitService {

//    @Autowired
//    private MyHabitMapper myHabitMapper;

//    @Autowired
//    private HabitCheckMapper habitCheckMapper;

    // 1. 나의 습관 조회
    public List<MyHabitVO> getMyHabit(long userId) {
//        return myHabitMapper.getMyHabit(userId);
        return null;
    }

    // 2. 습관 달성 체크
    public void addHabitChecked(HabitCheckVO habitCheckVO) {
//        habitCheckMapper.addHabitChecked(habitCheckVO);
    }

    // 3. 달성한 습관 조회
    public List<MyHabitVO> getCheckedHabit(long userId, String checkDate) {
//        return habitCheckMapper.getCheckedHabit(userId, checkDate);
//        log.info("getCheckHabit mapper: {}", habitCheckMapper);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("checkDate", checkDate);
        log.info("getCheckedHabit map: {}", map);
        return null;
//        return habitCheckMapper.getCheckedHabit(map);
    }

    // 4. 새로운 습관 체크 작성
    public void addMyHabit(MyHabitVO myHabitVO) {
//        myHabitMapper.insertMyHabit(myHabitVO);
    }

    // 5. 체크된 습관 가져오기
    public void addMyHabitFromOther(MyHabitVO myHabitVO) {
//        myHabitMapper.insertMyHabitFromOther(myHabitVO);
    }

    // 6. 습관 수정
    public void modifyMyHabit(MyHabitVO myHabitVO) {
//        myHabitMapper.updateMyHabit(myHabitVO);
    }

    // 7. 습관 삭제
    public void deleteMyHabit(long myHabitId) {
//        myHabitMapper.deleteMyHabit(myHabitId);
    }

    // 8. 습관 상태를 '진행'으로 변경
    public void modifyMyHabitStateS(long myHabitId) {
//        myHabitMapper.updateMyHabitStateS(myHabitId);
    }

    // 9. 습관 상태를 '대기'로 변경
    public void modifyMyHabitStateW(long myHabitId) {
//        myHabitMapper.updateMyHabitStateW(myHabitId);
    }

    // 10. 오늘 절약 가능한 예상 금액
    public int saveTotalAmount() {
//        return myHabitMapper.totalSaveAmount();
        return 0;
    }

    // 11. 실제 절약 금액
    public int saveRealAmount() {
//        return myHabitMapper.realSaveAmount();
        return 0;
    }

    // 12. 습관 커뮤니티에 업로드하기
    public void addHabitCommunity() {
//        myHabitMapper.insertHabitCommunity();
    }

    // 13. 인증 커뮤니티에 업로드하기
    public void addPostCommunity() {
//        myHabitMapper.insertPostCommunity();
    }
}
