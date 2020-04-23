package com.example.hellofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hellofragment.api.NetworkClient;
import com.example.hellofragment.api.WeatherApi;
import com.example.hellofragment.model.WeatherResult;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HelloFragmentA extends Fragment {

    private TextView cityName;                                      //Podany kod
    private String cityNameString;                                  //Podany kod
    private TextView forecast;                                      //Podany kod
    private ImageView weatherIcon;                                  //Podany kod

    private String UNITS = "metric";                                //Podany kod
    private String API_KEY = "eccf917310f1c6acbf2acb9e85bf1a0d";    //Podany kod


    //TODO zad. 3 interfejs do komunikacji
    public interface HelloFragmentAListener{
        void sendData(String text);
    }

    //TODO zad. 3
    private HelloFragmentAListener helloFragmentAListener;

    //TODO zad. 3 - upewnienie się, że ktoś implementuje oraz dołącznie
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof HelloFragmentAListener) {
            helloFragmentAListener = (HelloFragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement HelloFragmentAListener");
        }
    }

    //TODO zadanie 2.1: uzupełnij metodę onCreateview(...)
    //TODO zadanie 4:   przygowtowanie do API
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hello_fragment_a_final, container, false); //TODO zad. 2.1
        cityName = view.findViewById(R.id.city_name);                                               //TODO zad. 2.1
        if (cityNameString != null && !cityNameString.isEmpty()) {                                  //TODO zad. 4
            cityName.setText(cityNameString);                                                       //TODO zad. 4
        }
        forecast = view.findViewById(R.id.forecast);                                                //TODO zad. 4
        weatherIcon = view.findViewById(R.id.weather_icon);                                         //TODO zad. 4

        if(cityNameString != null) {
            fetchWeatherDetails();
        }
        return view;                                                                                //Podany kod
    }

    //TODO zad. 3
    @Override
    public void onPause() {
        super.onPause();
        helloFragmentAListener.sendData("new text");
    }

    //TODO zad. 3 zwolnienie instancji interfejsu
    @Override
    public void onDetach() {
        super.onDetach();
        helloFragmentAListener = null;
    }

    //TODO zad. 4
    void updateCity(String city){
        if (cityName != null) {
            cityNameString = city;
        }
    }

    private void fetchWeatherDetails() {
       //TODO Stworzyć instancję retrofit przy użyciu metody statycznej z klasy NetworkClient
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        //Tworzymy instancję interfejsu WeatherApi z wykorzystaniem obiektu retrofit
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        //TODO wywołać metodę z interfejsu WeatherApi, jako nazwę miasta przekaż zmienną cityNameString, klucz API_KEY jak i jednostka UNITS są zdefiniowana na początku klasy
        Call call = weatherApi.getWeatherByCity(cityNameString,UNITS, API_KEY);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    WeatherResult weatherResult = (WeatherResult) response.body();
                    //TODO Wypisać temperaturę, cisnienie i pędkość wiatru. Do konkretnych elementów odpowiedzi możemy się dostać przy pomocy stworzonych wcześniej getterów i setterów(np weatherResult.getName())
                    //     W textView nie należy dokonywać konkatenacji, dlatego w res.strings.xml przygotowano odpowiednie wyrażenie - "weather_information".
                    //     W celu jego wykorzystania należy posłużyć się metodą getString(R.string.weather_information, temperatura, wiatr, ciśnienie)
                    //      temperatura, wiatr, ciśnienie to oczywiście dane które wyciagneliśmy z naszej odpowiedzi (weatherResponse.getMain().getTemp())


                    forecast.setText(getString(R.string.weather_information, weatherResult.getMain().getTemp(),
                            weatherResult.getWind().getSpeed(),
                            weatherResult.getMain().getPressure() ));

                    //TODO Do wyświetlenia ikony odpowiadającej danej pogodzie użyjemy biblioteki picasso. W tym celu z odpowiedzi wydobywamy informację o ikonce (weatherResult.getWeather.get(0).getIcon)
                    //     i przygotowywujemy URL z którego ściągniemy obrazek. To adresu https://openweathermap.org/img/w/ + icon + .png
                    //     Teraz możemy załadować obrazek: Picasso.get.load(nasz adres URL).into(imageView w których chcemy wyświetlić ikonkę)

                    String icon = weatherResult.getWeather().get(0).getIcon();
                    String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";
                    Picasso.get().load(iconUrl).resize(250, 250).into(weatherIcon);

                } else {
                    Toast.makeText(getContext(), "wrong city name", Toast.LENGTH_LONG).show();
                    cityName.setText("wrong city name");
                }
            }
            @Override
            public void onFailure(Call call, Throwable t){
            }
        });
    }
}