package it.sportandreview.player_user;

import it.sportandreview.club.Club;
import it.sportandreview.club.ClubRepository;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.Field;
import it.sportandreview.field.FieldRepository;
import it.sportandreview.highlight.Highlight;
import it.sportandreview.highlight.HighlightRepository;
import it.sportandreview.user.User;
import it.sportandreview.user.UserRepository;
import it.sportandreview.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PlayerUserServiceImpl implements PlayerUserService {

    private final UserRepository userRepository;
    private final PlayerUserMapper playerUserMapper;
    private final ClubRepository clubRepository;
    private final FieldRepository fieldRepository;
    private final HighlightRepository highlightRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    @Autowired
    public PlayerUserServiceImpl(UserRepository userRepository, PlayerUserMapper playerUserMapper, ClubRepository clubRepository,
                                 FieldRepository fieldRepository, HighlightRepository highlightRepository, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager, UserService userService) {
        this.userRepository = userRepository;
        this.playerUserMapper = playerUserMapper;
        this.clubRepository = clubRepository;
        this.fieldRepository = fieldRepository;
        this.highlightRepository = highlightRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public PlayerUserDTO updateFavoriteClub(Long clubId, Long playerUserId, Boolean isFavorite) {
        Club club = clubRepository.findById(clubId).
                orElseThrow(() -> new NotFoundException("club", "Club" + "not exists into database"));
        User playerUser = userRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("player user", "PlayerUser" + "not exists into database"));
        if (isFavorite.equals(Boolean.TRUE)){
            playerUser.getFavoriteClubs().add(club);
        } else if (playerUser.getFavoriteClubs().contains(club)) {
            playerUser.getFavoriteClubs().remove(club);
        }
        User player = userRepository.save(playerUser);
        return playerUserMapper.toDto(player);
    }

    @Override
    public PlayerUserDTO updateFavoriteField(Long fieldId, Long playerUserId, Boolean isFavorite) {
        Field field = fieldRepository.findById(fieldId).
                orElseThrow(() -> new NotFoundException("field", "Field" + "not exists into database"));
        User playerUser = userRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("player user", "PlayerUser" + "not exists into database"));
        if (isFavorite.equals(Boolean.TRUE)){
            playerUser.getFavoriteFields().add(field);
        } else if (playerUser.getFavoriteFields().contains(field)) {
            playerUser.getFavoriteFields().remove(field);
        }
        User player = userRepository.save(playerUser);
        return playerUserMapper.toDto(player);
    }

    @Override
    public PlayerUserDTO updateFavoriteHighlight(Long highlightId, Long playerUserId, Boolean isFavorite) {
        Highlight highlight = highlightRepository.findById(highlightId).
                orElseThrow(() -> new NotFoundException("highlight", "Highlight" + "not exists into database"));
        User playerUser = userRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("player user", "PlayerUser" + "not exists into database"));
        if (isFavorite.equals(Boolean.TRUE)){
            playerUser.getFavoriteHighlights().add(highlight);
        } else if (playerUser.getFavoriteHighlights().contains(highlight)) {
            playerUser.getFavoriteHighlights().remove(highlight);
        }
        User player = userRepository.save(playerUser);
        return playerUserMapper.toDto(player);
    }

    @Override
    public PlayerUserDTO updateFriends(Long preferredPlayerUserId, Long playerUserId, Boolean isFavorite) {
        User preferredPlayerUser = userRepository.findById(preferredPlayerUserId).
                orElseThrow(() -> new NotFoundException("friends", "Friends" + "not exists into database"));
        User playerUser = userRepository.findById(playerUserId).
                orElseThrow(() -> new NotFoundException("player user", "PlayerUser" + "not exists into database"));
        if (isFavorite.equals(Boolean.TRUE)) {
            playerUser.getFriends().add(preferredPlayerUser);
            preferredPlayerUser.getFriends().add(playerUser);
        } else if (playerUser.getFriends().contains(preferredPlayerUser) && preferredPlayerUser.getFriends().contains(playerUser)) {
            playerUser.getFriends().remove(preferredPlayerUser);
            preferredPlayerUser.getFriends().remove(playerUser);
         }
        User player = userRepository.save(playerUser);
         return playerUserMapper.toDto(player);
    }

    @Override
    public List<PlayerUserDTO> findByNicknameContaining(String wordInserted) {
        return userRepository.findByNicknameContaining(wordInserted).stream().
                map(playerUserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PlayerUserDTO findById(Long userId) {
        User user = userService.findById(userId);
        return playerUserMapper.toDto(user);
    }

    @Override
    public PlayerUserDTO update(PlayerUserDTO playerUserDTO) {
        User user = userService.update(playerUserDTO);
        return playerUserMapper.toDto(user);
    }

    @Override
    public PlayerUserDTO updateProfileImage(Long userId, String profileImage) {
        User user = userService.updateProfileImage(userId, profileImage);
        return playerUserMapper.toDto(user);
    }

}



