package orionlofty.com.apps.gadspracticeproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import orionlofty.com.apps.gadspracticeproject.R;

public class SubmitDialogFragment extends DialogFragment {

    //interface that passes button onClick result from dialog fragment to submission activity
    public interface ButtonResponseListener {
        void onYesClicked(String status);
    }

    public ButtonResponseListener buttonResponseListener;

    private static final String TAG = "SubmitDialogFragment";
    private static final String ON_CLICK_RESULT = "ok";
    private TextView exitDialog;
    private Button buttonYes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.submit_dialog, container);
        exitDialog = view.findViewById(R.id.exit_dialog);
        buttonYes = view.findViewById(R.id.submit_dialog_button);
        setCancelable(false);

        exitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Yes clicked");
                buttonResponseListener.onYesClicked(ON_CLICK_RESULT);
            }
        });
        return view;
    }

    public SubmitDialogFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            buttonResponseListener = (ButtonResponseListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: " + e.getMessage() );
        }
    }
}
