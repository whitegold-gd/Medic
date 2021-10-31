package com.example.medic.Presentation.Repository.Room.DTO;

import androidx.room.Entity;

import com.example.medic.Domain.Model.User;

@Entity(tableName = "user", primaryKeys = {"id"})
public class UserDTO extends User {

    public static UserDTO convertFromUser(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        return dto;
    }
}
