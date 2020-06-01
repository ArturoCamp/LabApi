package countryApi;

import models.CountryRespusta;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApiService {
    @GET("countries")
    Call<CountryRespusta> obtenerListaCountry();
}
