package com.course.frandydlacruz.opentrivia.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.interfaces.OnSaveListener;

public class DialogUser extends DialogFragment {

    private EditText etUserName;
    private TextView tvOk;
    public OnSaveListener onSaveListener;
    private SharedPreferences savedUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_user, container, false);
        tvOk = view.findViewById(R.id.tvOk);
        etUserName = view.findViewById(R.id.etUserName);

        savedUser = getActivity().getSharedPreferences("savedUser", Context.MODE_PRIVATE);
        String user = savedUser.getString("savedUser", null);
        if(user != null) {
            etUserName.setText(user);
        }

        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                onSaveListener.sendInput(userName);
                ((Question) getActivity()).task.execute(((Question) getActivity()).userScore);
                getDialog().dismiss();

                SharedPreferences.Editor editor = savedUser.edit();
                editor.putString("savedUser", userName);
                editor.apply();

                startActivity(new Intent(getContext(), LobbyActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onSaveListener = (OnSaveListener) getActivity();
        } catch(ClassCastException e) {
            Log.e("Error In Dialog", "Exception: " + e.getMessage());
        }
    }
}
