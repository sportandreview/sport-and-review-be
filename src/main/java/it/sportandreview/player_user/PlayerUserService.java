package it.sportandreview.player_user;


import java.util.List;

public interface PlayerUserService {

    PlayerUserDTO updateFavoriteClub(Long clubId, Long playerUserId, Boolean isFavorite);

    PlayerUserDTO updateFavoriteField(Long fieldId, Long playerUserId, Boolean isFavorite);

    PlayerUserDTO updateFavoriteHighlight(Long highlightId, Long playerUserId, Boolean isFavorite);

    PlayerUserDTO updateFriends(Long preferredPlayerUserId, Long playerUserId, Boolean isFavorite);

    List<PlayerUserDTO> findByNicknameContaining(String wordInserted);
    PlayerUserDTO findById(Long userId);
    PlayerUserDTO update(PlayerUserDTO playerUserDTO);
    PlayerUserDTO updateProfileImage(Long userId, String profileImage);


}
