package it.sportandreview.service.impl;

import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;
import it.sportandreview.exception.EntityNotFoundException;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MessageSource messageSource;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("user.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));
    }


    @Override
    public User updateUser(Long id, User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("user.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));

        updateNonNullFields(existingUser, userDetails);

        return userRepository.save(existingUser);
    }

    private void updateNonNullFields(User existingUser, User userDetails) {
        Field[] fields = User.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object newValue = ReflectionUtils.getField(field, userDetails);

            if (newValue != null && !"password".equals(field.getName())
                    && !"roleType".equals(field.getName())
                    && !"sportSet".equals(field.getName())) {
                ReflectionUtils.setField(field, existingUser, newValue);
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(messageSource.getMessage("user.not.found", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

}
