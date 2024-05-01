package com.example.group;

import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.group.repository.GroupRepository;
import com.example.personal.*;
import com.example.personal.model.PersonalSchedule;
import com.example.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GroupEntityRepository implements GroupRepository {
    private final GroupJpaRepository groupJpaRepository;
    private final PersonalScheduleJpaRepository personalScheduleJpaRepository;
    private final PersonalScheduleRoutineJpaRepository personalScheduleRoutineJpaRepository;
    private final GroupScheduleJpaRepository groupScheduleJpaRepository;

    public GroupEntityRepository(GroupJpaRepository groupJpaRepository, PersonalScheduleJpaRepository personalScheduleJpaRepository, PersonalScheduleRoutineJpaRepository personalScheduleRoutineJpaRepository, GroupScheduleJpaRepository groupScheduleJpaRepository) {
        this.groupJpaRepository = groupJpaRepository;
        this.personalScheduleJpaRepository = personalScheduleJpaRepository;
        this.personalScheduleRoutineJpaRepository = personalScheduleRoutineJpaRepository;
        this.groupScheduleJpaRepository = groupScheduleJpaRepository;
    }
    @Override
    public PersonalSchedule.PersonalScheduleId addMember(Group group, PersonalSchedule personalSchedule) {
        Long personalScheduleId= personalScheduleJpaRepository.save(PersonalScheduleJpaEntity.ofDomain(personalSchedule,group)).getPersonalScheduleId();
        return new PersonalSchedule.PersonalScheduleId(personalScheduleId);
    }

    @Override
    public Group.GroupId saveGroupSchedule(GroupSchedule groupSchedule, User user) {
        GroupScheduleJpaEntity groupScheduleJpaEntity = GroupScheduleJpaEntity.ofDomain(groupSchedule,GroupJpaEntity.builder().build());
        return new Group.GroupId(groupScheduleJpaRepository.save(groupScheduleJpaEntity).getUserGroup().getGroupId());
    }

    @Override
    public void updateGroupSchedule(GroupSchedule groupSchedule) {
        groupScheduleJpaRepository.updateGroupSchedule(groupSchedule.getId().value(),
                groupSchedule.getName(),
                groupSchedule.getDate(),
                groupSchedule.getLocation(),
                groupSchedule.getDescription());
    }

    @Override
    public Group readGroup(Group.GroupId groupId) {
        GroupJpaEntity groupJpaEntity = groupJpaRepository.findByGroupId(groupId.value());
        List<PersonalSchedule> personalSchedules = personalScheduleJpaRepository.findAllByUserGroup_GroupId(groupId.value()).stream()
                .map(schedule -> {List <PersonalScheduleRoutineJpaEntity> routines = personalScheduleRoutineJpaRepository
                            .findAllByPersonalScheduleJpaEntityPersonalScheduleId(schedule.getPersonalScheduleId());
                    return schedule.toPersonalSchedule(routines);
                })
                .collect(Collectors.toList());
        GroupScheduleJpaEntity groupScheduleJpaEntity = groupScheduleJpaRepository.findByUserGroup_GroupId(groupId.value());
        return groupJpaEntity.toGroup(personalSchedules, groupScheduleJpaEntity);
    }

    @Override
    public List<Group> readGroupList(User user) {
        return null;
    }

    @Override
    public GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {
        GroupScheduleJpaEntity groupScheduleJpaEntity =groupScheduleJpaRepository.findByScheduleId(groupScheduleId.value());
        return groupScheduleJpaEntity.toGroupSchedule();
    }

    @Override
    public void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {
        groupScheduleJpaRepository.deleteById(groupScheduleId.value());
    }
}
