package com.example.task2;

public class Procent {

    // Метод для расчета процента от суммы
    public float countPr(int pr, double sum) {
        return (float)(sum * pr / 100.0);
    }

    // Метод для расчета суммы с учетом процента
    public float countSum(double sum, int pr) {
        return (float)(sum + countPr(pr, sum));
    }

    // Метод для округленного результата
    public int countSumTrunc(double sum, int pr) {
        return Math.round(countSum(sum, pr));
    }
}
