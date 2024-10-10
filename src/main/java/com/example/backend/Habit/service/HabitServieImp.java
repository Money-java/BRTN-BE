package com.example.backend.Habit.service;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.dto.HabitCreateResponseDTO;
import com.example.backend.Habit.mapper.HabitCheckMapper;
import com.example.backend.Habit.mapper.MyHabitMapper;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.exception.ConflictException;
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
        if ("undefined".equals(checkDate)) {
            checkDate = null;
        }
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
    public HabitCreateResponseDTO createHabitWithMyHabit(MyHabitVO myHabitVO) {
        myHabitMapper.insertHabit(myHabitVO);
        Long habitId = myHabitMapper.selectLastInsertedHabitId();
        myHabitVO.setHabitId(habitId);
        myHabitMapper.insertMyHabit(myHabitVO);
        Long myHabitId = myHabitMapper.selectLastInsertedMyHabitId();

        HabitCreateResponseDTO responseDTO = new HabitCreateResponseDTO();
        responseDTO.setHabitId(habitId);
        responseDTO.setMyHabitId(myHabitId);
        return responseDTO;
    }

    // 5. 습관 수정
    @Override
    public String modifyMyHabit(MyHabitVO myHabitVO) {
        // step 1 : 습관 작성자 아이디 가져오기
        MyHabitVO habit = myHabitMapper.getHabitById(myHabitVO.getHabitId());

        if (habit != null & habit.getUserId().equals(myHabitVO.getUserId())) {
            // step 2 : 습관 공유 커뮤니티에 내가 작성한 습관을 공유했는지 확인하기
            int count = myHabitMapper.getHabitCommunityById(myHabitVO.getHabitId());

            if (count == 0) {
                // step 3 : 내가 직접 작성한 습관이고 습관 공유 커뮤니티에 올라가지 않은 경우에만 습관 수정 가능
                myHabitMapper.updateMyHabit(myHabitVO);
                return "Successfully updated habit";
            } else {
                return "This habit has been shared in the community and cannot be modified";
            }
        }

        return "The habit does not belong to this user";
    }

    // 6. 습관 삭제
    @Override
    public void deleteMyHabit(Long myHabitId) {
        myHabitMapper.deleteMyHabit(myHabitId);
    }

    // 7. 습관 상태 변경
    @Override
    public void modifyMyHabitState(List<MyHabitVO> habitList, long userId) {
        myHabitMapper.updateMyHabitState(habitList, userId);
    }

    // 8. 절약 예상 금액
    @Override
    public int saveTotalAmount() {
        return myHabitMapper.totalSaveAmount();
    }

    // 9. 실제 절약 금액
    @Override
    public int saveRealAmount(long habitId) {
        return myHabitMapper.realSaveAmount(habitId);
    }

    // 10. 습관 커뮤니티 업로드
    @Override
    public String addHabitCommunity(long habitId) {
        // step 1 : HabitCommunity에 습관의 존재 여부 확인
        int count = myHabitMapper.checkHabitCommunityExists(habitId);

        // step 2 : 업로드 되어있지 않다면 habitId 삽입
        if (count == 0) {
            HabitCommunityVO habitCommunity = new HabitCommunityVO();
            habitCommunity.setHabitId(habitId);
            myHabitMapper.insertHabitCommunity(habitId);
            return "Habit ID " + habitId + " has been inserted into HabitCommunity.";
        } else {
            return "Habit ID " + habitId + " already exists in HabitCommunity.";
        }
    }

    // 11. 인증 커뮤니티 업로드
    @Override
    public void addPostCommunity(PostCommunityVO postCommunityVO) {
        if (postCommunityVO.getImageURL() == null) {
            postCommunityVO.setImageURL(null);
        }

        // step 1 : PostCommunity 테이블에 정보 삽입
        myHabitMapper.insertPostCommunity(postCommunityVO);

        // step 2 : HabitCheck 테이블의 정보 조회
        int count = myHabitMapper.checkHabitExists(postCommunityVO);

        if(count > 0) {
        } else {
            // step 3 : HabitCheck 테이블에 입력하려는 정보가 없다면 삽입
            myHabitMapper.addHabitCheck(postCommunityVO);
        }

        // step 4 : MyHabit 테이블에서 해당 습관 상태 완료로 변경
        myHabitMapper.updateMyHabitStateClear(postCommunityVO);
    }

    public List<HabitCheckCountDTO> getHabitCheckCounts(HabitCheckRequestDTO habitCheckRequestDTO) {
        return habitCheckMapper.getHabitCheckCounts(habitCheckRequestDTO);
    }

    // 인증한 습관 개수 조회
    @Override
    public int countCheckedHabit(long userId) {
        return habitCheckMapper.countCheckedHabit(userId);
    }

    // 인증한 습관 금액 조회
    @Override
    public int countCheckedMoney(long userId) {
        return habitCheckMapper.countCheckedMoney(userId);
    }

    @Override
    public List<MyHabitVO> getCheckedHabitAll(long userId) {
        return habitCheckMapper.getCheckedHabitAll(userId);
    }
}
