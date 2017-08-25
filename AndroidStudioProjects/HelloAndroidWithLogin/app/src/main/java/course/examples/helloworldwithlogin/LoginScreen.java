package course.examples.helloworldwithlogin;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LoginScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        final EditText uname = (EditText) findViewById(R.id.username_edittext);
        final EditText passwd = (EditText) findViewById(R.id.password_edittext);

        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (checkPassword(uname.getText(), passwd.getText())) {

                    // Create an explicit Intent for starting the HelloAndroid
                    // Activity
                    Intent helloAndroidIntent = new Intent(LoginScreen.this,
                            HelloAndroid.class);

                    // Use the Intent to start the HelloAndroid Activity
                    startActivity(helloAndroidIntent);
                } else {
                    uname.setText("");
                    passwd.setText("");
                }
            }
        });
        final TextView helloTextView = (TextView) findViewById(R.id.helloTextView);
        final Button helloButton = (Button) findViewById(R.id.hello_button);
        helloButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                //update a tex area to say hello
                helloTextView.setText(R.string.hello);
                helloButton.setBackgroundColor(getResources().getColor(R.color.black));

            }
        });

        final TextView toggleTextView = (TextView) findViewById(R.id.toggleTextView);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (toggleButton.isChecked() ) {
                    toggleTextView.setText(R.string.this_toggled);
                    toggleTextView.setTextColor(getResources().getColor(R.color.black));
                    toggleTextView.setBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    toggleTextView.setText(R.string.that_toggled);
                }
            }
        });
    }


    private boolean checkPassword(Editable uname, Editable passwd) {
        // Just pretending to extract text and check password
        return new Random().nextBoolean();
    }
}
