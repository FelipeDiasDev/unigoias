package com.example.unigoiascrud;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
// Adicionando anotação à nossa classe Dao
@androidx.room.Dao
public interface Dao {
    // o método abaixo é usado para adicionar dados ao banco de dados.
    @Insert
    void insert(CarroModal model);
    // o método abaixo é usado para atualizar os dados em nosso banco de dados.
    @Update
    void update(CarroModal model);
    // a linha abaixo é usada para deletar um curso específico em nosso banco de dados.
    @Delete
    void delete(CarroModal model);
    // na linha abaixo estamos fazendo uma consulta para deletar todos o cursos de nosso banco de dados.
    @Query("DELETE FROM carro_table")
    void deleteAllCarros();
    // a linha abaixo é para ler todos os cursos de nosso banco de dados.
    // Na linha, estamos ordenando nossos cursos em ordem crescente com o nome do nosso curso.
    @Query("SELECT * FROM carro_table ORDER BY carroModelo ASC")
    LiveData<List<CarroModal>> getAllCarros();
}

