package sheloumov.v.d.ShoeShop.responses;

import sheloumov.v.d.ShoeShop.entity.User;

import java.util.List;

public class UserResponse extends CommonResponse<User>{

    public UserResponse(List<User> data) {
        super(data);


    }
}
