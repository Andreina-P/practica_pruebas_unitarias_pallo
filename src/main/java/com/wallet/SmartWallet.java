package com.wallet;

public class SmartWallet {

    private double saldo;
    private String tipoUsuario;
    private String estado;

    private static final double SALDO_MAXIMO_STANDARD = 5000.0;
    private static final double UMBRAL_CASHBACK = 100.0;
    private static final double TASA_CASHBACK = 0.01;

    public SmartWallet() {
        this.saldo = 0.0;
        this.tipoUsuario = "Standard";
        this.estado = "Activa";
    }

    // Constructor que permite especificar tipo de usuario y saldo inicial
    public SmartWallet(String tipoUsuario, double saldoInicial) {
        this.tipoUsuario = tipoUsuario;
        this.saldo = saldoInicial;
        this.estado = "Activa";
    }

    public boolean deposit(double amount) {
        // 1: el monto debe ser mayor a 0
        if (amount <= 0) {
            return false;
        }

        double montoAAgregar = amount;
        // 2: si el monto es mayor a $100, se aplica un cashback del 1%
        if (amount > UMBRAL_CASHBACK) {
            montoAAgregar += amount * TASA_CASHBACK;
        }

        // 3: si el usuario es Standard, el saldo no puede superar $5,000
        if ("Standard".equals(tipoUsuario) && (saldo + montoAAgregar) > SALDO_MAXIMO_STANDARD) {
            return false;
        }

        saldo += montoAAgregar;
        return true;
    }

    public boolean withdraw(double amount) {
        // 1: no pueden ser montos negativos o cero
        if (amount <= 0) {
            return false;
        }

        // 2: no se puede retirar más del saldo disponible
        if (amount > saldo) {
            return false;
        }

        saldo -= amount;

        // 3: si el saldo queda en exactamente 0, la cuenta se marca como Inactiva
        if (saldo == 0) {
            estado = "Inactiva";
        }

        return true;
    }

    // Getters que van a ser necesarios para las pruebas unitarias
    public double obtenerSaldo() {
        return saldo;
    }

    public String obtenerTipoUsuario() {
        return tipoUsuario;
    }

    public String obtenerEstado() {
        return estado;
    }
}