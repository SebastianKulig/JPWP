package com.example.hellofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HelloFragmentB extends Fragment {

    //TODO zad. 4
    public interface SendCityNameListener {
        void newCity(String city);
    }

    private SendCityNameListener sendCityNameListener;  //Podany kod
    private TextView textView;                          //Podany kod
    private String textViewString;                      //Podany kod

    private EditText searchCity;                        //Podany kod

    //TODO zad. 4
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
    //TODO zadanie 2.1: uzupełnij metodę onCreateview(...)
    //TODO zadanie 3:   komunikacja
    //TODO zadanie 4:   przygowtowanie do API
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hello_fragment_b_final, container, false); //TODO zad. 2.1 oraz zad. 4
        textView = view.findViewById(R.id.textB);                                                   //TODO zad. 2.1
        searchCity = view.findViewById(R.id.search);                                                //TODO zad. 4

        searchCity.setOnEditorActionListener(editorActionListener);                                 //TODO zad. 4: kod do wklejenia

        if (textViewString != null && !textViewString.isEmpty()) {                                  //TODO zad. 3
            textView.setText(textViewString);                                                       //TODO zad. 3
        }
        return view;                                                                                //podany kod
    }

    //TODO zad. 4: gotowy kod do wklejenia
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

    //TODO zad. 3
    void updateData(String text) {
        if (textView != null){
            textViewString = text;
        }
    }

    //TODO zad. 4
    @Override
    public void onDetach() {
        super.onDetach();
        sendCityNameListener = null;
    }
}
