package com.example.unigoiascrud;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class NewCarroActivity extends AppCompatActivity {
    // criando as variáveis para o botão e para os edittext.
    private EditText carroModeloEdt, carroMarcaEdt, carroPlacaEdt;
    private Button carroBtn;
    // criando uma variável de string constante para
    // nome do curso, descrição e duração.
    public static final String EXTRA_ID =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_CARRO_MARCA =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_CARRO_MARCA";
    public static final String EXTRA_CARRO_MODELO =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_CARRO_MODELO";
    public static final String EXTRA_CARRO_PLACA =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_CARRO_PLACA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_carro);
        // inicializando nossas variáveis para cada visão.
        carroMarcaEdt = findViewById(R.id.idEdtCarroMarca);
        carroModeloEdt = findViewById(R.id.idEdtCarroModelo);
        carroPlacaEdt = findViewById(R.id.idEdtCarroPlaca);
        carroBtn = findViewById(R.id.idBtnSaveCarro);
        // a linha abaixo é para obter o intent,
        // pois estamos obtendo dados por meio de um intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //se obtivermos id para nossos dados,
            // estaremos definindo valores para nossos campos de edição de texto.
            carroMarcaEdt.setText(intent.getStringExtra(EXTRA_CARRO_MARCA));
            carroModeloEdt.setText(intent.getStringExtra(EXTRA_CARRO_MODELO));
            carroPlacaEdt.setText(intent.getStringExtra(EXTRA_CARRO_PLACA));
        }
        // adicionando ouvinte de clique para nosso botão Salvar.
        carroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obtendo o valor do texto de edittext e validando
// se os campos de texto estão vazios ou não.
                String carroMarca = carroMarcaEdt.getText().toString();
                String carroModelo= carroModeloEdt.getText().toString();
                String carroPlaca =
                        carroPlacaEdt.getText().toString();
                if (carroMarca.isEmpty() || carroPlaca.isEmpty() ||
                        carroModelo.isEmpty()) {
                    Toast.makeText(NewCarroActivity.this, "Entre com os detalhes do carro.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // chamando um método para salvar nosso curso.
                saveCourse(carroMarca, carroModelo, carroPlaca);
            }
        });
    }
    private void saveCourse(String carroMarca, String carroModelo,
                            String carroPlaca) {
        // dentro desse método, passamos todos os dados por meio de um intent.
        Intent data = new Intent();
        // na linha abaixo estamos passando todos os detalhes do nosso curso.
        data.putExtra(EXTRA_CARRO_MARCA, carroMarca);
        data.putExtra(EXTRA_CARRO_MODELO, carroModelo);
        data.putExtra(EXTRA_CARRO_PLACA, carroPlaca);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // na linha abaixo estamos passando nosso id.
            data.putExtra(EXTRA_ID, id);
        }
        // at last we are setting result as data.
        setResult(RESULT_OK, data);
        // exibindo uma mensagem toast após adicionar os dados
        Toast.makeText(this, "Carro salvo no banco de dados Room.",
                Toast.LENGTH_SHORT).show();
    }
}
