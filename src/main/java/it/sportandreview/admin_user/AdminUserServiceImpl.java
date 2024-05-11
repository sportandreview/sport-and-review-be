package it.sportandreview.admin_user;

import it.sportandreview.user.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AdminUserServiceImpl implements AdminUserService{

    private final UserRepository userRepository;
    private final AdminUserMapper adminUserMapper;
    private final UserService userService;

    @Autowired
    public AdminUserServiceImpl(UserRepository userRepository, AdminUserMapper adminUserMapper, UserService userService) {
        this.userRepository = userRepository;
        this.adminUserMapper = adminUserMapper;
        this.userService = userService;
    }

    @Override
    public AdminUserDTO findById(Long userId) {
        User user = userService.findById(userId);
        return adminUserMapper.toDto(user);
    }

    @Override
    public AdminUserDTO update(AdminUserDTO adminUserDTO) {
        User user = userService.update(adminUserDTO);
        return adminUserMapper.toDto(user);
    }

    @Override
    public AdminUserDTO updateProfileImage(Long userId, String profileImage) {
        User user = userService.updateProfileImage(userId, profileImage);
        return adminUserMapper.toDto(user);
    }
}
