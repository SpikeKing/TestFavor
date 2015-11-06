package spikeking.github.com.testfavor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.cocosw.favor.FavorAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    @Bind(R.id.user_name_wrapper) TextInputLayout mUserNameWrapper;
    @Bind(R.id.password_wrapper) TextInputLayout mPasswordWrapper;
    @Bind(R.id.user_name_show) TextView mUserNameShow;
    @Bind(R.id.password_show) TextView mPasswordShow;

    private Account mAccount; // 账户类

    @OnClick(R.id.commit_button) void commitAccount(View view) {
        hideKeyboard();

        String username = mUserNameWrapper.getEditText().getText().toString();
        String password = mPasswordWrapper.getEditText().getText().toString();

        if (!validateEmail(username)) {
            mUserNameWrapper.setError("邮箱地址错误");
        } else if (!validatePassword(password)) {
            mUserNameWrapper.setErrorEnabled(false);
            mPasswordWrapper.setError("密码错误");
            mPasswordWrapper.getEditText().setText("");
        } else {
            mUserNameWrapper.setErrorEnabled(false);
            mPasswordWrapper.setErrorEnabled(false);

            doLogin(username, password);
            mUserNameShow.setText(mAccount.getUserName());
            mPasswordShow.setText(mAccount.getPassword());
        }

    }

    private void doLogin(String username, String password) {
        mAccount.setUserName(username);
        mAccount.setPassword(password);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAccount = new FavorAdapter.Builder(this).build().create(Account.class);

        mUserNameShow.setText(mAccount.getUserName());
        mPasswordShow.setText(mAccount.getPassword());
    }

    // 隐藏键盘
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // 验证邮箱
    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // 验证密码
    private boolean validatePassword(String password) {
        return password.length() > 7;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

