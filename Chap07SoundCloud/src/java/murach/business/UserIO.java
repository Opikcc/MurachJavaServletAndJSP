package murach.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserIO {
  
  private static List<Map> userList;

  public static User getUser(String emailAddress, User user) {
    
    for (Map<String, User> listUser : userList) {
      if (listUser.containsKey(emailAddress)) {
        user = listUser.get(emailAddress);
      }      
    }
    
    return user;
  }

  public static User add(User user, String emailAddress) {
    
    boolean isExist = false;
    
    // Check if user already exist
    for (Map<String, User> listUser : userList) {
      if (listUser.containsKey(emailAddress)) {
        isExist = true;
        break;
      }      
    }
    
    if (!isExist) {
      Map<String, User> newUser = new HashMap();

      newUser.put(emailAddress, user);
      userList.add(newUser);
    }
    
    return user;
  }
  
  static {
    userList = new ArrayList();
  }
  
}
