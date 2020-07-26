package com.imzhizi.tdd.service;

import com.imzhizi.tdd.domain.BusinessException;
import com.imzhizi.tdd.domain.CodeMsg;
import com.imzhizi.tdd.domain.dto.LoginDto;
import com.imzhizi.tdd.domain.dto.RegisterDto;
import com.imzhizi.tdd.domain.model.Role;
import com.imzhizi.tdd.repository.redis.KeyUser;
import com.imzhizi.tdd.domain.vo.UserVo;
import com.imzhizi.tdd.domain.model.User;
import com.imzhizi.tdd.repository.RedisRepo;
import com.imzhizi.tdd.repository.UserRepo;
import com.imzhizi.tdd.repository.redis.UserKey;
import com.imzhizi.tdd.helper.MD5Helper;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * created by zhizi
 * on 5/31/20 16:44
 */
@Log4j2
@Service
public class UserService {
    private final UserRepo userRepo;
    private final RedisRepo redisRepo;
    private final ModelMapper modelMapper;

    public UserService(UserRepo userRepo, RedisRepo redisRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.redisRepo = redisRepo;
        this.modelMapper = modelMapper;
    }

    public User getUserById(UserKey userKey, long id) {
        return redisRepo.get(userKey, String.valueOf(id), User.class);
    }

    public User getUserById(long id) {
        return userRepo.findById(1);
    }

    public int saveUser(RegisterDto register) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(MD5Helper.md5(register.getPassword(), salt));
        user.setCreateDate(LocalDateTime.now());
        user.setSalt(salt);
        return userRepo.insertOne(user);
    }

    public User check(LoginDto login) throws BusinessException {
        if (login.getUsername() == null || login.getUsername().isEmpty()) {
            throw new BusinessException(CodeMsg.WRONG_FORMAT);
        }
        User user = userRepo.findByUsername(login.getUsername());
        if (user == null) {
            throw new BusinessException(CodeMsg.UN_EXISTS);
        }
        if (user.getUsername() == null || user.getSalt() == null) {
            throw new BusinessException(CodeMsg.USER_INFO_ERROR);
        }
        if (!MD5Helper.check(login.getPassword(), user.getSalt(), user.getPassword())) {
            throw new BusinessException(CodeMsg.WRONG_PASSWORD);
        }
        return user;
    }

    public UserVo setUser(User user) {
        UserVo userVo = transfer(user);
        userVo.setToken(UUID.randomUUID().toString());
        redisRepo.set(UserKey.AUTH, userVo.getToken(), new KeyUser(userVo.getId(), userVo.getUsername(), Role.admin));
        return userVo;
    }

    public UserVo transfer(User user) {
        return modelMapper.map(user, UserVo.class);
    }

    public boolean checkToken(String token) {
        return redisRepo.exists(UserKey.AUTH, token);
    }

    public KeyUser getUserByToken(String token) {
        return redisRepo.get(token, KeyUser.class);
    }
}
