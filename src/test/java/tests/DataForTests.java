package tests;

import helpers.DataGenerate;

public class DataForTests {
    static DataGenerate generate = new DataGenerate();

    public static final String
            projectName = generate.getProjectName(),
            taskName = generate.getTaskName(),
            taskDescription = generate.getTaskDescription();
}
