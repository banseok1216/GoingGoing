package com.example.group;

import com.example.BaseEntity;
import com.example.personal.PersonalSchedule;
import com.example.personal.PersonalScheduleJpaEntity;
import com.example.personal.PersonalScheduleRoutineJpaEntity;
import com.example.routine.Routine;
import com.example.user.User;
import com.example.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "userGroup", schema = "goinggoing")
public class GroupJpaEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "group_Id")
    private Long groupId;

    @Builder
    public GroupJpaEntity(Long groupId) {
        this.groupId = groupId;
    }

    public static GroupJpaEntity ofDomain(Group group){
        return  GroupJpaEntity.builder().groupId(group.getId().value()).build();
    }

    public Group toGroup(List<PersonalSchedule> personalSchedules, GroupScheduleJpaEntity groupScheduleJpaEntity){
        return  Group.withId(new Group.GroupId(this.groupId),groupScheduleJpaEntity.toGroupSchedule(),personalSchedules);
    }
}
