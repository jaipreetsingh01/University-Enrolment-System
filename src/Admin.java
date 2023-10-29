import java.util.List;

public class Admin {
    private String name;

    public Admin() {
    }

    private void clearDatabase(University uni) {
        uni.clearDatabase();
    }

    private void groupByGrade(University uni) {
        uni.groupByGrade();
    }

    private void partitionPassFail(University uni) {
        uni.partitionPassFail();
    }

    private void removeStudentByID(University uni) {
        uni.removeStudentByID();
    }

    private void displayStudentsList(University uni) {
        uni.displayStudentsList();
    }

    public void adminMenu(University uni) {
        System.out.print(Colors.CYAN + "\tAdmin System (c/g/p/r/s/x): " + Colors.RESET);
        char c;
        while ((c = In.nextChar()) != 'x') {
            switch (c) {
                case 'c':
                    clearDatabase(uni);
                    break;
                case 'g':
                    groupByGrade(uni);
                    break;
                case 'p':
                    partitionPassFail(uni);
                    break;
                case 'r':
                    removeStudentByID(uni);
                    break;
                case 's':
                    displayStudentsList(uni);
                    break;
                default:
                    // Help menu
                    break;
            }
            System.out.print(Colors.CYAN + "\tAdmin System (c/g/p/r/s/x): " + Colors.RESET);
        }
    }
    // System.out.println(Colors.YELLOW + "Thank You" + Colors.RESET);

}
