package orionlofty.com.apps.gadspracticeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import orionlofty.com.apps.gadspracticeproject.fragments.ErrorDialogFragment;
import orionlofty.com.apps.gadspracticeproject.fragments.SubmitDialogFragment;
import orionlofty.com.apps.gadspracticeproject.fragments.SuccessDialogFragment;
import orionlofty.com.apps.gadspracticeproject.model.MyDetailsToSubmit;
import orionlofty.com.apps.gadspracticeproject.services.SubmitService;
import orionlofty.com.apps.gadspracticeproject.services.SubmitServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmissionActivity extends AppCompatActivity implements SubmitDialogFragment.ButtonResponseListener {

    private static final String TAG = "ProjectSubmissionActivity";

    private EditText firstName, lastName, emailAddress, githubLink;
    TextView backButton;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        emailAddress = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.github_link);
        backButton = findViewById(R.id.back_from_submission);
        submitButton = findViewById(R.id.submit_project);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectSubmissionActivity.this, LeaderboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _firstName = firstName.getText().toString().trim();
                String _lastName = lastName.getText().toString().trim();
                String _emailAddress = emailAddress.getText().toString().trim();
                String _githubLink = githubLink.getText().toString().trim();

                if (!_firstName.equals("") && !_lastName.equals("") && !_emailAddress.equals("") && !_githubLink.equals("")){
                    if (emailPatternValidation(_emailAddress) && _githubLink.contains("github.com/")){
                        Log.e(TAG, "onClick: email address and link are valid");
                        showSubmitDialog();
                    }else {
                        Toast.makeText(ProjectSubmissionActivity.this, "Please enter a valid email or valid link to GitHub", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ProjectSubmissionActivity.this, "Make sure you entered information in all fields", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //making sure email entered is of standard pattern with @ sign and .com
    private boolean emailPatternValidation(String email){
        Pattern emailPattern;
        Matcher emailMatcher;
        String EMAIL_PATTERN = "[a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z]+\\.[a-z]+";
        emailPattern = Pattern.compile(EMAIL_PATTERN);
        emailMatcher = emailPattern.matcher(email);

        return emailMatcher.matches();
    }

    @Override
    public void onYesClicked(String status) {
        if (status.equals("ok")){
            SubmitService submitService = SubmitServiceBuilder.buildService(SubmitService.class);
            Call<MyDetailsToSubmit> submitRequest = submitService.submitDetails(
                    firstName.getText().toString().trim(),
                    lastName.getText().toString().trim(),
                    emailAddress.getText().toString().trim(),
                    githubLink.getText().toString().trim()
            );

            submitRequest.enqueue(new Callback<MyDetailsToSubmit>() {
                @Override
                public void onResponse(Call<MyDetailsToSubmit> call, Response<MyDetailsToSubmit> response) {
                    if (response.isSuccessful()){
                        Log.d(TAG, "onResponse: Server response" + response.toString());
                        showSuccessDialog();
                        Toast.makeText(getApplicationContext(), "Project Submission is Successful", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MyDetailsToSubmit> call, Throwable t) {
                    Log.d(TAG, "onFailure: Unable to submit details");
                    Toast.makeText(getApplicationContext(), "Unable to submit details", Toast.LENGTH_SHORT).show();
                    showErrorDialog();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProjectSubmissionActivity.this, LeaderboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showErrorDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        errorDialogFragment.show(fragmentManager, "error_dialog");
    }

    private void showSuccessDialog(){
        FragmentManager successFragmentManager = getSupportFragmentManager();
        SuccessDialogFragment successDialogFragment = new SuccessDialogFragment();
        successDialogFragment.show(successFragmentManager, "success_dialog");
    }

    private void showSubmitDialog(){
        FragmentManager submitFragmentManager = getSupportFragmentManager();
        SubmitDialogFragment submitDialogFragment = new SubmitDialogFragment();
        submitDialogFragment.show(submitFragmentManager, "submit_dialog");
    }
}