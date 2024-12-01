import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    private final HashMap<Integer, ArrayList<MonthlyItem>> monthlyData = new HashMap<>();
    private final FileReader fileReader = new FileReader();
    private final String[] months = {"Январь", "Февраль", "Март"};

    public void readMonthlyReports() {
        for (int i = 1; i <= 3; i++) {
            String fileName = String.format("m.202101%d.csv", i);
            ArrayList<String> lines = fileReader.readFileContents(fileName);

            if (!lines.isEmpty()) {
                ArrayList<MonthlyItem> items = new ArrayList<>();
                for (int j = 1; j < lines.size(); j++) {
                    String[] parts = lines.get(j).split(",");
                    items.add(new MonthlyItem(parts[0], Boolean.parseBoolean(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                }
                monthlyData.put(i, items);
            }
        }
        System.out.println("Месячные отчёты успешно загружены.");
    }

    public boolean isLoaded() {
        return !monthlyData.isEmpty();
    }

    public void printMonthlyReportInfo() {
        for (int month : monthlyData.keySet()) {
            ArrayList<MonthlyItem> items = monthlyData.get(month);
            int maxProfit = 0;
            int maxExpense = 0;
            String maxProfitItem = "";
            String maxExpenseItem = "";

            for (MonthlyItem item : items) {
                int total = item.quantity * item.unitPrice;
                if (item.isExpense) {
                    if (total > maxExpense) {
                        maxExpense = total;
                        maxExpenseItem = item.itemName;
                    }
                } else {
                    if (total > maxProfit) {
                        maxProfit = total;
                        maxProfitItem = item.itemName;
                    }
                }
            }
            System.out.println(months[month - 1]);
            System.out.println("Самый прибыльный товар: " + maxProfitItem + ", сумма: " + maxProfit);
            System.out.println("Самая большая трата: " + maxExpenseItem + ", сумма: " + maxExpense);
        }
    }

    public void compareWithYearlyReport(YearlyReport yearlyReport) {
        for (int month : monthlyData.keySet()) {
            ArrayList<MonthlyItem> items = monthlyData.get(month);
            int totalIncome = items.stream().filter(item -> !item.isExpense).mapToInt(item -> item.quantity * item.unitPrice).sum();
            int totalExpense = items.stream().filter(item -> item.isExpense).mapToInt(item -> item.quantity * item.unitPrice).sum();

            if (yearlyReport.getIncome(month) != totalIncome || yearlyReport.getExpense(month) != totalExpense) {
                System.out.println("Несоответствие в месяце: " + months[month - 1]);
                return;
            }
        }
        System.out.println("Все данные соответствуют.");
    }
}
