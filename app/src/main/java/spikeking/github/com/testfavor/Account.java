package spikeking.github.com.testfavor;

import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;

/**
 * Created by wangchenlong on 15/11/6.
 */
@AllFavor
public interface Account {
    void setUserName(String userName);

    @Default("No Name") String getUserName();

    void setPassword(String password);

    @Default("000000") String getPassword();
}
