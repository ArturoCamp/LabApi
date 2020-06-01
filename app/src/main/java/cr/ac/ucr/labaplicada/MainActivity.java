package cr.ac.ucr.labaplicada;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adaptador.ListaCountryAdapta;
import countryApi.CountryApiService;
import models.CountryRespusta;
import models.byCountry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "COUNTRY";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaCountryAdapta listaCountryAdapta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaCountryAdapta = new ListaCountryAdapta();
        recyclerView.setAdapter(listaCountryAdapta);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager= new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        retrofit= new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
    }
    private void obtenerDatos(){
        CountryApiService service= retrofit.create(CountryApiService.class);
        Call<CountryRespusta> countryRespustaCall= service. obtenerListaCountry();

        countryRespustaCall.enqueue(new Callback<CountryRespusta>() {
            @Override
            public void onResponse(Call<CountryRespusta> call, Response<CountryRespusta> response) {

                if(response.isSuccessful()){
                    CountryRespusta countryRespusta= response.body();
                    ArrayList<byCountry> listaCountry= new CountryRespusta().getResults();
                  listaCountryAdapta.adicionarLista(listaCountry);

                }else{
                    Log.e(TAG, "OnResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CountryRespusta> call, Throwable t) {


            }
        });
    }
}
