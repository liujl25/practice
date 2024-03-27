package com.liujunlong.beancopy;

import com.liujunlong.beancopy.bean.UserDO;
import com.liujunlong.beancopy.bean.UserDTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2022/9/26 00:17
 */
public class test {

    public static void main(String[] args) {
        simpleBeanCopy();
    }

    private static void simpleBeanCopy() {
        BeanCopier beanCopier = BeanCopier.create(UserDO.class, UserDTO.class, false);
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("aihe");
        userDO.setGmtCreate(new Date());
        userDO.setGender(0);
        userDO.setPassword("xxxxxx");
        UserDTO userDTO = new UserDTO();
        beanCopier.copy(userDO, userDTO,null);
        System.out.println("名称成功拷贝: "+userDTO.getName());
        System.out.println("Id成功拷贝: "+(long)userDTO.getId());
        System.out.println("性别成功拷贝: "+userDTO.getGender());
    }
}
