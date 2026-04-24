package com.wallet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartWalletTest {

    private SmartWallet smartWallet;

    @BeforeEach
    public void init() {
        smartWallet = new SmartWallet();
    }

    // ======= Caminos felices (depósitos y retiros válidos)========

    @Test
    public void testDepositoValidoSinCashback() {
        boolean resultado = smartWallet.deposit(50.0);

        assertTrue(resultado);
        assertEquals(50.0, smartWallet.obtenerSaldo());
    }

    @Test
    public void testDepositoConCashback() {
        boolean resultado = smartWallet.deposit(200.0);

        assertTrue(resultado);
        // $200 + 1% de cashback = $202
        assertEquals(202.0, smartWallet.obtenerSaldo());
    }

    @Test
    public void testRetiroValido() {
        smartWallet.deposit(500.0); // saldo: 505 (con cashback del 1%)
        boolean resultado = smartWallet.withdraw(100.0);

        assertTrue(resultado);
        assertEquals(405.0, smartWallet.obtenerSaldo()); //505 - 100 = 405
        assertEquals("Activa", smartWallet.obtenerEstado());
    }

    // ==== Límites (exactamente $100 de depósito, exactamente $5,000 de saldo) ====

    @Test
    public void testDepositoExactoCienNoAplicaCashback() {
        boolean resultado = smartWallet.deposit(100.0);

        assertTrue(resultado);
        // La regla dice "mayor a $100"
        assertEquals(100.0, smartWallet.obtenerSaldo());
    }

    @Test
    public void testSaldoMaximoExactoCincoMil() {
        SmartWallet smartWalletLlena = new SmartWallet("Standard", 4950.0);
        // deposito 50, resultante = 5000 exactos
        boolean resultado = smartWalletLlena.deposit(50.0);

        assertTrue(resultado);
        assertEquals(5000.0, smartWalletLlena.obtenerSaldo());
    }

    // ======= Casos de error (montos negativos, saldo insuficiente) ======

    @Test
    public void testDepositoNegativoRechazado() {
        boolean resultado = smartWallet.deposit(-50.0);

        assertFalse(resultado);
        assertEquals(0.0, smartWallet.obtenerSaldo());
    }

    @Test
    public void testDepositoCeroRechazado() {
        boolean resultado = smartWallet.deposit(0.0);

        assertFalse(resultado);
        assertEquals(0.0, smartWallet.obtenerSaldo());
    }

    @Test
    public void testRetiroMayorAlSaldo() {
        smartWallet.deposit(50.0);
        boolean resultado = smartWallet.withdraw(100.0);

        assertFalse(resultado);
        assertEquals(50.0, smartWallet.obtenerSaldo());
    }

    @Test
    public void testRetiroNegativoRechazado() {
        smartWallet.deposit(200.0); // saldo: 202 (con cashback)
        boolean resultado = smartWallet.withdraw(-50.0);

        assertFalse(resultado);
        assertEquals(202.0, smartWallet.obtenerSaldo());
    }
}
