package com.web.pocketmoney.service.user;

import com.web.pocketmoney.dto.user.*;
import com.web.pocketmoney.entity.user.User;
import com.web.pocketmoney.model.SingleResult;

public interface UserService {

    //True시 카인드스코어 +1, False시 카인드스코어 -1
    //tf, 상대방id, 내id 전달
    void kindScore(boolean tf, Long id, Long me);


    //회원 정보 조회
    UserDTO getUser(Long id);

    void modify(UserModifyDTO userModifyDTO, User user);

    void delete(Long id);

    default UserDTO entityToDto(User user){
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .roleSet(user.getRoles())
                .email(user.getEmail())
                .userName(user.getUsername())
                .password(user.getPassword())
                .sex(user.getSex())
                .nickName(user.getNickName())
                .age(user.getAge())
                .city(user.getCity())
                .kindScore(user.getKindScore())
                .build();

        return userDTO;
    }

    default User DtoToEntity(UserDTO userDTO){
        User user =  User.builder()
                .id(userDTO.getId())
                .roles(userDTO.getRoleSet())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .sex(userDTO.getSex())
                .nickName(userDTO.getNickName())
                .age(userDTO.getAge())
                .city(userDTO.getCity())
                .kindScore(userDTO.getKindScore())
                .build();
        return user;
    }
    SingleResult<TokenUserDTO> login(LoginDTO loginDto);
    SignupUserDTO signup(SignupUserDTO signupUserDTO);
}
