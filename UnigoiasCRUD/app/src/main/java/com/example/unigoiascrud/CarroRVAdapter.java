package com.example.unigoiascrud;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
public class CarroRVAdapter extends ListAdapter<CarroModal,
        CarroRVAdapter.ViewHolder> {
    // criando uma variável para o ouvinte de clique no item.
    private OnItemClickListener listener;
    // criando uma classe de construtor para nossa classe de adaptador.
    CarroRVAdapter() {
        super(DIFF_CALLBACK);
    }
    // criando um retorno de chamada para o item da visualização do reciclador.
    private static final DiffUtil.ItemCallback<CarroModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CarroModal>() {
                @Override
                public boolean areItemsTheSame(CarroModal oldItem, CarroModal
                        newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(CarroModal oldItem, CarroModal
                        newItem) {
                    // a linha abaixo é para verificar o nome do curso, descrição e duração do curso.
                    return oldItem.getCarroMarca().equals(newItem.getCarroMarca()) &&

                            oldItem.getCarroModelo().equals(newItem.getCarroModelo()) &&

                            oldItem.getCarroPlaca().equals(newItem.getCarroPlaca());
                }
            };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        // a linha abaixo é usada para aumentar nosso arquivo de layout
        // para cada item de nossa visualização do reciclador.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carro_rv_item, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // a linha de código abaixo é usada para
        // definir dados para cada item de nossa visão do reciclador.
        CarroModal model = getCarroAt(position);
        holder.carroMarcaTV.setText(model.getCarroMarca());
        holder.carroModeloTV.setText(model.getCarroModelo());
        holder.carroPlacaTV.setText(model.getCarroPlaca());
    }
    // criando um método para obter o modal do curso para uma posição específica.
    public CarroModal getCarroAt(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        // classe portadora de visão para criar uma variável para cada visão.
        TextView carroMarcaTV, carroModeloTV, carroPlacaTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // inicializando cada visão de nossa visão recicladora.
            carroMarcaTV = itemView.findViewById(R.id.idTVMarcaCarro);
            carroModeloTV = itemView.findViewById(R.id.idTVModeloCarro);
                carroPlacaTV = itemView.findViewById(R.id.idTVPlacaCarro);
            // adicionando ouvinte de clique para cada item da visualização do reciclador.
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // dentro do listener do clique, estamos passando a posição para
                            // o nosso item de visualização do reciclador.
                            int position = getAdapterPosition();
                            if (listener != null && position !=
                                    RecyclerView.NO_POSITION) {
                                listener.onItemClick(getItem(position));
                            }
                        }
                    });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(CarroModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}