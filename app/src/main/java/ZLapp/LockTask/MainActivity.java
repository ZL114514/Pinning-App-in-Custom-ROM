package ZLapp.LockTask;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		startLockTask();
		Button b1 = findViewById(R.id.b1);
		b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					startLockTask();
				}
			});
	}

}
