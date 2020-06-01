package adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cr.ac.ucr.labaplicada.R;
import models.byCountry;


public class ListaCountryAdapta extends RecyclerView.Adapter<ListaCountryAdapta.ViewHolder> {
    private ArrayList<byCountry> dataset;

    public ListaCountryAdapta(){
        dataset = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        byCountry p=dataset.get(position);
        holder.nombreTextView.setText(p.getCountry());
    }
    @Override
    public int getItemCount(){
        return dataset.size();
    }
    public void adicionarLista(ArrayList<byCountry> listaCountry){
        dataset.addAll(listaCountry);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView){
            super(itemView);

            fotoImageView= (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView= (TextView) itemView.findViewById(R.id.nombreTextView);
        }
    }

}
