package com.prisma.prisma_backend.service;

import com.prisma.prisma_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User saveUser(User user);

    void deleteUser(Long id);
}
