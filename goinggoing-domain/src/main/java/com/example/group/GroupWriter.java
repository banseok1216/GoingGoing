package com.example.group;

import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupWriter {
    private final GroupRepository groupRepository;
    public void save(Group group){
        groupRepository.save(group);
    }
}
