package com.example.goinggoing.personalschedule;

import com.example.goinggoing.group.dto.GroupScheduleResponseDto;
import com.example.goinggoing.personal.dto.PersonalScheduleResponseDto;
import com.example.goinggoing.personal.mapstruct.PersonalScheduleMapper;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalGroupGroupScheduleMapperTest {

    private final PersonalScheduleMapper mapper = Mappers.getMapper(PersonalScheduleMapper.class);

    @Test
    void toPersonalScheduleResponseDtoTest() {
        GroupSchedule groupSchedule = createSchedule();
        PersonalSchedule personalSchedule = createPersonalSchedule(groupSchedule);
        PersonalScheduleResponseDto.GetPersonalSchedule dto = mapper.toPersonalScheduleResponseDto(personalSchedule);

        assertEquals(personalSchedule.getPersonalScheduleId(), dto.getPersonalScheduleId());
        assertEquals(personalSchedule.getDuration(), dto.getDuration());
        assertEquals(personalSchedule.getScheduleDone(), dto.getScheduleDone());
        assertEquals(personalSchedule.getScheduleStart(), dto.getScheduleStart());
        assertEquals(personalSchedule.getScheduleStartTime(), dto.getScheduleStartTime());
        assertEquals(personalSchedule.getScheduleDoneTime(), dto.getScheduleDoneTime());
        assertEquals(personalSchedule.getGroupSchedule().getScheduleId(), dto.getScheduleId());
    }

    @Test
    void toResponseScheduleListDtoTest() {
        GroupSchedule firstGroupSchedule = createSchedule();
        PersonalSchedule firstPersonalSchedule = createPersonalSchedule(firstGroupSchedule);
        PersonalSchedule secondPersonalSchedule = createPersonalSchedule(firstGroupSchedule);

        List<PersonalSchedule> personalScheduleList = Arrays.asList(firstPersonalSchedule, secondPersonalSchedule);

        List<GroupScheduleResponseDto.GetSchedule> dtoList = mapper.toResponseScheduleListDto(personalScheduleList);


        assertEquals(2, dtoList.size());
        assertEquals(firstPersonalSchedule.getPersonalScheduleId(), dtoList.get(0).getPersonalScheduleId());
        assertEquals(firstPersonalSchedule.getDuration(), dtoList.get(0).getDuration());
        assertEquals(firstPersonalSchedule.getScheduleStartTime(), dtoList.get(0).getScheduleStartTime());
        assertEquals(firstPersonalSchedule.getScheduleDoneTime(), dtoList.get(0).getScheduleDoneTime());
        assertEquals(firstPersonalSchedule.getGroupSchedule().getScheduleId(), dtoList.get(0).getScheduleId());
        assertEquals(firstPersonalSchedule.getGroupSchedule().getScheduleLocation(), dtoList.get(0).getScheduleLocation());
        assertEquals(firstPersonalSchedule.getGroupSchedule().getScheduleDateTime(), dtoList.get(0).getScheduleDateTime());
        assertEquals(firstPersonalSchedule.getGroupSchedule().getScheduleName(), dtoList.get(0).getScheduleName());

        assertEquals(secondPersonalSchedule.getPersonalScheduleId(), dtoList.get(1).getPersonalScheduleId());
        assertEquals(secondPersonalSchedule.getDuration(), dtoList.get(1).getDuration());
        assertEquals(secondPersonalSchedule.getScheduleStartTime(), dtoList.get(1).getScheduleStartTime());
        assertEquals(secondPersonalSchedule.getScheduleDoneTime(), dtoList.get(1).getScheduleDoneTime());
        assertEquals(secondPersonalSchedule.getGroupSchedule().getScheduleId(), dtoList.get(1).getScheduleId());
        assertEquals(secondPersonalSchedule.getGroupSchedule().getScheduleLocation(), dtoList.get(1).getScheduleLocation());
        assertEquals(secondPersonalSchedule.getGroupSchedule().getScheduleDateTime(), dtoList.get(1).getScheduleDateTime());
        assertEquals(secondPersonalSchedule.getGroupSchedule().getScheduleName(), dtoList.get(1).getScheduleName());
    }

    private GroupSchedule createSchedule(){
        return GroupSchedule.builder().scheduleName("testLocation").scheduleId(1L).scheduleDateTime(LocalDateTime.now()).scheduleName("testSchedule").build();
    }
    private PersonalSchedule createPersonalSchedule(GroupSchedule groupSchedule){
        return PersonalSchedule.builder().personalScheduleId(1L).scheduleStart(true)
                .scheduleNotificationStart(true).scheduleNotificationDone(true)
                .scheduleDone(true).scheduleStartTime(LocalDateTime.now())
                .scheduleDoneTime(LocalDateTime.now()).scheduleDone(true)
                .groupSchedule(groupSchedule).build();
    }
}
