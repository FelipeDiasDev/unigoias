package com.example.unigoiascrud;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
public class CarroRepository {
    // abaixo da linha é criar uma variável para dao e listar todos os cursos.
    private Dao dao;
    private LiveData<List<CarroModal>> allCarros;
    // criando um construtor para nossas variáveis e passando as variáveis para ele.
    public CarroRepository(Application application) {
        CarroDatabase database = CarroDatabase.getInstance(application);
        dao = database.Dao();
        allCarros = dao.getAllCarros();
    }
    //criando um método para inserir os dados em nosso banco de dados..
    public void insert(CarroModal model) {
        new InsertCarroAsyncTask(dao).execute(model);
    }
    // criando um método para atualizar os dados no banco de dados.
    public void update(CarroModal model) {
        new UpdateCarroAsyncTask(dao).execute(model);
    }
    // criando um método para deletar os dados em nosso banco de dados.
    public void delete(CarroModal model) {
        new DeleteCarroAsyncTask(dao).execute(model);
    }
    // abaixo está o método para deletar todos os cursos.
    public void deleteAllCarros() {
        new DeleteAllCarrosAsyncTask(dao).execute();
    }
    // o método abaixo é ler todos os cursos
    public LiveData<List<CarroModal>> getAllCarros() {
        return allCarros;
    }
    // estamos criando um método de tarefa assíncrona para inserir um novo curso.
    private static class InsertCarroAsyncTask extends AsyncTask<CarroModal,
            Void, Void> {
        private Dao dao;
        private InsertCarroAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(CarroModal... model) {
            // a linha abaixo é usada para inserir nosso modal no dao.
            dao.insert(model[0]);
            return null;
        }
    }
    // estamos criando um método de tarefa assíncrona para atualizar nosso curso.
    private static class UpdateCarroAsyncTask extends AsyncTask<CarroModal,
            Void, Void> {
        private Dao dao;
        private UpdateCarroAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(CarroModal... models) {
            // a linha abaixo é usada para atualizar nosso modal no dao.
            dao.update(models[0]);
            return null;
        }
    }
    // estamos criando um método de tarefa assíncrona para excluir o curso.
    private static class DeleteCarroAsyncTask extends AsyncTask<CarroModal,
            Void, Void> {
        private Dao dao;
        private DeleteCarroAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(CarroModal... models) {
            // a linha abaixo é usada para deletar nosso modal de curso no dao.
                    dao.delete(models[0]);
            return null;
        }
    }
    // estamos criando um método de tarefa assíncrona para excluir todos os cursos.
    private static class DeleteAllCarrosAsyncTask extends AsyncTask<Void,
            Void, Void> {
        private Dao dao;
        private DeleteAllCarrosAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // método chamado para deletar todos os cursos.
            dao.deleteAllCarros();
            return null;
        }
    }
}
