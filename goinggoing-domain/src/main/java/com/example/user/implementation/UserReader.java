package com.example.user.implementation;


import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;
    public User readUser(User.UserId userId) {
        User user = userRepository.readUserById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }
    public RoutineWindow readUserRoutines(User.UserId userId)
    {
        return userRepository.readRoutineByUserId(userId);
    }
    public Routine readUserRoutine(Routine.RoutineId routineId){
        Routine routine = userRepository.readUserRoutine(routineId);
        if (routine == null) {
            throw new BusinessException(ErrorCode.USER_ROUTINE_NOT_FOUND);
        }
        return userRepository.readUserRoutine(routineId);
    }
}