import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    private final FileReader fileReader = new FileReader();
    private final HashMap<Integer, Integer> income = new HashMap<>();
    private final HashMap<Integer, Integer> expense = new HashMap<>();
    private boolean loaded = false;

    public void readYearlyReport() {
        String fileName = "y.2021.csv";
        ArrayList<String> lines = fileReader.readFileContents(fileName);

        if (!lines.isEmpty()) {
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                int month = Integer.parseInt(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                boolean isExpense = Boolean.parseBoolean(parts[2]);

                if (isExpense) {
                    expense.put(month, amount);
                } else {
                    income.put(month, amount);
                }
            }
            loaded = true;
            System.out.println("Годовой отчёт успешно загружен.");
        }
    }

    public boolean isLoaded() {
        return loaded;
    }

    public int getIncome(int month) {
        return income.getOrDefault(month, 0);
    }

    public int getExpense(int month) {
        return expense.getOrDefault(month, 0);
    }

    public void printYearlyReportInfo() {
        System.out.println("Год: 2021");
        for (int month : income.keySet()) {
            System.out.println("Месяц: " + month + ", прибыль: " + (income.get(month) - expense.get(month)));
        }
        double averageExpense = expense.values().stream().mapToInt(Integer::intValue).average().orElse(0);
        double averageIncome = income.values().stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Средний расход: " + averageExpense);
        System.out.println("Средний доход: " + averageIncome);
    }
}
