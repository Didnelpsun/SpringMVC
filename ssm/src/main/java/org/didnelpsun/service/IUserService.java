// IUserService.java
package org.didnelpsun.service;

import org.didnelpsun.entity.User;
import java.util.List;

public interface IUserService {
    List<User> selectAllUsers();
}
