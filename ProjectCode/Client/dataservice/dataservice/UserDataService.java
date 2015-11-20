package dataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.*;

public interface UserDataService extends Remote{
      public UserPO findUserPO(String id);
      public ArrayList<UserPO> findsUserPO(String id[]);
      public ResultMessage insertUserPO(UserPO user);
      public ResultMessage deleteUserPO(UserPO user);
      public ResultMessage updateUserPO(UserPO user);
      
      public LogPO findLogPO(String id);
      public ArrayList<LogPO> findsLogsPO(String id[]);
      
      
      
}