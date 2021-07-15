package Task;

import Exceptions.WrongTaskFormatException;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private final static String TASK_ID = "task_id";
    private final static String ASSIGNEE_ID = "assignee_id";
    private final static String TASK_STATE = "task_state";
    private final static String ACTIVE_STATE = "active";
    private final static String DISABLE_STATE = "disable";

    private final static String WRONG_TASK_FORMAT_EXCEPTION_MESSAGE = "Не правильный формат задачи";


    private static int getCountUniqueAssignees(ArrayList<HashMap<String, String>> tasks) {
        int count = 0;
        ArrayList<String> assignee_ids = new ArrayList<>();

        for (HashMap<String, String> task: tasks) {

            if (!dataIntegrityCheck(task)) throw new WrongTaskFormatException(WRONG_TASK_FORMAT_EXCEPTION_MESSAGE);

            String task_state = task.get(TASK_STATE);
            if (task_state.equals(ACTIVE_STATE)) {
                String assignee_id = task.get(ASSIGNEE_ID);
                if (assignee_id.length() > 0 && !assignee_ids.contains(assignee_id)) {
                    count++;
                    assignee_ids.add(assignee_id);
                }
            }
        }

        return count;
    }

    private static boolean dataIntegrityCheck(HashMap<String, String> task) {
        return task.size() == 3 && task.containsKey(TASK_ID) && task.containsKey(ASSIGNEE_ID) && task.containsKey(TASK_STATE);
    }


    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> tasks = new ArrayList<>();

        HashMap<String, String> task1 = new HashMap<>();
        task1.put(TASK_ID, "1");
        task1.put(ASSIGNEE_ID, "001");
        task1.put(TASK_STATE, ACTIVE_STATE);

        HashMap<String, String> task2 = new HashMap<>();
        task2.put(TASK_ID, "2");
        task2.put(ASSIGNEE_ID, "002");
        task2.put(TASK_STATE, ACTIVE_STATE);

        HashMap<String, String> task3 = new HashMap<>();
        task3.put(TASK_ID, "3");
        task3.put(ASSIGNEE_ID, "001");
        task3.put(TASK_STATE, ACTIVE_STATE);

        HashMap<String, String> task4 = new HashMap<>();
        task4.put(TASK_ID, "4");
        task4.put(ASSIGNEE_ID, "007");
        task4.put(TASK_STATE, DISABLE_STATE);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        System.out.println(getCountUniqueAssignees(tasks));

    }

}
