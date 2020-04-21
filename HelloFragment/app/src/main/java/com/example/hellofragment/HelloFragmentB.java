package com.example.hellofragment;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class HelloFragmentB extends Fragment {

    public interface SendCityNameListener {
        void newCity(String city);
    }

    private SendCityNameListener sendCityNameListener;
    private TextView textView;
    private String string;

    private EditText searchCity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendCityNameListener) {
            sendCityNameListener = (SendCityNameListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SendCityNameListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hello_fragment_b, container, false);
        textView = view.findViewById(R.id.textB);
        searchCity = view.findViewById(R.id.search);

        searchCity.setOnEditorActionListener(editorActionListener);

        if (string != null && !string.isEmpty()) {
            textView.setText(string);
        }
        return view;
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String obtainedText = String.valueOf(searchCity.getText());
                if (!obtainedText.isEmpty()) {
                    sendCityNameListener.newCity(obtainedText);
                    searchCity.setText("");
                } else {
                    Toast toast = Toast.makeText(getContext(), "Please enter city name",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 200);
                    toast.show();
                }
            }
            return false;
        }
    };

    void updateData(String text) {
        if (textView != null){
            string = text;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendCityNameListener = null;
    }
}
