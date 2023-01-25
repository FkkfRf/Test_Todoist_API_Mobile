package testsdata;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerate {
    Faker fakerRu = new Faker(new Locale("ru"));

    public String getProjectName() {

        return fakerRu.educator().university();
    }
    public String getTaskName() {
        return fakerRu.educator().course();
    }

    public String getTaskDescription() {

        return fakerRu.educator().campus();
    }

}
