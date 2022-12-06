package com.example.unigoiascrud;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    // criando variáveis para nossa visão recicladora.
    private RecyclerView carroRV;
    private static final int ADD_CARRO_REQUEST = 1;
    private static final int EDIT_CARRO_REQUEST = 2;
    private ViewModal viewmodal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inicializando nossa variável para nossa visão recicladora e fab.
        carroRV = findViewById(R.id.idRVCarro);
        FloatingActionButton fab = findViewById(R.id.idBotaoAdd);
        // adicionando ouvinte ao clicar para botão de ação flutuante.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar uma nova atividade para adicionar um novo curso e passar um valor constante nele.
                        Intent intent = new Intent(MainActivity.this,
                        NewCarroActivity.class);
                startActivityForResult(intent, ADD_CARRO_REQUEST);
            }
        });
        // setting layout manager to our adapter class.
        carroRV.setLayoutManager(new LinearLayoutManager(this));
        carroRV.setHasFixedSize(true);
        // adaptador de inicialização para visualização do reciclador.
        final CarroRVAdapter adapter = new CarroRVAdapter();
        // configurando a classe do adaptador para visualização do reciclador.
        carroRV.setAdapter(adapter);
        // passando um dado do modo de exibição.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        // a linha abaixo é usada para obter todos os cursos do modal de exibição.
                viewmodal.getAllCarros().observe(this, new
                        Observer<List<CarroModal>>() {
                            @Override
                            public void onChanged(List<CarroModal> models) {
                                // quando os dados são alterados em nossos modelos,
                                // estamos adicionando essa lista à nossa classe de adaptador.
                                adapter.submitList(models);
                            }
                        });
        // o método abaixo é usado para adicionar o método de deslizar para  excluir para o item da visualização do reciclador.
                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull
                    RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // na visualização do reciclador, o item deslocado, então estamos excluindo o item de nossa visualização do reciclador.

                        viewmodal.delete(adapter.getCarroAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Carro deletado.",
                        Toast.LENGTH_SHORT).show();
            }
        }).
                // a linha abaixo é usada para anexar isso à visualização do reciclador.
                attachToRecyclerView(carroRV);
        // a linha abaixo é usada para definir o ouvinte de clique de item para nosso item de visualização do reciclador.
                adapter.setOnItemClickListener(new CarroRVAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CarroModal model) {
                        // após clicar no item da visualização do reciclador,
                        // estamos abrindo uma nova atividade e passando dados para a nossa atividade.
                        Intent intent = new Intent(MainActivity.this,
                                NewCarroActivity.class);
                        intent.putExtra(NewCarroActivity.EXTRA_ID, model.getId());
                        intent.putExtra(NewCarroActivity.EXTRA_CARRO_MARCA,
                                model.getCarroMarca());
                        intent.putExtra(NewCarroActivity.EXTRA_CARRO_MODELO,
                                model.getCarroModelo());
                        intent.putExtra(NewCarroActivity.EXTRA_CARRO_PLACA,
                                model.getCarroPlaca());
                        // a linha abaixo é para iniciar uma nova atividade e adicionar uma constante de edição de curso.
                        startActivityForResult(intent, EDIT_CARRO_REQUEST);
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CARRO_REQUEST && resultCode == RESULT_OK) {
            String carroMarca =
                    data.getStringExtra(NewCarroActivity.EXTRA_CARRO_MARCA);
            String carroModelo =
                    data.getStringExtra(NewCarroActivity.EXTRA_CARRO_MODELO);
            String carroPlaca =
                    data.getStringExtra(NewCarroActivity.EXTRA_CARRO_PLACA);
            CarroModal model = new CarroModal(carroModelo, carroMarca,
                    carroPlaca);
            viewmodal.insert(model);
            Toast.makeText(this, "Carro salvo.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_CARRO_REQUEST && resultCode ==
                RESULT_OK) {
            int id = data.getIntExtra(NewCarroActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Carro não atualizado.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String carroMarca =
                    data.getStringExtra(NewCarroActivity.EXTRA_CARRO_MARCA);
            String carroModelo =
                    data.getStringExtra(NewCarroActivity.EXTRA_CARRO_MODELO);
            String carroPlaca =
                    data.getStringExtra(NewCarroActivity.EXTRA_CARRO_PLACA);
            CarroModal model = new CarroModal(carroMarca, carroModelo,
                    carroPlaca);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Carro atualizado.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Carro não salvo.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}