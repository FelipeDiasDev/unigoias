package com.example.unigoiascrud;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
public class ViewModal extends AndroidViewModel {
    // criando uma nova variável para o repositório do curso.
    private CarroRepository repository;
    // a linha abaixo é para criar uma variável de
    // dados onde todos os cursos estão presentes.
    private LiveData<List<CarroModal>> allCarros;
    // construtor para nosso modo de exibição.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new CarroRepository(application);
        allCarros = repository.getAllCarros();
    }
    // o método abaixo é usado para inserir os dados em nosso repositório.
    public void insert(CarroModal model) {
        repository.insert(model);
    }
    // a linha abaixo é para atualizar os dados em nosso repositório.
    public void update(CarroModal model) {
        repository.update(model);
    }
    // a linha abaixo é para deletar os dados em nosso repositório.
    public void delete(CarroModal model) {
        repository.delete(model);
    }
    // o método abaixo é excluir todos os cursos de nossa lista.
    public void deleteAllCarros() {
        repository.deleteAllCarros();
    }
    // o método abaixo é obter todos os cursos de nossa lista.
    public LiveData<List<CarroModal>> getAllCarros() {
        return allCarros;
    }
}