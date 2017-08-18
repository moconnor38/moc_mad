package course.examples.theanswer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class TheAnswer extends Activity {

	public static final int[] answers = { 42, -10, 0, 100, 1000 };
	public static final int answer = 42;
	private static final String THE_ANSWER_LOG = "TheAnswerLog";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Required call through to Activity.onCreate()
		// Restore any saved instance state
		super.onCreate(savedInstanceState);

		// Set up the application's user interface (content view)
		setContentView(R.layout.answer_layout);

		// Get a reference to a TextView in the content view
		TextView answerView = (TextView) findViewById(R.id.answer_view);

		Log.d(THE_ANSWER_LOG, "The answer is: " + findAnswer());
		Log.d(THE_ANSWER_LOG, "The answer text is: " + getMessage(findAnswer()));

		// Set desired text in answerView TextView
		answerView
				.setText("The answer to life, the universe and everything is:\n\n"
						+ getMessage(findAnswer()));
	}
	protected void onStart() {
        super.onStart();
        Log.i(THE_ANSWER_LOG, "On start called the app is now visible and about to be started.");

	}

	protected void onStop() {
        super.onStop();
        Log.i(THE_ANSWER_LOG, "On Stop called the app is now invisible and about to be stopped");
    }
	private String getMessage(int val) {
		if (val == answer) {
			return "42";
		}
			return "We may never know";
	}

	private int findAnswer() {
		for (int val : answers) {
			if (val == answer)
				return val;
		}
		return -1;
	}
}
