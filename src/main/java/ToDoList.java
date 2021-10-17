import java.util.Scanner;

public class ToDoList {
    static ElementTaskList task=new ElementTaskList();
    static int countTask=0;
    public static void main(String[] args) {
        String commandLine;
        String tempString;
        Scanner sc=new Scanner(System.in);
        boolean exitFlag = true;
        do {
            outputMenu();
            commandLine= sc.nextLine();
            commandLine=commandLine.trim();
            tempString=commandLine.toLowerCase();
            if (tempString.startsWith("add")){
                addTask(commandLine);
            }
            else if (tempString.startsWith("print")){
                printTask(tempString);
            }
            else if (tempString.startsWith("toggle")){
                toggleTask(tempString);
            }
            else if (tempString.startsWith("quit")){
                System.out.println("\nСпасибо за работу с программой ToDoList!\nВсего доброго!");
                exitFlag=false;
            }
            else{
                System.out.println("Команда введена неверно. Повторите ввод\n");
            }
        }while (exitFlag);
    }
    public static void outputMenu(){
        System.out.println("Добро пожаловать в ToDoList!\n");
        System.out.println("1.Add (добавить задачу)");
        System.out.println("2.Print (печать списка задач)");
        System.out.println("3.Toggle (изменение статуса задачи)");
        System.out.println("4.Quit (завершение работы)\n");
        System.out.print("Пожалуйста, введите команду: ");
    }
    public static void addTask(String arg){
        String tempString=arg.toLowerCase();
        if (!tempString.equals("add")){
            task.name=arg.substring(4);
            task.id=1;
            task.status=false;
            countTask=1;
            System.out.println("Задача добавлена\n");
        }
        else{
            System.out.println("Невозможно создать задачу - не задан параметр команды Add\n");
        }
    }
    public static void printTask(String arg){
        if (countTask==0){
            System.out.println("В списке отсутствуют задачи\n");
            return;
        }
        if (!arg.equals("print")){
            arg=arg.substring(6);
            arg=arg.trim();
            if (arg.equals("all")) {
                System.out.print(task.id+"."+" [");
                if (task.status){
                    System.out.print("x] ");
                }
                else{
                    System.out.print(" ] ");
                }
                System.out.println(task.name+"\n");
            }
            else{
                System.out.println("Некорректный параметр команды Print\n");
            }
        }
        else{
            if (!task.status){
                System.out.println(task.id+". [ ] "+task.name);
                System.out.println();
            }
            else{
                System.out.println("В списке отсутствуют невыполненные задачи\n");
            }
        }
    }
    public static void toggleTask(String arg){
        int argNum;
        if (!arg.equals("toggle")){
            arg=arg.substring(7);
        }
        else{
            System.out.println("Команда Toggle обязательно должна содержать параметр!\n");
            return;
        }
        try
        {
            argNum=Integer.parseInt(arg.trim());
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("Некорректный ввод. Параметр команды может быть только числом!\n");
            return;
        }
        if (argNum<=0){
            System.out.println("Некорректный параметр Toggle - id задачи должно быть больше нуля\n");
            return;
        }
        else if (argNum>countTask){
            System.out.println("Некорректный параметр Toggle - id больше чем число имеющихся задач\n");
        }
            else {
            task.status=!task.status;
            System.out.println("Статус задачи изменен\n");
            }
    }
}
