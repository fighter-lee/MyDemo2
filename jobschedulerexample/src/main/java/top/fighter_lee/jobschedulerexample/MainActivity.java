package top.fighter_lee.jobschedulerexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void start(View view) {
        Intent startServiceIntent = new Intent(context, JobSchedulerService.class);
        context.startService(startServiceIntent);

        ComponentName componentName = new ComponentName(context, JobSchedulerService.class);
        JobInfo.Builder builder = new JobInfo.Builder(888, componentName)
                .setPeriodic(10 * 1000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);   //网络可用才触发

        JobInfo info = builder.build();

        JobScheduler tm = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(info);
    }
}
