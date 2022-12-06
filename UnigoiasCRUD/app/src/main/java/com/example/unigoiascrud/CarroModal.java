package com.example.unigoiascrud;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
// // a linha abaixo é para definir o nome da tabela.
@Entity(tableName = "carro_table")
public class CarroModal {
    // a linha abaixo é para auto incrementar id para cada curso.
    @PrimaryKey(autoGenerate = true)
    // variável para o nosso id.
    private int id;
    // variável para o nome do curso.
    private String carroModelo;
    // variável para a descrição do curso.
    private String carroMarca;
    // variável para a duração do curso.
    private String carroPlaca;
    // abaixo da linha estamos criando uma classe de construtor.
    // Dentro da classe do construtor não estamos passando nosso id
    // porque ele está incrementando automaticamente
    public CarroModal(String carroMarca, String carroModelo, String carroPlaca) {
        this.carroMarca = carroMarca;
        this.carroModelo = carroModelo;
        this.carroPlaca = carroPlaca;
    }
    // na linha abaixo estamos criando métodos getter e setter.
    public String getCarroModelo() {
        return carroModelo;
    }
    public void setCarroModelo(String carroModelo) {
        this.carroModelo = carroModelo;
    }
    public String getCarroPlaca() {
        return carroPlaca;
    }
    public void setCarroPlaca(String carroPlaca) {
        this.carroPlaca = carroPlaca;
    }
    public String getCarroMarca() {
        return carroMarca;
    }
    public void setCarroMarca(String carroMarca) {
        this.carroMarca = carroMarca;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

