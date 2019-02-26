import Backpack.Backpack;
import Crossover.AutobreedingStrategy;
import Crossover.PanmixiaStrategy;
import InitialPopulation.HammingDifferentPopulationStrategy;
import InitialPopulation.RandomPopulationStrategy;
import Mutation.AdditionStrategy;
import Mutation.InversionStrategy;
import Selection.RankStrategy;
import Selection.RouletteStrategy;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[][] items = {
            {11, 1}, // 1
            {18, 25},// 2
            {23, 29},// 3
            {21, 24},// 4
            {23, 27},// 5
            {30, 21},// 6
            {23, 24},// 7
            {3, 13}, // 8
            {4, 16}, // 9
            {6, 15}, // 10
            {24, 23},// 11
            {2, 14}, // 12
            {10, 15},// 13
            {15, 3}, // 14
            {6, 16}  // 15
        };

        Backpack task = new Backpack(items, 106);

        int choice;
        Scanner in = new Scanner(System.in);

        GeneticEngine engine = new GeneticEngine(task, 10);

        System.out.println("Начальная популяция:");
        System.out.println("1. Полностью случайна");
        System.out.println("2. Генетически различна");

        choice = in.nextInt();

        switch (choice) {
            case 1:
                engine.init(new RandomPopulationStrategy(engine.backpack));
                break;
            case 2:
                engine.init(new HammingDifferentPopulationStrategy(engine.backpack));
                break;
            default:
                System.out.println("Неверный выбор");
        }

        System.out.println("Селекция:");
        System.out.println("1. Пропорциональная");
        System.out.println("2. Линейная ранговая");

        choice = in.nextInt();

        switch (choice) {
            case 1:
                engine.setSelectionStrategy(new RouletteStrategy(engine.backpack));
                break;
            case 2:
                engine.setSelectionStrategy(new RankStrategy(engine.backpack));
                break;
            default:
                System.out.println("Неверный выбор");
        }

        System.out.println("Кроссовер:");
        System.out.println("1. Панмиксия");
        System.out.println("2. Аутбридинг");

        choice = in.nextInt();

        switch (choice) {
            case 1:
                engine.setCrossoverStrategy(new PanmixiaStrategy(engine.backpack));
                break;
            case 2:
                engine.setCrossoverStrategy(new AutobreedingStrategy(engine.backpack));
                break;
            default:
                System.out.println("Неверный выбор");
        }

        System.out.println("Мутация:");
        System.out.println("1. Дополнение");
        System.out.println("2. Инверсия");

        choice = in.nextInt();

        switch (choice) {
            case 1:
                engine.setMutationStrategy(new AdditionStrategy(engine.backpack));
                break;
            case 2:
                engine.setMutationStrategy(new InversionStrategy(engine.backpack));
                break;
            default:
                System.out.println("Неверный выбор");
        }

        summary(engine);
        while(input()) {
            engine.evolve();
            summary(engine);
        }
    }

    private static void summary(GeneticEngine engine) {
        TableAdapter adapter = new TableAdapter(engine.backpack);
        System.out.println("Текущее поколение: " + engine.generation);
        System.out.println("Наиболее приспособленная особь в текущем поколении:");
        adapter.toTable(engine.best()).printTable();
        System.out.println("Особи текущего поколения:");
        adapter.toTable(engine.population).printTable();
        System.out.println();
    }

    public static boolean input() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
